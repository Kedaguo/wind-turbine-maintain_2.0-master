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
import com.ruoyi.system.domain.ArrangementRepair;
import com.ruoyi.system.service.IArrangementRepairService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * arrangementRepairController
 * 
 * @author JianDa
 * @date 2023-04-13
 */
@RestController
@RequestMapping("/system/arrangementRepair")
public class ArrangementRepairController extends BaseController
{
    @Autowired
    private IArrangementRepairService arrangementRepairService;

    /**
     * 查询arrangementRepair列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementRepair:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArrangementRepair arrangementRepair)
    {
        startPage();
        List<ArrangementRepair> list = arrangementRepairService.selectArrangementRepairList(arrangementRepair);
        return getDataTable(list);
    }

    /**
     * 导出arrangementRepair列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementRepair:export')")
    @Log(title = "arrangementRepair", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArrangementRepair arrangementRepair)
    {
        List<ArrangementRepair> list = arrangementRepairService.selectArrangementRepairList(arrangementRepair);
        ExcelUtil<ArrangementRepair> util = new ExcelUtil<ArrangementRepair>(ArrangementRepair.class);
        util.exportExcel(response, list, "arrangementRepair数据");
    }

    /**
     * 获取arrangementRepair详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementRepair:query')")
    @GetMapping(value = "/{arId}")
    public AjaxResult getInfo(@PathVariable("arId") Long arId)
    {
        return success(arrangementRepairService.selectArrangementRepairByArId(arId));
    }

    /**
     * 新增arrangementRepair
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementRepair:add')")
    @Log(title = "arrangementRepair", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrangementRepair arrangementRepair)
    {
        return toAjax(arrangementRepairService.insertArrangementRepair(arrangementRepair));
    }

    /**
     * 修改arrangementRepair
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementRepair:edit')")
    @Log(title = "arrangementRepair", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArrangementRepair arrangementRepair)
    {
        return toAjax(arrangementRepairService.updateArrangementRepair(arrangementRepair));
    }

    /**
     * 删除arrangementRepair
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementRepair:remove')")
    @Log(title = "arrangementRepair", businessType = BusinessType.DELETE)
	@DeleteMapping("/{arIds}")
    public AjaxResult remove(@PathVariable Long[] arIds)
    {
        return toAjax(arrangementRepairService.deleteArrangementRepairByArIds(arIds));
    }
}
