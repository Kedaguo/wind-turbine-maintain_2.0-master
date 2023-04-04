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
    @PreAuthorize("@ss.hasPermi('system:taskTurbine:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskTurbine taskTurbine)
    {
        startPage();
        List<TurbineWind> list = taskTurbineService.selectTaskTurbineList(taskTurbine);
        return getDataTable(list);
    }
    /*
    查询任务所分配的资源-风机
     */
//    @PreAuthorize("@ss.hasPermi('system:taskTurbine:listByUser')")
    @GetMapping("/listByUser")
    public TableDataInfo selectTaskTurbineListByUser(@RequestParam Long taskId, HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskTurbineDto> list = taskTurbineService.selectTaskTurbineListByUser(taskId,loginUser.getUserId());
        return getDataTable(list);
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
    public AjaxResult getInfo(@PathVariable("tId") Long tId)
    {
        return success(taskTurbineService.selectTaskTurbineByTId(tId));
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
