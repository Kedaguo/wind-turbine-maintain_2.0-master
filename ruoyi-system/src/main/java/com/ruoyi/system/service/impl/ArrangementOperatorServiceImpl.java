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
 * @date 2023-04-13
 */
@Service
public class ArrangementOperatorServiceImpl implements IArrangementOperatorService 
{
    @Autowired
    private ArrangementOperatorMapper arrangementOperatorMapper;

    /**
     * 查询arrangementOperator
     * 
     * @param aoId arrangementOperator主键
     * @return arrangementOperator
     */
    @Override
    public ArrangementOperator selectArrangementOperatorByAoId(Long aoId)
    {
        return arrangementOperatorMapper.selectArrangementOperatorByAoId(aoId);
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
     * @param aoIds 需要删除的arrangementOperator主键
     * @return 结果
     */
    @Override
    public int deleteArrangementOperatorByAoIds(Long[] aoIds)
    {
        return arrangementOperatorMapper.deleteArrangementOperatorByAoIds(aoIds);
    }

    /**
     * 删除arrangementOperator信息
     * 
     * @param aoId arrangementOperator主键
     * @return 结果
     */
    @Override
    public int deleteArrangementOperatorByAoId(Long aoId)
    {
        return arrangementOperatorMapper.deleteArrangementOperatorByAoId(aoId);
    }
}
