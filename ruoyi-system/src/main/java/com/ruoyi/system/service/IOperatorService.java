package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Operator;

/**
 * operatorService接口
 * 
 * @author JianDa
 * @date 2023-04-07
 */
public interface IOperatorService 
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
     * 批量删除operator
     * 
     * @param oIds 需要删除的operator主键集合
     * @return 结果
     */
    public int deleteOperatorByOIds(Long[] oIds);

    /**
     * 删除operator信息
     * 
     * @param oId operator主键
     * @return 结果
     */
    public int deleteOperatorByOId(Long oId);
}
