package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysUserGroup;

/**
 * 用户和小组绑定Service接口
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
public interface ISysUserGroupService 
{
    /**
     * 查询用户和小组绑定
     * 
     * @param userId 用户和小组绑定主键
     * @return 用户和小组绑定
     */
    public SysUserGroup selectSysUserGroupByUserId(Long userId);

    /**
     * 查询用户和小组绑定列表
     * 
     * @param sysUserGroup 用户和小组绑定
     * @return 用户和小组绑定集合
     */
    public List<SysUserGroup> selectSysUserGroupList(SysUserGroup sysUserGroup);

    /**
     * 新增用户和小组绑定
     * 
     * @param sysUserGroup 用户和小组绑定
     * @return 结果
     */
    public int insertSysUserGroup(SysUserGroup sysUserGroup);

    /**
     * 修改用户和小组绑定
     * 
     * @param sysUserGroup 用户和小组绑定
     * @return 结果
     */
    public int updateSysUserGroup(SysUserGroup sysUserGroup);

    /**
     * 批量删除用户和小组绑定
     * 
     * @param userIds 需要删除的用户和小组绑定主键集合
     * @return 结果
     */
    public int deleteSysUserGroupByUserIds(Long[] userIds);

    /**
     * 删除用户和小组绑定信息
     * 
     * @param userId 用户和小组绑定主键
     * @return 结果
     */
    public int deleteSysUserGroupByUserId(Long userId);
}
