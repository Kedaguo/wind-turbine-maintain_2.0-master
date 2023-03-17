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
import com.ruoyi.system.domain.SysUserGroup;
import com.ruoyi.system.service.ISysUserGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户和小组绑定Controller
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/system/userGroup")
public class SysUserGroupController extends BaseController
{
    @Autowired
    private ISysUserGroupService sysUserGroupService;

    /**
     * 查询用户和小组绑定列表
     */
    @PreAuthorize("@ss.hasPermi('system:userGroup:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserGroup sysUserGroup)
    {
        startPage();
        List<SysUserGroup> list = sysUserGroupService.selectSysUserGroupList(sysUserGroup);
        return getDataTable(list);
    }

    /**
     * 导出用户和小组绑定列表
     */
    @PreAuthorize("@ss.hasPermi('system:userGroup:export')")
    @Log(title = "用户和小组绑定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserGroup sysUserGroup)
    {
        List<SysUserGroup> list = sysUserGroupService.selectSysUserGroupList(sysUserGroup);
        ExcelUtil<SysUserGroup> util = new ExcelUtil<SysUserGroup>(SysUserGroup.class);
        util.exportExcel(response, list, "用户和小组绑定数据");
    }

    /**
     * 获取用户和小组绑定详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userGroup:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(sysUserGroupService.selectSysUserGroupByUserId(userId));
    }

    /**
     * 新增用户和小组绑定
     */
    @PreAuthorize("@ss.hasPermi('system:userGroup:add')")
    @Log(title = "用户和小组绑定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserGroup sysUserGroup)
    {
        return toAjax(sysUserGroupService.insertSysUserGroup(sysUserGroup));
    }

    /**
     * 修改用户和小组绑定
     */
    @PreAuthorize("@ss.hasPermi('system:userGroup:edit')")
    @Log(title = "用户和小组绑定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserGroup sysUserGroup)
    {
        return toAjax(sysUserGroupService.updateSysUserGroup(sysUserGroup));
    }

    /**
     * 删除用户和小组绑定
     */
    @PreAuthorize("@ss.hasPermi('system:userGroup:remove')")
    @Log(title = "用户和小组绑定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserGroupService.deleteSysUserGroupByUserIds(userIds));
    }
}
