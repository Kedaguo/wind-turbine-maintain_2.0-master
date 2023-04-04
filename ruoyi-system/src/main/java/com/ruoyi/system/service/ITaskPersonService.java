package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Person;
import com.ruoyi.system.domain.TaskPerson;
import com.ruoyi.system.domain.dto.TaskPersonDto;

/**
 * taskPersonService接口
 * 
 * @author JianDa
 * @date 2023-03-25
 */
public interface ITaskPersonService 
{
    /**
     * 查询taskPerson
     * 
     * @param pId taskPerson主键
     * @return taskPerson
     */
    public TaskPerson selectTaskPersonByPId(Long pId);

    public List<TaskPersonDto> selectTaskPersonByUser(Long taskId,Long userId);

    /**
     * 查询taskPerson列表
     * 
     * @param taskPerson taskPerson
     * @return taskPerson集合
     */
    public List<Person> selectTaskPersonList(TaskPerson taskPerson);

    /**
     * 新增taskPerson
     * 
     * @param taskPerson taskPerson
     * @return 结果
     */
    public int insertTaskPerson(TaskPerson taskPerson);

    /**
     * 修改taskPerson
     * 
     * @param taskPerson taskPerson
     * @return 结果
     */
    public int updateTaskPerson(TaskPerson taskPerson);

    /**
     * 批量删除taskPerson
     * 
     * @param pIds 需要删除的taskPerson主键集合
     * @return 结果
     */
    public int deleteTaskPersonByPIds(Long[] pIds);

    /**
     * 删除taskPerson信息
     * 
     * @param pId taskPerson主键
     * @return 结果
     */
    public int deleteTaskPersonByPId(Long pId);
}
