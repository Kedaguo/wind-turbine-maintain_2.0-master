package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.domain.dto.TaskStudentDto;
import com.ruoyi.system.domain.dto.TaskTeacher;
import com.ruoyi.system.domain.dto.TaskTeacherDto;
import com.ruoyi.system.mapper.SysUserMapper;
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

    @Resource
    private SysUserMapper sysUserMapper;
    /**
     * 查询taskStudent
     * 
     * @param userId taskStudent主键
     * @return taskStudent
     */
    @Override
    public TaskStudent selectTaskStudentByUserId(TaskStudent taskStudent)
    {
        return taskStudentMapper.selectTaskStudentByUserId(taskStudent);
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
            BeanUtils.copyProperties(taskStudent1,taskStudentDto);
            Task task = taskMapper.selectTaskByTaskId(taskStudent1.getTaskId());
            BeanUtils.copyProperties(task,taskStudentDto);
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

    @Override
    public List<TaskTeacherDto> selectTaskStudentByTeacher (String userName) {
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("task_create_by",userName);
        List<Task> tasks = taskMapper.selectList(taskQueryWrapper);
        List<TaskTeacherDto> list = new ArrayList<>();
        for (Task task:tasks){
            TaskTeacherDto taskTeacherDto = selectTaskStudentByTaskId(task);
            list.add(taskTeacherDto);
        }
        return list;
    }
    public TaskTeacherDto selectTaskStudentByTaskId(Task task){

        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(task.getTaskId());
        //遍历所有任务id相关的学生
        List<TaskStudent> taskStudents = taskStudentMapper.selectTaskStudentList(taskStudent);
        List<TaskTeacher> taskTeachers = new ArrayList<>();
        //遍历所有学生
        for (TaskStudent taskStudent1:taskStudents){
            TaskTeacher taskTeacher = new TaskTeacher();
            BeanUtils.copyProperties(task,taskTeacher);
            BeanUtils.copyProperties(taskStudent1,taskTeacher);
            SysUser sysUser = sysUserMapper.selectUserById(taskStudent1.getUserId());
            if (sysUser!=null){
                taskTeacher.setUserName(sysUser.getUserName());
                taskTeacher.setNickName(sysUser.getNickName());
            }
            taskTeachers.add(taskTeacher);
        }
        TaskTeacherDto taskTeacherDto = new TaskTeacherDto();
        if (taskTeachers.size()!=0) {
            BeanUtils.copyProperties(taskTeachers.get(0), taskTeacherDto);
            ArrayList<TaskTeacher> taskTeachers1 = new ArrayList<>();
            Integer i=0;
            for (TaskTeacher taskTeacher:taskTeachers){
                i++;
                taskTeacher.setTaskId(Long.valueOf(taskTeacher.getTaskId().toString()+i.toString()));
                taskTeachers1.add(taskTeacher);
            }
            taskTeacherDto.setChildren(taskTeachers1);
        }
        System.out.println("taskTeacherDto"+taskTeacherDto);
        return taskTeacherDto;
    }
}
