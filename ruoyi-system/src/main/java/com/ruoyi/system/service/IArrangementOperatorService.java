package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ArrangementOperator;

/**
 * arrangementOperatorService接口
 * 
 * @author JianDa
 * @date 2023-05-09
 */
public interface IArrangementOperatorService 
{
    /**
     * 查询arrangementOperator
     * 
     * @param aId arrangementOperator主键
     * @return arrangementOperator
     */
    public ArrangementOperator selectArrangementOperatorByAId(Long aId);

    /**
     * 查询arrangementOperator列表
     * 
     * @param arrangementOperator arrangementOperator
     * @return arrangementOperator集合
     */
    public List<ArrangementOperator> selectArrangementOperatorList(ArrangementOperator arrangementOperator);

    /**
     * 新增arrangementOperator
     * 
     * @param arrangementOperator arrangementOperator
     * @return 结果
     */
    public int insertArrangementOperator(ArrangementOperator arrangementOperator);

    /**
     * 修改arrangementOperator
     * 
     * @param arrangementOperator arrangementOperator
     * @return 结果
     */
    public int updateArrangementOperator(ArrangementOperator arrangementOperator);

    /**
     * 批量删除arrangementOperator
     * 
     * @param aIds 需要删除的arrangementOperator主键集合
     * @return 结果
     */
    public int deleteArrangementOperatorByAIds(Long[] aIds);

    /**
     * 删除arrangementOperator信息
     * 
     * @param aId arrangementOperator主键
     * @return 结果
     */
    public int deleteArrangementOperatorByAId(Long aId);
}
