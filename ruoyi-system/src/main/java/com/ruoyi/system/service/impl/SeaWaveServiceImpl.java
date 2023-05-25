package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SeaWaveMapper;
import com.ruoyi.system.domain.SeaWave;
import com.ruoyi.system.service.ISeaWaveService;

import javax.annotation.Resource;

/**
 * seaWaveService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-21
 */
@Service
public class SeaWaveServiceImpl implements ISeaWaveService
{
    @Resource
    private SeaWaveMapper seaWaveMapper;

    /**
     * 查询seaWave
     * 
     * @param sId seaWave主键
     * @return seaWave
     */
    @Override
    public SeaWave selectSeaWaveBySId(Long sId)
    {
        return seaWaveMapper.selectSeaWaveBySId(sId);
    }

    @Override
    public List<SeaWave> selectWeatherList (Date date) {
        QueryWrapper<SeaWave> seaWaveQueryWrapper = new QueryWrapper<>();
        seaWaveQueryWrapper.ge("st_time",date)
                .last("LIMIT 24");
        return seaWaveMapper.selectList(seaWaveQueryWrapper);
    }

    /**
     * 查询seaWave列表
     * 
     * @param seaWave seaWave
     * @return seaWave
     */
    @Override
    public List<SeaWave> selectSeaWaveList(SeaWave seaWave)
    {
        return seaWaveMapper.selectSeaWaveList(seaWave);
    }

    /**
     * 新增seaWave
     * 
     * @param seaWave seaWave
     * @return 结果
     */
    @Override
    public int insertSeaWave(SeaWave seaWave)
    {
        return seaWaveMapper.insertSeaWave(seaWave);
    }

    /**
     * 修改seaWave
     * 
     * @param seaWave seaWave
     * @return 结果
     */
    @Override
    public int updateSeaWave(SeaWave seaWave)
    {
        return seaWaveMapper.updateSeaWave(seaWave);
    }

    /**
     * 批量删除seaWave
     * 
     * @param sIds 需要删除的seaWave主键
     * @return 结果
     */
    @Override
    public int deleteSeaWaveBySIds(Long[] sIds)
    {
        return seaWaveMapper.deleteSeaWaveBySIds(sIds);
    }

    /**
     * 删除seaWave信息
     * 
     * @param sId seaWave主键
     * @return 结果
     */
    @Override
    public int deleteSeaWaveBySId(Long sId)
    {
        return seaWaveMapper.deleteSeaWaveBySId(sId);
    }
}
