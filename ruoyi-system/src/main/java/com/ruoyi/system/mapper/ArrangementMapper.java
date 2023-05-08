package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.Arrangement;

/**
 * arrangementMapper接口
 * 
 * @author JianDa
 * @date 2023-04-14
 */
public interface ArrangementMapper extends BaseMapper<Arrangement>
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
    public int insertArrangement(Arrangement arrangement);

    /**
     * 修改arrangement
     * 
     * @param arrangement arrangement
     * @return 结果
     */
    public int updateArrangement(Arrangement arrangement);

    /**
     * 删除arrangement
     * 
     * @param aId arrangement主键
     * @return 结果
     */
    public int deleteArrangementByAId(Long aId);

    /**
     * 批量删除arrangement
     * 
     * @param aIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArrangementByAIds(Long[] aIds);
}
