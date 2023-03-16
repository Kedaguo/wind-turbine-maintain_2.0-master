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
import com.ruoyi.system.domain.Boat;
import com.ruoyi.system.service.IBoatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * boatController
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
@Api(tags = "船舶管理")
@RestController
@RequestMapping("/system/boat")
public class BoatController extends BaseController
{
    @Autowired
    private IBoatService boatService;

    /**
     * 查询boat列表
     */
    @ApiOperation(value = "船舶列表查询")
    @PreAuthorize("@ss.hasPermi('system:boat:list')")
    @GetMapping("/list")
    public TableDataInfo list(Boat boat)
    {
        startPage();
        List<Boat> list = boatService.selectBoatList(boat);
        return getDataTable(list);
    }

    /**
     * 导出boat列表
     */
    @ApiOperation(value = "导出船舶列表")
    @PreAuthorize("@ss.hasPermi('system:boat:export')")
    @Log(title = "boat", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Boat boat)
    {
        List<Boat> list = boatService.selectBoatList(boat);
        ExcelUtil<Boat> util = new ExcelUtil<Boat>(Boat.class);
        util.exportExcel(response, list, "boat数据");
    }

    /**
     * 获取boat详细信息
     */
    @ApiOperation(value = "获取船舶详细信息")
    @PreAuthorize("@ss.hasPermi('system:boat:query')")
    @GetMapping(value = "/{bId}")
    public AjaxResult getInfo(@PathVariable("bId") Long bId)
    {
        return success(boatService.selectBoatByBId(bId));
    }

    /**
     * 新增boat
     */
    @ApiOperation(value = "新增船舶")
    @PreAuthorize("@ss.hasPermi('system:boat:add')")
    @Log(title = "boat", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Boat boat)
    {
        return toAjax(boatService.insertBoat(boat));
    }

    /**
     * 修改boat
     */
    @ApiOperation(value = "修改船舶")
    @PreAuthorize("@ss.hasPermi('system:boat:edit')")
    @Log(title = "boat", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Boat boat)
    {
        return toAjax(boatService.updateBoat(boat));
    }

    /**
     * 删除boat
     */
    @ApiOperation(value = "删除船舶")
    @PreAuthorize("@ss.hasPermi('system:boat:remove')")
    @Log(title = "boat", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bIds}")
    public AjaxResult remove(@PathVariable Long[] bIds)
    {
        return toAjax(boatService.deleteBoatByBIds(bIds));
    }
}
