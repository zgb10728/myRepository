package com.xxxx.crm.controller;

import com.xxxx.crm.annotaions.RequirePermission;
import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.CusDevPlanQuery;
import com.xxxx.crm.service.CusDevPlanService;
import com.xxxx.crm.service.SaleChanceService;
import com.xxxx.crm.vo.CusDevPlan;
import com.xxxx.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * todo 客户开发计划 controller
 */
@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {


    @Resource
    private SaleChanceService saleChanceService;

    @Resource
    private CusDevPlanService cusDevPlanService;


    /**
     * todo 客户开发主页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "cusDevPlan/cus_dev_plan";
    }

    /**
     * todo 进入开发计划项数据页面
     *
     * @param model
     * @param sid
     * @return
     */
    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Model model, Integer sid) {
        //通过id查询营销机会数据
        SaleChance saleChance = saleChanceService.selectByPrimaryKey(sid);
        //将数据存到作用域中
        model.addAttribute("saleChance", saleChance);
        System.out.println(saleChance);
        System.out.println(saleChance);
        return "cusDevPlan/cus_dev_plan_data";
    }

    /**
     * todo 查询营销机会的计划项数据列表
     *
     * @param cusDevPlanQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCusDevPlanByParams(CusDevPlanQuery cusDevPlanQuery) {
        //System.out.println(1111);
        return cusDevPlanService.queryCusDevPlanByParams(cusDevPlanQuery);
    }

    /**
     * todo 添加计划项
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveCusDevPlan(CusDevPlan cusDevPlan){
        System.out.println(cusDevPlan);
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success("计划项添加成功!!!");
    }

    /**
     * todo 更新计划项
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项更新成功!!!");
    }

    /**
     *todo 转发跳转到计划数据项页面
     * @param sid
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid,Integer id,Model model){
        model.addAttribute("cusDevPlan",cusDevPlanService.selectByPrimaryKey(id));
        model.addAttribute("sid",sid);
        return "cusDevPlan/add_update";
    }

    /**
     * todo 删除计划项
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id){
        cusDevPlanService.delCusDevPlan(id);
        return success("计划项删除成功!!!");
    }


}
