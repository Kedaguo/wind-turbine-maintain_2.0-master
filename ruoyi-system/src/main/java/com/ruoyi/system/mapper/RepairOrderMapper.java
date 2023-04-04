package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.RepairOrder;

/**
 * repairOrderMapper接口
 * 
 * @author JianDa
 * @date 2023-04-04
 */
public interface RepairOrderMapper extends BaseMapper<RepairOrder>
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
     * 删除repairOrder
     * 
     * @param rId repairOrder主键
     * @return 结果
     */
    public int deleteRepairOrderByRId(Long rId);

    /**
     * 批量删除repairOrder
     * 
     * @param rIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRepairOrderByRIds(Long[] rIds);
}
