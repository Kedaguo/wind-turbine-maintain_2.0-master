package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysGroupMapper;
import com.ruoyi.system.domain.SysGroup;
import com.ruoyi.system.service.ISysGroupService;

/**
 * 小组管理Service业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@Service
public class SysGroupServiceImpl implements ISysGroupService 
{
    @Autowired
    private SysGroupMapper sysGroupMapper;

    /**
     * 查询小组管理
     * 
     * @param groupId 小组管理主键
     * @return 小组管理
     */
    @Override
    public SysGroup selectSysGroupByGroupId(Long groupId)
    {
        return sysGroupMapper.selectSysGroupByGroupId(groupId);
    }

    /**
     * 查询小组管理列表
     * 
     * @param sysGroup 小组管理
     * @return 小组管理
     */
    @Override
    public List<SysGroup> selectSysGroupList(SysGroup sysGroup)
    {
        return sysGroupMapper.selectSysGroupList(sysGroup);
    }

    /**
     * 新增小组管理
     * 
     * @param sysGroup 小组管理  分配小组
     * @return 结果
     */
    @Override
    public int insertSysGroup(SysGroup sysGroup)
    {


        return sysGroupMapper.insertSysGroup(sysGroup);
    }

    /**
     * 修改小组管理
     * 
     * @param sysGroup 小组管理
     * @return 结果
     */
    @Override
    public int updateSysGroup(SysGroup sysGroup)
    {
        return sysGroupMapper.updateSysGroup(sysGroup);
    }

    /**
     * 批量删除小组管理
     * 
     * @param groupIds 需要删除的小组管理主键
     * @return 结果
     */
    @Override
    public int deleteSysGroupByGroupIds(Long[] groupIds)
    {
        return sysGroupMapper.deleteSysGroupByGroupIds(groupIds);
    }

    /**
     * 删除小组管理信息
     * 
     * @param groupId 小组管理主键
     * @return 结果
     */
    @Override
    public int deleteSysGroupByGroupId(Long groupId)
    {
        return sysGroupMapper.deleteSysGroupByGroupId(groupId);
    }
}
