package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.vo.ArrangementVo;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IArrangementService;

import javax.annotation.Resource;

/**
 * arrangementService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-14
 */
@Service
public class ArrangementServiceImpl implements IArrangementService 
{
    @Resource
    private ArrangementMapper arrangementMapper;

    @Resource
    private ArrangementBoatMapper arrangementBoatMapper;
    @Resource
    private PortMapper portMapper;

    @Resource
    private RepairOrderMapper repairOrderMapper;

    @Resource
    private TurbineWindMapper turbineWindMapper;

    @Resource
    private TaskBoatMapper taskBoatMapper;

    @Resource
    private BoatMapper boatMapper;

    @Resource
    private TaskOperatorMapper taskOperatorMapper;

    /**
     * 查询arrangement
     * 
     * @param aId arrangement主键
     * @return arrangement
     */
    @Override
    public Arrangement selectArrangementByAId(Long aId)
    {
        return arrangementMapper.selectArrangementByAId(aId);
    }

    /**
     * 查询arrangement列表
     * 
     * @param arrangement arrangement
     * @return arrangement
     */
    @Override
    public List<Arrangement> selectArrangementList(Arrangement arrangement)
    {
        return arrangementMapper.selectArrangementList(arrangement);
    }

    /**
     * 新增arrangement
     * 
     * @param
     * @return 结果
     */
    @Override
    public int insertArrangement(ArrangementVo arrangementVo)
    {

        Port port = portMapper.selectPortByPId(arrangementVo.getpId());
        String startLocation = port.getpLongitude()+","+ port.getpLatitude();
        List<Long> repairOrderIds = arrangementVo.getArrangementRepairIds();
        RepairOrder repairOrder = repairOrderMapper.selectRepairOrderByRId(repairOrderIds.get(repairOrderIds.size() - 1));
        TurbineWind turbineWind = turbineWindMapper.selectTurbineWindByTId(repairOrder.gettId());
        String endLocation = turbineWind.gettLongitude()+","+turbineWind.gettLatitude();
        Arrangement arrangement = new Arrangement();
        BeanUtils.copyProperties(arrangementVo,arrangement);
        arrangement.setStartLocation(startLocation);
        arrangement.setEndLocation(endLocation);
        int i = arrangementMapper.insertArrangement(arrangement);
        //规划关联船舶
        arrangementRelevanceBoat(arrangementVo);
        //路径规划关联维修工
        arrangementRelevanceOperator(arrangementVo);
        //路径规划关联维修单
//        arrangementRelevanceRepairOrder(arrangementVo);
        return 1;
    }
//    //路径规划关联维修单
//    public AjaxResult arrangementRelevanceRepairOrder(ArrangementVo arrangementVo){
//
//
//    }
    //路径规划关联维修人员
    public AjaxResult arrangementRelevanceOperator(ArrangementVo arrangementVo){
        TaskOperator taskOperator = new TaskOperator();
        taskOperator.setoState(1l);
        BeanUtils.copyProperties(arrangementVo,taskOperator);
        List<TaskOperator> taskOperators = taskOperatorMapper.selectTaskOperatorList(taskOperator);
        if (taskOperators.size()>=arrangementVo.getoNum()){
            for (int i=0;i<arrangementVo.getoNum();i++){
                TaskOperator taskOperator1 = new TaskOperator();
                taskOperator1.setoState(2l);
                BeanUtils.copyProperties(taskOperators.get(i),taskOperator1);
                int taskOperatorUpdateFlag = taskOperatorMapper.updateTaskOperator(taskOperator1);
            }
        }else{
            return AjaxResult.error("维修人员数量不足",401);
        }
        return AjaxResult.success();

    }
    //路径规划关联船舶
    public AjaxResult arrangementRelevanceBoat(ArrangementVo arrangementVo){
        TaskBoat taskBoat = new TaskBoat();
        taskBoat.setTaskId(arrangementVo.getTaskId());
        taskBoat.setUserId(arrangementVo.getUserId());
        List<TaskBoat> taskBoats = taskBoatMapper.selectTaskBoatList(taskBoat);
        ArrayList<TaskBoat> taskBoats1 = new ArrayList<>();
        ArrangementBoat arrangementBoat = new ArrangementBoat();
        for (TaskBoat taskBoat1:taskBoats){
            Boat boat = boatMapper.selectBoatByBId(taskBoat1.getbId());
            if (boat.getbType() == arrangementVo.getbType()){
                arrangementBoat.setaId(arrangementVo.getaId());
                taskBoats1.add(taskBoat1);
            }
        }
        if (taskBoats1.size() >= arrangementVo.getbNum()){
            for (int i=0;i<arrangementVo.getbNum();i++){
                TaskBoat taskBoat1 = new TaskBoat();
                BeanUtils.copyProperties(taskBoats1.get(i),taskBoat1);
                //出海作业
                taskBoat1.setbState(1);
                //去风场；返回风场
                taskBoat1.setbWorkState(2);
                int taskBoatUpFlag = taskBoatMapper.updateTaskBoat(taskBoat1);
                arrangementBoat.setbId(taskBoats1.get(i).getbId());
                //分配船舶
                int insertArrangementBoatFlag = arrangementBoatMapper.insertArrangementBoat(arrangementBoat);
            }
        }else {
            return AjaxResult.error("船舶数量不足，不可出海作业",401);
        }
        return AjaxResult.success();
    }
    //

    /**
     * 修改arrangement
     * 
     * @param arrangement arrangement
     * @return 结果
     */
    @Override
    public int updateArrangement(Arrangement arrangement)
    {
        return arrangementMapper.updateArrangement(arrangement);
    }

    /**
     * 批量删除arrangement
     * 
     * @param aIds 需要删除的arrangement主键
     * @return 结果
     */
    @Override
    public int deleteArrangementByAIds(Long[] aIds)
    {
        return arrangementMapper.deleteArrangementByAIds(aIds);
    }

    /**
     * 删除arrangement信息
     * 
     * @param aId arrangement主键
     * @return 结果
     */
    @Override
    public int deleteArrangementByAId(Long aId)
    {
        return arrangementMapper.deleteArrangementByAId(aId);
    }
}
