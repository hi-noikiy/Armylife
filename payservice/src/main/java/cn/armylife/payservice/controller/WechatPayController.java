package cn.armylife.payservice.controller;import cn.armylife.common.domain.Payments;import cn.armylife.common.util.NumberID;import cn.armylife.payservice.domain.WeChatUsers;import cn.armylife.payservice.feignservice.TranctionsService;import cn.armylife.payservice.service.WechatService;import cn.armylife.payservice.utils.*;import com.alibaba.fastjson.JSONObject;import com.github.wxpay.sdk.WXPay;import com.google.common.collect.Maps;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Value;import org.springframework.boot.context.properties.EnableConfigurationProperties;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.bind.annotation.RestController;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.InputStream;import java.math.BigDecimal;import java.text.SimpleDateFormat;import java.util.Date;import java.util.HashMap;import java.util.Map;import java.util.logging.Logger;@RequestMapping("MemberWxpay")@EnableConfigurationProperties(WeChatUsers.class)@RestControllerpublic class WechatPayController {    @Autowired    WechatService memberWechatPayService;    @Autowired    WeChatUsers weChatUsers;    @Autowired    MemberWXMyConfigUtil wxMyConfigUtil;    @Autowired    TranctionsService tranctionsService;    Logger logger=Logger.getLogger("MemberWechatPayController.class");    @Value("${server.port}")    int port;    //生成用户订单，存入数据库，然后生成一堆信息，向微信支付系统请求prepay_id    // 得到prepay_id后，将一堆信息打包发送到前端，由前端调起支付界面    /**     * 微信支付     * @param orderDesc     * @param Total     * @param orderCardID     * @param attach //"2"为理发店学员使用,"4":理发店会员充值,续费     * @param memberWechat     * @param request     * @return     */    @RequestMapping(path = {"/pay"}, method = {RequestMethod.POST, RequestMethod.GET})    @ResponseBody    public Map<String, String> order(String orderDesc, String Total,String orderCardID,String attach,String memberWechat,HttpServletRequest request){        logger.info(weChatUsers.toString());        String openId=memberWechat;        String appId= weChatUsers.getAppId();        String paternerKey= weChatUsers.getKEY();        String mchID= weChatUsers.getMCHID();        logger.info("支付金额:"+Total);        Map<String, String> result = new HashMap<>();        // 将充值金额的单位由元转换为分        int index = Total.indexOf(".");        int length = Total.length();        Long amLong = 0L;        if(index == -1){            amLong = Long.valueOf(Total+"00");        }else if(length - index >= 3){            amLong = Long.valueOf((Total.substring(0, index+3)).replace(".", ""));        }else if(length - index == 2){            amLong = Long.valueOf((Total.substring(0, index+2)).replace(".", "")+0);        }else{            amLong = Long.valueOf((Total.substring(0, index+1)).replace(".", "")+"00");        }        String orderTotal= amLong.toString();        try {            // ---------------生成用户订单-----------------            String orderId=orderCardID;            System.out.println(orderId);//"orderCardID");//生成订单编号            // 最好设置一个订单状态标志位，在第三步“接收交易状态”时再修改标志位            // 将用户订单存入数据库等等操作            // --------------------------------------------            // ---------------获取用户的IP------------------            String ip = request.getHeader("x-forwarded-for");            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){                ip = request.getHeader("Proxy-Client-IP");            }            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){                ip = request.getHeader("WL-Proxy-Client-IP");            }            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){                ip = request.getRemoteAddr();            }            if(ip.indexOf(",")!=-1){                String[] ips = ip.split(",");                ip = ips[0].trim();            }            // -------------------------------------------            // ----- 统一下单参数------            // 注意，参数的顺序不能错！！！！否则无法成功下单            Map<String, String> paraMap = new HashMap<String, String>();            paraMap.put("appid", appId);            paraMap.put("body", orderDesc);            paraMap.put("mch_id", mchID);            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());            paraMap.put("openid", openId);            paraMap.put("out_trade_no",orderId );//订单号            paraMap.put("spbill_create_ip", ip);            paraMap.put("total_fee",orderTotal);            paraMap.put("trade_type", "JSAPI");            paraMap.put("attach", attach);            paraMap.put("notify_url",weChatUsers.getNOTIFYURL());// 此路径是微信服务器调用支付结果通知路径            String sign = WXPayUtil.generateSignature(paraMap,paternerKey);            paraMap.put("sign", sign);            String xml = WXPayUtil.mapToXml(paraMap);//将所有参数(map)转xml格式            // 统一下单接口            String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";            //发送post请求"统一下单接口"返回预支付id:prepay_id            String xmlStr = WXPayUtil.sendPost(unifiedorder_url, xml);            //System.out.println("xml是"+xmlStr);            //以下内容是返回前端页面的json数据            String prepay_id = "";//预支付id            if (xmlStr.indexOf("SUCCESS") != -1) {                logger.info("支付系统返回了prepay_id");                Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);                logger.info("map"+map);                prepay_id =map.get("prepay_id");            }else {                logger.info("prepay_id获取失败");            }            // =============至此已成功获取到prepay_id================            logger.info("您的prepay_id的值是："+prepay_id);            // 将“微信内H5调起支付”需要的参数打包成JSON，发给前端            result.put("appId", appId);            result.put("timeStamp", WXPayUtil.getCurrentTimestampMs());            result.put("nonceStr", WXPayUtil.generateNonceStr());            result.put("signType", "MD5");            result.put("package", "prepay_id=" + prepay_id);            String paySign = WXPayUtil.generateSignature(result, paternerKey);            result.put("paySign", paySign);            logger.info(JSONObject.toJSONString(result));        } catch (Exception e) {            logger.info("错误:" + e);        }//            resultInfo.setCode("2");//            resultInfo.setMessage("异常：" + e.toString());//            resultInfo.setData("000");}        if(result!=null){            logger.info("成功获得prepay_id，且将数据发送到前端:"+result);        }else{            logger.info("获取prepayId失败");        }        return result;//            return JSONObject.fromObject(result).toString();    }    @RequestMapping("/Redundcallback")    @ResponseBody    public Map<String, String> payCallBack(HttpServletRequest request, HttpServletResponse response)throws Exception {        logger.info("微信支付收到异步通知");        WXPay wxPay=null;        InputStream inputStream = null;        Map<String, String> notifyMap = new HashMap<String, String>();        try {            wxPay=new WXPay(wxMyConfigUtil);            inputStream = request.getInputStream();            String xml = WXPayUtil.inputStream2String(inputStream);//            String xml="123";            notifyMap = WXPayUtil.xmlToMap(xml);//将微信发的xml转map            //logger.info("支付系统返回支付结果"+xml);            if (wxPay.isPayResultNotifySignatureValid(notifyMap)){                if(notifyMap.get("return_code").equals("SUCCESS")){                    logger.info("return_code是："+notifyMap.get("return_code"));                    // 交易成功                    if(notifyMap.get("result_code").equals("SUCCESS")){                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");                        Date date=new Date();                        String creatTime=sdf.format(date);                        String transaction_id=notifyMap.get("transaction_id");                        logger.info("微信订单号:"+transaction_id);                        String attach=notifyMap.get("attach");                        logger.info("attach:"+attach);                        String out_trade_no=notifyMap.get("out_trade_no");                        String openid=notifyMap.get("openid");                        logger.info("商户订单:"+out_trade_no);                        String total_fee=notifyMap.get("total_fee");                        logger.info("支付金额:"+total_fee);                        Payments payments=new Payments();                        payments.setRefundsNumber(transaction_id);                        payments.setPayStatus("3");                        payments.setEndTime(creatTime);                        payments.setRefunsTime(creatTime);                        payments.setRefundDesc("订单退款");                        payments.setRefundTotal(new BigDecimal(total_fee));                        payments.setPayRefund(1);                        payments.setPaymentsId(Long.valueOf(out_trade_no));//                        int paymentsnews=payMentsService.updatePayMentsForCallback(payments);//                        if (paymentsnews==0){//                            logger.info("出错"+paymentsnews);//                        }                    }                }else{                    // 交易失败的处理                    notifyMap.put("0","0");                    return notifyMap;                }            }else {                logger.info("签名失败"+notifyMap);            }            response.getOutputStream().print("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>"); //告知微信支付系统已收到消息            inputStream.close();        } catch (Exception e) {            e.printStackTrace();        }        return notifyMap;    }    /**     * 关闭订单     * @param orderCardID     * @return     * @throws Exception     */    @RequestMapping("closeorder")    @ResponseBody    public int closeorder(String orderCardID)throws Exception {        Integer msg;        try {        Map<String, String> close=new HashMap<>();        close.put("appId", weChatUsers.getAppId());        close.put("mch_id", weChatUsers.getMCHID());        close.put("nonceStr", WXPayUtil.generateNonceStr());        close.put("out_trade_no",orderCardID);        String paySign = WXPayUtil.generateSignature(close, weChatUsers.getKEY());        close.put("paySign",paySign);        String xml = WXPayUtil.mapToXml(close);        String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/closeorder";        String xmlStr = WXPayUtil.sendPost(unifiedorder_url, xml);        //预支付id        if (xmlStr.indexOf("SUCCESS") != -1) {            System.out.println("成功关闭");            msg=1;        }else {            System.out.println("关闭失败");            msg=0;        }}catch (Exception e ){            e.printStackTrace();            msg=null;        }        return msg;    }    @RequestMapping("topay")    public String topay(){        return "Member/Wechat";    }    @RequestMapping("towx")    public String towxpay(){        return "Wechatpay";    }    @Autowired    WeChatWithdrawUtils weChatWithdrawUtils;    @RequestMapping("WechatExtract")    @ResponseBody    public int WechatExtract(String openid,BigDecimal reward,Long memberId,String orderfee) throws Exception {//        HttpSession session=request.getSession();//        Member member=(Member)session.getAttribute("users");        BigDecimal total= new BigDecimal(orderfee);            logger.info("余额:"+reward);            BigDecimal remoney= total;            logger.info("取现金额:"+remoney);            if (remoney.compareTo(reward)==1||remoney.compareTo(reward)==0){                logger.info("比较失败");                return 0;            }        Map<String, String> map = Maps.newHashMap();        //元转换为分,微信支付已分为单位        BigDecimal sumAmount = new BigDecimal(orderfee);        //乘以100，转换为分        BigDecimal transAmt = sumAmount.multiply(new BigDecimal(100));        int memberamount=transAmt.intValue();        System.out.println(transAmt.toString());        //订单号        String Order = String.valueOf( NumberID.nextId(port));        map.put("partner_trade_no",Order);        //随机字符串        map.put("nonce_str", OtherUtils.getNonceStr().toString());        //openid("c从数据库中查询")        //map.put("openid",openid.get(0).getUserlevel());        map.put("openid", openid);        //金额        map.put("amount", String.valueOf(memberamount));        ////企业付款操作说明信息        map.put("desc", "提现");        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");        Date date=new Date();        //调用支付        String returnInfo = weChatWithdrawUtils.withdrawRequestOnce(map, 3000, 3000, true);        Map<String, String> resultMap = WXPayUtil.xmlToMap(returnInfo);        if (resultMap.containsKey("result_code") && "SUCCESS".equals(resultMap.getOrDefault("result_code", ""))) {            logger.info("提现成功");            logger.info("返回结果：" + returnInfo);            Long orderId=Long.valueOf(Order);//            int msg=tranctionsService.Addtranctions(orderId,total,resultMap.getOrDefault("payment_no",""));//            if (msg==0){//                return 0;//            }            return 1;        } else {            logger.info("提现失败");            logger.info("返回结果：" + returnInfo);            return 0;        }    }    @RequestMapping("tran")    @ResponseBody    public int tran(Long orderId,BigDecimal total,String Wechatid){            int msg=tranctionsService.Addtranctions(orderId,total,Wechatid);//resultMap.getOrDefault("payment_no",""));            return msg;    }    @RequestMapping(value = "/getSignature", method = RequestMethod.POST)    @ResponseBody    public Map WeixinController(HttpServletRequest request, HttpServletResponse response) {        String access_token=WXPayUtil.getAccessToken(weChatUsers.getAppId(),weChatUsers.getAppSecret());        System.out.println(access_token);        String ticket=WXPayUtil.getTicket(access_token);        System.out.println(ticket);        String noncestr=WXPayUtil.generateNonceStr();        Long timestamp=WXPayUtil.getCurrentTimestamp();        String url=request.getParameter("url");        System.out.println(url);        System.out.println(ticket);        String str = "jsapi_ticket="+ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;        System.out.println(str);//6、将字符串进行sha1加密        String Signature = sha1.getSha1(str);        Map msg=new HashMap();        msg.put("appId",weChatUsers.getAppId());        msg.put("noncestr",noncestr);        msg.put("timestamp",timestamp);        msg.put("Signature",Signature);        return msg;    }    /**     * 退款     * @param orderCardId //支付凭证     * @param refundfree //退款金额     * @param totalfree //总金额     * @param desc //说明     * @return     * @throws Exception     */    @RequestMapping(value = "/orderrefund", method = RequestMethod.POST)    @ResponseBody    public  int orderrefund(Long orderCardId, double refundfree,double totalfree,String desc) throws Exception {        Integer msg;        String refundfree1 = WXPayUtil.getfree(Double.toString(refundfree));        String totalfree1 = WXPayUtil.getfree(Double.toString(totalfree));        //生成退款订单号        int card = (int) (1 + Math.random() * (99 - 9 + 1));        Date now = new Date();        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");//可以方便地修改日期格式        String time = dateFormat.format(now);        String order = String.valueOf(card) + String.valueOf(time);//        String free=request.getParameter("refundfree");//        String total=request.getParameter("totalfree");        String refunddesc =desc;//        System.out.println(refunddesc);        Map<String, String> refund = new HashMap<>();        refund.put("appid", weChatUsers.getAppId());        System.out.println(weChatUsers.getAppId());        refund.put("mch_id", weChatUsers.getMCHID());        refund.put("nonce_str", WXPayUtil.generateNonceStr());        refund.put("out_refund_no", order);        refund.put("out_trade_no", String.valueOf(orderCardId));        refund.put("refund_fee", refundfree1);        refund.put("total_fee", totalfree1);        refund.put("refund_desc", refunddesc);        refund.put("notify_url","https://www.armylife.cn/api/PayMents/Redundcallback");        String paySign = WXPayUtil.generateSignature(refund, weChatUsers.getKEY());        refund.put("sign", paySign);//        System.out.println(refund);        String xml = WXPayUtil.mapToXml(refund);        String unifiedorder_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";        String resultXml = ClientCustomSSL.doRefund(unifiedorder_url, xml, weChatUsers.getMCHID());        Map<String,String> resultMap = WXPayUtil.xmlToMap(resultXml);        System.out.println(resultXml);//            String xmlStr = WXPayUtil.sendPost(unifiedorder_url, xml);        if (resultXml.indexOf("SUCCESS") != -1) {            String refundid=resultMap.get("refund_id");            msg = 1;        } else {            System.out.println("退款失败");//            System.out.println(resultXml);            msg = 0;        }        return msg;    }}