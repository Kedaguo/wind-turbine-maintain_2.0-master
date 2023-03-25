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
import com.ruoyi.system.domain.TaskBoat;
import com.ruoyi.system.service.ITaskBoatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskBoatController
 * 
 * @author JianDa
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/system/taskBoat")
public class TaskBoatController extends BaseController
{
    @Autowired
    private ITaskBoatService taskBoatService;

    /**
     * 查询taskBoat列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskBoat:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskBoat taskBoat)
    {
        startPage();
        List<TaskBoat> list = taskBoatService.selectTaskBoatList(taskBoat);
        return getDataTable(list);
    }

    /**
     * 导出taskBoat列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskBoat:export')")
    @Log(title = "taskBoat", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskBoat taskBoat)
    {
        List<TaskBoat> list = taskBoatService.selectTaskBoatList(taskBoat);
        ExcelUtil<TaskBoat> util = new ExcelUtil<TaskBoat>(TaskBoat.class);
        util.exportExcel(response, list, "taskBoat数据");
    }

    /**
     * 获取taskBoat详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskBoat:query')")
    @GetMapping(value = "/{bId}")
    public AjaxResult getInfo(@PathVariable("bId") Long bId)
    {
        return success(taskBoatService.selectTaskBoatByBId(bId));
    }

    /**
     * 新增taskBoat
     */
    @PreAuthorize("@ss.hasPermi('system:taskBoat:add')")
    @Log(title = "taskBoat", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskBoat taskBoat)
    {
        return toAjax(taskBoatService.insertTaskBoat(taskBoat));
    }

    /**
     * 修改taskBoat
     */
    @PreAuthorize("@ss.hasPermi('system:taskBoat:edit')")
    @Log(title = "taskBoat", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskBoat taskBoat)
    {
        return toAjax(taskBoatService.updateTaskBoat(taskBoat));
    }

    /**
     * 删除taskBoat
     */
    @PreAuthorize("@ss.hasPermi('system:taskBoat:remove')")
    @Log(title = "taskBoat", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bIds}")
    public AjaxResult remove(@PathVariable Long[] bIds)
    {
        return toAjax(taskBoatService.deleteTaskBoatByBIds(bIds));
    }
}
