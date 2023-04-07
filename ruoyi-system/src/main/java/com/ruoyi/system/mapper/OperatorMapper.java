package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.Operator;

/**
 * operatorMapper接口
 * 
 * @author JianDa
 * @date 2023-04-07
 */
public interface OperatorMapper extends BaseMapper<Operator>
{
    /**
     * 查询operator
     * 
     * @param oId operator主键
     * @return operator
     */
    public Operator selectOperatorByOId(Long oId);

    /**
     * 查询operator列表
     * 
     * @param operator operator
     * @return operator集合
     */
    public List<Operator> selectOperatorList(Operator operator);

    /**
     * 新增operator
     * 
     * @param operator operator
     * @return 结果
     */
    public int insertOperator(Operator operator);

    /**
     * 修改operator
     * 
     * @param operator operator
     * @return 结果
     */
    public int updateOperator(Operator operator);

    /**
     * 删除operator
     * 
     * @param oId operator主键
     * @return 结果
     */
    public int deleteOperatorByOId(Long oId);

    /**
     * 批量删除operator
     * 
     * @param oIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperatorByOIds(Long[] oIds);
}
