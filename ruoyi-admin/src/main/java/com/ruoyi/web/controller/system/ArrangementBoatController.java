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
import com.ruoyi.system.domain.ArrangementBoat;
import com.ruoyi.system.service.IArrangementBoatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * arrangementBoatController
 * 
 * @author JianDa
 * @date 2023-04-14
 */
@RestController
@RequestMapping("/system/arrangementBoat")
public class ArrangementBoatController extends BaseController
{
    @Autowired
    private IArrangementBoatService arrangementBoatService;

    /**
     * 查询arrangementBoat列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBoat:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArrangementBoat arrangementBoat)
    {
        startPage();
        List<ArrangementBoat> list = arrangementBoatService.selectArrangementBoatList(arrangementBoat);
        return getDataTable(list);
    }

    /**
     * 导出arrangementBoat列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBoat:export')")
    @Log(title = "arrangementBoat", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArrangementBoat arrangementBoat)
    {
        List<ArrangementBoat> list = arrangementBoatService.selectArrangementBoatList(arrangementBoat);
        ExcelUtil<ArrangementBoat> util = new ExcelUtil<ArrangementBoat>(ArrangementBoat.class);
        util.exportExcel(response, list, "arrangementBoat数据");
    }

    /**
     * 获取arrangementBoat详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBoat:query')")
    @GetMapping(value = "/{aId}")
    public AjaxResult getInfo(@PathVariable("aId") Long aId)
    {
        return success(arrangementBoatService.selectArrangementBoatByAId(aId));
    }

    /**
     * 新增arrangementBoat
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBoat:add')")
    @Log(title = "arrangementBoat", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrangementBoat arrangementBoat)
    {
        return toAjax(arrangementBoatService.insertArrangementBoat(arrangementBoat));
    }

    /**
     * 修改arrangementBoat
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBoat:edit')")
    @Log(title = "arrangementBoat", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArrangementBoat arrangementBoat)
    {
        return toAjax(arrangementBoatService.updateArrangementBoat(arrangementBoat));
    }

    /**
     * 删除arrangementBoat
     */
    @PreAuthorize("@ss.hasPermi('system:arrangementBoat:remove')")
    @Log(title = "arrangementBoat", businessType = BusinessType.DELETE)
	@DeleteMapping("/{aIds}")
    public AjaxResult remove(@PathVariable Long[] aIds)
    {
        return toAjax(arrangementBoatService.deleteArrangementBoatByAIds(aIds));
    }
}
