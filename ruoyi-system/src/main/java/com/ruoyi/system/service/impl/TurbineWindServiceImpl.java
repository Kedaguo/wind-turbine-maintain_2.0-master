package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TurbineWindMapper;
import com.ruoyi.system.domain.TurbineWind;
import com.ruoyi.system.service.ITurbineWindService;

import javax.annotation.Resource;

/**
 * windTurbineService业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-15
 */
@Service
public class TurbineWindServiceImpl implements ITurbineWindService 
{
    @Autowired
    private TurbineWindMapper turbineWindMapper;



    /**
     * 查询windTurbine
     * 
     * @param tId windTurbine主键
     * @return windTurbine
     */
    @Override
    public TurbineWind selectTurbineWindByTId(Long tId)
    {
        return turbineWindMapper.selectTurbineWindByTId(tId);
    }

    /**
     * 查询windTurbine列表
     * 
     * @param turbineWind windTurbine
     * @return windTurbine
     */
    @Override
    public List<TurbineWind> selectTurbineWindList(TurbineWind turbineWind)
    {
        return turbineWindMapper.selectTurbineWindList(turbineWind);
    }

    /**
     * 新增windTurbine
     *
     * @return 结果
     */
    @Override
    public int insertTurbineWind(TurbineWind turbineWind)
    {
        turbineWind.setCreateTime(DateUtils.getNowDate());
//        turbineWind.settFaultState(0l);
//        turbineWind.settMaintainState(0l);
        return turbineWindMapper.insertTurbineWind(turbineWind);
    }

    /**
     * 修改windTurbine
     * 
     * @param turbineWind windTurbine
     * @return 结果
     */
    @Override
    public int updateTurbineWind(TurbineWind turbineWind)
    {
        turbineWind.setUpdateTime(DateUtils.getNowDate());
        return turbineWindMapper.updateTurbineWind(turbineWind);
    }

    /**
     * 批量删除windTurbine
     * 
     * @param tIds 需要删除的windTurbine主键
     * @return 结果
     */
    @Override
    public int deleteTurbineWindByTIds(Long[] tIds)
    {
        return turbineWindMapper.deleteTurbineWindByTIds(tIds);
    }

    /**
     * 删除windTurbine信息
     * 
     * @param tId windTurbine主键
     * @return 结果
     */
    @Override
    public int deleteTurbineWindByTId(Long tId)
    {
        return turbineWindMapper.deleteTurbineWindByTId(tId);
    }
}
