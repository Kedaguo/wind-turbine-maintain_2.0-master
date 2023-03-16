package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PortMapper;
import com.ruoyi.system.domain.Port;
import com.ruoyi.system.service.IPortService;

/**
 * 港口信息Service业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
@Service
public class PortServiceImpl implements IPortService 
{
    @Autowired
    private PortMapper portMapper;

    /**
     * 查询港口信息
     * 
     * @param pId 港口信息主键
     * @return 港口信息
     */
    @Override
    public Port selectPortByPId(Long pId)
    {
        return portMapper.selectPortByPId(pId);
    }

    /**
     * 查询港口信息列表
     * 
     * @param port 港口信息
     * @return 港口信息
     */
    @Override
    public List<Port> selectPortList(Port port)
    {
        return portMapper.selectPortList(port);
    }

    /**
     * 新增港口信息
     * 
     * @param port 港口信息
     * @return 结果
     */
    @Override
    public int insertPort(Port port)
    {
        return portMapper.insertPort(port);
    }

    /**
     * 修改港口信息
     * 
     * @param port 港口信息
     * @return 结果
     */
    @Override
    public int updatePort(Port port)
    {
        return portMapper.updatePort(port);
    }

    /**
     * 批量删除港口信息
     * 
     * @param pIds 需要删除的港口信息主键
     * @return 结果
     */
    @Override
    public int deletePortByPIds(Long[] pIds)
    {
        return portMapper.deletePortByPIds(pIds);
    }

    /**
     * 删除港口信息信息
     * 
     * @param pId 港口信息主键
     * @return 结果
     */
    @Override
    public int deletePortByPId(Long pId)
    {
        return portMapper.deletePortByPId(pId);
    }
}
