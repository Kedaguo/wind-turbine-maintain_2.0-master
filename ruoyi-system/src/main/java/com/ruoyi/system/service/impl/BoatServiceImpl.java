package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BoatMapper;
import com.ruoyi.system.domain.Boat;
import com.ruoyi.system.service.IBoatService;

/**
 * boatService业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
@Service
public class BoatServiceImpl implements IBoatService 
{
    @Autowired
    private BoatMapper boatMapper;

    /**
     * 查询boat
     * 
     * @param bId boat主键
     * @return boat
     */
    @Override
    public Boat selectBoatByBId(Long bId)
    {
        return boatMapper.selectBoatByBId(bId);
    }

    /**
     * 查询boat列表
     * 
     * @param boat boat
     * @return boat
     */
    @Override
    public List<Boat> selectBoatList(Boat boat)
    {
        return boatMapper.selectBoatList(boat);
    }

    /**
     * 新增boat
     * 
     * @param boat boat
     * @return 结果
     */
    @Override
    public int insertBoat(Boat boat)
    {
        return boatMapper.insertBoat(boat);
    }

    /**
     * 修改boat
     * 
     * @param boat boat
     * @return 结果
     */
    @Override
    public int updateBoat(Boat boat)
    {
        return boatMapper.updateBoat(boat);
    }

    /**
     * 批量删除boat
     * 
     * @param bIds 需要删除的boat主键
     * @return 结果
     */
    @Override
    public int deleteBoatByBIds(Long[] bIds)
    {
        return boatMapper.deleteBoatByBIds(bIds);
    }

    /**
     * 删除boat信息
     * 
     * @param bId boat主键
     * @return 结果
     */
    @Override
    public int deleteBoatByBId(Long bId)
    {
        return boatMapper.deleteBoatByBId(bId);
    }
}
