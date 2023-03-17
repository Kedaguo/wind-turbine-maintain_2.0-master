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
import com.ruoyi.system.domain.SysGroupTurbine;
import com.ruoyi.system.service.ISysGroupTurbineService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小组分配风机Controller
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/system/groupTurbine")
public class SysGroupTurbineController extends BaseController
{
    @Autowired
    private ISysGroupTurbineService sysGroupTurbineService;

    /**
     * 查询小组分配风机列表
     */
    @PreAuthorize("@ss.hasPermi('system:groupTurbine:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysGroupTurbine sysGroupTurbine)
    {
        startPage();
        List<SysGroupTurbine> list = sysGroupTurbineService.selectSysGroupTurbineList(sysGroupTurbine);
        return getDataTable(list);
    }

    /**
     * 导出小组分配风机列表
     */
    @PreAuthorize("@ss.hasPermi('system:groupTurbine:export')")
    @Log(title = "小组分配风机", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysGroupTurbine sysGroupTurbine)
    {
        List<SysGroupTurbine> list = sysGroupTurbineService.selectSysGroupTurbineList(sysGroupTurbine);
        ExcelUtil<SysGroupTurbine> util = new ExcelUtil<SysGroupTurbine>(SysGroupTurbine.class);
        util.exportExcel(response, list, "小组分配风机数据");
    }

    /**
     * 获取小组分配风机详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:groupTurbine:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId)
    {
        return success(sysGroupTurbineService.selectSysGroupTurbineByGroupId(groupId));
    }

    /**
     * 新增小组分配风机
     */
    @PreAuthorize("@ss.hasPermi('system:groupTurbine:add')")
    @Log(title = "小组分配风机", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysGroupTurbine sysGroupTurbine)
    {
        return toAjax(sysGroupTurbineService.insertSysGroupTurbine(sysGroupTurbine));
    }

    /**
     * 修改小组分配风机
     */
    @PreAuthorize("@ss.hasPermi('system:groupTurbine:edit')")
    @Log(title = "小组分配风机", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysGroupTurbine sysGroupTurbine)
    {
        return toAjax(sysGroupTurbineService.updateSysGroupTurbine(sysGroupTurbine));
    }

    /**
     * 删除小组分配风机
     */
    @PreAuthorize("@ss.hasPermi('system:groupTurbine:remove')")
    @Log(title = "小组分配风机", businessType = BusinessType.DELETE)
	@DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds)
    {
        return toAjax(sysGroupTurbineService.deleteSysGroupTurbineByGroupIds(groupIds));
    }
}
