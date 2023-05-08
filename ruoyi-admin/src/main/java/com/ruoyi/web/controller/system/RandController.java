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
import com.ruoyi.system.domain.Rand;
import com.ruoyi.system.service.IRandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * randController
 * 
 * @author JianDa
 * @date 2023-05-06
 */
@RestController
@RequestMapping("/system/rand")
public class RandController extends BaseController
{
    @Autowired
    private IRandService randService;

    /**
     * 查询rand列表
     */
    @PreAuthorize("@ss.hasPermi('system:rand:list')")
    @GetMapping("/list")
    public TableDataInfo list(Rand rand)
    {
        startPage();
        List<Rand> list = randService.selectRandList(rand);
        return getDataTable(list);
    }

    /**
     * 导出rand列表
     */
    @PreAuthorize("@ss.hasPermi('system:rand:export')")
    @Log(title = "rand", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Rand rand)
    {
        List<Rand> list = randService.selectRandList(rand);
        ExcelUtil<Rand> util = new ExcelUtil<Rand>(Rand.class);
        util.exportExcel(response, list, "rand数据");
    }

    /**
     * 获取rand详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rand:query')")
    @GetMapping(value = "/{randId}")
    public AjaxResult getInfo(@PathVariable("randId") Long randId)
    {
        return success(randService.selectRandByRandId(randId));
    }

    /**
     * 新增rand
     */
//    @PreAuthorize("@ss.hasPermi('system:rand:add')")
    @Log(title = "rand", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add()
    {
        return toAjax(randService.insertRand());
    }

    /**
     * 修改rand
     */
    @PreAuthorize("@ss.hasPermi('system:rand:edit')")
    @Log(title = "rand", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Rand rand)
    {
        return toAjax(randService.updateRand(rand));
    }

    /**
     * 删除rand
     */
    @PreAuthorize("@ss.hasPermi('system:rand:remove')")
    @Log(title = "rand", businessType = BusinessType.DELETE)
	@DeleteMapping("/{randIds}")
    public AjaxResult remove(@PathVariable Long[] randIds)
    {
        return toAjax(randService.deleteRandByRandIds(randIds));
    }
}
