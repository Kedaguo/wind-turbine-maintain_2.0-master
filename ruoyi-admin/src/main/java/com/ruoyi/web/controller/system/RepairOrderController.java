package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Fault;
import com.ruoyi.system.domain.TurbineFault;
import com.ruoyi.system.domain.TurbineMaintain;
import com.ruoyi.system.service.IFaultService;
import com.ruoyi.system.service.IMaintainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 故障信息Controller
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
@Api(tags = "维修单")
@RestController
@RequestMapping("/system/repairOrder")
public class RepairOrderController extends BaseController
{
    @Autowired
    private IFaultService faultService;

    @Resource
    private IMaintainService iMaintainService;


    /*
    产生一条维修单主要根据以下逻辑：
        1 随机选一个风机然后产生维修单
        2 选中之后风机的状态是在0 未开始工作 1 停机且需要保养  2 停机不需要保养  3 工作但需要保养  4正常工作
        3 产生维修单
     */

    /*
    初始化故障信息
     */
    @ApiOperation(value = "初始化故障信息")
    @PreAuthorize(value = "@ss.hasPermi('system:turbinefault:generateFault')")
    @PostMapping(value ="/generateFault")
    public AjaxResult generateFault(@RequestBody List<TurbineFault> turbineFaults){
        return iMaintainService.generateFault(turbineFaults);
    }

/*

 */
    @ApiOperation(value = "初始化保养信息")
    @PreAuthorize(value = "@ss.hasPermi('system:turbinefault:generateFault')")
    @PostMapping(value ="/generateMaintain")
    public AjaxResult generateMaintain(@RequestBody List<TurbineMaintain> turbineMaintains){
        return iMaintainService.generateMaintain(turbineMaintains);
    }
//    @ApiOperation(value = "产生维修单")
//    @PreAuthorize("@ss.hasPermi('system:maintain:query')")
//    @GetMapping(value = "/{mId}")
//    public AjaxResult generateRepairOrder(@PathVariable("mId") Long mId)
//    {
////        return success(iMaintainService.selectMaintainByMId(mId));
//        return AjaxResult.success();
//    }


    /**
     * 获取maintain详细信息
     */
//    @ApiOperation(value = "获取保养详细信息")
//    @PreAuthorize("@ss.hasPermi('system:maintain:query')")
//    @GetMapping(value = "/{mId}")
//    public AjaxResult getInfo(@PathVariable("mId") Long mId)
//    {
//        return success(iMaintainService.selectMaintainByMId(mId));
//    }


}
