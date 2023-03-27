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
import com.ruoyi.system.domain.TlWindturbine;
import com.ruoyi.system.service.ITlWindturbineService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 故障风机监控Controller
 * 
 * @author 张存
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/monitor/offline")
public class TlWindturbineController extends BaseController
{
    @Autowired
    private ITlWindturbineService tlWindturbineService;

    /**
     * 查询故障风机监控列表
     */
    @PreAuthorize("@ss.hasPermi('monitor/offline:offline:list')")
    @GetMapping("/list")
    public TableDataInfo list(TlWindturbine tlWindturbine)
    {
        startPage();
        List<TlWindturbine> list = tlWindturbineService.selectTlWindturbineList(tlWindturbine);
        return getDataTable(list);
    }

    /**
     * 导出故障风机监控列表
     */
    @PreAuthorize("@ss.hasPermi('monitor/offline:offline:export')")
    @Log(title = "故障风机监控", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TlWindturbine tlWindturbine)
    {
        List<TlWindturbine> list = tlWindturbineService.selectTlWindturbineList(tlWindturbine);
        ExcelUtil<TlWindturbine> util = new ExcelUtil<TlWindturbine>(TlWindturbine.class);
        util.exportExcel(response, list, "故障风机监控数据");
    }

    /**
     * 获取故障风机监控详细信息
     */
    @PreAuthorize("@ss.hasPermi('monitor/offline:offline:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tlWindturbineService.selectTlWindturbineById(id));
    }

    /**
     * 新增故障风机监控
     */
    @PreAuthorize("@ss.hasPermi('monitor/offline:offline:add')")
    @Log(title = "故障风机监控", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TlWindturbine tlWindturbine)
    {
        return toAjax(tlWindturbineService.insertTlWindturbine(tlWindturbine));
    }

    /**
     * 修改故障风机监控
     */
    @PreAuthorize("@ss.hasPermi('monitor/offline:offline:edit')")
    @Log(title = "故障风机监控", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TlWindturbine tlWindturbine)
    {
        return toAjax(tlWindturbineService.updateTlWindturbine(tlWindturbine));
    }

    /**
     * 删除故障风机监控
     */
    @PreAuthorize("@ss.hasPermi('monitor/offline:offline:remove')")
    @Log(title = "故障风机监控", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tlWindturbineService.deleteTlWindturbineByIds(ids));
    }
}
