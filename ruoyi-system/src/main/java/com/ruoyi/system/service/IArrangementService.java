package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Arrangement;
import com.ruoyi.system.domain.vo.ArrangementVo;

/**
 * arrangementService接口
 * 
 * @author JianDa
 * @date 2023-04-14
 */
public interface IArrangementService 
{
    /**
     * 查询arrangement
     * 
     * @param aId arrangement主键
     * @return arrangement
     */
    public Arrangement selectArrangementByAId(Long aId);

    /**
     * 查询arrangement列表
     * 
     * @param arrangement arrangement
     * @return arrangement集合
     */
    public List<Arrangement> selectArrangementList(Arrangement arrangement);

    /**
     * 新增arrangement
     * 
     * @param arrangement arrangement
     * @return 结果
     */
    public int insertArrangement(ArrangementVo arrangementVo);

    /**
     * 修改arrangement
     * 
     * @param arrangement arrangement
     * @return 结果
     */
    public int updateArrangement(Arrangement arrangement);

    /**
     * 批量删除arrangement
     * 
     * @param aIds 需要删除的arrangement主键集合
     * @return 结果
     */
    public int deleteArrangementByAIds(Long[] aIds);

    /**
     * 删除arrangement信息
     * 
     * @param aId arrangement主键
     * @return 结果
     */
    public int deleteArrangementByAId(Long aId);
}
