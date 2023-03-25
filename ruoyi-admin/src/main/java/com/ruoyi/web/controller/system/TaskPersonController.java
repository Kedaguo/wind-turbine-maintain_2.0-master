package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TaskPerson;
import com.ruoyi.system.service.ITaskPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskPersonController
 * 
 * @author JianDa
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/system/taskPerson")
public class TaskPersonController extends BaseController
{
    @Autowired
    private ITaskPersonService taskPersonService;

    /**
     * 查询taskPerson列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskPerson:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskPerson taskPerson)
    {
        startPage();
        List<TaskPerson> list = taskPersonService.selectTaskPersonList(taskPerson);
        return getDataTable(list);
    }

    /**
     * 导出taskPerson列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskPerson:export')")
    @Log(title = "taskPerson", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskPerson taskPerson)
    {
        List<TaskPerson> list = taskPersonService.selectTaskPersonList(taskPerson);
        ExcelUtil<TaskPerson> util = new ExcelUtil<TaskPerson>(TaskPerson.class);
        util.exportExcel(response, list, "taskPerson数据");
    }

    /**
     * 获取taskPerson详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskPerson:query')")
    @GetMapping(value = "/{pId}")
    public AjaxResult getInfo(@PathVariable("pId") Long pId)
    {
        return success(taskPersonService.selectTaskPersonByPId(pId));
    }

    /**
     * 新增taskPerson
     */
    @PreAuthorize("@ss.hasPermi('system:taskPerson:add')")
    @Log(title = "taskPerson", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskPerson taskPerson)
    {
        return toAjax(taskPersonService.insertTaskPerson(taskPerson));
    }

    /**
     * 修改taskPerson
     */
    @PreAuthorize("@ss.hasPermi('system:taskPerson:edit')")
    @Log(title = "taskPerson", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskPerson taskPerson)
    {
        return toAjax(taskPersonService.updateTaskPerson(taskPerson));
    }

    /**
     * 删除taskPerson
     */
    @PreAuthorize("@ss.hasPermi('system:taskPerson:remove')")
    @Log(title = "taskPerson", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pIds}")
    public AjaxResult remove(@PathVariable Long[] pIds)
    {
        return toAjax(taskPersonService.deleteTaskPersonByPIds(pIds));
    }
}
