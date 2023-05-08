package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.ArrangementRepair;

/**
 * arrangementRepairMapper接口
 * 
 * @author JianDa
 * @date 2023-04-18
 */
public interface ArrangementRepairMapper extends BaseMapper<ArrangementRepair>
{
    /**
     * 查询arrangementRepair
     * 
     * @param aId arrangementRepair主键
     * @return arrangementRepair
     */
    public ArrangementRepair selectArrangementRepairByAId(Long aId);

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
     * 删除arrangementRepair
     * 
     * @param aId arrangementRepair主键
     * @return 结果
     */
    public int deleteArrangementRepairByAId(Long aId);

    /**
     * 批量删除arrangementRepair
     * 
     * @param aIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArrangementRepairByAIds(Long[] aIds);
}
