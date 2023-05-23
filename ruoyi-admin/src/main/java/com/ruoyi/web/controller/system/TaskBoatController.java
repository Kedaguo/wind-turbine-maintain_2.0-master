package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.Boat;
import com.ruoyi.system.domain.dto.TaskBoatDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Resource
    private TokenService tokenService;
    /**
     * 查询taskBoat列表  根据task_id 查询当前学生的已分配船舶
     */
//    @PreAuthorize("@ss.hasPermi('system:taskBoat:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskBoat taskBoat,HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskBoatDto> taskBoatDtos = taskBoatService.selectTaskBoatDtoByUserId(taskBoat.getTaskId(), loginUser.getUserId());
        return getDataTable(taskBoatDtos);
    }
    /**
     * 查询taskBoat 总数
     */
//    @PreAuthorize("@ss.hasPermi('system:taskBoat:getBoatNumber')")
    @GetMapping("/getBoatNumber")
    public AjaxResult getBoatNumber(TaskBoat taskBoat,HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskBoat.setUserId(loginUser.getUserId());
        return success(taskBoatService.selectTaskBoatListSize(taskBoat));
    }
    /**
     * 查询taskBoat 待命数量
     */
//    @PreAuthorize("@ss.hasPermi('system:taskBoat:getStandbyBoatNumber')")
    @GetMapping("/getStandbyBoatNumber")
    public AjaxResult getStandbyBoatNumber(TaskBoat taskBoat,HttpServletRequest request)
    {
        taskBoat.setbState(0);
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskBoat.setUserId(loginUser.getUserId());
        return success(taskBoatService.selectTaskBoatListSize(taskBoat));
    }
    /**
     * 查询taskBoat 等待条件出海作业
     */
//    @PreAuthorize("@ss.hasPermi('system:taskBoat:getWaitBoatNumber')")
    @GetMapping("/getWaitBoatNumber")
    public AjaxResult getWaitBoatNumber(TaskBoat taskBoat,HttpServletRequest request)
    {
        taskBoat.setbState(1);
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskBoat.setUserId(loginUser.getUserId());
        return success(taskBoatService.selectTaskBoatListSize(taskBoat));
    }
    /**
     * 查询taskBoat 工作数量
     */
//    @PreAuthorize("@ss.hasPermi('system:taskBoat:getWorkBoatNumber')")
    @GetMapping("/getWorkBoatNumber")
    public AjaxResult getWorkBoatNumber(TaskBoat taskBoat,HttpServletRequest request)
    {
        taskBoat.setbState(2);
        LoginUser loginUser = tokenService.getLoginUser(request);
        taskBoat.setUserId(loginUser.getUserId());
        System.out.println("bbbbbbooooooaaaatttt"+taskBoat);
        return success(taskBoatService.selectTaskBoatListSize(taskBoat));
    }

    /**
     * 查询taskBoat列表  根据task_id 查询当前学生的已分配船舶
     */
//    @PreAuthorize("@ss.hasPermi('system:taskBoat:listByUser')")
    @GetMapping("/listByUser")
    public TableDataInfo selectTaskBoatDtoByUserId(@RequestParam Long taskId,HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<TaskBoatDto> list = taskBoatService.selectTaskBoatDtoByUserId(taskId,loginUser.getUserId());
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
        List<Boat> list = taskBoatService.selectTaskBoatList(taskBoat);
        ExcelUtil<Boat> util = new ExcelUtil<Boat>(Boat.class);
        util.exportExcel(response, list, "taskBoat数据");
    }

    /**
     * 获取taskBoat详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:taskBoat:query')")
//    @GetMapping(value = "/{bId}")
//    public AjaxResult getInfo(@PathVariable("bId") Long bId)
//    {
//        return success(taskBoatService.selectTaskBoatByBId(bId));
//    }

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
