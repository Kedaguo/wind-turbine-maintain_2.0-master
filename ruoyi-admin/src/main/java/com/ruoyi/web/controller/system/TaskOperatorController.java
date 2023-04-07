package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.dto.TaskOperatorDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TaskOperator;
import com.ruoyi.system.service.ITaskOperatorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskOperatorController
 * 
 * @author JianDa
 * @date 2023-04-07
 */
@RestController
@RequestMapping("/system/taskOperator")
public class TaskOperatorController extends BaseController
{
    @Autowired
    private ITaskOperatorService taskOperatorService;

    @Resource
    private TokenService tokenService;


    /**
     * 查询taskOperator列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskOperator:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskOperator taskOperator)
    {
        startPage();
        List<TaskOperator> list = taskOperatorService.selectTaskOperatorList(taskOperator);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('system:taskOperator:listByUser')")
    @GetMapping("/listByUser")
    public TableDataInfo selectTaskOperatorByUser(@RequestParam Long taskId, HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskOperatorDto> list = taskOperatorService.selectTaskOperatorByUser(taskId,loginUser.getUserId());
        return getDataTable(list);
    }
    /**
     * 导出taskOperator列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskOperator:export')")
    @Log(title = "taskOperator", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskOperator taskOperator)
    {
        List<TaskOperator> list = taskOperatorService.selectTaskOperatorList(taskOperator);
        ExcelUtil<TaskOperator> util = new ExcelUtil<TaskOperator>(TaskOperator.class);
        util.exportExcel(response, list, "taskOperator数据");
    }

    /**
     * 获取taskOperator详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskOperator:query')")
    @GetMapping(value = "/{oId}")
    public AjaxResult getInfo(@PathVariable("oId") Long oId)
    {
        return success(taskOperatorService.selectTaskOperatorByOId(oId));
    }

    /**
     * 新增taskOperator
     */
    @PreAuthorize("@ss.hasPermi('system:taskOperator:add')")
    @Log(title = "taskOperator", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskOperator taskOperator)
    {
        return toAjax(taskOperatorService.insertTaskOperator(taskOperator));
    }

    /**
     * 修改taskOperator
     */
    @PreAuthorize("@ss.hasPermi('system:taskOperator:edit')")
    @Log(title = "taskOperator", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskOperator taskOperator)
    {
        return toAjax(taskOperatorService.updateTaskOperator(taskOperator));
    }

    /**
     * 删除taskOperator
     */
    @PreAuthorize("@ss.hasPermi('system:taskOperator:remove')")
    @Log(title = "taskOperator", businessType = BusinessType.DELETE)
	@DeleteMapping("/{oIds}")
    public AjaxResult remove(@PathVariable Long[] oIds)
    {
        return toAjax(taskOperatorService.deleteTaskOperatorByOIds(oIds));
    }
}
