package cn.armylife.common.domain;import org.springframework.boot.context.properties.ConfigurationProperties;import org.springframework.context.annotation.PropertySource;import org.springframework.stereotype.Component;/** * * @author jiony * * @Description: 微信相关常量 */@Component@ConfigurationProperties(prefix = "wechat.users")@PropertySource(value = "classpath:application.properties")public class WeChatUsers {    /**     * token     */    private String token;    /**     * app id     */    private String appId;    /**     * app secret     */    private String appSecret;    /**     * 静默授权回调地址     */    private String callBackSlientUrl;    /**     * 商户id     */    private String MCHID;    /**     * 异步回调地址     */    private String NOTIFYURL;    /**     * 微信统一下单地址     */    private String wxorder;    //证书路径//    public static final String SSLCERT_PATH = "apiclient_cert.p12";   // public static final String SSLCERT_PATH = "E:\\javaPicture\\yezz\\apiclient_cert.p12";    @Override    public String toString() {        return "WeChatUsers{" +                "token='" + token + '\'' +                ", appId='" + appId + '\'' +                ", appSecret='" + appSecret + '\'' +                ", callBackSlientUrl='" + callBackSlientUrl + '\'' +                ", MCHID='" + MCHID + '\'' +                ", NOTIFYURL='" + NOTIFYURL + '\'' +                ", wxorder='" + wxorder + '\'' +                ", KEY='" + KEY + '\'' +                '}';    }    /**     * key     */    private String KEY;    public String getToken() {        return token;    }    public void setToken(String token) {        this.token = token;    }    public String getAppId() {        return appId;    }    public void setAppId(String appId) {        this.appId = appId;    }    public String getAppSecret() {        return appSecret;    }    public void setAppSecret(String appSecret) {        this.appSecret = appSecret;    }    public String getCallBackSlientUrl() {        return callBackSlientUrl;    }    public void setCallBackSlientUrl(String callBackSlientUrl) {        this.callBackSlientUrl = callBackSlientUrl;    }    public String getMCHID() {        return MCHID;    }    public void setMCHID(String MCHID) {        this.MCHID = MCHID;    }    public String getNOTIFYURL() {        return NOTIFYURL;    }    public void setNOTIFYURL(String NOTIFYURL) {        this.NOTIFYURL = NOTIFYURL;    }    public String getWxorder() {        return wxorder;    }    public void setWxorder(String wxorder) {        this.wxorder = wxorder;    }    public String getKEY() {        return KEY;    }    public void setKEY(String KEY) {        this.KEY = KEY;    }}