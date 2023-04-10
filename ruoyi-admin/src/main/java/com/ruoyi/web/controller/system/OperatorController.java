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
import com.ruoyi.system.domain.Operator;
import com.ruoyi.system.service.IOperatorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * operatorController
 * 
 * @author JianDa
 * @date 2023-04-07
 */
@RestController
@RequestMapping("/system/operator")
public class OperatorController extends BaseController
{
    @Autowired
    private IOperatorService operatorService;

    /**
     * 查询operator列表
     */
    @PreAuthorize("@ss.hasPermi('system:operator:list')")
    @GetMapping("/list")
    public TableDataInfo list(Operator operator)
    {
        startPage();
        List<Operator> list = operatorService.selectOperatorList(operator);
        return getDataTable(list);
    }

    /**
     * 导出operator列表
     */
    @PreAuthorize("@ss.hasPermi('system:operator:export')")
    @Log(title = "operator", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Operator operator)
    {
        List<Operator> list = operatorService.selectOperatorList(operator);
        ExcelUtil<Operator> util = new ExcelUtil<Operator>(Operator.class);
        util.exportExcel(response, list, "operator数据");
    }

    /**
     * 获取operator详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:operator:query')")
    @GetMapping(value = "/{oId}")
    public AjaxResult getInfo(@PathVariable("oId") Long oId)
    {
        return success(operatorService.selectOperatorByOId(oId));
    }

    /**
     * 新增operator
     */
    @PreAuthorize("@ss.hasPermi('system:operator:add')")
    @Log(title = "operator", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Operator operator)
    {
        return toAjax(operatorService.insertOperator(operator));
    }

    /**
     * 修改operator
     */
    @PreAuthorize("@ss.hasPermi('system:operator:edit')")
    @Log(title = "operator", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Operator operator)
    {
        return toAjax(operatorService.updateOperator(operator));
    }

    /**
     * 删除operator
     */
    @PreAuthorize("@ss.hasPermi('system:operator:remove')")
    @Log(title = "operator", businessType = BusinessType.DELETE)
	@DeleteMapping("/{oIds}")
    public AjaxResult remove(@PathVariable Long[] oIds)
    {
        return toAjax(operatorService.deleteOperatorByOIds(oIds));
    }
}
