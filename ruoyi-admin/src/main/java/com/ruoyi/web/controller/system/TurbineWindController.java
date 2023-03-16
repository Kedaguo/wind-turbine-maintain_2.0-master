package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.TurbineWind;
import com.ruoyi.system.domain.vo.TurbineWindVo;
import com.ruoyi.system.service.ITurbineWindService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * windTurbineController
 * 
 * @author 赵建达
 * @date 2023-03-15
 */
@RestController
@RequestMapping("/system/windTurbine")
public class TurbineWindController extends BaseController
{
    @Autowired
    private ITurbineWindService turbineWindService;

    /**
     * 查询windTurbine列表
     */
    @PreAuthorize("@ss.hasPermi('system:windTurbine:list')")
    @GetMapping("/list")
    public TableDataInfo list(TurbineWind turbineWind)
    {
        startPage();
        List<TurbineWind> list = turbineWindService.selectTurbineWindList(turbineWind);
        return getDataTable(list);
    }

    /**
     * 导出windTurbine列表
     */
    @PreAuthorize("@ss.hasPermi('system:windTurbine:export')")
    @Log(title = "windTurbine", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TurbineWind turbineWind)
    {
        List<TurbineWind> list = turbineWindService.selectTurbineWindList(turbineWind);
        ExcelUtil<TurbineWind> util = new ExcelUtil<TurbineWind>(TurbineWind.class);
        util.exportExcel(response, list, "windTurbine数据");
    }

    /**
     * 获取windTurbine详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:windTurbine:query')")
    @GetMapping(value = "/{tId}")
    public AjaxResult getInfo(@PathVariable("tId") Long tId)
    {
        return success(turbineWindService.selectTurbineWindByTId(tId));
    }

    /**
     * 新增windTurbine
     */
    @PreAuthorize("@ss.hasPermi('system:windTurbine:add')")
    @Log(title = "windTurbine", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TurbineWind turbineWind)
    {
        return toAjax(turbineWindService.insertTurbineWind(turbineWind));
    }

    /**
     * 修改windTurbine
     */
    @PreAuthorize("@ss.hasPermi('system:windTurbine:edit')")
    @Log(title = "windTurbine", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TurbineWind turbineWind)
    {
        return toAjax(turbineWindService.updateTurbineWind(turbineWind));
    }

    /**
     * 删除windTurbine
     */
    @PreAuthorize("@ss.hasPermi('system:windTurbine:remove')")
    @Log(title = "windTurbine", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tIds}")
    public AjaxResult remove(@PathVariable Long[] tIds)
    {
        return toAjax(turbineWindService.deleteTurbineWindByTIds(tIds));
    }
}
