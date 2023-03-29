package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
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

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private TaskStudentMapper taskStudentMapper;


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

    @Override
    public List<Task> selectTaskListByTeacher (String username) {
        Task task = new Task();
        task.setTaskCreateBy(username);
        return taskMapper.selectTaskList(task);

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
        Date nowDate = DateUtils.getNowDate();
        task.setTaskCreateTime(nowDate);
        task.setTaskCreateBy(loginUser.getUsername());
        int i = taskMapper.insert(task);
        String taskCreateBy = task.getTaskCreateBy();
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("task_create_time",nowDate)
                .eq("task_create_by",taskCreateBy);
        Task task1 = taskMapper.selectOne(taskQueryWrapper);
        System.out.println("task1"+task1);
//        任务关联风机
        taskRelevanceTurbine(task1);
        //任务关联维修人员
        taskRelevancePerson(task1);
        //任务关联船舶
        taskRelevanceBoat(task1);
        //任务关联学生
        taskRelevanceStudent(task1);

        return AjaxResult.success();


    }
    /*
    任务关联学生
     */
    public void taskRelevanceStudent(Task task){
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("role_id",101l);
        List<SysUserRole> students =sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        List<Long> studentIds = students.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
        System.out.println("studentIds"+studentIds);
        for(Long user_id:studentIds){
            TaskStudent taskStudent = new TaskStudent();
            taskStudent.setUserId(user_id);
            taskStudent.setTaskId(task.getTaskId());
            taskStudent.setTaskState(1l);//任务未开始
            taskStudentMapper.insertTaskStudent(taskStudent);
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
//    public Task checkOnLyOneTask(Task task){
//        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
//        taskQueryWrapper.eq("user_id",task.getUserId())
//                .eq("task_state",1l);
//        Task task1 = taskMapper.selectOne(taskQueryWrapper);
//        return task1;
//    }



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
            deleteTaskStudentByTaskStudentIds(taskId);
        }
        return taskMapper.deleteTaskByTaskIds(taskIds);
    }
    //
    public int deleteTaskBoatByTaskBoatIds(Long taskId){
        TaskBoat taskBoat = new TaskBoat();
        taskBoat.setTaskId(taskId);
        List<TaskBoat> taskBoats = taskBoatMapper.selectTaskBoatList(taskBoat);
//        long[] taskBoatIds = taskBoats.stream().map(TaskBoat::getbId).mapToLong(t->t.longValue()).toArray();
//        Long[] taskBoatIds = taskBoats.stream().map(TaskBoat::getbId).toArray(Long[]::new);
        for (TaskBoat taskBoat1:taskBoats){
            QueryWrapper<TaskBoat> taskBoatQueryWrapper = new QueryWrapper<>();
            taskBoatQueryWrapper.eq("b_id",taskBoat1.getbId())
                    .eq("task_id",taskBoat1.getTaskId());
            taskBoatMapper.delete(taskBoatQueryWrapper);
        }
        return 1;
    }
    public int deleteTaskPersonByTaskPersonIds(Long taskId){
        System.out.println("taskId"+taskId);
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setTaskId(taskId);
        List<TaskPerson> taskPeople = taskPersonMapper.selectTaskPersonList(taskPerson);
        for (TaskPerson taskPerson1:taskPeople){
            QueryWrapper<TaskPerson> taskPersonQueryWrapper = new QueryWrapper<>();
            taskPersonQueryWrapper.eq("p_id",taskPerson1.getpId())
                    .eq("task_id",taskPerson1.getTaskId());
            taskPersonMapper.delete(taskPersonQueryWrapper);
        }

//        List<Long> taskPeopleIds = taskPeople.stream().map(TaskPerson::getpId).collect(Collectors.toList());
//        Long[] taskPeopleIds = taskPeople.stream().map(TaskPerson::getpId).toArray(Long[]::new);
        return 1;


    }
    public int deleteTaskTurbineByTaskTurbineIds(Long taskId){
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.setTaskId(taskId);
        List<TaskTurbine> taskTurbines = taskTurbineMapper.selectTaskTurbineList(taskTurbine);
//        List<Long> taskTurbineIds = taskTurbines.stream().map(TaskTurbine::gettId).collect(Collectors.toList());
//        Long[] taskTurbineIds = taskTurbines.stream().map(TaskTurbine::gettId).toArray(Long[]::new);
        for (TaskTurbine taskTurbine1:taskTurbines){
            QueryWrapper<TaskTurbine> taskTurbineQueryWrapper = new QueryWrapper<>();
            taskTurbineQueryWrapper.eq("t_id",taskTurbine1.gettId())
                    .eq("task_id",taskTurbine1.getTaskId());
            taskTurbineMapper.delete(taskTurbineQueryWrapper);
        }
        return 1;


    }
    public int deleteTaskStudentByTaskStudentIds(Long taskId){
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(taskId);
        List<TaskStudent> taskStudents = taskStudentMapper.selectTaskStudentList(taskStudent);
//        List<Long> taskStudentsIds = taskStudents.stream().map(TaskStudent::getUserId).collect(Collectors.toList());
//        Long[] taskStudentsIds =taskStudents.stream().map(TaskStudent::getUserId).toArray(Long[]::new);
        for (TaskStudent taskStudent1:taskStudents){
            QueryWrapper<TaskStudent> taskStudentQueryWrapper = new QueryWrapper<>();
            taskStudentQueryWrapper.eq("user_id",taskStudent1.getUserId())
                    .eq("task_id",taskStudent1.getTaskId());
            taskStudentMapper.delete(taskStudentQueryWrapper);
        }
        return 1;
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
