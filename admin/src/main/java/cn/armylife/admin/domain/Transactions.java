package cn.armylife.admin.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transactions implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactions.tranactions_id
     *
     * @mbggenerated
     */
    private Long tranactionsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactions.pay_total
     *
     * @mbggenerated
     */
    private BigDecimal payTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactions.pay_desc
     *
     * @mbggenerated
     */
    private String payDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactions.pay_user
     *
     * @mbggenerated
     */
    private String payUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactions.pay_wechatnum
     *
     * @mbggenerated
     */
    private String payWechatnum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactions.creat_time
     *
     * @mbggenerated
     */
    private String creatTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactions.end_time
     *
     * @mbggenerated
     */
    private String endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table transactions
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactions.tranactions_id
     *
     * @return the value of transactions.tranactions_id
     *
     * @mbggenerated
     */
    public Long getTranactionsId() {
        return tranactionsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactions.tranactions_id
     *
     * @param tranactionsId the value for transactions.tranactions_id
     *
     * @mbggenerated
     */
    public void setTranactionsId(Long tranactionsId) {
        this.tranactionsId = tranactionsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactions.pay_total
     *
     * @return the value of transactions.pay_total
     *
     * @mbggenerated
     */
    public BigDecimal getPayTotal() {
        return payTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactions.pay_total
     *
     * @param payTotal the value for transactions.pay_total
     *
     * @mbggenerated
     */
    public void setPayTotal(BigDecimal payTotal) {
        this.payTotal = payTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactions.pay_desc
     *
     * @return the value of transactions.pay_desc
     *
     * @mbggenerated
     */
    public String getPayDesc() {
        return payDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactions.pay_desc
     *
     * @param payDesc the value for transactions.pay_desc
     *
     * @mbggenerated
     */
    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc == null ? null : payDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactions.pay_user
     *
     * @return the value of transactions.pay_user
     *
     * @mbggenerated
     */
    public String getPayUser() {
        return payUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactions.pay_user
     *
     * @param payUser the value for transactions.pay_user
     *
     * @mbggenerated
     */
    public void setPayUser(String payUser) {
        this.payUser = payUser == null ? null : payUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactions.pay_wechatnum
     *
     * @return the value of transactions.pay_wechatnum
     *
     * @mbggenerated
     */
    public String getPayWechatnum() {
        return payWechatnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactions.pay_wechatnum
     *
     * @param payWechatnum the value for transactions.pay_wechatnum
     *
     * @mbggenerated
     */
    public void setPayWechatnum(String payWechatnum) {
        this.payWechatnum = payWechatnum == null ? null : payWechatnum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactions.creat_time
     *
     * @return the value of transactions.creat_time
     *
     * @mbggenerated
     */
    public String getCreatTime() {
        return creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactions.creat_time
     *
     * @param creatTime the value for transactions.creat_time
     *
     * @mbggenerated
     */
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactions.end_time
     *
     * @return the value of transactions.end_time
     *
     * @mbggenerated
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactions.end_time
     *
     * @param endTime the value for transactions.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tranactionsId=").append(tranactionsId);
        sb.append(", payTotal=").append(payTotal);
        sb.append(", payDesc=").append(payDesc);
        sb.append(", payUser=").append(payUser);
        sb.append(", payWechatnum=").append(payWechatnum);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}