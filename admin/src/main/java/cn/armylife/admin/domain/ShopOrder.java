package cn.armylife.admin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopOrder implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.orders_id
     *
     * @mbggenerated
     */
    private Long ordersId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.orders_type
     *
     * @mbggenerated
     */
    private Integer ordersType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.orders_people
     *
     * @mbggenerated
     */
    private Integer ordersPeople;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.appintment
     *
     * @mbggenerated
     */
    private String appintment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.product_id
     *
     * @mbggenerated
     */
    private String productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.first_product
     *
     * @mbggenerated
     */
    private String firstProduct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.shop_id
     *
     * @mbggenerated
     */
    private Long shopId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.stu_id
     *
     * @mbggenerated
     */
    private Long stuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.orders_status
     *
     * @mbggenerated
     */
    private String ordersStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.isdelivery
     *
     * @mbggenerated
     */
    private Integer isdelivery;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.reserve_number
     *
     * @mbggenerated
     */
    private Long reserveNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.order_total
     *
     * @mbggenerated
     */
    private BigDecimal orderTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.delivery_total
     *
     * @mbggenerated
     */
    private BigDecimal deliveryTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.orders_address
     *
     * @mbggenerated
     */
    private String ordersAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.orders_reserve
     *
     * @mbggenerated
     */
    private String ordersReserve;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.user_phone
     *
     * @mbggenerated
     */
    private String userPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.member_name
     *
     * @mbggenerated
     */
    private String memberName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.orders_desc
     *
     * @mbggenerated
     */
    private String ordersDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.creat_time
     *
     * @mbggenerated
     */
    private String creatTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.end_time
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.is_refund
     *
     * @mbggenerated
     */
    private Integer isRefund;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.refund_desc
     *
     * @mbggenerated
     */
    private String refundDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.isexpress
     *
     * @mbggenerated
     */
    private Integer isexpress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_order.is_show
     *
     * @mbggenerated
     */
    private Integer isShow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shop_order
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.orders_id
     *
     * @return the value of shop_order.orders_id
     *
     * @mbggenerated
     */
    public Long getOrdersId() {
        return ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.orders_id
     *
     * @param ordersId the value for shop_order.orders_id
     *
     * @mbggenerated
     */
    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.orders_type
     *
     * @return the value of shop_order.orders_type
     *
     * @mbggenerated
     */
    public Integer getOrdersType() {
        return ordersType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.orders_type
     *
     * @param ordersType the value for shop_order.orders_type
     *
     * @mbggenerated
     */
    public void setOrdersType(Integer ordersType) {
        this.ordersType = ordersType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.orders_people
     *
     * @return the value of shop_order.orders_people
     *
     * @mbggenerated
     */
    public Integer getOrdersPeople() {
        return ordersPeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.orders_people
     *
     * @param ordersPeople the value for shop_order.orders_people
     *
     * @mbggenerated
     */
    public void setOrdersPeople(Integer ordersPeople) {
        this.ordersPeople = ordersPeople;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.appintment
     *
     * @return the value of shop_order.appintment
     *
     * @mbggenerated
     */
    public String getAppintment() {
        return appintment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.appintment
     *
     * @param appintment the value for shop_order.appintment
     *
     * @mbggenerated
     */
    public void setAppintment(String appintment) {
        this.appintment = appintment == null ? null : appintment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.product_id
     *
     * @return the value of shop_order.product_id
     *
     * @mbggenerated
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.product_id
     *
     * @param productId the value for shop_order.product_id
     *
     * @mbggenerated
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.first_product
     *
     * @return the value of shop_order.first_product
     *
     * @mbggenerated
     */
    public String getFirstProduct() {
        return firstProduct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.first_product
     *
     * @param firstProduct the value for shop_order.first_product
     *
     * @mbggenerated
     */
    public void setFirstProduct(String firstProduct) {
        this.firstProduct = firstProduct == null ? null : firstProduct.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.shop_id
     *
     * @return the value of shop_order.shop_id
     *
     * @mbggenerated
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.shop_id
     *
     * @param shopId the value for shop_order.shop_id
     *
     * @mbggenerated
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.stu_id
     *
     * @return the value of shop_order.stu_id
     *
     * @mbggenerated
     */
    public Long getStuId() {
        return stuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.stu_id
     *
     * @param stuId the value for shop_order.stu_id
     *
     * @mbggenerated
     */
    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.orders_status
     *
     * @return the value of shop_order.orders_status
     *
     * @mbggenerated
     */
    public String getOrdersStatus() {
        return ordersStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.orders_status
     *
     * @param ordersStatus the value for shop_order.orders_status
     *
     * @mbggenerated
     */
    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus == null ? null : ordersStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.isdelivery
     *
     * @return the value of shop_order.isdelivery
     *
     * @mbggenerated
     */
    public Integer getIsdelivery() {
        return isdelivery;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.isdelivery
     *
     * @param isdelivery the value for shop_order.isdelivery
     *
     * @mbggenerated
     */
    public void setIsdelivery(Integer isdelivery) {
        this.isdelivery = isdelivery;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.reserve_number
     *
     * @return the value of shop_order.reserve_number
     *
     * @mbggenerated
     */
    public Long getReserveNumber() {
        return reserveNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.reserve_number
     *
     * @param reserveNumber the value for shop_order.reserve_number
     *
     * @mbggenerated
     */
    public void setReserveNumber(Long reserveNumber) {
        this.reserveNumber = reserveNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.order_total
     *
     * @return the value of shop_order.order_total
     *
     * @mbggenerated
     */
    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.order_total
     *
     * @param orderTotal the value for shop_order.order_total
     *
     * @mbggenerated
     */
    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.delivery_total
     *
     * @return the value of shop_order.delivery_total
     *
     * @mbggenerated
     */
    public BigDecimal getDeliveryTotal() {
        return deliveryTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.delivery_total
     *
     * @param deliveryTotal the value for shop_order.delivery_total
     *
     * @mbggenerated
     */
    public void setDeliveryTotal(BigDecimal deliveryTotal) {
        this.deliveryTotal = deliveryTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.orders_address
     *
     * @return the value of shop_order.orders_address
     *
     * @mbggenerated
     */
    public String getOrdersAddress() {
        return ordersAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.orders_address
     *
     * @param ordersAddress the value for shop_order.orders_address
     *
     * @mbggenerated
     */
    public void setOrdersAddress(String ordersAddress) {
        this.ordersAddress = ordersAddress == null ? null : ordersAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.orders_reserve
     *
     * @return the value of shop_order.orders_reserve
     *
     * @mbggenerated
     */
    public String getOrdersReserve() {
        return ordersReserve;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.orders_reserve
     *
     * @param ordersReserve the value for shop_order.orders_reserve
     *
     * @mbggenerated
     */
    public void setOrdersReserve(String ordersReserve) {
        this.ordersReserve = ordersReserve == null ? null : ordersReserve.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.user_phone
     *
     * @return the value of shop_order.user_phone
     *
     * @mbggenerated
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.user_phone
     *
     * @param userPhone the value for shop_order.user_phone
     *
     * @mbggenerated
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.member_name
     *
     * @return the value of shop_order.member_name
     *
     * @mbggenerated
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.member_name
     *
     * @param memberName the value for shop_order.member_name
     *
     * @mbggenerated
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.orders_desc
     *
     * @return the value of shop_order.orders_desc
     *
     * @mbggenerated
     */
    public String getOrdersDesc() {
        return ordersDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.orders_desc
     *
     * @param ordersDesc the value for shop_order.orders_desc
     *
     * @mbggenerated
     */
    public void setOrdersDesc(String ordersDesc) {
        this.ordersDesc = ordersDesc == null ? null : ordersDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.creat_time
     *
     * @return the value of shop_order.creat_time
     *
     * @mbggenerated
     */
    public String getCreatTime() {
        return creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.creat_time
     *
     * @param creatTime the value for shop_order.creat_time
     *
     * @mbggenerated
     */
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.end_time
     *
     * @return the value of shop_order.end_time
     *
     * @mbggenerated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.end_time
     *
     * @param endTime the value for shop_order.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.is_refund
     *
     * @return the value of shop_order.is_refund
     *
     * @mbggenerated
     */
    public Integer getIsRefund() {
        return isRefund;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.is_refund
     *
     * @param isRefund the value for shop_order.is_refund
     *
     * @mbggenerated
     */
    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.refund_desc
     *
     * @return the value of shop_order.refund_desc
     *
     * @mbggenerated
     */
    public String getRefundDesc() {
        return refundDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.refund_desc
     *
     * @param refundDesc the value for shop_order.refund_desc
     *
     * @mbggenerated
     */
    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc == null ? null : refundDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.isexpress
     *
     * @return the value of shop_order.isexpress
     *
     * @mbggenerated
     */
    public Integer getIsexpress() {
        return isexpress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.isexpress
     *
     * @param isexpress the value for shop_order.isexpress
     *
     * @mbggenerated
     */
    public void setIsexpress(Integer isexpress) {
        this.isexpress = isexpress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_order.is_show
     *
     * @return the value of shop_order.is_show
     *
     * @mbggenerated
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_order.is_show
     *
     * @param isShow the value for shop_order.is_show
     *
     * @mbggenerated
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ordersId=").append(ordersId);
        sb.append(", ordersType=").append(ordersType);
        sb.append(", ordersPeople=").append(ordersPeople);
        sb.append(", appintment=").append(appintment);
        sb.append(", productId=").append(productId);
        sb.append(", firstProduct=").append(firstProduct);
        sb.append(", shopId=").append(shopId);
        sb.append(", stuId=").append(stuId);
        sb.append(", ordersStatus=").append(ordersStatus);
        sb.append(", isdelivery=").append(isdelivery);
        sb.append(", reserveNumber=").append(reserveNumber);
        sb.append(", orderTotal=").append(orderTotal);
        sb.append(", deliveryTotal=").append(deliveryTotal);
        sb.append(", ordersAddress=").append(ordersAddress);
        sb.append(", ordersReserve=").append(ordersReserve);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", memberName=").append(memberName);
        sb.append(", ordersDesc=").append(ordersDesc);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", isRefund=").append(isRefund);
        sb.append(", refundDesc=").append(refundDesc);
        sb.append(", isexpress=").append(isexpress);
        sb.append(", isShow=").append(isShow);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}