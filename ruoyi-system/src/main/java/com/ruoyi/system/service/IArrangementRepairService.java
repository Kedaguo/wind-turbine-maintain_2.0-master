package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ArrangementRepair;

/**
 * arrangementRepairService接口
 * 
 * @author JianDa
 * @date 2023-04-13
 */
public interface IArrangementRepairService 
{
    /**
     * 查询arrangementRepair
     * 
     * @param arId arrangementRepair主键
     * @return arrangementRepair
     */
    public ArrangementRepair selectArrangementRepairByArId(Long arId);

    /**
     * 查询arrangementRepair列表
     * 
     * @param arrangementRepair arrangementRepair
     * @return arrangementRepair集合
     */
    public List<ArrangementRepair> selectArrangementRepairList(ArrangementRepair arrangementRepair);

    /**
     * 新增arrangementRepair
     * 
     * @param arrangementRepair arrangementRepair
     * @return 结果
     */
    public int insertArrangementRepair(ArrangementRepair arrangementRepair);

    /**
     * 修改arrangementRepair
     * 
     * @param arrangementRepair arrangementRepair
     * @return 结果
     */
    public int updateArrangementRepair(ArrangementRepair arrangementRepair);

    /**
     * 批量删除arrangementRepair
     * 
     * @param arIds 需要删除的arrangementRepair主键集合
     * @return 结果
     */
    public int deleteArrangementRepairByArIds(Long[] arIds);

    /**
     * 删除arrangementRepair信息
     * 
     * @param arId arrangementRepair主键
     * @return 结果
     */
    public int deleteArrangementRepairByArId(Long arId);
}
