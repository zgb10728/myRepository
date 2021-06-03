package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {
    /**
     * todo 根据用户名查询
     * @param userName
     * @return
     */
    User selectUserByName(String userName);

    /**
     * todo 查询所有的销售人员
     */
    public List<Map<String,Object>> queryAllSales();
}