package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.Person;
import com.ruoyi.system.domain.dto.TaskPersonDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Resource
    private TokenService tokenService;

    /**
     * 查询taskPerson列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskPerson:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskPerson taskPerson)
    {
        startPage();
        List<Person> list = taskPersonService.selectTaskPersonList(taskPerson);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:taskPerson:listByUser')")
    @GetMapping("/listByUser")
    public TableDataInfo selectTaskPersonByUser(@RequestParam Long taskId, HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskPersonDto> list = taskPersonService.selectTaskPersonByUser(taskId,loginUser.getUserId());
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
        List<Person> list = taskPersonService.selectTaskPersonList(taskPerson);
        ExcelUtil<Person> util = new ExcelUtil<Person>(Person.class);
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
