package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.TaskPort;

/**
 * taskPortMapper接口
 * 
 * @author JianDa
 * @date 2023-04-03
 */
public interface TaskPortMapper extends BaseMapper<TaskPort>
{
    /**
     * 查询taskPort
     * 
     * @param pId taskPort主键
     * @return taskPort
     */
    public TaskPort selectTaskPortByPId(Long pId);

    /**
     * 查询taskPort列表
     * 
     * @param taskPort taskPort
     * @return taskPort集合
     */
    public List<TaskPort> selectTaskPortList(TaskPort taskPort);

    /**
     * 新增taskPort
     * 
     * @param taskPort taskPort
     * @return 结果
     */
    public int insertTaskPort(TaskPort taskPort);

    /**
     * 修改taskPort
     * 
     * @param taskPort taskPort
     * @return 结果
     */
    public int updateTaskPort(TaskPort taskPort);

    /**
     * 删除taskPort
     * 
     * @param pId taskPort主键
     * @return 结果
     */
    public int deleteTaskPortByPId(Long pId);

    /**
     * 批量删除taskPort
     * 
     * @param pIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskPortByPIds(Long[] pIds);
}
