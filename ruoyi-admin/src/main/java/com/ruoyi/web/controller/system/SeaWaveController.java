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
import com.ruoyi.system.domain.SeaWave;
import com.ruoyi.system.service.ISeaWaveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * seaWaveController
 * 
 * @author JianDa
 * @date 2023-04-21
 */
@RestController
@RequestMapping("/system/seaWave")
public class SeaWaveController extends BaseController
{
    @Autowired
    private ISeaWaveService seaWaveService;

    /**
     * 查询seaWave列表
     */
    @PreAuthorize("@ss.hasPermi('system:seaWave:list')")
    @GetMapping("/list")
    public TableDataInfo list(SeaWave seaWave)
    {
        startPage();
        List<SeaWave> list = seaWaveService.selectSeaWaveList(seaWave);
        return getDataTable(list);
    }

    /**
     * 导出seaWave列表
     */
    @PreAuthorize("@ss.hasPermi('system:seaWave:export')")
    @Log(title = "seaWave", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeaWave seaWave)
    {
        List<SeaWave> list = seaWaveService.selectSeaWaveList(seaWave);
        ExcelUtil<SeaWave> util = new ExcelUtil<SeaWave>(SeaWave.class);
        util.exportExcel(response, list, "seaWave数据");
    }

    /**
     * 获取seaWave详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:seaWave:query')")
    @GetMapping(value = "/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return success(seaWaveService.selectSeaWaveBySId(sId));
    }

    /**
     * 新增seaWave
     */
    @PreAuthorize("@ss.hasPermi('system:seaWave:add')")
    @Log(title = "seaWave", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeaWave seaWave)
    {
        return toAjax(seaWaveService.insertSeaWave(seaWave));
    }

    /**
     * 修改seaWave
     */
    @PreAuthorize("@ss.hasPermi('system:seaWave:edit')")
    @Log(title = "seaWave", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeaWave seaWave)
    {
        return toAjax(seaWaveService.updateSeaWave(seaWave));
    }

    /**
     * 删除seaWave
     */
    @PreAuthorize("@ss.hasPermi('system:seaWave:remove')")
    @Log(title = "seaWave", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sIds}")
    public AjaxResult remove(@PathVariable Long[] sIds)
    {
        return toAjax(seaWaveService.deleteSeaWaveBySIds(sIds));
    }
}
