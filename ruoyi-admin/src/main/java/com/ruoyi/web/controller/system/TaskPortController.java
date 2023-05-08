package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.Port;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TaskPort;
import com.ruoyi.system.service.ITaskPortService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskPortController
 * 
 * @author JianDa
 * @date 2023-04-03
 */
@RestController
@RequestMapping("/system/taskPort")
public class TaskPortController extends BaseController
{
    @Autowired
    private ITaskPortService taskPortService;

    @Resource
    private TokenService tokenService;
    /**
     * 查询taskPort列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskPort:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskPort taskPort)
    {
        startPage();
        List<TaskPort> list = taskPortService.selectTaskPortList(taskPort);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:taskPort:listByUser')")
    @GetMapping("/listByUser")
    public TableDataInfo listByUser(@PathVariable("taskId") Long taskId, HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<Port> list = taskPortService.selectTaskPortListByUser(taskId,loginUser.getUserId());
        return getDataTable(list);
    }
    /**
     * 导出taskPort列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskPort:export')")
    @Log(title = "taskPort", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskPort taskPort)
    {
        List<TaskPort> list = taskPortService.selectTaskPortList(taskPort);
        ExcelUtil<TaskPort> util = new ExcelUtil<TaskPort>(TaskPort.class);
        util.exportExcel(response, list, "taskPort数据");
    }

    /**
     * 获取taskPort详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskPort:query')")
    @GetMapping(value = "/{pId}")
    public AjaxResult getInfo(@PathVariable("pId") Long pId)
    {
        return success(taskPortService.selectTaskPortByPId(pId));
    }

    /**
     * 新增taskPort
     */
    @PreAuthorize("@ss.hasPermi('system:taskPort:add')")
    @Log(title = "taskPort", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskPort taskPort)
    {
        return toAjax(taskPortService.insertTaskPort(taskPort));
    }

    /**
     * 修改taskPort
     */
    @PreAuthorize("@ss.hasPermi('system:taskPort:edit')")
    @Log(title = "taskPort", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskPort taskPort)
    {
        return toAjax(taskPortService.updateTaskPort(taskPort));
    }

    /**
     * 删除taskPort
     */
    @PreAuthorize("@ss.hasPermi('system:taskPort:remove')")
    @Log(title = "taskPort", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pIds}")
    public AjaxResult remove(@PathVariable Long[] pIds)
    {
        return toAjax(taskPortService.deleteTaskPortByPIds(pIds));
    }
}
