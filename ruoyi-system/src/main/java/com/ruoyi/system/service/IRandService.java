package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Rand;

/**
 * randService接口
 * 
 * @author JianDa
 * @date 2023-05-06
 */
public interface IRandService 
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
     *
     * @return 结果
     */
    public int insertRand();

    /**
     * 修改rand
     * 
     * @param rand rand
     * @return 结果
     */
    public int updateRand(Rand rand);

    /**
     * 批量删除rand
     * 
     * @param randIds 需要删除的rand主键集合
     * @return 结果
     */
    public int deleteRandByRandIds(Long[] randIds);

    /**
     * 删除rand信息
     * 
     * @param randId rand主键
     * @return 结果
     */
    public int deleteRandByRandId(Long randId);
}
