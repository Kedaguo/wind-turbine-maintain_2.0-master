package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ArrangementPort;

/**
 * arrangementBortService接口
 * 
 * @author JianDa
 * @date 2023-04-13
 */
public interface IArrangementPortService 
{
    /**
     * 查询arrangementBort
     * 
     * @param apId arrangementBort主键
     * @return arrangementBort
     */
    public ArrangementPort selectArrangementPortByApId(Long apId);

    /**
     * 查询arrangementBort列表
     * 
     * @param arrangementPort arrangementBort
     * @return arrangementBort集合
     */
    public List<ArrangementPort> selectArrangementPortList(ArrangementPort arrangementPort);

    /**
     * 新增arrangementBort
     * 
     * @param arrangementPort arrangementBort
     * @return 结果
     */
    public int insertArrangementPort(ArrangementPort arrangementPort);

    /**
     * 修改arrangementBort
     * 
     * @param arrangementPort arrangementBort
     * @return 结果
     */
    public int updateArrangementPort(ArrangementPort arrangementPort);

    /**
     * 批量删除arrangementBort
     * 
     * @param apIds 需要删除的arrangementBort主键集合
     * @return 结果
     */
    public int deleteArrangementPortByApIds(Long[] apIds);

    /**
     * 删除arrangementBort信息
     * 
     * @param apId arrangementBort主键
     * @return 结果
     */
    public int deleteArrangementPortByApId(Long apId);
}
