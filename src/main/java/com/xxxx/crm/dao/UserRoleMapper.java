package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    /**
     * todo 根据用户id查询该用户有几个角色
     * @param userId
     * @return
     */
    int countUserRoleByUserId(Integer userId);

    /**
     * todo 删除该用户的角色,然后后面重新分配
     * @param userId
     * @return
     */
    int deleteUserRoleByUserId(Integer userId);
}