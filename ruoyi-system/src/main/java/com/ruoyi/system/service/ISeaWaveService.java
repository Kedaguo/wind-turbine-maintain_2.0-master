package com.ruoyi.system.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.system.domain.SeaWave;

/**
 * seaWaveService接口
 * 
 * @author JianDa
 * @date 2023-04-21
 */
public interface ISeaWaveService 
{
    /**
     * 查询seaWave
     * 
     * @param sId seaWave主键
     * @return seaWave
     */
    public SeaWave selectSeaWaveBySId(Long sId);

    public List<SeaWave> selectWeatherList(Date date);
    /**
     * 查询seaWave列表
     * 
     * @param seaWave seaWave
     * @return seaWave集合
     */
    public List<SeaWave> selectSeaWaveList(SeaWave seaWave);

    /**
     * 新增seaWave
     * 
     * @param seaWave seaWave
     * @return 结果
     */
    public int insertSeaWave(SeaWave seaWave);

    /**
     * 修改seaWave
     * 
     * @param seaWave seaWave
     * @return 结果
     */
    public int updateSeaWave(SeaWave seaWave);

    /**
     * 批量删除seaWave
     * 
     * @param sIds 需要删除的seaWave主键集合
     * @return 结果
     */
    public int deleteSeaWaveBySIds(Long[] sIds);

    /**
     * 删除seaWave信息
     * 
     * @param sId seaWave主键
     * @return 结果
     */
    public int deleteSeaWaveBySId(Long sId);
}
