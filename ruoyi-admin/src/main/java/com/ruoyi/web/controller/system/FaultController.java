package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.system.domain.Fault;
import com.ruoyi.system.service.IFaultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 故障信息Controller
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
@Api(tags = "故障管理")
@RestController
@RequestMapping("/system/fault")
public class FaultController extends BaseController
{
    @Autowired
    private IFaultService faultService;

    /**
     * 查询故障信息列表
     */
    @ApiOperation(value = "查询故障信息列表")
    @PreAuthorize("@ss.hasPermi('system:fault:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fault fault)
    {
        startPage();
        List<Fault> list = faultService.selectFaultList(fault);
        return getDataTable(list);
    }

    /**
     * 查询故障信息列表-student
     */
//    @PreAuthorize("@ss.hasPermi('system:fault:list')")
    @GetMapping("/listByStudent")
    public TableDataInfo listByStudent(Fault fault)
    {
        startPage();
        List<Fault> list = faultService.selectFaultList(fault);
        return getDataTable(list);
    }

    /**
     * 导出故障信息列表
     */
    @ApiOperation(value = "导出故障信息列表")
    @PreAuthorize("@ss.hasPermi('system:fault:export')")
    @Log(title = "故障信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Fault fault)
    {
        List<Fault> list = faultService.selectFaultList(fault);
        ExcelUtil<Fault> util = new ExcelUtil<Fault>(Fault.class);
        util.exportExcel(response, list, "故障信息数据");
    }

    /**
     * 获取故障信息详细信息
     */
    @ApiOperation(value = "获取故障信息详细信息")
    @PreAuthorize("@ss.hasPermi('system:fault:query')")
    @GetMapping(value = "/{fId}")
    public AjaxResult getInfo(@PathVariable("fId") Long fId)
    {
        return success(faultService.selectFaultByFId(fId));
    }

    /**
     * 新增故障信息
     */
    @ApiOperation(value = "新增故障信息")
    @PreAuthorize("@ss.hasPermi('system:fault:add')")
    @Log(title = "故障信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fault fault)
    {
        return toAjax(faultService.insertFault(fault));
    }

    /**
     * 修改故障信息
     */
    @ApiOperation(value = "修改故障信息")
    @PreAuthorize("@ss.hasPermi('system:fault:edit')")
    @Log(title = "故障信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fault fault)
    {
        return toAjax(faultService.updateFault(fault));
    }

    /**
     * 删除故障信息
     */
    @ApiOperation(value = "删除故障信息")
    @PreAuthorize("@ss.hasPermi('system:fault:remove')")
    @Log(title = "故障信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fIds}")
    public AjaxResult remove(@PathVariable Long[] fIds)
    {
        return toAjax(faultService.deleteFaultByFIds(fIds));
    }
}
