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
import com.ruoyi.system.domain.ArrangementPort;
import com.ruoyi.system.service.IArrangementPortService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * arrangementBortController
 * 
 * @author JianDa
 * @date 2023-04-13
 */
@RestController
@RequestMapping("/system/arrangementBort")
public class ArrangementPortController extends BaseController
{
    @Autowired
    private IArrangementPortService arrangementPortService;

    /**
     * 查询arrangementBort列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBort:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArrangementPort arrangementPort)
    {
        startPage();
        List<ArrangementPort> list = arrangementPortService.selectArrangementPortList(arrangementPort);
        return getDataTable(list);
    }

    /**
     * 导出arrangementBort列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBort:export')")
    @Log(title = "arrangementBort", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArrangementPort arrangementPort)
    {
        List<ArrangementPort> list = arrangementPortService.selectArrangementPortList(arrangementPort);
        ExcelUtil<ArrangementPort> util = new ExcelUtil<ArrangementPort>(ArrangementPort.class);
        util.exportExcel(response, list, "arrangementBort数据");
    }

    /**
     * 获取arrangementBort详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBort:query')")
    @GetMapping(value = "/{apId}")
    public AjaxResult getInfo(@PathVariable("apId") Long apId)
    {
        return success(arrangementPortService.selectArrangementPortByApId(apId));
    }

    /**
     * 新增arrangementBort
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBort:add')")
    @Log(title = "arrangementBort", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrangementPort arrangementPort)
    {
        return toAjax(arrangementPortService.insertArrangementPort(arrangementPort));
    }

    /**
     * 修改arrangementBort
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBort:edit')")
    @Log(title = "arrangementBort", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArrangementPort arrangementPort)
    {
        return toAjax(arrangementPortService.updateArrangementPort(arrangementPort));
    }

    /**
     * 删除arrangementBort
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBort:remove')")
    @Log(title = "arrangementBort", businessType = BusinessType.DELETE)
	@DeleteMapping("/{apIds}")
    public AjaxResult remove(@PathVariable Long[] apIds)
    {
        return toAjax(arrangementPortService.deleteArrangementPortByApIds(apIds));
    }
}
