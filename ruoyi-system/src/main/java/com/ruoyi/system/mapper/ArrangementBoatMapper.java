package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ArrangementBoat;

/**
 * arrangementBoatMapper接口
 * 
 * @author JianDa
 * @date 2023-04-14
 */
public interface ArrangementBoatMapper 
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
     * 删除arrangementBoat
     * 
     * @param aId arrangementBoat主键
     * @return 结果
     */
    public int deleteArrangementBoatByAId(Long aId);

    /**
     * 批量删除arrangementBoat
     * 
     * @param aIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArrangementBoatByAIds(Long[] aIds);
}
