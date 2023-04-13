package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ArrangementMapper;
import com.ruoyi.system.domain.Arrangement;
import com.ruoyi.system.service.IArrangementService;

/**
 * arrangementService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-13
 */
@Service
public class ArrangementServiceImpl implements IArrangementService 
{
    @Autowired
    private ArrangementMapper arrangementMapper;

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
     * @param arrangement arrangement
     * @return 结果
     */
    @Override
    public int insertArrangement(Arrangement arrangement)
    {





        return arrangementMapper.insertArrangement(arrangement);
    }

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
