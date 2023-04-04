package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.RepairOrder;

/**
 * repairOrderService接口
 * 
 * @author JianDa
 * @date 2023-04-04
 */
public interface IRepairOrderService 
{
    /**
     * 查询repairOrder
     * 
     * @param rId repairOrder主键
     * @return repairOrder
     */
    public RepairOrder selectRepairOrderByRId(Long rId);

    /**
     * 查询repairOrder列表
     * 
     * @param repairOrder repairOrder
     * @return repairOrder集合
     */
    public List<RepairOrder> selectRepairOrderList(RepairOrder repairOrder);

    /**
     * 新增repairOrder
     * 
     * @param repairOrder repairOrder
     * @return 结果
     */
    public int insertRepairOrder(RepairOrder repairOrder);

    /**
     * 修改repairOrder
     * 
     * @param repairOrder repairOrder
     * @return 结果
     */
    public int updateRepairOrder(RepairOrder repairOrder);

    /**
     * 批量删除repairOrder
     * 
     * @param rIds 需要删除的repairOrder主键集合
     * @return 结果
     */
    public int deleteRepairOrderByRIds(Long[] rIds);

    /**
     * 删除repairOrder信息
     * 
     * @param rId repairOrder主键
     * @return 结果
     */
    public int deleteRepairOrderByRId(Long rId);
}
