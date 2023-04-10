package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OperatorMapper;
import com.ruoyi.system.domain.Operator;
import com.ruoyi.system.service.IOperatorService;

/**
 * operatorService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-07
 */
@Service
public class OperatorServiceImpl implements IOperatorService 
{
    @Autowired
    private OperatorMapper operatorMapper;

    /**
     * 查询operator
     * 
     * @param oId operator主键
     * @return operator
     */
    @Override
    public Operator selectOperatorByOId(Long oId)
    {
        return operatorMapper.selectOperatorByOId(oId);
    }

    /**
     * 查询operator列表
     * 
     * @param operator operator
     * @return operator
     */
    @Override
    public List<Operator> selectOperatorList(Operator operator)
    {
        return operatorMapper.selectOperatorList(operator);
    }

    /**
     * 新增operator
     * 
     * @param operator operator
     * @return 结果
     */
    @Override
    public int insertOperator(Operator operator)
    {
        return operatorMapper.insertOperator(operator);
    }

    /**
     * 修改operator
     * 
     * @param operator operator
     * @return 结果
     */
    @Override
    public int updateOperator(Operator operator)
    {
        return operatorMapper.updateOperator(operator);
    }

    /**
     * 批量删除operator
     * 
     * @param oIds 需要删除的operator主键
     * @return 结果
     */
    @Override
    public int deleteOperatorByOIds(Long[] oIds)
    {
        return operatorMapper.deleteOperatorByOIds(oIds);
    }

    /**
     * 删除operator信息
     * 
     * @param oId operator主键
     * @return 结果
     */
    @Override
    public int deleteOperatorByOId(Long oId)
    {
        return operatorMapper.deleteOperatorByOId(oId);
    }
}
