package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.dto.ArrangementDto;
import com.ruoyi.system.domain.vo.ArrangementVo;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private TaskStudentMapper taskStudentMapper;
    
    @Resource
    private ArrangementRepairMapper arrangementRepairMapper;

    @Resource
    private ArrangementOperatorMapper arrangementOperatorMapper;

    @Resource
    private SysUserMapper sysUserMapper;

//    @Resource
//    private RyTask ryTask;

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

    @Override
    public List<Arrangement> selectArrangementList (Arrangement arrangement) {
        return arrangementMapper.selectArrangementList(arrangement);
    }

    /**
     * 查询arrangement列表
     * 
     * @param arrangement arrangement
     * @return arrangement
     */
    @Override
    public List<ArrangementDto> selectArrangementDtoList(Arrangement arrangement)
    {
        List<Arrangement> arrangements = arrangementMapper.selectArrangementList(arrangement);
        ArrayList<ArrangementDto> arrangementDtos = new ArrayList<>();
        for (Arrangement arrangement1:arrangements){
            ArrangementDto arrangementDto = new ArrangementDto();
            Port port = portMapper.selectPortByPId(arrangement1.getpId());
            SysUser sysUser = sysUserMapper.selectUserById(arrangement1.getUserId());
            arrangementDto.setPPortName(port.getpPortname());
            arrangementDto.setUserName(sysUser.getUserName());
            BeanUtils.copyProperties(arrangement1,arrangementDto);
            arrangementDtos.add(arrangementDto);
        }
        return arrangementDtos;
    }

    /**
     * 新增arrangement
     * 
     * @param
     * @return 结果
     */
    @Override
    public int insertArrangement(ArrangementVo arrangementVo) throws Exception
    {

        //更新出海作业船舶初始位置
//        Port port = portMapper.selectPortByPId(arrangementVo.getpId());
//        String startLocation = port.getpLongitude()+","+ port.getpLatitude();
        //出海作业维修单数量最少为1
        List<Long> repairOrderIds = arrangementVo.getArrangementRepairIds();
        RepairOrder repairOrder = repairOrderMapper.selectRepairOrderByRId(repairOrderIds.get(repairOrderIds.size() - 1));
        TurbineWind turbineWind = turbineWindMapper.selectTurbineWindByTId(repairOrder.gettId());
        //本次出海作业最终位置
//        String endLocation = turbineWind.gettLongitude()+","+turbineWind.gettLatitude();

        Arrangement arrangement = new Arrangement();
        BeanUtils.copyProperties(arrangementVo,arrangement);
//        arrangement.setStartLocation(startLocation);
//        arrangement.setEndLocation(endLocation);
        arrangement.setaState(0);
        //毫秒级时间戳用来查询刚刚插入的数据
        arrangement.setaCreateTime(DateUtils.getMillsTime());
        //添加路径规划
        int flag = arrangementMapper.insertArrangement(arrangement);
        QueryWrapper<Arrangement> arrangementQueryWrapper = new QueryWrapper<>();
        arrangementQueryWrapper.eq("task_id",arrangement.getTaskId())
                .eq("user_id",arrangement.getUserId())
                .eq("a_create_time",arrangement.getaCreateTime());
        Arrangement arrangement1 = arrangementMapper.selectOne(arrangementQueryWrapper);
        arrangementVo.setaId(arrangement1.getaId());
        //规划关联船舶
        arrangementRelevanceBoat(arrangementVo);
        //路径规划关联维修工
        arrangementRelevanceOperator(arrangementVo);
        //路径规划关联维修单
        arrangementRelevanceRepairOrder(arrangementVo);
        return flag;
    }
    //路径规划关联维修单
    public void arrangementRelevanceRepairOrder(ArrangementVo arrangementVo){
        List<Long> tIds = arrangementVo.getArrangementRepairIds();
        Long count = 1l;
        for (Long tId:tIds){
            QueryWrapper<RepairOrder> repairOrderQueryWrapper = new QueryWrapper<>();
            repairOrderQueryWrapper.eq("t_id",tId)
                    .eq("task_id",arrangementVo.getTaskId())
                    .eq("user_id",arrangementVo.getUserId())
                    .eq("r_state",0);
            RepairOrder repairOrder = repairOrderMapper.selectOne(repairOrderQueryWrapper);
            //sequence  代表维修顺序
            ArrangementRepair arrangementRepair = new ArrangementRepair();
            arrangementRepair.setaId(arrangementVo.getaId());
            arrangementRepair.setrId(repairOrder.getrId());
            arrangementRepair.setSequence(count++);
            int insertFlag = arrangementRepairMapper.insertArrangementRepair(arrangementRepair);
            repairOrder.setrState(1);
            int flag = repairOrderMapper.updateRepairOrder(repairOrder);
        }
    }
    //路径规划关联维修人员
    public AjaxResult arrangementRelevanceOperator(ArrangementVo arrangementVo){
        //查询分配员工
        TaskOperator taskOperator = new TaskOperator();
        taskOperator.setoState(1l);
        BeanUtils.copyProperties(arrangementVo,taskOperator);
        List<TaskOperator> taskOperators = taskOperatorMapper.selectTaskOperatorList(taskOperator);
        //判断现有员工数量是否满足出海作业所需员工数量
        for (int i=0;i<arrangementVo.getoNum();i++){
            TaskOperator taskOperator1 = new TaskOperator();
            taskOperator1.setoState(2l);
            BeanUtils.copyProperties(taskOperators.get(i),taskOperator1);
            int taskOperatorUpdateFlag = taskOperatorMapper.updateTaskOperator(taskOperator1);
            //规范关联维修工
            ArrangementOperator arrangementOperator = new ArrangementOperator();
            arrangementOperator.setaId(arrangementVo.getaId());
            arrangementOperator.setoId(taskOperators.get(i).getoId());
            int flag = arrangementOperatorMapper.insertArrangementOperator(arrangementOperator);

        }
        return AjaxResult.success();

    }
    //路径规划关联船舶
    public AjaxResult arrangementRelevanceBoat(ArrangementVo arrangementVo){
        //查询现有船舶
//        TaskBoat taskBoat = new TaskBoat();
//        taskBoat.setTaskId(arrangementVo.getTaskId());
//        taskBoat.setUserId(arrangementVo.getUserId());
//        taskBoat.setbState(0);
//        taskBoat.setbWorkState(0);
//        List<TaskBoat> taskBoats = taskBoatMapper.selectTaskBoatList(taskBoat);
//        List<Long> bIds = taskBoats.stream().map(TaskBoat::getbId).collect(Collectors.toList());
//        for (Long bId:bIds){
//            Boat boat = boatMapper.selectBoatByBId(bId);
//            if (boat.getbType() == arrangementVo.getbType()){
//                //规划关联船舶
//                ArrangementBoat arrangementBoat = new ArrangementBoat();
//                arrangementBoat.setaId(arrangementBoat.getaId());
//                arrangementBoat.setbId(bId);
//                arrangementBoatMapper.insertArrangementBoat(arrangementBoat);
//                //更新船舶状态
//                taskBoat.setbId(bId);
//                taskBoat.setbState(1);
//                taskBoat.setbWorkState(1);
//            }
//
//        }
        TaskBoat taskBoat = new TaskBoat();
        BeanUtils.copyProperties(arrangementVo,taskBoat);
        //等待条件允许作业
        taskBoat.setbState(1);
        int flag = taskBoatMapper.updateTaskBoat(taskBoat);

        return AjaxResult.success();
    }
    //

    /**
     * 修改arrangement
     * 
     * @param
     * @return 结果
     */
    @Override
    public int updateArrangement(ArrangementVo arrangementVo)
    {

        if (arrangementVo.getArrangementRepairIds()!=null){
            ArrangementRepair arrangementRepair = new ArrangementRepair();
            arrangementRepair.setaId(arrangementVo.getaId());
            List<ArrangementRepair> arrangementRepairs = arrangementRepairMapper.selectArrangementRepairList(arrangementRepair);
            Integer sequence = arrangementVo.getArrangementRepairIds().size();
            for (Long rId:arrangementVo.getArrangementRepairIds()){
                ArrangementRepair arrangementRepair1 = new ArrangementRepair();
                arrangementRepair1.setaId(arrangementVo.getaId());
                arrangementRepair1.setrId(rId);
                sequence++;
                arrangementRepair1.setSequence(sequence.longValue());
                arrangementRepairMapper.insertArrangementRepair(arrangementRepair1);
            }
        }
        Arrangement arrangement = new Arrangement();
        BeanUtils.copyProperties(arrangementVo,arrangement);
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
