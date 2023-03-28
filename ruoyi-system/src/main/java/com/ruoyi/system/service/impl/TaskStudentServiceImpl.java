package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.Task;
import com.ruoyi.system.domain.dto.TaskStudentDto;
import com.ruoyi.system.mapper.TaskMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskStudentMapper;
import com.ruoyi.system.domain.TaskStudent;
import com.ruoyi.system.service.ITaskStudentService;

import javax.annotation.Resource;

/**
 * taskStudentService业务层处理
 * 
 * @author JianDa
 * @date 2023-03-27
 */
@Service
public class TaskStudentServiceImpl implements ITaskStudentService 
{
    @Autowired
    private TaskStudentMapper taskStudentMapper;

    @Resource
    private TaskMapper taskMapper;

    /**
     * 查询taskStudent
     * 
     * @param userId taskStudent主键
     * @return taskStudent
     */
    @Override
    public TaskStudent selectTaskStudentByUserId(Long userId)
    {
        return taskStudentMapper.selectTaskStudentByUserId(userId);
    }

    /**
     * 查询taskStudent列表
     * 
     * @param
     * @return
     */
    @Override
    public List<TaskStudentDto> selectTaskStudentList(Long userId)
    {
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setUserId(userId);
        List<TaskStudent> taskStudents = taskStudentMapper.selectTaskStudentList(taskStudent);
        ArrayList<TaskStudentDto> taskStudentDtos = new ArrayList<>();
        for (TaskStudent taskStudent1:taskStudents){
            TaskStudentDto taskStudentDto = new TaskStudentDto();
            BeanUtils.copyProperties(taskStudentDto,taskStudent1);
            Task task = taskMapper.selectById(taskStudent1.getTaskId());
            BeanUtils.copyProperties(taskStudentDto,task);
            taskStudentDtos.add(taskStudentDto);
        }
        return taskStudentDtos;
    }

    /**
     * 新增taskStudent
     * 
     * @param taskStudent taskStudent
     * @return 结果
     */
    @Override
    public int insertTaskStudent(TaskStudent taskStudent)
    {
        return taskStudentMapper.insertTaskStudent(taskStudent);
    }

    /**
     * 修改taskStudent
     * 
     * @param taskStudent taskStudent
     * @return 结果
     */
    @Override
    public int updateTaskStudent(TaskStudent taskStudent)
    {
        return taskStudentMapper.updateTaskStudent(taskStudent);
    }

    /**
     * 批量删除taskStudent
     * 
     * @param userIds 需要删除的taskStudent主键
     * @return 结果
     */
    @Override
    public int deleteTaskStudentByUserIds(Long[] userIds)
    {
        return taskStudentMapper.deleteTaskStudentByUserIds(userIds);
    }

    /**
     * 删除taskStudent信息
     * 
     * @param userId taskStudent主键
     * @return 结果
     */
    @Override
    public int deleteTaskStudentByUserId(Long userId)
    {
        return taskStudentMapper.deleteTaskStudentByUserId(userId);
    }
}
