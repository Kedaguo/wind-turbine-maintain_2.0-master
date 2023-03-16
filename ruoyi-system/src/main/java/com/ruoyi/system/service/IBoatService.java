package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Boat;

/**
 * boatService接口
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
public interface IBoatService 
{
    /**
     * 查询boat
     * 
     * @param bId boat主键
     * @return boat
     */
    public Boat selectBoatByBId(Long bId);

    /**
     * 查询boat列表
     * 
     * @param boat boat
     * @return boat集合
     */
    public List<Boat> selectBoatList(Boat boat);

    /**
     * 新增boat
     * 
     * @param boat boat
     * @return 结果
     */
    public int insertBoat(Boat boat);

    /**
     * 修改boat
     * 
     * @param boat boat
     * @return 结果
     */
    public int updateBoat(Boat boat);

    /**
     * 批量删除boat
     * 
     * @param bIds 需要删除的boat主键集合
     * @return 结果
     */
    public int deleteBoatByBIds(Long[] bIds);

    /**
     * 删除boat信息
     * 
     * @param bId boat主键
     * @return 结果
     */
    public int deleteBoatByBId(Long bId);
}
