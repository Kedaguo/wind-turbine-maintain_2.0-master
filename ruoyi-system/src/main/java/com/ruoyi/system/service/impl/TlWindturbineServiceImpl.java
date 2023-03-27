package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TlWindturbineMapper;
import com.ruoyi.system.domain.TlWindturbine;
import com.ruoyi.system.service.ITlWindturbineService;

/**
 * 故障风机监控Service业务层处理
 * 
 * @author 张存
 * @date 2023-03-12
 */
@Service
public class TlWindturbineServiceImpl implements ITlWindturbineService 
{
    @Autowired
    private TlWindturbineMapper tlWindturbineMapper;

    /**
     * 查询故障风机监控
     * 
     * @param id 故障风机监控主键
     * @return 故障风机监控
     */
    @Override
    public TlWindturbine selectTlWindturbineById(Long id)
    {
        return tlWindturbineMapper.selectTlWindturbineById(id);
    }

    /**
     * 查询故障风机监控列表
     * 
     * @param tlWindturbine 故障风机监控
     * @return 故障风机监控
     */
    @Override
    public List<TlWindturbine> selectTlWindturbineList(TlWindturbine tlWindturbine)
    {
        return tlWindturbineMapper.selectTlWindturbineList(tlWindturbine);
    }

    /**
     * 新增故障风机监控
     * 
     * @param tlWindturbine 故障风机监控
     * @return 结果
     */
    @Override
    public int insertTlWindturbine(TlWindturbine tlWindturbine)
    {
        return tlWindturbineMapper.insertTlWindturbine(tlWindturbine);
    }

    /**
     * 修改故障风机监控
     * 
     * @param tlWindturbine 故障风机监控
     * @return 结果
     */
    @Override
    public int updateTlWindturbine(TlWindturbine tlWindturbine)
    {
        return tlWindturbineMapper.updateTlWindturbine(tlWindturbine);
    }

    /**
     * 批量删除故障风机监控
     * 
     * @param ids 需要删除的故障风机监控主键
     * @return 结果
     */
    @Override
    public int deleteTlWindturbineByIds(Long[] ids)
    {
        return tlWindturbineMapper.deleteTlWindturbineByIds(ids);
    }

    /**
     * 删除故障风机监控信息
     * 
     * @param id 故障风机监控主键
     * @return 结果
     */
    @Override
    public int deleteTlWindturbineById(Long id)
    {
        return tlWindturbineMapper.deleteTlWindturbineById(id);
    }
}
