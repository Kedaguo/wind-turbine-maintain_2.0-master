package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.TaskStudent;

/**
 * taskStudentMapper接口
 * 
 * @author JianDa
 * @date 2023-03-27
 */
public interface TaskStudentMapper extends BaseMapper<TaskStudent>
{
    /**
     * 查询taskStudent
     * 
     * @param userId taskStudent主键
     * @return taskStudent
     */
    public TaskStudent selectTaskStudentByUserId(Long userId);

    /**
     * 查询taskStudent列表
     * 
     * @param taskStudent taskStudent
     * @return taskStudent集合
     */
    public List<TaskStudent> selectTaskStudentList(TaskStudent taskStudent);

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
     * 删除taskStudent
     * 
     * @param userId taskStudent主键
     * @return 结果
     */
    public int deleteTaskStudentByUserId(Long userId);

    /**
     * 批量删除taskStudent
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskStudentByUserIds(Long[] userIds);
}
