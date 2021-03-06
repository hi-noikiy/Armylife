package cn.armylife.market.controller;

import cn.armylife.common.domain.*;
import cn.armylife.market.feign.PayMentsService;
import cn.armylife.market.util.MessageWechat;
import cn.armylife.market.feign.MemberService;
import cn.armylife.market.mapper.DeliveryOrderMapper;
import cn.armylife.market.service.OrderService;
import cn.armylife.market.service.ProductService;
import cn.armylife.market.util.NumberID;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RestController
public class OrderController {
    Logger logger=Logger.getLogger("OrderController.class");

    @Autowired
    MemberService memberService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    DeliveryOrderMapper deliveryOrderMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    MessageWechat messageWechat;
    @Autowired private PayMentsService payMentsService;


    @Value("${server.port}")
    int port;

    /**
     * 添加订单
     * @param shopOrder
     * @return
     */
    @RequestMapping("creatShopOrder")
    @ResponseBody
    public Long creatShopOrder(ShopOrder shopOrder,Integer orderExpress, HttpServletRequest request) throws ParseException,Exception {
        HttpSession session=request.getSession();
        Member member1=(Member)session.getAttribute("Student");
        Member member=memberService.selectMemberforId(member1.getMemberId());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String creatime=sdf.format(date);
        shopOrder.setCreatTime(creatime);
        shopOrder.setOrdersStatus("0");
        shopOrder.setIsexpress(0);
        try {
            shopOrder.setEndTime(sdf.parse(shopOrder.getAppintment()));
        }catch (NullPointerException e){
//            e.printStackTrace();
//            shopOrder.setEndTime(new Date("0000-00-00 00:00:00"));
        }
        Long int1 = NumberID.nextId(port);
        shopOrder.setOrdersId(int1);
        shopOrder.setStuId(member.getMemberId());
        shopOrder.setMemberName(member.getMemberNickname());
        if (shopOrder.getDeliveryTotal() != null) {
            shopOrder.setIsShow(1);
        }
        logger.info("SopOrder:"+shopOrder);
        List<Product> productList=new ArrayList<>();
        List<OrderDetail> expressList=new ArrayList<>();
        ListOperations<String, Object> lo = redisTemplate.opsForList();;
        ListOperations<String, OrderDetail> ll = redisTemplate.opsForList();
        productList=(List<Product>) lo.index("userId-"+member.getMemberId()+"s"+shopOrder.getShopId(), 0);
        logger.info("orderExpress:"+orderExpress);
    try {
        int code=2;
         if (orderExpress==1){
             shopOrder.setShopId(5L);
             shopOrder.setIsdelivery(1);
             shopOrder.setIsexpress(1);
             shopOrder.setDeliveryTotal(shopOrder.getOrderTotal());
             shopOrder.setOrderTotal(new BigDecimal(0));
             String userId="userId-";
            for (int i=0;i<ll.size(userId+member.getMemberId());i++){
                expressList.add(ll.index("userId-"+member.getMemberId(), i));
            }
            for (int i=0;i<expressList.size();i++){
                OrderDetail orderDetail=expressList.get(i);
                orderDetail.setOrderId(int1);
                orderService.orderdatailinsert(orderDetail);
            }
        }else if(code==orderExpress){
            logger.info("不是快递,是理发会员");
            orderExpress=2;
        }
    }catch (NullPointerException e){
            logger.info("不是快递:NUll"+e);
            orderExpress=0;
        }
        int msg =orderService.insert(shopOrder,productList,orderExpress);
        if (msg==0){
            return 0L;
        }
        if (shopOrder.getOrderTotal().compareTo(new BigDecimal(0))==0&&shopOrder.getIsexpress()==0){
            WXtemplate wXtemplate=new WXtemplate();
            wXtemplate.setTemplate("WD4fbaWwjhJzwB1VXV3jFWqNYpSvD_Dye1sUJ5xZCus");
            wXtemplate.setOpenid(member.getMemberWechat());
            wXtemplate.setFirst("您好,已成功创建订单!");
            wXtemplate.setRemark1("点击可查看订单详情");
            Map<String,String> key=new HashMap<>();
            key.put("key1",String.valueOf(int1));
            key.put("key2",creatime);
            wXtemplate.setKey(key);
            wXtemplate.setUrl("ArmyStudents/OrderWechat.html?ordersId="+int1);
            messageWechat.newOrderService(wXtemplate);
            if (shopOrder.getOrdersType()==3){

            }
        }
        lo.rightPop("userId-"+member.getMemberId()+"s"+shopOrder.getShopId());
        lo.rightPop("userId-"+member.getMemberId());
        logger.info("订单Id"+int1);
        return int1;
    }

    /**
     * 添加下单美团订单
     * @param request
     * @param shopOrder
     * @return
     */
    @RequestMapping("AddMeiTuanDeliveryOrder")
    @ResponseBody
    public int AddMeiTuanDeliveryOrder(HttpServletRequest request, ShopOrder shopOrder){
        HttpSession session=request.getSession();

        Long int1 = NumberID.nextId(port);
        Member member=(Member)session.getAttribute("Students");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String creatime=sdf.format(date);
        shopOrder.setCreatTime(creatime);
        shopOrder.setIsexpress(0);
        shopOrder.setOrdersStatus("0");
        shopOrder.setOrdersType(4);
        shopOrder.setStuId(member.getMemberId());

        //对下单用户进行微信通知
        WXtemplate wXtemplate=new WXtemplate();
        wXtemplate.setTemplate("WD4fbaWwjhJzwB1VXV3jFWqNYpSvD_Dye1sUJ5xZCus");
        wXtemplate.setOpenid(member.getMemberWechat());
        wXtemplate.setFirst("您好,已成功创建订单!");
        wXtemplate.setRemark1("点击可查看订单详情");
        Map<String,String> key=new HashMap<>();
        key.put("key1",String.valueOf(int1));
        key.put("key2",creatime);
        wXtemplate.setKey(key);
        wXtemplate.setUrl("ArmyStudents/OrderWechat.html?ordersId="+int1);
        messageWechat.newOrderService(wXtemplate);

        List<Member> deliverys=orderService.allDelivery();
        for(int i=0;i<deliverys.size();i++){
            //对跑腿用户进行微信通知
            wXtemplate.setTemplate("rEg8FotTl5Dseyz31FYv3577cFTA0mpkv81fr4lW8hA");
            wXtemplate.setOpenid(deliverys.get(i).getMemberWechat());
            wXtemplate.setFirst("您好,有美团代取订单了!");
            wXtemplate.setRemark1("点击可查看订单详情");
            Map<String,String> key2=new HashMap<>();
            key2.put("key1",shopOrder.getOrdersId().toString());
            key2.put("key2",shopOrder.getOrdersAddress());
            wXtemplate.setKey(key2);
            wXtemplate.setUrl("ArmyStudents/OrderWechat.html?ordersId="+int1);
            messageWechat.newOrderService(wXtemplate);
        }
        return orderService.insertMeiTuan(shopOrder);
    }

    /**
     * 查询学员与商铺的订单
     * @param shopOrder
     * @param request
     * @return
     */
    @RequestMapping("selectAllForMember")
    @ResponseBody
    public PageInfo<ShopOrder> selectAllForMember(ShopOrder shopOrder, int pageNum,int type, HttpServletRequest request){
        HttpSession session=request.getSession();
        PageHelper.startPage(pageNum, 100);
        Member member=new Member();
        switch (type){
            case 1:
                member=(Member) session.getAttribute("Student");
                shopOrder.setStuId(member.getMemberId());
                break;
            case 2:
                member=(Member) session.getAttribute("Shop");
                shopOrder.setShopId(member.getMemberId());
                break;
        }
        if (member==null){
            return null;
        }

        PageInfo<ShopOrder> pageInfo = new PageInfo<>(orderService.selectAllForMember(shopOrder));
        if (pageInfo.getPageNum()==1&pageNum>1){
            return null;
        }
        return pageInfo;
    };

    /**
     * 查询跑腿订单
     * @param deliveryOrder
     * @param request
     * @return
     */
    @RequestMapping("selectAlldeliveryForMember")
    @ResponseBody
    public List<DeliveryOrder> selectAlldeliveryForMember(DeliveryOrder deliveryOrder,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Delivery");
        if (member==null||!"2".equals(member.getMemberType())){
            logger.info("用户未登录,阻止进行");
            return null;
        }
        deliveryOrder.setDeliveryUserId(member.getMemberId());
        List<DeliveryOrder> pageInfo = orderService.selectAlldeliveryForMember(deliveryOrder);
        return pageInfo;
    };


    /**
     * 查询全部跑腿订单
     * @return
     */
    @RequestMapping("selectAfterOrder")
    @ResponseBody
    public List<DeliveryOrder> selectAfterOrder(){
        List<DeliveryOrder> pageInfo =orderService.selectAlldelivery();
        return pageInfo;
    };


    /**
     * 更新订单信息
     * @param shopOrder
     * @return
     */
    @RequestMapping("updateShopOrder")
    @ResponseBody
    public int updateShopOrder(ShopOrder shopOrder){
        return 1;
    }

    /**
     * 跑腿点击取货
     * @param ordersId
     * @return
     */
    @RequestMapping("getShopOrder")
    @ResponseBody
    public int getShopOrder(Long ordersId){
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setOrdersId(ordersId);
        shopOrder.setOrdersStatus("2");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        Date date=new Date();
        String creattime=sdf.format(date);
        shopOrder.setCreatTime(creattime);
        int msg=orderService.endOrders(shopOrder);
        return msg;
    }

    /**
     * 跑腿点击送达
     * @param ordersId
     * @return
     */
    @RequestMapping("endShopOrder")
    @ResponseBody
    public int endShopOrder(Long ordersId){
        return orderService.orderFulfillment(ordersId);
    }

    /**
     * 跑腿点击接单,向跑腿订单表插入订单数据,更新订单状态
     * @param ordersId
     * @param request
     * @return
     */
    @RequestMapping("EnableOrder")
    @ResponseBody
    public int enableOrder(Long ordersId,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Delivery");
        ShopOrder shopOrder=orderService.selectOrder(ordersId);
        DeliveryOrder deliveryOrder=new DeliveryOrder();
        deliveryOrder.setOrdersId(ordersId);
        deliveryOrder.setDeliveryAddress(shopOrder.getOrdersAddress());
        deliveryOrder.setDeliveryUserId(member.getMemberId());
        deliveryOrder.setDeliveryTotal(shopOrder.getDeliveryTotal());
        deliveryOrder.setUserPhone(shopOrder.getUserPhone());
        deliveryOrder.setUserName(member.getMemberName());
        ShopOrder order=new ShopOrder();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        Date date=new Date();
        String creattime=sdf.format(date);
        order.setOrdersStatus("2");
        order.setCreatTime(creattime);
        order.setOrdersId(ordersId);
        order.setIsShow(0);
        orderService.endOrders(order);
        return deliveryOrderMapper.insert(deliveryOrder);
    }

    /**
     * 在订单结算后,增加物品,新建子订单
     * @param afterOrder
     * @param request
     * @return
     */
    @RequestMapping("AddAfterOrder")
    @ResponseBody
    public int addAfterOrder(AfterOrder afterOrder,Long ordersId, @RequestParam(value = "productIds[]") String[] productIds, String people, HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Student");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        String creatime=sdf.format(date);
        BigDecimal total=BigDecimal.ZERO;
        Product product=new Product();
        Map<Product,String> productMap=new HashMap<>();
        try{
            for (int i=0;i<productIds.length;i++) {
                String[] str2 = productIds[i].split("\\*");
                product = productService.selectProductNews(str2[0]);
                product.setOrderNum(str2[1]);
                OrderDetail orderDetail=new OrderDetail();
                orderDetail.setOrderId(ordersId);
                orderDetail.setOrderDetailNum(Integer.valueOf(str2[1]));
                orderDetail.setOrderDetailName(product.getProductName());
                orderDetail.setOrderDetailPng(product.getProductAvator());
                orderDetail.setOrderDetailTotal(product.getProductTotal());
                productService.orderdetailinsert(orderDetail);
            }
        afterOrder.setCreatTime(creatime);
        afterOrder.setAfterOrderId(NumberID.nextId(port));
        ShopOrder order=orderService.selectOrder(ordersId);
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setOrdersId(ordersId);
        shopOrder.setOrdersPeople(order.getOrdersPeople()+Integer.valueOf(people));
        orderService.endOrders(shopOrder);
        }catch (NullPointerException e){
            logger.info("空指针:"+e);
        }
        if (member==null){
            logger.info("用户未登录,阻止进行");
            return 2;
        }
        return orderService.AddAfterOrder(afterOrder);
    }

    /**
     * 查询子订单
     * @param orderId
     * @return
     */
    @RequestMapping("SelectAfterOrder")
    @ResponseBody
    public AfterOrder selectAfterOrder(Long orderId){
        return orderService.SelectAfterOrder(orderId);
    }

    /**
     * 通过ordersId查询订单
     * @param orderIds
     * @return
     */
    @RequestMapping("selectOrder")
    @ResponseBody
    public ShopOrder selectOrder(Long orderIds){
           ShopOrder shopOrder=orderService.selectOrder(orderIds);
           return shopOrder;
    }

    /**
     * 更新到店用餐人数
     * @param number
     * @param ordersId
     * @return
     */
    @RequestMapping("plusOrderPeoPle")
    @ResponseBody
    public int plusOrderPeoPle(int number, Long ordersId){
        ShopOrder order=orderService.selectOrder(ordersId);
        int num=order.getOrdersPeople();
        int people=num+number;
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setOrdersId(ordersId);
        shopOrder.setOrdersPeople(people);
        return orderService.updatePeopleForOrder(shopOrder);
    }

    /**
     *创建加人订单
     * @param peopel
     * @param request
     * @return
     */
    @RequestMapping("creatPeopleOrder")
    @ResponseBody
    public Long creatPeople(int peopel,Long ordersId,HttpServletRequest request){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date();
        String time=sdf.format(date);
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Student");
        ShopOrder order=orderService.selectOrder(ordersId);
        Long int1=NumberID.nextId(port);
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setOrdersPeople(peopel);
        shopOrder.setOrdersId(int1);
        shopOrder.setOrderTotal(new BigDecimal(peopel*2));
        shopOrder.setOrdersDesc("到店用餐增加人数");
        shopOrder.setCreatTime(time);
        shopOrder.setOrdersStatus("0");
        shopOrder.setStuId(member.getMemberId());
        shopOrder.setShopId(order.getShopId());
        orderService.creatPeople(shopOrder);
        return int1;
    }

    /**s
     * 查询需要跑腿的订单
     * @param request
     * @return
     */
    @RequestMapping("selectAlldelivery")
    @ResponseBody
    public List<DeliveryOrder> selectAlldelivery(HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=new Member();
        try{
            member=(Member)session.getAttribute("Delivery");
        }catch (NullPointerException e){
            logger.info("空指针异常"+e);
        }
        logger.info(member.toString());
        if (member==null||!"2".equals(member.getMemberType())){
            logger.info("用户未登录,阻止进行");
            return null;
        }
        List<DeliveryOrder> pageInfo = orderService.selectAlldelivery();
        return pageInfo;
    }

//    /**
//     * 得到当前商铺的排队人数
//     * @param request
//     * @param shopId
//     * @return
//     */
//    @RequestMapping("getShopPeople")
//    @ResponseBody
//    public int getShopPeople(Long orderId){
//        return orderService.getShopPeople(orderId);
//    }

    /**
     * 确认接单
     * @param orderId
     * @return
     */
    @RequestMapping("confirmOrder")
    @ResponseBody
    public int confirmOrder(Long orderId){
        return orderService.confirmOrder(orderId);
    }

    /**
     * 拒绝退款
     * @param orderId
     * @return
     */
    @RequestMapping("rejectRefund")
    @ResponseBody
    public int rejectRefund(Long orderId){
        return orderService.rejectRefund(orderId);
    }

    /**
     * 确认退款
     * @param orderId
     * @return
     */
    @RequestMapping("confirmRefund")
    @ResponseBody
    public int confirmRefund(Long orderId){
        return orderService.confirmRefund(orderId);
    }

    /**
     * 餐后结算
     * @param total
     * @param orderId
     * @param request
     * @return
     */
    @RequestMapping("afterOrderRefund")
    @ResponseBody
    public int afterOrderRefund(BigDecimal total,Long orderId,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Shop");
        return orderService.afterOrderRefund(orderId,total);
    }

    /**
     * 订单状态改为退款
     * @param ordersId
     * @param request
     * @return
     */
    @RequestMapping("goRefund")
    @ResponseBody
    public int goRefund(Long ordersId,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Student");
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setOrdersStatus("5");
        shopOrder.setOrdersId(ordersId);
        ShopOrder order=orderService.selectOrder(shopOrder.getOrdersId());
        if (order.getIsexpress()==1){
            String totalfee=order.getOrderTotal().toString();
            BigDecimal total=order.getOrderTotal();
            logger.info("退款资金"+totalfee);
            Payments payments=payMentsService.selectPaments(ordersId);
            int msg=1;
            switch (payments.getPayApp()){
                case "2":
                    logger.info("支付宝退款");
                    msg=payMentsService.Alipayrefund(String.valueOf(ordersId),"退款申请",totalfee.toString(),"取消订单");
                    if (msg==0){
                        return 0;
                    }
                    break;
                case "1":
                    logger.info("微信退款");
                    msg=payMentsService.WechatPayTransfer(ordersId,total,total,"取消订单");
                    if (msg==0){
                        return 0;
                    }
                    break;
            }
        }

        return orderService.endOrders(shopOrder);
    }

    /**
     * 取货
     * @param ordersId
     * @param request
     * @return
     */
    @RequestMapping("getProduct")
    @ResponseBody
    public int getProduct(Long ordersId,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Student");
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setOrdersStatus("4");
        shopOrder.setOrdersId(ordersId);
        return orderService.endOrders(shopOrder);
    }

    /**
     * 开始消费
     * @param ordersId
     * @param request
     * @return
     */
    @RequestMapping("EndProduct")
    @ResponseBody
    public int endProduct(Long ordersId,HttpServletRequest request){
        HttpSession session=request.getSession();
        return orderService.updateMemberTotal(ordersId);
    }

    /**
     * 查询已结束订单
     * @param pageNum
     * @return
     */
    @RequestMapping("selectEndOrder")
    @ResponseBody
    public PageInfo<ShopOrder> selectEndOrder(int pageNum,HttpServletRequest request){
        HttpSession session=request.getSession();
        PageHelper.startPage(pageNum, 10);
        Member member=(Member)session.getAttribute("Student");
        Long stuId=member.getMemberId();
        PageInfo<ShopOrder> pageInfo=new PageInfo<>(orderService.selectEndOrder(stuId));
        return pageInfo;
    };

    /**
     * 更新理发店金额
     * @param total
     * @param memberId
     * @param paymentsId
     * @return
     */
    @RequestMapping("updateHairAmount")
    @ResponseBody
    public int updateHairAmount(String total,Long memberId,Long paymentsId){

        return orderService.updateHairAmount(total,memberId,paymentsId);
    }

    /**
     * 删除商户分类
     * @param shopTagId
     * @return
     */
    @RequestMapping("removeTag")
    @ResponseBody
    public int removeTag(Long shopTagId,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member shop=(Member)session.getAttribute("Shop");
        return productService.removeTag(shopTagId,shop.getMemberId());
    };

    /**
     * 更新shop_tag的tag_name名
     * @param shopTag
     * @return
     */
    @RequestMapping("updateTagName")
    @ResponseBody
    public int updateTagName(ShopTag shopTag){
        return productService.updateTagName(shopTag);
    };

    /**
     * 查询所有理发会员
     * @return
     */
    @RequestMapping("selectHairAll")
    @ResponseBody
    public List<Hairvip> selectHairAll(){
        return orderService.selectHairAll();
    };

    /**
     * 通过会员名查询用户信息
     * @param vipName
     * @return
     */
    @RequestMapping("searchHairVip")
    @ResponseBody
    public List<Hairvip> searchHairVip(String vipName){
        return orderService.searchHairVip(vipName);
    };

    /**
     * 操作会员次数
     * @param hairvip
     * @param code
     * @return
     */
    @RequestMapping("eidtHairNum")
    @ResponseBody
    public int eidtHairNum(Hairvip hairvip,int code){
        return orderService.editHairNum(hairvip,code);
    }

    /**
     * 获取商家被购买商品奖金
     * @param shopId
     * @param request
     * @param productId
     * @return
     */
    public Product shopManager(Long shopId,HttpServletRequest request,Long productId){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Shop");
        Product product=productService.getProduct(productId);
        return product;
    }

}
