package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TaskStudent;
import com.ruoyi.system.domain.dto.TaskStudentDto;
import com.ruoyi.system.domain.dto.TaskTeacherDto;

/**
 * taskStudentService接口
 * 
 * @author JianDa
 * @date 2023-03-27
 */
public interface ITaskStudentService 
{
    /**
     * 查询taskStudent
     * 
     * @param userId taskStudent主键
     * @return taskStudent
     */
    public TaskStudent selectTaskStudentByUserId(TaskStudent taskStudent);

    /**
     * 查询taskStudent列表
     * 
     * @param taskStudent taskStudent
     * @return taskStudent集合
     */
    public List<TaskStudentDto> selectTaskStudentList(Long userId);

    /**
     * 新增taskStudent
     * 
     * @param taskStudent taskStudent
     * @return 结果
     */
    public int insertTaskStudent(TaskStudent taskStudent);

    /**
     * 修改taskStudent
     * 
     * @param taskStudent taskStudent
     * @return 结果
     */
    public int updateTaskStudent(TaskStudent taskStudent);

    /**
     * 批量删除taskStudent
     * 
     * @param userIds 需要删除的taskStudent主键集合
     * @return 结果
     */
    public int deleteTaskStudentByUserIds(Long[] userIds);

    /**
     * 删除taskStudent信息
     * 
     * @param userId taskStudent主键
     * @return 结果
     */
    public int deleteTaskStudentByUserId(Long userId);

    public List<TaskTeacherDto> selectTaskStudentByTeacher (String userName);
}
