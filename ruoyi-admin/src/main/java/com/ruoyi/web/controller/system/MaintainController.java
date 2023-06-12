package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.Fault;
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
import com.ruoyi.system.domain.Maintain;
import com.ruoyi.system.service.IMaintainService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * maintainController
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
@Api
@RestController
@RequestMapping("/system/maintain")
public class MaintainController extends BaseController
{
    @Autowired
    private IMaintainService maintainService;

    /**
     * 查询maintain列表
     */
    @ApiOperation(value = "查询保养列表")
    @PreAuthorize("@ss.hasPermi('system:maintain:list')")
    @GetMapping("/list")
    public TableDataInfo list(Maintain maintain)
    {
        startPage();
        List<Maintain> list = maintainService.selectMaintainList(maintain);
        return getDataTable(list);
    }

    @GetMapping("/listByStudent")
    public TableDataInfo listByStudent(Maintain maintain)
    {
        startPage();
        List<Maintain> list = maintainService.selectMaintainList(maintain);
        return getDataTable(list);
    }
    /**
     * 导出maintain列表
     */
    @ApiOperation(value = "导出保养列表")
    @PreAuthorize("@ss.hasPermi('system:maintain:export')")
    @Log(title = "maintain", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Maintain maintain)
    {
        List<Maintain> list = maintainService.selectMaintainList(maintain);
        ExcelUtil<Maintain> util = new ExcelUtil<Maintain>(Maintain.class);
        util.exportExcel(response, list, "maintain数据");
    }

    /**
     * 获取maintain详细信息
     */
    @ApiOperation(value = "获取保养详细信息")
    @PreAuthorize("@ss.hasPermi('system:maintain:query')")
    @GetMapping(value = "/{mId}")
    public AjaxResult getInfo(@PathVariable("mId") Long mId)
    {
        return success(maintainService.selectMaintainByMId(mId));
    }

    /**
     * 新增maintain
     */
    @ApiOperation(value = "新增保养信息")
    @PreAuthorize("@ss.hasPermi('system:maintain:add')")
    @Log(title = "maintain", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Maintain maintain)
    {
        return toAjax(maintainService.insertMaintain(maintain));
    }

    /**
     * 修改maintain
     */
    @ApiOperation(value = "修改保养信息")
    @PreAuthorize("@ss.hasPermi('system:maintain:edit')")
    @Log(title = "maintain", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Maintain maintain)
    {
        return toAjax(maintainService.updateMaintain(maintain));
    }

    /**
     * 删除maintain
     */
    @ApiOperation(value = "删除保养信息")
    @PreAuthorize("@ss.hasPermi('system:maintain:remove')")
    @Log(title = "maintain", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mIds}")
    public AjaxResult remove(@PathVariable Long[] mIds)
    {
        return toAjax(maintainService.deleteMaintainByMIds(mIds));
    }
}
