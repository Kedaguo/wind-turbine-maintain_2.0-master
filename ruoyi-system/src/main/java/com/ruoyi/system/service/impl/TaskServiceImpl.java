package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.ITaskService;

import javax.annotation.Resource;

/**
 * taskService业务层处理
 *
 * @author 赵建达
 * @date 2023-03-24
 */
@Service
public class TaskServiceImpl implements ITaskService
{
    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskBoatMapper taskBoatMapper;

    @Resource
    private TaskPersonMapper taskPersonMapper;

    @Resource
    private TaskTurbineMapper taskTurbineMapper;

    @Resource
    private TurbineWindMapper turbineWindMapper;

    @Resource
    private PersonMapper personMapper;

    @Resource
    private BoatMapper boatMapper;

    /**
     * 查询task
     *
     * @param taskId task主键
     * @return task
     */
    @Override
    public Task selectTaskByTaskId(Long taskId)
    {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    /**
     * 查询task列表
     *
     * @param task task
     * @return task
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 教师发布任务
     *
     * @param task task
     * @return 结果
     */
    @Override
    public AjaxResult insertTask(Task task, LoginUser loginUser)
    {
        Task task1 = checkOnLyOneTask(task);

        if (task1==null){
            task.setTaskCreateTime(DateUtils.getNowDate());
            task.setTaskCreateBy(loginUser.getUsername());
            task.setTaskState(1l);
            taskMapper.insertTask(task);
            Task task2 = checkOnLyOneTask(task);
            //任务关联风机
            taskRelevanceTurbine(task2);
            //任务关联维修人员
            taskRelevancePerson(task2);
            //任务关联船舶配
            taskRelevanceBoat(task2);
            return AjaxResult.success();
        }else{
            return AjaxResult.error("此学生存在未开始的任务，不可添加新任务！！！",104);
        }
    }
    /*
    任务关联风机
     */
    public void taskRelevanceTurbine(Task task){
        List<TurbineWind> turbineWinds = turbineWindMapper.selectTurbineWindList(null);
        for (TurbineWind turbineWind:turbineWinds){
            TaskTurbine taskTurbine = new TaskTurbine();
            taskTurbine.setTaskId(task.getTaskId());
            taskTurbine.settId(turbineWind.gettId());
            taskTurbine.setfState(0);
            taskTurbine.setmState(0);
            int i = taskTurbineMapper.insertTaskTurbine(taskTurbine);
        }
    }
    /*
    任务关联维修人员
     */
    public void taskRelevancePerson(Task task){
        List<Person> people = personMapper.selectPersonList(null);
        for (Person person:people){
            TaskPerson taskPerson = new TaskPerson();
            taskPerson.setTaskId(task.getTaskId());
            taskPerson.setpId(person.getId());
            taskPerson.setpWaitNum(person.getpNum());
            taskPerson.setpWorkNum(0l);
            int i = taskPersonMapper.insertTaskPerson(taskPerson);
        }

    }
    /*
    任务关联船舶
     */
    public void taskRelevanceBoat(Task task){
        List<Boat> boats = boatMapper.selectBoatList(null);
        for (Boat boat:boats){
            TaskBoat taskBoat = new TaskBoat();
            taskBoat.setTaskId(task.getTaskId());
            taskBoat.setbId(boat.getbId());
            taskBoat.setbState(0);
            taskBoat.setbWorkState(0);
            taskBoat.setbLongitude(boat.getbLongitude());
            taskBoat.setbLatitude(boat.getbLatitude());
            int i = taskBoatMapper.insertTaskBoat(taskBoat);
        }

    }
    /*
    校验学生当且仅当一个未开始的任务，则不可添加未开始的任务
    0 当前学生未开始的任务为0   1 当前学生未开始的任务为1   other 当前学生未开始的任务错误

     */
    public Task checkOnLyOneTask(Task task){
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("user_id",task.getUserId())
                .eq("task_state",1l);
        Task task1 = taskMapper.selectOne(taskQueryWrapper);
        return task1;
    }



    /**
     * 修改task
     *
     * @param task task
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除task
     *
     * @param taskIds 需要删除的task主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskIds(Long[] taskIds)
    {
        for (Long taskId:taskIds){
            deleteTaskBoatByTaskBoatIds(taskId);
            deleteTaskPersonByTaskPersonIds(taskId);
            deleteTaskTurbineByTaskTurbineIds(taskId);
        }
        return taskMapper.deleteTaskByTaskIds(taskIds);
    }
    //
    public int deleteTaskBoatByTaskBoatIds(Long taskId){
        TaskBoat taskBoat = new TaskBoat();
        taskBoat.setTaskId(taskId);
        List<TaskBoat> taskBoats = taskBoatMapper.selectTaskBoatList(taskBoat);
        List<Long> taskBoatIds = taskBoats.stream().map(TaskBoat::getbId).collect(Collectors.toList());
        return taskBoatMapper.deleteBatchIds(taskBoatIds);
    }
    public int deleteTaskPersonByTaskPersonIds(Long taskId){
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setTaskId(taskId);
        List<TaskPerson> taskPeople = taskPersonMapper.selectTaskPersonList(taskPerson);
        List<Long> taskPeopleIds = taskPeople.stream().map(TaskPerson::getpId).collect(Collectors.toList());
        return taskPersonMapper.deleteBatchIds(taskPeopleIds);
    }
    public int deleteTaskTurbineByTaskTurbineIds(Long taskId){
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.setTaskId(taskId);
        List<TaskTurbine> taskTurbines = taskTurbineMapper.selectTaskTurbineList(taskTurbine);
        List<Long> taskTurbineIds = taskTurbines.stream().map(TaskTurbine::gettId).collect(Collectors.toList());
        return taskTurbineMapper.deleteBatchIds(taskTurbineIds);
    }
    /**
     * 删除task信息
     *
     * @param taskId task主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskId(Long taskId)
    {
        return taskMapper.deleteTaskByTaskId(taskId);
    }
}
