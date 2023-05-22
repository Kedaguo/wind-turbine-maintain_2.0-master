package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.TurbineWind;
import com.ruoyi.system.domain.dto.TaskTurbineDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TaskTurbine;
import com.ruoyi.system.service.ITaskTurbineService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskTurbineController
 * 
 * @author JianDa
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/system/taskTurbine")
public class TaskTurbineController extends BaseController
{
    @Autowired
    private ITaskTurbineService taskTurbineService;

    @Resource
    private TokenService tokenService;
    /**
     * 查询taskTurbine列表    学生传task_id
     */
    @PostMapping("/list")
    public AjaxResult list(TaskTurbine taskTurbine,HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskTurbine.setUserId(loginUser.getUserId());
        List<TurbineWind> list = taskTurbineService.selectTaskTurbineList(taskTurbine);
        return success(list);
    }


    /*
    查询任务所分配的资源-风机
     */
//    @PreAuthorize("@ss.hasPermi('system:taskTurbine:listByState')")

    //正常工作——不需要保养、故障
    @PostMapping("/normalTurbineList")
    public AjaxResult selectNormalTaskTurbineList(@RequestBody TaskTurbine taskTurbine, HttpServletRequest request)
    {
        System.out.println("任务编号" + taskTurbine);
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskTurbine.setfState(2);
        taskTurbine.setmState(3);
        taskTurbine.setUserId(loginUser.getUserId());
        List<TaskTurbineDto> list = taskTurbineService.selectTaskTurbineListByStudent(taskTurbine);
        System.out.println(list);
        return success(list);
    }

    //故障停机
    @PostMapping("/fStateTurbineList")
    public AjaxResult selectFStateTurbineList(@RequestBody TaskTurbine taskTurbine, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskTurbine.setfState(1);
        taskTurbine.setUserId(loginUser.getUserId());
        List<TaskTurbineDto> list = taskTurbineService.selectTaskTurbineListByStudent(taskTurbine);
        return AjaxResult.success(list);
    }

    //需要保养且停机
    @PostMapping("/mStateTurbineList")
    public AjaxResult selectMStateTurbineList(@RequestBody TaskTurbine taskTurbine, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskTurbine.setmState(2);
        taskTurbine.setUserId(loginUser.getUserId());
        List<TaskTurbineDto> list = taskTurbineService.selectTaskTurbineListByStudent(taskTurbine);
        return AjaxResult.success(list);
    }
    //需要保养但仍可以工作
    @PostMapping("/mStateWorkTurbineList")
    public AjaxResult selectMStateWorkTurbineList(@RequestBody TaskTurbine taskTurbine, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskTurbine.setmState(1);
        taskTurbine.setUserId(loginUser.getUserId());
        List<TaskTurbineDto> list = taskTurbineService.selectTaskTurbineListByStudent(taskTurbine);
        return AjaxResult.success(list);
    }

    /**
     * 导出taskTurbine列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskTurbine:export')")
    @Log(title = "taskTurbine", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskTurbine taskTurbine)
    {
        List<TurbineWind> list = taskTurbineService.selectTaskTurbineList(taskTurbine);
        ExcelUtil<TurbineWind> util = new ExcelUtil<TurbineWind>(TurbineWind.class);
        util.exportExcel(response, list, "taskTurbine数据");
    }

    /**
     * 获取taskTurbine详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskTurbine:query')")
    @GetMapping(value = "/{tId}")
    public AjaxResult getInfo(@PathVariable("tId") Long tId,@PathVariable("taskId") Long taskId,@PathVariable("userId") Long userId)
    {
        return success(taskTurbineService.selectTaskTurbineByTId(tId,taskId,userId));
    }

    /**
     * 新增taskTurbine
     */
    @PreAuthorize("@ss.hasPermi('system:taskTurbine:add')")
    @Log(title = "taskTurbine", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskTurbine taskTurbine)
    {
        return toAjax(taskTurbineService.insertTaskTurbine(taskTurbine));
    }

    /**
     * 修改taskTurbine
     */
    @PreAuthorize("@ss.hasPermi('system:taskTurbine:edit')")
    @Log(title = "taskTurbine", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskTurbine taskTurbine)
    {
        return toAjax(taskTurbineService.updateTaskTurbine(taskTurbine));
    }

    /**
     * 删除taskTurbine
     */
    @PreAuthorize("@ss.hasPermi('system:taskTurbine:remove')")
    @Log(title = "taskTurbine", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tIds}")
    public AjaxResult remove(@PathVariable Long[] tIds)
    {
        return toAjax(taskTurbineService.deleteTaskTurbineByTIds(tIds));
    }
}
