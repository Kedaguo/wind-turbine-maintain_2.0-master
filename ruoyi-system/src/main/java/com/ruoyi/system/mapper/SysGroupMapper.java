package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysGroup;

/**
 * 小组管理Mapper接口
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
public interface SysGroupMapper extends BaseMapper<SysGroup>
{
    /**
     * 查询小组管理
     * 
     * @param groupId 小组管理主键
     * @return 小组管理
     */
    public SysGroup selectSysGroupByGroupId(Long groupId);

    /**
     * 查询小组管理列表
     * 
     * @param sysGroup 小组管理
     * @return 小组管理集合
     */
    public List<SysGroup> selectSysGroupList(SysGroup sysGroup);

    /**
     * 新增小组管理
     * 
     * @param sysGroup 小组管理
     * @return 结果
     */
    public int insertSysGroup(SysGroup sysGroup);

    /**
     * 修改小组管理
     * 
     * @param sysGroup 小组管理
     * @return 结果
     */
    public int updateSysGroup(SysGroup sysGroup);

    /**
     * 删除小组管理
     * 
     * @param groupId 小组管理主键
     * @return 结果
     */
    public int deleteSysGroupByGroupId(Long groupId);

    /**
     * 批量删除小组管理
     * 
     * @param groupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysGroupByGroupIds(Long[] groupIds);
}
