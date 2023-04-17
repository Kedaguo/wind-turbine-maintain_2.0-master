package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ArrangementBoat;

/**
 * arrangementBoatService接口
 * 
 * @author JianDa
 * @date 2023-04-14
 */
public interface IArrangementBoatService 
{
    /**
     * 查询arrangementBoat
     * 
     * @param aId arrangementBoat主键
     * @return arrangementBoat
     */
    public ArrangementBoat selectArrangementBoatByAId(Long aId);

    /**
     * 查询arrangementBoat列表
     * 
     * @param arrangementBoat arrangementBoat
     * @return arrangementBoat集合
     */
    public List<ArrangementBoat> selectArrangementBoatList(ArrangementBoat arrangementBoat);

    /**
     * 新增arrangementBoat
     * 
     * @param arrangementBoat arrangementBoat
     * @return 结果
     */
    public int insertArrangementBoat(ArrangementBoat arrangementBoat);

    /**
     * 修改arrangementBoat
     * 
     * @param arrangementBoat arrangementBoat
     * @return 结果
     */
    public int updateArrangementBoat(ArrangementBoat arrangementBoat);

    /**
     * 批量删除arrangementBoat
     * 
     * @param aIds 需要删除的arrangementBoat主键集合
     * @return 结果
     */
    public int deleteArrangementBoatByAIds(Long[] aIds);

    /**
     * 删除arrangementBoat信息
     * 
     * @param aId arrangementBoat主键
     * @return 结果
     */
    public int deleteArrangementBoatByAId(Long aId);
}
