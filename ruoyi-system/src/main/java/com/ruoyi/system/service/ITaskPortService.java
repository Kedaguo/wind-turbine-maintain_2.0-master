package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Port;
import com.ruoyi.system.domain.TaskPort;
import com.ruoyi.system.domain.dto.TaskPortDto;

/**
 * taskPortService接口
 * 
 * @author JianDa
 * @date 2023-04-03
 */
public interface ITaskPortService 
{
    /**
     * 查询taskPort
     * 
     * @param pId taskPort主键
     * @return taskPort
     */
    public TaskPort selectTaskPortByPId(Long pId);


    public List<Port> selectTaskPortListByUser(Long taskId, Long userId);
    /**
     * 查询taskPort列表
     * 
     * @param taskPort taskPort
     * @return taskPort集合
     */
    public List<TaskPortDto> selectTaskPortList(TaskPort taskPort);

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
     * 批量删除taskPort
     * 
     * @param pIds 需要删除的taskPort主键集合
     * @return 结果
     */
    public int deleteTaskPortByPIds(Long[] pIds);

    /**
     * 删除taskPort信息
     * 
     * @param pId taskPort主键
     * @return 结果
     */
    public int deleteTaskPortByPId(Long pId);
}
