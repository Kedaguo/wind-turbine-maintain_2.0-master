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
import com.ruoyi.system.domain.Port;
import com.ruoyi.system.service.IPortService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 港口信息Controller
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/system/port")
public class PortController extends BaseController
{
    @Autowired
    private IPortService portService;

    /**
     * 查询港口信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:port:list')")
    @GetMapping("/list")
    public TableDataInfo list(Port port)
    {
        startPage();
        List<Port> list = portService.selectPortList(port);
        return getDataTable(list);
    }

    /**
     * 导出港口信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:port:export')")
    @Log(title = "港口信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Port port)
    {
        List<Port> list = portService.selectPortList(port);
        ExcelUtil<Port> util = new ExcelUtil<Port>(Port.class);
        util.exportExcel(response, list, "港口信息数据");
    }

    /**
     * 获取港口信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:port:query')")
    @GetMapping(value = "/{pId}")
    public AjaxResult getInfo(@PathVariable("pId") Long pId)
    {
        return success(portService.selectPortByPId(pId));
    }

    /**
     * 新增港口信息
     */
    @PreAuthorize("@ss.hasPermi('system:port:add')")
    @Log(title = "港口信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Port port)
    {
        return toAjax(portService.insertPort(port));
    }

    /**
     * 修改港口信息
     */
    @PreAuthorize("@ss.hasPermi('system:port:edit')")
    @Log(title = "港口信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Port port)
    {
        return toAjax(portService.updatePort(port));
    }

    /**
     * 删除港口信息
     */
    @PreAuthorize("@ss.hasPermi('system:port:remove')")
    @Log(title = "港口信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pIds}")
    public AjaxResult remove(@PathVariable Long[] pIds)
    {
        return toAjax(portService.deletePortByPIds(pIds));
    }
}
