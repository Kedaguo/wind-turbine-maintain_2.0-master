package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysGroupTurbineMapper;
import com.ruoyi.system.domain.SysGroupTurbine;
import com.ruoyi.system.service.ISysGroupTurbineService;

/**
 * 小组分配风机Service业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@Service
public class SysGroupTurbineServiceImpl implements ISysGroupTurbineService 
{
    @Autowired
    private SysGroupTurbineMapper sysGroupTurbineMapper;

    /**
     * 查询小组分配风机
     * 
     * @param groupId 小组分配风机主键
     * @return 小组分配风机
     */
    @Override
    public SysGroupTurbine selectSysGroupTurbineByGroupId(Long groupId)
    {
        return sysGroupTurbineMapper.selectSysGroupTurbineByGroupId(groupId);
    }

    /**
     * 查询小组分配风机列表
     * 
     * @param sysGroupTurbine 小组分配风机
     * @return 小组分配风机
     */
    @Override
    public List<SysGroupTurbine> selectSysGroupTurbineList(SysGroupTurbine sysGroupTurbine)
    {
        return sysGroupTurbineMapper.selectSysGroupTurbineList(sysGroupTurbine);
    }

    /**
     * 新增小组分配风机
     * 
     * @param sysGroupTurbine 小组分配风机
     * @return 结果
     */
    @Override
    public int insertSysGroupTurbine(SysGroupTurbine sysGroupTurbine)
    {
        return sysGroupTurbineMapper.insertSysGroupTurbine(sysGroupTurbine);
    }

    /**
     * 修改小组分配风机
     * 
     * @param sysGroupTurbine 小组分配风机
     * @return 结果
     */
    @Override
    public int updateSysGroupTurbine(SysGroupTurbine sysGroupTurbine)
    {
        return sysGroupTurbineMapper.updateSysGroupTurbine(sysGroupTurbine);
    }

    /**
     * 批量删除小组分配风机
     * 
     * @param groupIds 需要删除的小组分配风机主键
     * @return 结果
     */
    @Override
    public int deleteSysGroupTurbineByGroupIds(Long[] groupIds)
    {
        return sysGroupTurbineMapper.deleteSysGroupTurbineByGroupIds(groupIds);
    }

    /**
     * 删除小组分配风机信息
     * 
     * @param groupId 小组分配风机主键
     * @return 结果
     */
    @Override
    public int deleteSysGroupTurbineByGroupId(Long groupId)
    {
        return sysGroupTurbineMapper.deleteSysGroupTurbineByGroupId(groupId);
    }
}
