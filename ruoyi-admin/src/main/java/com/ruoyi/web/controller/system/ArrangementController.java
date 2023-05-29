package com.ruoyi.web.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.vo.ArrangementVo;
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
import com.ruoyi.system.domain.Arrangement;
import com.ruoyi.system.service.IArrangementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * arrangementController
 * 
 * @author JianDa
 * @date 2023-04-14
 */
@RestController
@RequestMapping("/system/arrangement")
public class ArrangementController extends BaseController
{
    @Autowired
    private IArrangementService arrangementService;

    @Resource
    private TokenService tokenService;
    /**
     * 查询arrangement列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Arrangement arrangement)
    {
        startPage();
        List<Arrangement> list = arrangementService.selectArrangementList(arrangement);
        return getDataTable(list);
    }

    //出海作业结束

    /**
     * 导出arrangement列表
     */
    @PreAuthorize("@ss.hasPermi('system:arrangement:export')")
    @Log(title = "arrangement", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Arrangement arrangement)
    {
        List<Arrangement> list = arrangementService.selectArrangementList(arrangement);
        ExcelUtil<Arrangement> util = new ExcelUtil<Arrangement>(Arrangement.class);
        util.exportExcel(response, list, "arrangement数据");
    }

    /**
     * 获取arrangement详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:arrangement:query')")
    @GetMapping(value = "/{aId}")
    public AjaxResult getInfo(@PathVariable("aId") Long aId)
    {
        return success(arrangementService.selectArrangementByAId(aId));
    }

    /**
     * 新增arrangement
     */
    @PreAuthorize("@ss.hasPermi('system:arrangement:add')")
    @Log(title = "arrangement", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrangementVo arrangementVo, HttpServletRequest request) throws Exception
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        arrangementVo.setUserId(loginUser.getUserId());
        return toAjax(arrangementService.insertArrangement(arrangementVo));
    }

    /**
     * 修改arrangement
     */
    @PreAuthorize("@ss.hasPermi('system:arrangement:edit')")
    @Log(title = "arrangement", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArrangementVo arrangementVo)
    {
        return toAjax(arrangementService.updateArrangement(arrangementVo));
    }

    /**
     * 删除arrangement
     */
    @PreAuthorize("@ss.hasPermi('system:arrangement:remove')")
    @Log(title = "arrangement", businessType = BusinessType.DELETE)
	@DeleteMapping("/{aIds}")
    public AjaxResult remove(@PathVariable Long[] aIds)
    {
        return toAjax(arrangementService.deleteArrangementByAIds(aIds));
    }
}
