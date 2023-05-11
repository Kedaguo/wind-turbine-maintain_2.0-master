package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ArrangementOperatorMapper;
import com.ruoyi.system.domain.ArrangementOperator;
import com.ruoyi.system.service.IArrangementOperatorService;

/**
 * arrangementOperatorService业务层处理
 * 
 * @author JianDa
 * @date 2023-05-09
 */
@Service
public class ArrangementOperatorServiceImpl implements IArrangementOperatorService 
{
    @Autowired
    private ArrangementOperatorMapper arrangementOperatorMapper;

    /**
     * 查询arrangementOperator
     * 
     * @param aId arrangementOperator主键
     * @return arrangementOperator
     */
    @Override
    public ArrangementOperator selectArrangementOperatorByAId(Long aId)
    {
        return arrangementOperatorMapper.selectArrangementOperatorByAId(aId);
    }

    /**
     * 查询arrangementOperator列表
     * 
     * @param arrangementOperator arrangementOperator
     * @return arrangementOperator
     */
    @Override
    public List<ArrangementOperator> selectArrangementOperatorList(ArrangementOperator arrangementOperator)
    {
        return arrangementOperatorMapper.selectArrangementOperatorList(arrangementOperator);
    }

    /**
     * 新增arrangementOperator
     * 
     * @param arrangementOperator arrangementOperator
     * @return 结果
     */
    @Override
    public int insertArrangementOperator(ArrangementOperator arrangementOperator)
    {
        return arrangementOperatorMapper.insertArrangementOperator(arrangementOperator);
    }

    /**
     * 修改arrangementOperator
     * 
     * @param arrangementOperator arrangementOperator
     * @return 结果
     */
    @Override
    public int updateArrangementOperator(ArrangementOperator arrangementOperator)
    {
        return arrangementOperatorMapper.updateArrangementOperator(arrangementOperator);
    }

    /**
     * 批量删除arrangementOperator
     * 
     * @param aIds 需要删除的arrangementOperator主键
     * @return 结果
     */
    @Override
    public int deleteArrangementOperatorByAIds(Long[] aIds)
    {
        return arrangementOperatorMapper.deleteArrangementOperatorByAIds(aIds);
    }

    /**
     * 删除arrangementOperator信息
     * 
     * @param aId arrangementOperator主键
     * @return 结果
     */
    @Override
    public int deleteArrangementOperatorByAId(Long aId)
    {
        return arrangementOperatorMapper.deleteArrangementOperatorByAId(aId);
    }
}
