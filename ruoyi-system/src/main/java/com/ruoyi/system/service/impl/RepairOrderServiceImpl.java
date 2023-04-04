package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RepairOrderMapper;
import com.ruoyi.system.domain.RepairOrder;
import com.ruoyi.system.service.IRepairOrderService;

/**
 * repairOrderService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-04
 */
@Service
public class RepairOrderServiceImpl implements IRepairOrderService 
{
    @Autowired
    private RepairOrderMapper repairOrderMapper;

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
