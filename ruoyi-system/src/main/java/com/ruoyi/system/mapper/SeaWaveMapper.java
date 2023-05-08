package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SeaWave;

/**
 * seaWaveMapper接口
 * 
 * @author JianDa
 * @date 2023-04-21
 */
public interface SeaWaveMapper extends BaseMapper<SeaWave>
{
    /**
     * 查询seaWave
     * 
     * @param sId seaWave主键
     * @return seaWave
     */
    public SeaWave selectSeaWaveBySId(Long sId);

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
     * 删除seaWave
     * 
     * @param sId seaWave主键
     * @return 结果
     */
    public int deleteSeaWaveBySId(Long sId);

    /**
     * 批量删除seaWave
     * 
     * @param sIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeaWaveBySIds(Long[] sIds);
}
