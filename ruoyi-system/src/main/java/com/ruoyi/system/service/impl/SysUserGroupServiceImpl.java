package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserGroupMapper;
import com.ruoyi.system.domain.SysUserGroup;
import com.ruoyi.system.service.ISysUserGroupService;

/**
 * 用户和小组绑定Service业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@Service
public class SysUserGroupServiceImpl implements ISysUserGroupService 
{
    @Autowired
    private SysUserGroupMapper sysUserGroupMapper;

    /**
     * 查询用户和小组绑定
     * 
     * @param userId 用户和小组绑定主键
     * @return 用户和小组绑定
     */
    @Override
    public SysUserGroup selectSysUserGroupByUserId(Long userId)
    {
        return sysUserGroupMapper.selectSysUserGroupByUserId(userId);
    }

    /**
     * 查询用户和小组绑定列表
     * 
     * @param sysUserGroup 用户和小组绑定
     * @return 用户和小组绑定
     */
    @Override
    public List<SysUserGroup> selectSysUserGroupList(SysUserGroup sysUserGroup)
    {
        return sysUserGroupMapper.selectSysUserGroupList(sysUserGroup);
    }

    /**
     * 新增用户和小组绑定
     * 
     * @param sysUserGroup 用户和小组绑定
     * @return 结果
     */
    @Override
    public int insertSysUserGroup(SysUserGroup sysUserGroup)
    {
        return sysUserGroupMapper.insertSysUserGroup(sysUserGroup);
    }

    /**
     * 修改用户和小组绑定
     * 
     * @param sysUserGroup 用户和小组绑定
     * @return 结果
     */
    @Override
    public int updateSysUserGroup(SysUserGroup sysUserGroup)
    {
        return sysUserGroupMapper.updateSysUserGroup(sysUserGroup);
    }

    /**
     * 批量删除用户和小组绑定
     * 
     * @param userIds 需要删除的用户和小组绑定主键
     * @return 结果
     */
    @Override
    public int deleteSysUserGroupByUserIds(Long[] userIds)
    {
        return sysUserGroupMapper.deleteSysUserGroupByUserIds(userIds);
    }

    /**
     * 删除用户和小组绑定信息
     * 
     * @param userId 用户和小组绑定主键
     * @return 结果
     */
    @Override
    public int deleteSysUserGroupByUserId(Long userId)
    {
        return sysUserGroupMapper.deleteSysUserGroupByUserId(userId);
    }
}
