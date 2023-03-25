package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskPersonMapper;
import com.ruoyi.system.domain.TaskPerson;
import com.ruoyi.system.service.ITaskPersonService;

/**
 * taskPersonService业务层处理
 * 
 * @author JianDa
 * @date 2023-03-25
 */
@Service
public class TaskPersonServiceImpl implements ITaskPersonService 
{
    @Autowired
    private TaskPersonMapper taskPersonMapper;

    /**
     * 查询taskPerson
     * 
     * @param pId taskPerson主键
     * @return taskPerson
     */
    @Override
    public TaskPerson selectTaskPersonByPId(Long pId)
    {
        return taskPersonMapper.selectTaskPersonByPId(pId);
    }

    /**
     * 查询taskPerson列表
     * 
     * @param taskPerson taskPerson
     * @return taskPerson
     */
    @Override
    public List<TaskPerson> selectTaskPersonList(TaskPerson taskPerson)
    {
        return taskPersonMapper.selectTaskPersonList(taskPerson);
    }

    /**
     * 新增taskPerson
     * 
     * @param taskPerson taskPerson
     * @return 结果
     */
    @Override
    public int insertTaskPerson(TaskPerson taskPerson)
    {
        return taskPersonMapper.insertTaskPerson(taskPerson);
    }

    /**
     * 修改taskPerson
     * 
     * @param taskPerson taskPerson
     * @return 结果
     */
    @Override
    public int updateTaskPerson(TaskPerson taskPerson)
    {
        return taskPersonMapper.updateTaskPerson(taskPerson);
    }

    /**
     * 批量删除taskPerson
     * 
     * @param pIds 需要删除的taskPerson主键
     * @return 结果
     */
    @Override
    public int deleteTaskPersonByPIds(Long[] pIds)
    {
        return taskPersonMapper.deleteTaskPersonByPIds(pIds);
    }

    /**
     * 删除taskPerson信息
     * 
     * @param pId taskPerson主键
     * @return 结果
     */
    @Override
    public int deleteTaskPersonByPId(Long pId)
    {
        return taskPersonMapper.deleteTaskPersonByPId(pId);
    }
}
