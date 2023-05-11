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
import com.ruoyi.system.domain.ArrangementOperator;
import com.ruoyi.system.service.IArrangementOperatorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * arrangementOperatorController
 * 
 * @author JianDa
 * @date 2023-05-09
 */
@RestController
@RequestMapping("/system/arrangementOperator")
public class ArrangementOperatorController extends BaseController
{
    @Autowired
    private IArrangementOperatorService arrangementOperatorService;

    /**
     * 查询arrangementOperator列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementOperator:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArrangementOperator arrangementOperator)
    {
        startPage();
        List<ArrangementOperator> list = arrangementOperatorService.selectArrangementOperatorList(arrangementOperator);
        return getDataTable(list);
    }

    /**
     * 导出arrangementOperator列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementOperator:export')")
    @Log(title = "arrangementOperator", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArrangementOperator arrangementOperator)
    {
        List<ArrangementOperator> list = arrangementOperatorService.selectArrangementOperatorList(arrangementOperator);
        ExcelUtil<ArrangementOperator> util = new ExcelUtil<ArrangementOperator>(ArrangementOperator.class);
        util.exportExcel(response, list, "arrangementOperator数据");
    }

    /**
     * 获取arrangementOperator详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementOperator:query')")
    @GetMapping(value = "/{aId}")
    public AjaxResult getInfo(@PathVariable("aId") Long aId)
    {
        return success(arrangementOperatorService.selectArrangementOperatorByAId(aId));
    }

    /**
     * 新增arrangementOperator
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementOperator:add')")
    @Log(title = "arrangementOperator", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrangementOperator arrangementOperator)
    {
        return toAjax(arrangementOperatorService.insertArrangementOperator(arrangementOperator));
    }

    /**
     * 修改arrangementOperator
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementOperator:edit')")
    @Log(title = "arrangementOperator", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArrangementOperator arrangementOperator)
    {
        return toAjax(arrangementOperatorService.updateArrangementOperator(arrangementOperator));
    }

    /**
     * 删除arrangementOperator
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementOperator:remove')")
    @Log(title = "arrangementOperator", businessType = BusinessType.DELETE)
	@DeleteMapping("/{aIds}")
    public AjaxResult remove(@PathVariable Long[] aIds)
    {
        return toAjax(arrangementOperatorService.deleteArrangementOperatorByAIds(aIds));
    }
}
