package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.Person;
import com.ruoyi.system.domain.dto.TaskPersonDto;
import com.ruoyi.system.mapper.PersonMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskPersonMapper;
import com.ruoyi.system.domain.TaskPerson;
import com.ruoyi.system.service.ITaskPersonService;

import javax.annotation.Resource;

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

    @Resource
    private PersonMapper personMapper;


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

    @Override
    public List<TaskPersonDto> selectTaskPersonByUser (Long taskId,Long userId) {
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setTaskId(taskId);
        taskPerson.setUserId(userId);
        List<TaskPerson> taskPeople = taskPersonMapper.selectTaskPersonList(taskPerson);
        ArrayList<TaskPersonDto> taskPersonDtos = new ArrayList<>();
        for (TaskPerson taskPerson1:taskPeople){
            Person person = personMapper.selectPersonById(taskPerson1.getpId());
            TaskPersonDto taskPersonDto = new TaskPersonDto();
            BeanUtils.copyProperties(person,taskPersonDto);
            BeanUtils.copyProperties(taskPerson1,taskPersonDto);
            taskPersonDtos.add(taskPersonDto);

        }

        return taskPersonDtos;
    }

    /**
     * 查询taskPerson列表
     * 
     * @param taskPerson taskPerson
     * @return taskPerson
     */
    @Override
    public List<Person> selectTaskPersonList(TaskPerson taskPerson)
    {
        List<TaskPerson> taskPeople = taskPersonMapper.selectTaskPersonList(taskPerson);
        List<Long> peopleIds = taskPeople.stream().map(TaskPerson::getpId).collect(Collectors.toList());
        return personMapper.selectBatchIds(peopleIds);
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
