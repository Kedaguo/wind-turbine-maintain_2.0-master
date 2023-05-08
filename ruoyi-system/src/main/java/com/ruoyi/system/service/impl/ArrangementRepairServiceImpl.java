package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ArrangementRepairMapper;
import com.ruoyi.system.domain.ArrangementRepair;
import com.ruoyi.system.service.IArrangementRepairService;

/**
 * arrangementRepairService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-18
 */
@Service
public class ArrangementRepairServiceImpl implements IArrangementRepairService 
{
    @Autowired
    private ArrangementRepairMapper arrangementRepairMapper;

    /**
     * 查询arrangementRepair
     * 
     * @param aId arrangementRepair主键
     * @return arrangementRepair
     */
    @Override
    public ArrangementRepair selectArrangementRepairByAId(Long aId)
    {
        return arrangementRepairMapper.selectArrangementRepairByAId(aId);
    }

    /**
     * 查询arrangementRepair列表
     * 
     * @param arrangementRepair arrangementRepair
     * @return arrangementRepair
     */
    @Override
    public List<ArrangementRepair> selectArrangementRepairList(ArrangementRepair arrangementRepair)
    {
        return arrangementRepairMapper.selectArrangementRepairList(arrangementRepair);
    }

    /**
     * 新增arrangementRepair
     * 
     * @param arrangementRepair arrangementRepair
     * @return 结果
     */
    @Override
    public int insertArrangementRepair(ArrangementRepair arrangementRepair)
    {
        return arrangementRepairMapper.insertArrangementRepair(arrangementRepair);
    }

    /**
     * 修改arrangementRepair
     * 
     * @param arrangementRepair arrangementRepair
     * @return 结果
     */
    @Override
    public int updateArrangementRepair(ArrangementRepair arrangementRepair)
    {
        return arrangementRepairMapper.updateArrangementRepair(arrangementRepair);
    }

    /**
     * 批量删除arrangementRepair
     * 
     * @param aIds 需要删除的arrangementRepair主键
     * @return 结果
     */
    @Override
    public int deleteArrangementRepairByAIds(Long[] aIds)
    {
        return arrangementRepairMapper.deleteArrangementRepairByAIds(aIds);
    }

    /**
     * 删除arrangementRepair信息
     * 
     * @param aId arrangementRepair主键
     * @return 结果
     */
    @Override
    public int deleteArrangementRepairByAId(Long aId)
    {
        return arrangementRepairMapper.deleteArrangementRepairByAId(aId);
    }
}
