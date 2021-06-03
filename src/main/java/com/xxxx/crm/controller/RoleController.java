package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.RoleQuery;
import com.xxxx.crm.service.RoleService;
import com.xxxx.crm.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * todo 角色管理模块
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    /**
     * todo 查询角色列表
     *
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleService.queryAllRoles(userId);
    }

    /**
     * todo 跳转到角色页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "role/role";
    }

    /**
     * todo 角色条件查询
     *
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryRoleByParams(RoleQuery roleQuery) {
        return roleService.queryRoleByParams(roleQuery);
    }

    /**
     * todo 弹出添加或更新框
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdateRolePage(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("role", roleService.selectByPrimaryKey(id));
        }
        return "role/add_update";
    }

    /**
     * todo 保存角色
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveRole(Role role) {
        roleService.saveRole(role);
        return success("角色记录添加成功!!!");
    }

    /**
     * todo 更新角色
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role) {
        roleService.updateRole(role);
        return success("角色记录更新成功!!!");
    }

    /**
     * todo 根据id删除角色
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer id){
        roleService.deleteRole(id);
        return success("角色记录删除成功!!!");
    }

    /**
     *todo 弹出授权框
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }

    /**
     * todo 新增权限
     * @param mids
     * @param roleId
     * @return
     */
    @RequestMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer[] mids,Integer roleId){
        /*System.out.println(mids);
        System.out.println(roleId);*/
        roleService.addGrant(mids,roleId);
        return success("权限添加成功");
    }
}
