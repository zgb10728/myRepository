package com.xxxx.crm.model;

public class UserModel {
    /**
     * todo 用户id
     */
    private String userIdStr;
    /**
     * todo 用户名
     */
    private String userName;
    /**
     * todo 用户真实名
     */
    private String trueName;

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
}
