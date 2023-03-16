package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TurbineWind;
import com.ruoyi.system.domain.vo.TurbineWindVo;

/**
 * windTurbineService接口
 * 
 * @author 赵建达
 * @date 2023-03-15
 */
public interface ITurbineWindService 
{
    /**
     * 查询windTurbine
     * 
     * @param tId windTurbine主键
     * @return windTurbine
     */
    public TurbineWind selectTurbineWindByTId(Long tId);

    /**
     * 查询windTurbine列表
     * 
     * @param turbineWind windTurbine
     * @return windTurbine集合
     */
    public List<TurbineWind> selectTurbineWindList(TurbineWind turbineWind);

    /**
     * 新增windTurbine
     * 
     * @param turbineWind windTurbine
     * @return 结果
     */
    public int insertTurbineWind(TurbineWind turbineWind);

    /**
     * 修改windTurbine
     * 
     * @param turbineWind windTurbine
     * @return 结果
     */
    public int updateTurbineWind(TurbineWind turbineWind);

    /**
     * 批量删除windTurbine
     * 
     * @param tIds 需要删除的windTurbine主键集合
     * @return 结果
     */
    public int deleteTurbineWindByTIds(Long[] tIds);

    /**
     * 删除windTurbine信息
     * 
     * @param tId windTurbine主键
     * @return 结果
     */
    public int deleteTurbineWindByTId(Long tId);
}
