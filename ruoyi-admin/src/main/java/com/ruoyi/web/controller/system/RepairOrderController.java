package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.dto.RepairOrderFaultDto;
import com.ruoyi.system.domain.dto.RepairOrderMaintainDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.RepairOrder;
import com.ruoyi.system.service.IRepairOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * repairOrderController
 * 
 * @author JianDa
 * @date 2023-04-04
 */
@RestController
@RequestMapping("/system/repairOrder")
public class RepairOrderController extends BaseController
{
    @Autowired
    private IRepairOrderService repairOrderService;

    @Resource
    private TokenService tokenService;

    /**
     * 查询repairOrder列表
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(RepairOrder repairOrder)
    {
        startPage();
        List<RepairOrder> list = repairOrderService.selectRepairOrderList(repairOrder);
        return getDataTable(list);
    }
    /**
     * 查询repairOrderFault列表  --学生
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:repairOrderFaultList')")
    @GetMapping("/repairOrderFaultList")
    public TableDataInfo selectRepairOrderFaultList(@RequestParam Long taskId, HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<RepairOrderFaultDto> list = repairOrderService.selectRepairOrderFaultListByStudent(taskId,loginUser.getUserId());
        return getDataTable(list);
    }
    /**
     * 查询repairOrder列表  --学生
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:repairOrderMaintainList')")
    @GetMapping("/repairOrderMaintainList")
    public TableDataInfo selectRepairOrderMaintainList(@RequestParam Long taskId, HttpServletRequest request)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(request);
        List<RepairOrderMaintainDto> list = repairOrderService.selectRepairOrderMaintainList(taskId,loginUser.getUserId());
        return getDataTable(list);
    }
    /**
     * 导出repairOrder列表
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:export')")
    @Log(title = "repairOrder", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RepairOrder repairOrder)
    {
        List<RepairOrder> list = repairOrderService.selectRepairOrderList(repairOrder);
        ExcelUtil<RepairOrder> util = new ExcelUtil<RepairOrder>(RepairOrder.class);
        util.exportExcel(response, list, "repairOrder数据");
    }

    /**
     * 获取repairOrder详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:query')")
    @GetMapping(value = "/{rId}")
    public AjaxResult getInfo(@PathVariable("rId") Long rId)
    {
        return success(repairOrderService.selectRepairOrderByRId(rId));
    }

    /**
     * 新增repairOrder
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:add')")
    @Log(title = "repairOrder", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RepairOrder repairOrder)
    {
        return toAjax(repairOrderService.insertRepairOrder(repairOrder));
    }

    /**
     * 修改repairOrder
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:edit')")
    @Log(title = "repairOrder", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RepairOrder repairOrder)
    {
        return toAjax(repairOrderService.updateRepairOrder(repairOrder));
    }

    /**
     * 删除repairOrder
     */
    @PreAuthorize("@ss.hasPermi('system:repairOrder:remove')")
    @Log(title = "repairOrder", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rIds}")
    public AjaxResult remove(@PathVariable Long[] rIds)
    {
        return toAjax(repairOrderService.deleteRepairOrderByRIds(rIds));
    }
}
