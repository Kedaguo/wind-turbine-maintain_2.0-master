package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.Port;
import com.ruoyi.system.mapper.PortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskPortMapper;
import com.ruoyi.system.domain.TaskPort;
import com.ruoyi.system.service.ITaskPortService;

import javax.annotation.Resource;

/**
 * taskPortService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-03
 */
@Service
public class TaskPortServiceImpl implements ITaskPortService 
{
    @Autowired
    private TaskPortMapper taskPortMapper;

    @Resource
    private PortMapper portMapper;

    /**
     * 查询taskPort
     * 
     * @param pId taskPort主键
     * @return taskPort
     */
    @Override
    public TaskPort selectTaskPortByPId(Long pId)
    {
        return taskPortMapper.selectTaskPortByPId(pId);
    }

    @Override
    public List<Port> selectTaskPortListByUser (Long taskId, Long userId) {
        TaskPort taskPort = new TaskPort();
        taskPort.setTaskId(taskId);
        taskPort.setUserId(userId);
        List<TaskPort> taskPorts = taskPortMapper.selectTaskPortList(taskPort);
        List<Long> pIds = taskPorts.stream().map(TaskPort::getpId).collect(Collectors.toList());
        return portMapper.selectBatchIds(pIds);
    }

    /**
     * 查询taskPort列表
     * 
     * @param taskPort taskPort
     * @return taskPort
     */
    @Override
    public List<TaskPort> selectTaskPortList(TaskPort taskPort)
    {
        return taskPortMapper.selectTaskPortList(taskPort);
    }

    /**
     * 新增taskPort
     * 
     * @param taskPort taskPort
     * @return 结果
     */
    @Override
    public int insertTaskPort(TaskPort taskPort)
    {
        return taskPortMapper.insertTaskPort(taskPort);
    }

    /**
     * 修改taskPort
     * 
     * @param taskPort taskPort
     * @return 结果
     */
    @Override
    public int updateTaskPort(TaskPort taskPort)
    {
        return taskPortMapper.updateTaskPort(taskPort);
    }

    /**
     * 批量删除taskPort
     * 
     * @param pIds 需要删除的taskPort主键
     * @return 结果
     */
    @Override
    public int deleteTaskPortByPIds(Long[] pIds)
    {
        return taskPortMapper.deleteTaskPortByPIds(pIds);
    }

    /**
     * 删除taskPort信息
     * 
     * @param pId taskPort主键
     * @return 结果
     */
    @Override
    public int deleteTaskPortByPId(Long pId)
    {
        return taskPortMapper.deleteTaskPortByPId(pId);
    }
}
