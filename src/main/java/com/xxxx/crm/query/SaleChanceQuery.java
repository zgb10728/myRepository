package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

/**
 * 营销机会分页
 */
public class SaleChanceQuery extends BaseQuery {

    /**
     * todo 客户名称
     */
    private String customerName;
    /**
     * todo createMan
     */
    private String createMan;
    /**
     * todo 分配状态
     */
    private String state;

    /**
     * todo 开发状态
     */
    private Integer devResult;

    /**
     * todo 分配人
     */
    private Integer assignMan;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDevResult() {
        return devResult;
    }

    public void setDevResult(Integer devResult) {
        this.devResult = devResult;
    }

    public Integer getAssignMan() {
        return assignMan;
    }

    public void setAssignMan(Integer assignMan) {
        this.assignMan = assignMan;
    }

    @Override
    public String toString() {
        return "SaleChanceQuery{" +
                "customerName='" + customerName + '\'' +
                ", createMan='" + createMan + '\'' +
                ", state='" + state + '\'' +
                ", devResult=" + devResult +
                ", assignMan=" + assignMan +
                '}';
    }
}
