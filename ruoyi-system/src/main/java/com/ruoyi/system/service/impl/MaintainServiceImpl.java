package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Random;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IMaintainService;

import javax.annotation.Resource;

/**
 * maintainService业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
@Service
public class MaintainServiceImpl implements IMaintainService
{
    @Autowired
    private MaintainMapper maintainMapper;

    @Resource
    private TurbineFaultMapper turbineFaultMapper;

    @Resource
    private FaultMapper faultMapper;

    @Resource
    private TurbineWindMapper turbineWindMapper;

    @Resource
    private TurbineMaintainMapper turbineMaintainMapper;
    /**
     * 查询maintain
     * 
     * @param mId maintain主键
     * @return maintain
     */
    @Override
    public Maintain selectMaintainByMId(Long mId)
    {
        return maintainMapper.selectMaintainByMId(mId);
    }

    /**
     * 查询maintain列表
     * 
     * @param maintain maintain
     * @return maintain
     */
    @Override
    public List<Maintain> selectMaintainList(Maintain maintain)
    {
        return maintainMapper.selectMaintainList(maintain);
    }

    /**
     * 新增maintain
     * 
     * @param maintain maintain
     * @return 结果
     */
    @Override
    public int insertMaintain(Maintain maintain)
    {
        return maintainMapper.insertMaintain(maintain);
    }

    /**
     * 修改maintain
     * 
     * @param maintain maintain
     * @return 结果
     */
    @Override
    public int updateMaintain(Maintain maintain)
    {
        return maintainMapper.updateMaintain(maintain);
    }

    /**
     * 批量删除maintain
     * 
     * @param mIds 需要删除的maintain主键
     * @return 结果
     */
    @Override
    public int deleteMaintainByMIds(Long[] mIds)
    {
        return maintainMapper.deleteMaintainByMIds(mIds);
    }

    /**
     * 删除maintain信息
     * 
     * @param mId maintain主键
     * @return 结果
     */
    @Override
    public int deleteMaintainByMId(Long mId)
    {
        return maintainMapper.deleteMaintainByMId(mId);
    }

    @Override
    public AjaxResult generateFault (List<TurbineFault> turbineFaults) {
        TurbineWind turbineWind = new TurbineWind();
        for (TurbineFault turbineFault:turbineFaults){
            turbineFaultMapper.insertTurbineFault(turbineFault);
            turbineWind.settId(turbineFault.gettId());
//            turbineWind.settFaultState(1l);
            turbineWindMapper.updateTurbineWind(turbineWind);
        }
        return AjaxResult.success();
    }

//    @Override
//    public AjaxResult generateFault (List<TurbineFault> turbineFaults) {
////        TurbineWind turbineWind = new TurbineWind();
////        TurbineFault turbineFault = new TurbineFault();
////        List<Fault> faults = faultMapper.selectFaultList(null);
////        Random random = new Random();
////        long l = random.nextInt(faults.size());
////        for (Long id:ids){
////            turbineFault.settId(id);
////            turbineFault.setfId(l);
////            turbineFaultMapper.insertTurbineFault(turbineFault);
////            turbineWind.settId(id);
////            turbineWind.settFaultState(1L);
////            turbineWindMapper.updateTurbineWind(turbineWind);
////        }
////        TurbineFault turbineFault1 = turbineFaultMapper.selectTurbineFaultByTId(ids.get(ids.size()-1));
////        if (turbineFault1 != null){
////            return AjaxResult.success();
////        }else {
////            return AjaxResult.error("102","随机生成风机故障信息失败");
////        }
//    }

    @Override
    public AjaxResult generateMaintain (List<TurbineMaintain> maintains) {
        TurbineWind turbineWind = new TurbineWind();
        for (TurbineMaintain turbineMaintain:maintains){
            turbineMaintainMapper.insertTurbineMaintain(turbineMaintain);
//            turbineWind.settMaintainState(3l);
            turbineWind.settId(turbineMaintain.gettId());
            turbineWindMapper.updateTurbineWind(turbineWind);
        }
        return AjaxResult.success();
    }
}
