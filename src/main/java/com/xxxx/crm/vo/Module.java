package com.xxxx.crm.vo;

import java.util.Date;

/**
 * 资管管理模块
 */
public class Module {
    /**
     * 模块id
     */
    private Integer id;

    /**
     * 模块资源名
     */
    private String moduleName;

    /**
     * 模块资源类型
     */
    private String moduleStyle;

    /**
     * 模块资源访问地址
     */
    private String url;

    /**
     * 模块资源对应的父id
     */
    private Integer parentId;

    /**
     * 上级资源权限码
     */
    private String parentOptValue;

    /**
     * 等级(层级)
     */
    private Integer grade;

    /**
     * 权限吗
     */
    private String optValue;

    /**
     *排序号
     */
    private Integer orders;

    /**
     * 有效状态
     */
    private Byte isValid;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleStyle() {
        return moduleStyle;
    }

    public void setModuleStyle(String moduleStyle) {
        this.moduleStyle = moduleStyle == null ? null : moduleStyle.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentOptValue() {
        return parentOptValue;
    }

    public void setParentOptValue(String parentOptValue) {
        this.parentOptValue = parentOptValue == null ? null : parentOptValue.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue == null ? null : optValue.trim();
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Byte getIsValid() {
        return isValid;
    }

    public void setIsValid(Byte isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}