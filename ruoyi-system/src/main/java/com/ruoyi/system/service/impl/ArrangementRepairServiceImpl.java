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
 * @date 2023-04-13
 */
@Service
public class ArrangementRepairServiceImpl implements IArrangementRepairService 
{
    @Autowired
    private ArrangementRepairMapper arrangementRepairMapper;

    /**
     * 查询arrangementRepair
     * 
     * @param arId arrangementRepair主键
     * @return arrangementRepair
     */
    @Override
    public ArrangementRepair selectArrangementRepairByArId(Long arId)
    {
        return arrangementRepairMapper.selectArrangementRepairByArId(arId);
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
     * @param arIds 需要删除的arrangementRepair主键
     * @return 结果
     */
    @Override
    public int deleteArrangementRepairByArIds(Long[] arIds)
    {
        return arrangementRepairMapper.deleteArrangementRepairByArIds(arIds);
    }

    /**
     * 删除arrangementRepair信息
     * 
     * @param arId arrangementRepair主键
     * @return 结果
     */
    @Override
    public int deleteArrangementRepairByArId(Long arId)
    {
        return arrangementRepairMapper.deleteArrangementRepairByArId(arId);
    }
}
