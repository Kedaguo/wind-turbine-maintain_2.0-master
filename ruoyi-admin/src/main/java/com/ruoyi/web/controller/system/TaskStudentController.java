package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.dto.TaskStudentDto;
import com.ruoyi.system.domain.dto.TaskTeacherDto;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TaskStudent;
import com.ruoyi.system.service.ITaskStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskStudentController
 * 
 * @author JianDa
 * @date 2023-03-27
 */
@Api(tags = "任务关联学生")
@RestController
@RequestMapping("/system/taskStudent")
public class TaskStudentController extends BaseController
{
    @Autowired
    private ITaskStudentService taskStudentService;

    @Resource
    private TokenService tokenService;


    /**
     *  学生查询自己的所有任务
     */
    @PreAuthorize("@ss.hasPermi('system:taskStudent:list')")
    @GetMapping("/list")
    public TableDataInfo list(HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskStudentDto> list = taskStudentService.selectTaskStudentList(loginUser.getUserId());
        return getDataTable(list);
    }
/*
查询taskStudent列表
 */
    @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/taskTeacherList")
    public TableDataInfo taskList(HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskTeacherDto> list = taskStudentService.selectTaskStudentByTeacher(loginUser.getUsername());
        return getDataTable(list);
    }

    /**
     * 导出taskStudent列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskStudent:export')")
    @Log(title = "taskStudent", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskStudentDto> list = taskStudentService.selectTaskStudentList(loginUser.getUserId());
        ExcelUtil<TaskStudentDto> util = new ExcelUtil<TaskStudentDto>(TaskStudentDto.class);
        util.exportExcel(response, list, "taskStudent数据");
    }

    /**
     * 获取taskStudent详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:taskStudent:query')")
    @PostMapping(value = "/getSimulationTime")
    public AjaxResult getSimulationTime(@RequestParam Long taskId,HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setUserId(loginUser.getUserId());
        taskStudent.setTaskId(taskId);
        return success(taskStudentService.selectTaskStudentByUserId(taskStudent).getTaskSimulateTime());
    }

    /**
     * 新增taskStudent
     */
    @PreAuthorize("@ss.hasPermi('system:taskStudent:add')")
    @Log(title = "taskStudent", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskStudent taskStudent)
    {
        return toAjax(taskStudentService.insertTaskStudent(taskStudent));
    }

    /**
     * 修改taskStudent
     */
    @PreAuthorize("@ss.hasPermi('system:taskStudent:edit')")
    @Log(title = "taskStudent", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskStudent taskStudent)
    {
        return toAjax(taskStudentService.updateTaskStudent(taskStudent));
    }

    /**
     * 删除taskStudent
     */
    @PreAuthorize("@ss.hasPermi('system:taskStudent:remove')")
    @Log(title = "taskStudent", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(taskStudentService.deleteTaskStudentByUserIds(userIds));
    }
}
