package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.dao.UserRoleMapper;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.query.UserQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.Md5Util;
import com.xxxx.crm.utils.PhoneUtil;
import com.xxxx.crm.utils.UserIDBase64;
import com.xxxx.crm.vo.User;
import com.xxxx.crm.vo.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户管理 service
 */
@Service
public class UserService extends BaseService<User, Integer> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 用户登录
     * 1. 验证参数
     * 姓名 非空判断
     * 密码 非空判断
     * 2. 根据用户名，
     * 查询用户对象
     * 3. 判断用户是否存在
     * 用户对象为空，记录不存在，方法结束
     * 4. 用户对象不为空
     * 用户存在，校验密码
     * 密码不正确，方法结束
     * 5. 密码正确
     * 用户登录成功，返回用户的相关信息 （定义UserModel类，返回用户某些信息）
     */
    /**
     * todo 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel userLogin(String userName, String userPwd) {
        //1.验证参数
        checkLoginParams(userName, userPwd);
        //2.根据用户名,查询用户对象
        User user = userMapper.selectUserByName(userName);
        //3.判断用户是否存在 (用户对象为空，记录不存在，方法结束)
        AssertUtil.isTrue(null == user, "用户不存在或已被注销!!!");
        // 4. 用户对象不为空（用户存在，校验密码。密码不正确，方法结束）
        checkLoginPwd(userPwd, user.getUserPwd());
        // 5. 密码正确（用户登录成功，返回用户的相关信息）
        return buildUserInfo(user);

    }

    /**
     *  用户密码修改
     *      1. 参数校验 * 用户ID：userId 非空 用户对象必须存在
     *          原始密码：oldPassword 非空 与数据库中密文密码保持一致
     *          新密码：newPassword 非空 与原始密码不能相同
     *          确认密码：confirmPassword 非空 与新密码保持一致
     *      2. 设置用户新密码
     *           新密码进行加密处理
     *      3. 执行更新操作
     *           受影响的行数小于1，则表示修改失败
     *
     *      注：在对应的更新方法上，添加事务控制
     */
    /**
     * todo 更新用户密码
     *
     * @param userId          todo 用户id
     * @param oldPassword     todo 旧密码
     * @param newPassword     todo 新密码
     * @param confirmPassword todo 确认密码
     */
    public void updateUserPassword(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        // 通过userId获取用户对象
        User user = userMapper.selectByPrimaryKey(userId);
        // 1. 参数校验
        checkPasswordParams(user, oldPassword, newPassword, confirmPassword);
        // 2. 设置用户新密码
        user.setUserPwd(Md5Util.encode(newPassword));
        // 3. 执行更新操作
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "用户密码更新失败!!!");
    }

    /**
     * todo 验证用户密码修改参数
     * 用户ID：userId 非空 用户对象必须存在
     * 原始密码：oldPassword 非空 与数据库中密文密码保持一致
     * 新密码：newPassword 非空 与原始密码不能相同
     * 确认密码：confirmPassword 非空 与新密码保持一致
     *
     * @param user
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkPasswordParams(User user, String oldPassword, String newPassword, String confirmPassword) {
        // user对象 非空验证
        AssertUtil.isTrue(null == user, "用户未登录或不存在!!!");
        // 原始密码 非空验证
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "请输入原始密码!!!");
        // 原始密码要与数据库中的密文密码保持一致
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPassword))), "原始密码不正确!!!");
        // 新密码 非空校验
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "请输入新密码!!!");
        // 新密码与原始密码不能相同
        AssertUtil.isTrue(oldPassword.equals(newPassword), "新密码不能与原始密码相同!!!");
        // 确认密码 非空校验
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "请输入确认密码!!!");
        // 新密码要与确认密码保持一致
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "新密码与确认密码不一致!!!");
    }

    /**
     * todo 构建返回的用户信息
     *
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        //设置用户信息( userId 加密)
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    /**
     * todo 验证登录密码
     *
     * @param userPwd
     * @param userPwd1
     */
    private void checkLoginPwd(String userPwd, String userPwd1) {
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(userPwd1), "用户密码不正确!!!");
    }

    /**
     * todo 验证用户登录参数
     *
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        //判断姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空!!!");
        //判断密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空!!!");
    }

    /**
     * todo 查询所有的销售人员
     *
     * @return
     */
    public List<Map<String, Object>> queryAllSales() {
        return userMapper.queryAllSales();
    }

    /**
     * todo 权限管理 往下
     */

    /**
     * todo 多条件分页查询用户数据
     *
     * @param userQuery
     * @return
     */
    public Map<String, Object> queryUserByParams(UserQuery userQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByParams(userQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 添加用户
     *  1. 参数校验
     *      用户名 非空 唯一性
     *      邮箱   非空
     *      手机号 非空  格式合法
     *  2. 设置默认参数
     *      isValid 1
     *      creteDate   当前时间
     *      updateDate  当前时间
     *      userPwd 123456 -> md5加密
     *  3. 执行添加，判断结果
     */

    /**
     * todo 添加用户
     *
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        //System.out.println(user);
        //1.参数校验
        checkParams(user, user.getEmail(), user.getPhone());
        //2.设置默认参数
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        //3.执行添加,判断结果
        AssertUtil.isTrue(userMapper.insertSelective(user) == null, "用户添加失败!!!");

        /**
         * todo 权限管理模块
         */
        /**
         * todo 用户角色分配
         */
        //System.out.println(user.getId());
        relaionUserRole(user.getId(), user.getRoleIds());
    }

    /**
     * 用户角色分配
     * 原始角色不存在   添加新的角色记录
     * 原始角色存在     添加新的角色记录
     * 原始角色存在     清空所有角色
     * 原始角色存在     移除部分角色
     * 如何进行角色分配???
     * 如果用户原始角色存在  首先清空原始所有角色  添加新的角色记录到用户角色表
     */
    /**
     * todo 给对应的用户添加角色
     * @param userId
     * @param roleIds
     */
    private void relaionUserRole(Integer userId, String roleIds) {

        int count = userRoleMapper.countUserRoleByUserId(userId);
        if (count > 0) {
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "用户角色分配失败!!!");
        }
        if (StringUtils.isNotBlank(roleIds)) {
            //重新添加新的角色
            List<UserRole> userRoleList = new ArrayList<>();
            for (String s : roleIds.split(",")) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(s));
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRoleList.add(userRole);
            }
            //System.out.println(userRoleList);
            AssertUtil.isTrue(userRoleMapper.insertBatch(userRoleList) < userRoleList.size(), "用户角色分配失败");
        }
    }

    /**
     * todo 参数校验
     *
     * @param user
     * @param email
     * @param phone
     */
    private void checkParams(User user, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空!!!");

        AssertUtil.isTrue(userMapper.selectUserByName(user.getUserName()) != null && user.getId() == null, "用户名已存在!!!");
        AssertUtil.isTrue(StringUtils.isBlank(email), "请输入邮箱地址!!!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号格式不正确!!!");
    }

    /**
     * 更新用户
     *  1. 参数校验
     *      id  非空  记录必须存在
     *      用户名 非空  唯一性
     *      email 非空
     *      手机号 非空 格式合法
     *  2. 设置默认参数
     *      updateDate
     *  3. 执行更新，判断结果
     * @param user
     */
    /**
     * todo 更新用户
     *
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        //1.参数校验
        //通过id查询用户对象
        AssertUtil.isTrue(userMapper.selectByPrimaryKey(user.getId()) == null, "待更新用户不存在!!!");
        //2.验证参数
        //checkParams(user.getUserName(), user.getEmail(), user.getPhone());
        checkParams(user, user.getEmail(), user.getPhone());
        //3.设置默认参数
        user.setUpdateDate(new Date());
        //3.执行更新,判断结果
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "用户更新失败!!!");
        //System.out.println(user);

        /**
         * todo 用户权限管理
         */

        //文档写法
        //Integer userId = userMapper.selectUserByName(user.getUserName()).getId();
        //既然是更新操作,上面已经有id了,就不用去查了,直拿来用
        relaionUserRole(user.getId(), user.getRoleIds());
    }

    /**
     * todo 删除用户
     *
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserByIds(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "请选择待删除的用户记录!!!");

        for (Integer id : ids) {

            User user = selectByPrimaryKey(id);
            AssertUtil.isTrue(null == user, "待删除记录不存在!");

            int count = userRoleMapper.countUserRoleByUserId(id);
            if (count > 0) {
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(id) != count, "用户角色删除失败!!!");
            }
        }
        AssertUtil.isTrue(deleteBatch(ids) != ids.length, "用户记录删除失败!!!");
    }

}
