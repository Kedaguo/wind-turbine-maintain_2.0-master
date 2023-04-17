package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ArrangementBoatMapper;
import com.ruoyi.system.domain.ArrangementBoat;
import com.ruoyi.system.service.IArrangementBoatService;

/**
 * arrangementBoatService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-14
 */
@Service
public class ArrangementBoatServiceImpl implements IArrangementBoatService 
{
    @Autowired
    private ArrangementBoatMapper arrangementBoatMapper;

    /**
     * 查询arrangementBoat
     * 
     * @param aId arrangementBoat主键
     * @return arrangementBoat
     */
    @Override
    public ArrangementBoat selectArrangementBoatByAId(Long aId)
    {
        return arrangementBoatMapper.selectArrangementBoatByAId(aId);
    }

    /**
     * 查询arrangementBoat列表
     * 
     * @param arrangementBoat arrangementBoat
     * @return arrangementBoat
     */
    @Override
    public List<ArrangementBoat> selectArrangementBoatList(ArrangementBoat arrangementBoat)
    {
        return arrangementBoatMapper.selectArrangementBoatList(arrangementBoat);
    }

    /**
     * 新增arrangementBoat
     * 
     * @param arrangementBoat arrangementBoat
     * @return 结果
     */
    @Override
    public int insertArrangementBoat(ArrangementBoat arrangementBoat)
    {
        return arrangementBoatMapper.insertArrangementBoat(arrangementBoat);
    }

    /**
     * 修改arrangementBoat
     * 
     * @param arrangementBoat arrangementBoat
     * @return 结果
     */
    @Override
    public int updateArrangementBoat(ArrangementBoat arrangementBoat)
    {
        return arrangementBoatMapper.updateArrangementBoat(arrangementBoat);
    }

    /**
     * 批量删除arrangementBoat
     * 
     * @param aIds 需要删除的arrangementBoat主键
     * @return 结果
     */
    @Override
    public int deleteArrangementBoatByAIds(Long[] aIds)
    {
        return arrangementBoatMapper.deleteArrangementBoatByAIds(aIds);
    }

    /**
     * 删除arrangementBoat信息
     * 
     * @param aId arrangementBoat主键
     * @return 结果
     */
    @Override
    public int deleteArrangementBoatByAId(Long aId)
    {
        return arrangementBoatMapper.deleteArrangementBoatByAId(aId);
    }
}
