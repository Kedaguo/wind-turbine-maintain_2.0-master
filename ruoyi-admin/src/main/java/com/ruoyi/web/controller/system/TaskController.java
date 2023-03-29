package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.system.service.ITaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskController
 * 
 * @author 赵建达
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/system/task")
public class TaskController extends BaseController
{
    @Autowired
    private ITaskService taskService;

    @Resource
    private TokenService tokenService;



    /**
     *老师查看  task_create_by  username
     */
    @PreAuthorize("@ss.hasPermi('system:task:listByTeacher')")
    @GetMapping("/list")
    public TableDataInfo list(HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<Task> list = taskService.selectTaskListByTeacher(loginUser.getUsername());
        return getDataTable(list);
    }
    /**
     *查询task列表  学生查看查询task列表  user_id
     */
    @PreAuthorize("@ss.hasPermi('system:task:listByStudent')")
    @GetMapping("/listByStudent")
    public TableDataInfo listByStudent(Task task)
    {
        startPage();
        List<Task> list = taskService.selectTaskList(task);
        return getDataTable(list);
    }

    /**
     * 导出task列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:export')")
    @Log(title = "task", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Task task)
    {
        List<Task> list = taskService.selectTaskList(task);
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        util.exportExcel(response, list, "task数据");
    }

    /**
     * 获取task详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(taskService.selectTaskByTaskId(taskId));
    }

    /**
     * 教师发布任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:add')")
    @Log(title = "task", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Task task, HttpServletRequest request)
    {
        System.out.println("task"+task);
        LoginUser loginUser = tokenService.getLoginUser(request);
        return taskService.insertTask(task,loginUser);
    }

    /**
     * 修改task
     */
    @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "task", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Task task)
    {
        return toAjax(taskService.updateTask(task));
    }

    /**
     * 删除task
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "task", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(taskService.deleteTaskByTaskIds(taskIds));
    }
}
