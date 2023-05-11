package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ArrangementPortMapper;
import com.ruoyi.system.domain.ArrangementPort;
import com.ruoyi.system.service.IArrangementPortService;

/**
 * arrangementBortService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-13
 */
@Service
public class ArrangementPortServiceImpl implements IArrangementPortService 
{
    @Autowired
    private ArrangementPortMapper arrangementPortMapper;

    /**
     * 查询arrangementBort
     * 
     * @param apId arrangementBort主键
     * @return arrangementBort
     */
    @Override
    public ArrangementPort selectArrangementPortByApId(Long apId)
    {
        return arrangementPortMapper.selectArrangementPortByApId(apId);
    }

    /**
     * 查询arrangementBort列表
     * 
     * @param arrangementPort arrangementBort
     * @return arrangementBort
     */
    @Override
    public List<ArrangementPort> selectArrangementPortList(ArrangementPort arrangementPort)
    {
        return arrangementPortMapper.selectArrangementPortList(arrangementPort);
    }

    /**
     * 新增arrangementBort
     * 
     * @param arrangementPort arrangementBort
     * @return 结果
     */
    @Override
    public int insertArrangementPort(ArrangementPort arrangementPort)
    {
        return arrangementPortMapper.insertArrangementPort(arrangementPort);
    }

    /**
     * 修改arrangementBort
     * 
     * @param arrangementPort arrangementBort
     * @return 结果
     */
    @Override
    public int updateArrangementPort(ArrangementPort arrangementPort)
    {
        return arrangementPortMapper.updateArrangementPort(arrangementPort);
    }

    /**
     * 批量删除arrangementBort
     * 
     * @param apIds 需要删除的arrangementBort主键
     * @return 结果
     */
    @Override
    public int deleteArrangementPortByApIds(Long[] apIds)
    {
        return arrangementPortMapper.deleteArrangementPortByApIds(apIds);
    }

    /**
     * 删除arrangementBort信息
     * 
     * @param apId arrangementBort主键
     * @return 结果
     */
    @Override
    public int deleteArrangementPortByApId(Long apId)
    {
        return arrangementPortMapper.deleteArrangementPortByApId(apId);
    }
}
