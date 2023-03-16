package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TurbineFault;

/**
 * turbineFaultService接口
 * 
 * @author 赵建达
 * @date 2023-03-14
 */
public interface ITurbineFaultService 
{
    /**
     * 查询turbineFault
     * 
     * @param tId turbineFault主键
     * @return turbineFault
     */
    public TurbineFault selectTurbineFaultByTId(Long tId);

    /**
     * 查询turbineFault列表
     * 
     * @param turbineFault turbineFault
     * @return turbineFault集合
     */
    public List<TurbineFault> selectTurbineFaultList(TurbineFault turbineFault);

    /**
     * 新增turbineFault
     * 
     * @param turbineFault turbineFault
     * @return 结果
     */
    public int insertTurbineFault(TurbineFault turbineFault);

    /**
     * 修改turbineFault
     * 
     * @param turbineFault turbineFault
     * @return 结果
     */
    public int updateTurbineFault(TurbineFault turbineFault);

    /**
     * 批量删除turbineFault
     * 
     * @param tIds 需要删除的turbineFault主键集合
     * @return 结果
     */
    public int deleteTurbineFaultByTIds(Long[] tIds);

    /**
     * 删除turbineFault信息
     * 
     * @param tId turbineFault主键
     * @return 结果
     */
    public int deleteTurbineFaultByTId(Long tId);
}
