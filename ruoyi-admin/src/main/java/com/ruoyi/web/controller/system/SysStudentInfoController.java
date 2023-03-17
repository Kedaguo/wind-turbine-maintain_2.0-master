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
import com.ruoyi.system.domain.SysStudentInfo;
import com.ruoyi.system.service.ISysStudentInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生信息管理Controller
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/system/studentInfo")
public class SysStudentInfoController extends BaseController
{
    @Autowired
    private ISysStudentInfoService sysStudentInfoService;

    /**
     * 查询学生信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:studentInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStudentInfo sysStudentInfo)
    {
        startPage();
        List<SysStudentInfo> list = sysStudentInfoService.selectSysStudentInfoList(sysStudentInfo);
        return getDataTable(list);
    }

    /**
     * 导出学生信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:studentInfo:export')")
    @Log(title = "学生信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysStudentInfo sysStudentInfo)
    {
        List<SysStudentInfo> list = sysStudentInfoService.selectSysStudentInfoList(sysStudentInfo);
        ExcelUtil<SysStudentInfo> util = new ExcelUtil<SysStudentInfo>(SysStudentInfo.class);
        util.exportExcel(response, list, "学生信息管理数据");
    }

    /**
     * 获取学生信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:studentInfo:query')")
    @GetMapping(value = "/{studentInfoId}")
    public AjaxResult getInfo(@PathVariable("studentInfoId") Long studentInfoId)
    {
        return success(sysStudentInfoService.selectSysStudentInfoByStudentInfoId(studentInfoId));
    }

    /**
     * 新增学生信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:studentInfo:add')")
    @Log(title = "学生信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStudentInfo sysStudentInfo)
    {
        return toAjax(sysStudentInfoService.insertSysStudentInfo(sysStudentInfo));
    }

    /**
     * 修改学生信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:studentInfo:edit')")
    @Log(title = "学生信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStudentInfo sysStudentInfo)
    {
        return toAjax(sysStudentInfoService.updateSysStudentInfo(sysStudentInfo));
    }

    /**
     * 删除学生信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:studentInfo:remove')")
    @Log(title = "学生信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentInfoIds}")
    public AjaxResult remove(@PathVariable Long[] studentInfoIds)
    {
        return toAjax(sysStudentInfoService.deleteSysStudentInfoByStudentInfoIds(studentInfoIds));
    }
}
