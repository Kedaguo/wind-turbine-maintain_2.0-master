package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.Operator;
import com.ruoyi.system.domain.dto.TaskOperatorDto;
import com.ruoyi.system.mapper.OperatorMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskOperatorMapper;
import com.ruoyi.system.domain.TaskOperator;
import com.ruoyi.system.service.ITaskOperatorService;

import javax.annotation.Resource;

/**
 * taskOperatorService业务层处理
 * 
 * @author JianDa
 * @date 2023-04-07
 */
@Service
public class TaskOperatorServiceImpl implements ITaskOperatorService 
{
    @Autowired
    private TaskOperatorMapper taskOperatorMapper;

    @Resource
    private OperatorMapper operatorMapper;
    /**
     * 查询taskOperator
     * 
     * @param oId taskOperator主键
     * @return taskOperator
     */
    @Override
    public TaskOperator selectTaskOperatorByOId(Long oId)
    {
        return taskOperatorMapper.selectTaskOperatorByOId(oId);
    }

    /**
     * 查询taskOperator列表
     * 
     * @param taskOperator taskOperator
     * @return taskOperator
     */
    @Override
    public List<TaskOperator> selectTaskOperatorList(TaskOperator taskOperator)
    {
        return taskOperatorMapper.selectTaskOperatorList(taskOperator);
    }

    @Override
    public List<TaskOperatorDto> selectTaskOperatorByUser (Long taskId, Long userId) {
        TaskOperator taskOperator = new TaskOperator();
        taskOperator.setTaskId(taskId);
        taskOperator.setUserId(userId);
        List<TaskOperator> taskOperators = taskOperatorMapper.selectTaskOperatorList(taskOperator);
        ArrayList<TaskOperatorDto> taskOperatorDtos = new ArrayList<>();
        for (TaskOperator taskOperator1:taskOperators){
            Operator operator = operatorMapper.selectOperatorByOId(taskOperator1.getoId());
            TaskOperatorDto taskOperatorDto = new TaskOperatorDto();
            BeanUtils.copyProperties(operator,taskOperatorDto);
            BeanUtils.copyProperties(taskOperator1,taskOperatorDto);
            taskOperatorDtos.add(taskOperatorDto);
        }
        return taskOperatorDtos;
    }

    /**
     * 新增taskOperator
     * 
     * @param taskOperator taskOperator
     * @return 结果
     */
    @Override
    public int insertTaskOperator(TaskOperator taskOperator)
    {
        return taskOperatorMapper.insertTaskOperator(taskOperator);
    }

    /**
     * 修改taskOperator
     * 
     * @param taskOperator taskOperator
     * @return 结果
     */
    @Override
    public int updateTaskOperator(TaskOperator taskOperator)
    {
        return taskOperatorMapper.updateTaskOperator(taskOperator);
    }

    /**
     * 批量删除taskOperator
     * 
     * @param oIds 需要删除的taskOperator主键
     * @return 结果
     */
    @Override
    public int deleteTaskOperatorByOIds(Long[] oIds)
    {
        return taskOperatorMapper.deleteTaskOperatorByOIds(oIds);
    }

    /**
     * 删除taskOperator信息
     * 
     * @param oId taskOperator主键
     * @return 结果
     */
    @Override
    public int deleteTaskOperatorByOId(Long oId)
    {
        return taskOperatorMapper.deleteTaskOperatorByOId(oId);
    }
}
