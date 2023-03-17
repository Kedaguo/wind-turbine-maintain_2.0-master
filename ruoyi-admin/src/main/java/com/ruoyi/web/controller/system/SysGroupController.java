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
import com.ruoyi.system.domain.SysGroup;
import com.ruoyi.system.service.ISysGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小组管理Controller
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/system/group")
public class SysGroupController extends BaseController
{
    @Autowired
    private ISysGroupService sysGroupService;

    /**
     * 查询小组管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysGroup sysGroup)
    {
        startPage();
        List<SysGroup> list = sysGroupService.selectSysGroupList(sysGroup);
        return getDataTable(list);
    }

    /**
     * 导出小组管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:export')")
    @Log(title = "小组管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysGroup sysGroup)
    {
        List<SysGroup> list = sysGroupService.selectSysGroupList(sysGroup);
        ExcelUtil<SysGroup> util = new ExcelUtil<SysGroup>(SysGroup.class);
        util.exportExcel(response, list, "小组管理数据");
    }

    /**
     * 获取小组管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId)
    {
        return success(sysGroupService.selectSysGroupByGroupId(groupId));
    }

    /**
     * 新增小组管理
     */
    @PreAuthorize("@ss.hasPermi('system:group:add')")
    @Log(title = "小组管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysGroup sysGroup)
    {
        return toAjax(sysGroupService.insertSysGroup(sysGroup));
    }

    /**
     * 修改小组管理
     */
    @PreAuthorize("@ss.hasPermi('system:group:edit')")
    @Log(title = "小组管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysGroup sysGroup)
    {
        return toAjax(sysGroupService.updateSysGroup(sysGroup));
    }

    /**
     * 删除小组管理
     */
    @PreAuthorize("@ss.hasPermi('system:group:remove')")
    @Log(title = "小组管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds)
    {
        return toAjax(sysGroupService.deleteSysGroupByGroupIds(groupIds));
    }
}
