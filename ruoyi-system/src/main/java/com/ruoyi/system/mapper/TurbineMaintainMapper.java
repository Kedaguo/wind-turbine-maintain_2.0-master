package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TurbineMaintain;

/**
 * turbineMaintainMapper接口
 * 
 * @author 赵建达
 * @date 2023-03-14
 */
public interface TurbineMaintainMapper 
{
    /**
     * 查询turbineMaintain
     * 
     * @param tId turbineMaintain主键
     * @return turbineMaintain
     */
    public TurbineMaintain selectTurbineMaintainByTId(Long tId);

    /**
     * 查询turbineMaintain列表
     * 
     * @param turbineMaintain turbineMaintain
     * @return turbineMaintain集合
     */
    public List<TurbineMaintain> selectTurbineMaintainList(TurbineMaintain turbineMaintain);

    /**
     * 新增turbineMaintain
     * 
     * @param turbineMaintain turbineMaintain
     * @return 结果
     */
    public int insertTurbineMaintain(TurbineMaintain turbineMaintain);

    /**
     * 修改turbineMaintain
     * 
     * @param turbineMaintain turbineMaintain
     * @return 结果
     */
    public int updateTurbineMaintain(TurbineMaintain turbineMaintain);

    /**
     * 删除turbineMaintain
     * 
     * @param tId turbineMaintain主键
     * @return 结果
     */
    public int deleteTurbineMaintainByTId(Long tId);

    /**
     * 批量删除turbineMaintain
     * 
     * @param tIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTurbineMaintainByTIds(Long[] tIds);
}
