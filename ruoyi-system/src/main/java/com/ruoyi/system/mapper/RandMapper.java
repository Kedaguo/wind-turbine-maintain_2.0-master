package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Rand;

/**
 * randMapper接口
 * 
 * @author JianDa
 * @date 2023-05-06
 */
public interface RandMapper 
{
    /**
     * 查询rand
     * 
     * @param randId rand主键
     * @return rand
     */
    public Rand selectRandByRandId(Long randId);

    /**
     * 查询rand列表
     * 
     * @param rand rand
     * @return rand集合
     */
    public List<Rand> selectRandList(Rand rand);

    /**
     * 新增rand
     * 
     * @param rand rand
     * @return 结果
     */
    public int insertRand(Rand rand);

    /**
     * 修改rand
     * 
     * @param rand rand
     * @return 结果
     */
    public int updateRand(Rand rand);

    /**
     * 删除rand
     * 
     * @param randId rand主键
     * @return 结果
     */
    public int deleteRandByRandId(Long randId);

    /**
     * 批量删除rand
     * 
     * @param randIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRandByRandIds(Long[] randIds);
}
