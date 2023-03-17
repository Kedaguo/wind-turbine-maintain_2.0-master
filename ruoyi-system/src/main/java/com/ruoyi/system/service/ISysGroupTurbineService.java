package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysGroupTurbine;

/**
 * 小组分配风机Service接口
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
public interface ISysGroupTurbineService 
{
    /**
     * 查询小组分配风机
     * 
     * @param groupId 小组分配风机主键
     * @return 小组分配风机
     */
    public SysGroupTurbine selectSysGroupTurbineByGroupId(Long groupId);

    /**
     * 查询小组分配风机列表
     * 
     * @param sysGroupTurbine 小组分配风机
     * @return 小组分配风机集合
     */
    public List<SysGroupTurbine> selectSysGroupTurbineList(SysGroupTurbine sysGroupTurbine);

    /**
     * 新增小组分配风机
     * 
     * @param sysGroupTurbine 小组分配风机
     * @return 结果
     */
    public int insertSysGroupTurbine(SysGroupTurbine sysGroupTurbine);

    /**
     * 修改小组分配风机
     * 
     * @param sysGroupTurbine 小组分配风机
     * @return 结果
     */
    public int updateSysGroupTurbine(SysGroupTurbine sysGroupTurbine);

    /**
     * 批量删除小组分配风机
     * 
     * @param groupIds 需要删除的小组分配风机主键集合
     * @return 结果
     */
    public int deleteSysGroupTurbineByGroupIds(Long[] groupIds);

    /**
     * 删除小组分配风机信息
     * 
     * @param groupId 小组分配风机主键
     * @return 结果
     */
    public int deleteSysGroupTurbineByGroupId(Long groupId);
}
