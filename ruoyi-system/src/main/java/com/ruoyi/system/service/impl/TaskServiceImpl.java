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
    private TaskTurbineMapper taskTurbineMapper;

    @Resource
    private TurbineWindMapper turbineWindMapper;

    @Resource
    private OperatorMapper operatorMapper;

    @Resource
    private BoatMapper boatMapper;

    @Resource
    private PortMapper portMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private TaskStudentMapper taskStudentMapper;

    @Resource
    private TaskPortMapper taskPortMapper;

    @Resource
    private TaskOperatorMapper taskOperatorMapper;

    @Resource
    private SysJobMapper sysJobMapper;

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
//        String nowDate = DateUtils.getTime();
        task.setTaskCreateTime(DateUtils.getMillsTime());
        task.setTaskCreateBy(loginUser.getUsername());
        int i = taskMapper.insert(task);
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("task_create_time",task.getTaskCreateTime())
                .eq("task_create_by",task.getTaskCreateBy());
        Task task1 = taskMapper.selectOne(taskQueryWrapper);
        //任务关联风机
        taskRelevanceTurbine(task1);
        //任务关联维修人员
        taskRelevanceOperator(task1);
        //任务关联船舶
        taskRelevanceBoat(task1);
        //任务关联学生
        taskRelevanceStudent(task1);
        //任务关联港口
        taskRelevancePort(task1);

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
        for(Long userId:studentIds){
            insertTaskStudent(task,userId);
            insertJob(task,userId);
        }

    }
    public void insertTaskStudent(Task task,Long userId){
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setUserId(userId);
        taskStudent.setTaskId(task.getTaskId());
        taskStudent.setTaskState(1l);//任务未开始
        taskStudent.setTaskSimulateTime(task.getTaskStartTime());
        taskStudent.setTaskCount(0);
        taskStudent.setTaskRandMaintain(0);
        taskStudent.setTaskRandFault(0);
        taskStudent.setTaskCharge(0L);
        taskStudentMapper.insertTaskStudent(taskStudent);
    }
    public void insertJob(Task task,Long userId){
        SysJob sysJob = new SysJob();
        sysJob.setJobName("仿真时钟");
        sysJob.setJobGroup("DEFAULT");
        //默认runSpeed是 1000
        String invokeTarget = "ryTask.windTurbineSimulation("+task.getTaskId()+"L,"+userId+"L,"+"1000"+")";
        sysJob.setInvokeTarget(invokeTarget);
        sysJob.setCronExpression("* * * * * ?");
        sysJob.setMisfirePolicy("3");
        sysJob.setConcurrent("1");
        sysJob.setStatus("1");
        sysJob.setCreateBy(task.getTaskCreateBy());
        sysJob.setCreateJobTime(DateUtils.getMillsTime());
        sysJobMapper.insertJob(sysJob);
        //用户、任务和仿真进行关联
        SysJob sysJob1 = sysJobMapper.selectJobByCreateJobTime(sysJob.getCreateJobTime());
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(task.getTaskId());
        taskStudent.setUserId(userId);
        taskStudent.setJobId(sysJob1.getJobId());
        int flag = taskStudentMapper.updateTaskStudent(taskStudent);
    }

    /*
    任务关联风机
     */
    public void taskRelevanceTurbine(Task task){
        //学生分配风机
        List<TurbineWind> turbineWinds = turbineWindMapper.selectTurbineWindList(null);
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("role_id",101);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        for (SysUserRole sysUserRole:sysUserRoles){
            for (TurbineWind turbineWind:turbineWinds){
                TaskTurbine taskTurbine = new TaskTurbine();
                taskTurbine.setUserId(sysUserRole.getUserId());
                taskTurbine.setTaskId(task.getTaskId());
                taskTurbine.settId(turbineWind.gettId());
                taskTurbine.setfState(2);
                taskTurbine.setmState(3);
                taskTurbine.settCharge(0l);
                int i = taskTurbineMapper.insertTaskTurbine(taskTurbine);
            }
        }
    }
    /*
    任务关联维修人员
     */
    public void taskRelevanceOperator(Task task){
        List<Operator> operators = operatorMapper.selectOperatorList(null);
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("role_id",101);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        for (SysUserRole sysUserRole:sysUserRoles){
            for (Operator operator:operators){
                TaskOperator taskOperator = new TaskOperator();
                taskOperator.setTaskId(task.getTaskId());
                taskOperator.setUserId(sysUserRole.getUserId());
                taskOperator.setoId(operator.getoId());
                taskOperator.setoState(1l);
                int i = taskOperatorMapper.insertTaskOperator(taskOperator);
            }
        }

    }
    /*
    任务关联船舶
     */
    public void taskRelevanceBoat(Task task){
        List<Boat> boats = boatMapper.selectBoatList(null);
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("role_id",101);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        for (SysUserRole sysUserRole:sysUserRoles) {
            for (Boat boat : boats) {
                TaskBoat taskBoat = new TaskBoat();
                taskBoat.setTaskId(task.getTaskId());
                taskBoat.setUserId(sysUserRole.getUserId());
                taskBoat.setbId(boat.getbId());
                taskBoat.setbState(0);
                taskBoat.setbWorkState(0);
                taskBoat.setbLongitude(boat.getbLongitude());
                taskBoat.setbLatitude(boat.getbLatitude());
                int i = taskBoatMapper.insertTaskBoat(taskBoat);
            }
        }
    }
    /*
        任务关联港口
         */

    public void taskRelevancePort(Task task){
        List<Port> ports = portMapper.selectPortList(null);
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("role_id",101);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        for (SysUserRole sysUserRole:sysUserRoles){
            for (Port port:ports){
                TaskPort taskPort = new TaskPort();
                taskPort.setTaskId(task.getTaskId());
                taskPort.setUserId(sysUserRole.getUserId());
                taskPort.setpId(port.getpId());
                int i = taskPortMapper.insertTaskPort(taskPort);
            }
        }
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
        System.out.println("taskIds"+taskIds);
        for (Long taskId:taskIds){
            deleteTaskBoatByTaskBoatIds(taskId);
            deleteTaskPersonByTaskOperatorIds(taskId);
            deleteTaskTurbineByTaskTurbineIds(taskId);
            deleteTaskStudentByTaskStudentIds(taskId);
            deleteTaskPortByTaskPortIds(taskId);
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
                    .eq("task_id",taskBoat1.getTaskId())
                    .eq("user_id",taskBoat1.getUserId());
            taskBoatMapper.delete(taskBoatQueryWrapper);
        }
        return 1;
    }
    public int deleteTaskPersonByTaskOperatorIds(Long taskId){
        TaskOperator taskOperator = new TaskOperator();
        taskOperator.setTaskId(taskId);
        List<TaskOperator> taskOperators = taskOperatorMapper.selectTaskOperatorList(taskOperator);
        for (TaskOperator taskOperator1:taskOperators){
            QueryWrapper<TaskOperator> taskOperatorQueryWrapper = new QueryWrapper<>();
            taskOperatorQueryWrapper.eq("o_id",taskOperator1.getoId())
                    .eq("task_id",taskOperator1.getTaskId())
                    .eq("user_id",taskOperator1.getUserId());
            int i = taskOperatorMapper.delete(taskOperatorQueryWrapper);
        }
        return 1;


    }
    public int deleteTaskTurbineByTaskTurbineIds(Long taskId){
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.setTaskId(taskId);
        List<TaskTurbine> taskTurbines = taskTurbineMapper.selectTaskTurbineList(taskTurbine);
        for (TaskTurbine taskTurbine1:taskTurbines){
            QueryWrapper<TaskTurbine> taskTurbineQueryWrapper = new QueryWrapper<>();
            taskTurbineQueryWrapper.eq("t_id",taskTurbine1.gettId())
                    .eq("task_id",taskTurbine1.getTaskId())
                    .eq("user_id",taskTurbine1.getUserId());
            taskTurbineMapper.delete(taskTurbineQueryWrapper);
        }
        return 1;

    }
    public int deleteTaskPortByTaskPortIds(Long taskId){
        TaskPort taskPort = new TaskPort();
        taskPort.setTaskId(taskId);
        List<TaskPort> taskPorts = taskPortMapper.selectTaskPortList(taskPort);
        for (TaskPort taskPort1:taskPorts){
            QueryWrapper<TaskPort> taskPortQueryWrapper = new QueryWrapper<>();
            taskPortQueryWrapper.eq("p_id",taskPort1.getpId())
                    .eq("task_id",taskPort1.getTaskId())
                    .eq("user_id",taskPort1.getUserId());
            taskPortMapper.delete(taskPortQueryWrapper);
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
            //delete quartz sysJob
            taskStudentMapper.delete(taskStudentQueryWrapper);
            int flag = sysJobMapper.deleteJobById(taskStudent1.getJobId());
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
