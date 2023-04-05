package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.dto.RepairOrderFaultDto;
import com.ruoyi.system.domain.dto.RepairOrderMaintainDto;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IRepairOrderService;

import javax.annotation.Resource;

/**
 * repairOrderService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-04
 */
@Service
public class RepairOrderServiceImpl implements IRepairOrderService 
{
    @Resource
    private RepairOrderMapper repairOrderMapper;

    @Resource
    private TurbineWindMapper turbineWindMapper;

    @Resource
    private TaskTurbineMapper taskTurbineMapper;

    @Resource
    private FaultMapper faultMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private MaintainMapper maintainMapper;

    /**
     * 查询repairOrder
     * 
     * @param rId repairOrder主键
     * @return repairOrder
     */
    @Override
    public RepairOrder selectRepairOrderByRId(Long rId)
    {
        return repairOrderMapper.selectRepairOrderByRId(rId);
    }

    @Override
    public List<RepairOrderMaintainDto> selectRepairOrderMaintainList (Long taskId, Long userId) {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setUserId(userId);
        repairOrder.setTaskId(taskId);
        repairOrder.setrType(2);
        List<RepairOrder> repairOrders = repairOrderMapper.selectRepairOrderList(repairOrder);
        ArrayList<RepairOrderMaintainDto> repairOrderMaintainDtos = new ArrayList<>();
        for (RepairOrder repairOrder1:repairOrders){
            RepairOrderMaintainDto repairOrderMaintainDto = new RepairOrderMaintainDto();
            repairOrderMaintainDto.setRCreateTime(repairOrder1.getrCreateTime());
            repairOrderMaintainDto.setRState(repairOrder1.getrState());
            repairOrderMaintainDto.setUserName(sysUserMapper.selectUserById(userId).getUserName());
            TurbineWind turbineWind = turbineWindMapper.selectTurbineWindByTId(repairOrder1.gettId());
            BeanUtils.copyProperties(turbineWind,repairOrderMaintainDto);
            QueryWrapper<TaskTurbine> taskTurbineQueryWrapper = new QueryWrapper<>();
            taskTurbineQueryWrapper.eq("task_id",repairOrder1.getTaskId())
                    .eq("user_id",repairOrder1.getUserId())
                    .eq("t_id",repairOrder1.gettId());
            TaskTurbine taskTurbine = taskTurbineMapper.selectOne(taskTurbineQueryWrapper);
            Maintain maintain = maintainMapper.selectMaintainByMId(taskTurbine.getmId());
            BeanUtils.copyProperties(maintain,repairOrderMaintainDto);
            repairOrderMaintainDtos.add(repairOrderMaintainDto);
        }
        return repairOrderMaintainDtos;
    }

    @Override
    public List<RepairOrderFaultDto> selectRepairOrderFaultListByStudent (Long taskId, Long userId) {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setTaskId(taskId);
        repairOrder.setUserId(userId);
        repairOrder.setrType(1);
        List<RepairOrder> repairOrders = repairOrderMapper.selectRepairOrderList(repairOrder);
        ArrayList<RepairOrderFaultDto> repairOrderFaultDtos = new ArrayList<>();
        for (RepairOrder repairOrder1:repairOrders){
            RepairOrderFaultDto repairOrderDto = new RepairOrderFaultDto();
            repairOrderDto.setRCreateTime(repairOrder1.getrCreateTime());
            repairOrderDto.setRState(repairOrder1.getrState());
            repairOrderDto.setUserName(sysUserMapper.selectUserById(repairOrder1.getUserId()).getUserName());
            TurbineWind turbineWind = turbineWindMapper.selectTurbineWindByTId(repairOrder1.gettId());
            BeanUtils.copyProperties(turbineWind,repairOrderDto);
            QueryWrapper<TaskTurbine> taskTurbineQueryWrapper = new QueryWrapper<>();
            taskTurbineQueryWrapper.eq("task_id",repairOrder1.getTaskId())
                    .eq("user_id",repairOrder1.getUserId())
                    .eq("t_id",repairOrder1.gettId());
            TaskTurbine taskTurbine = taskTurbineMapper.selectOne(taskTurbineQueryWrapper);
            Fault fault = faultMapper.selectFaultByFId(taskTurbine.getfId());
            BeanUtils.copyProperties(fault,repairOrderDto);
            repairOrderFaultDtos.add(repairOrderDto);

        }
        return repairOrderFaultDtos;
    }

    /**
     * 查询repairOrder列表
     * 
     * @param repairOrder repairOrder
     * @return repairOrder
     */
    @Override
    public List<RepairOrder> selectRepairOrderList(RepairOrder repairOrder)
    {
        return repairOrderMapper.selectRepairOrderList(repairOrder);
    }

    /**
     * 新增repairOrder
     * 
     * @param repairOrder repairOrder
     * @return 结果
     */
    @Override
    public int insertRepairOrder(RepairOrder repairOrder)
    {
        return repairOrderMapper.insertRepairOrder(repairOrder);
    }

    /**
     * 修改repairOrder
     * 
     * @param repairOrder repairOrder
     * @return 结果
     */
    @Override
    public int updateRepairOrder(RepairOrder repairOrder)
    {
        return repairOrderMapper.updateRepairOrder(repairOrder);
    }

    /**
     * 批量删除repairOrder
     * 
     * @param rIds 需要删除的repairOrder主键
     * @return 结果
     */
    @Override
    public int deleteRepairOrderByRIds(Long[] rIds)
    {
        return repairOrderMapper.deleteRepairOrderByRIds(rIds);
    }

    /**
     * 删除repairOrder信息
     * 
     * @param rId repairOrder主键
     * @return 结果
     */
    @Override
    public int deleteRepairOrderByRId(Long rId)
    {
        return repairOrderMapper.deleteRepairOrderByRId(rId);
    }
}
