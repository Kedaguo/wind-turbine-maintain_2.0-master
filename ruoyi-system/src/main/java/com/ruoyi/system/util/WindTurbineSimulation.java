package com.ruoyi.system.util;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.dto.TaskTurbineDto;
import com.ruoyi.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

@Configuration
public class WindTurbineSimulation {

    @Resource
    private ISysJobService iSysJobService;

    @Resource
    private IFaultService iFaultService;

    @Resource
    private IMaintainService iMaintainService;

    @Resource
    private ITaskTurbineService iTaskTurbineService;

    @Resource
    private ITaskService iTaskService;

    @Resource
    private IRepairOrderService repairOrderService;

    @Resource
    private ITurbineWindService iTurbineWindService;

    @Resource
    private ITaskStudentService iTaskStudentService;

    @Resource
    private IRandService iRandService;

    private Random random = new Random();

    private double lambda = 0;

    private Integer SecondsOfYear = 31536000;

    private Integer MinutesOfYear = 525600;

    private Integer HoursOfYear = 8760;

    private Integer DayOfYear = 365;

    private Integer Month = 12;

    private Integer SecondsOfHour = 3600;

    public void  deviceSimulation(Long taskId,Long userId,Integer runSpeed) throws Exception{
        System.out.println("taskId"+taskId+"userId"+userId+"runSpeed"+runSpeed);
        //Simulation Time
        Integer taskCount = TaskCounts(taskId,userId);
        Integer randCount = 1;
        //模拟仿真时间已经修改-taskStudent
        Date date = simulationTimes(taskCount, runSpeed, taskId, userId);
        List<TaskTurbine> taskTurbineFaults = selectTaskTurbineFault(taskId, userId);
        for (TaskTurbine taskTurbineFault :taskTurbineFaults){
            //风机发电
            turbineWindCharge(taskTurbineFault.gettId(),taskId,userId,date);
            List<Fault> faults = iFaultService.selectFaultList(null);
            for (Fault fault:faults){
                lambda = Double.parseDouble(fault.getfFrequencyPerYear());
                double rand = taskRandFaultPlus(taskId, userId);
                //指数分布函数-按每小时发生故障指数分布
                double probability = (1 - Math.exp(-lambda))/HoursOfYear;
                System.out.println("rand"+rand+"lambda"+lambda);
                if (rand<=probability){
                    handleFault(taskTurbineFault,fault);
                    //发生故障后跳出循环
                    break;
                }
            }

        }
        List<TaskTurbine> taskTurbineMaintains = selectTaskTurbineMaintain(taskId, userId);
        for (TaskTurbine taskTurbineMaintain:taskTurbineMaintains){
            //风机发电
            turbineWindCharge(taskTurbineMaintain.gettId(),taskId,userId,date);
            System.out.println("taskTurbineMaintain"+taskTurbineMaintain);
            List<Maintain> maintains = iMaintainService.selectMaintainList(null);
            for (Maintain maintain:maintains){
                lambda = Double.parseDouble(maintain.getmFrequencyPerYear());
//                double rand = random.nextDouble();
                double rand = taskRandMaintainPlus(taskId,userId);
                //指数分布函数-按每年发生保养指数分布
                double probability = (1 - Math.exp(-lambda))/HoursOfYear;
                System.out.println("rand"+rand+"probability"+probability);
                if (rand<=probability){
                    handleMaintain(taskTurbineMaintain,maintain);
                    //发生故障后跳出循环
                    break;
                }
            }
        }
        //终止仿真模拟
        simulationJobFinish(date,userId,taskId);
    }
    //task_rand_fault次数++
    public double taskRandFaultPlus(Long taskId,Long userId){
        //task_rand_fault 次数
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(taskId);
        taskStudent.setUserId(userId);
        TaskStudent taskStudent1 = iTaskStudentService.selectTaskStudentByUserId(taskStudent);
        Integer taskRandFault = taskStudent1.getTaskRandFault();
        if(taskRandFault == 10000){
            taskRandFault=0;
        }
        taskStudent1.setTaskRandFault(taskRandFault+1);
        int flag= iTaskStudentService.updateTaskStudent(taskStudent1);
        Rand rand = iRandService.selectRandByRandId(taskRandFault.longValue());
        return Double.parseDouble(rand.getRand());
    }
    //task_rand_maintain次数++
    public double taskRandMaintainPlus(Long taskId,Long userId){
        //task_rand_fault 次数
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(taskId);
        taskStudent.setUserId(userId);
        TaskStudent taskStudent1 = iTaskStudentService.selectTaskStudentByUserId(taskStudent);
        Integer taskRandMaintain = taskStudent1.getTaskRandMaintain();
        if(taskRandMaintain == 10000){
            taskRandMaintain=0;
        }
        taskStudent1.setTaskRandMaintain(taskRandMaintain+1);
        int flag= iTaskStudentService.updateTaskStudent(taskStudent1);
        Rand rand = iRandService.selectRandByRandId(taskRandMaintain.longValue());
        return Double.parseDouble(rand.getRand());
    }

    //仿真模拟到结束时间终止
    public void simulationJobFinish(Date date,Long userId,Long taskId) throws Exception{
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setUserId(userId);
        taskStudent.setTaskId(taskId);
        TaskStudent taskStudent1 = iTaskStudentService.selectTaskStudentByUserId(taskStudent);
        Task task = new Task();
        Date taskEndTime = iTaskService.selectTaskByTaskId(taskId).getTaskEndTime();
        int result = date.compareTo(taskEndTime);
        if (result < 0){
            System.out.println("仿真时间:"+taskStudent1.getTaskSimulateTime());
        }else {
            //终止任务
            SysJob sysJob = new SysJob();
            sysJob.setJobId(taskStudent1.getJobId());
            sysJob.setStatus(String.valueOf(1));
            int i = iSysJobService.changeStatus(sysJob);
            //总发电量
            taskStudentChargeSum(taskId,userId);
        }
        //更新用户模拟时间
        taskStudent.setTaskSimulateTime(date);
        iTaskStudentService.updateTaskStudent(taskStudent);
    }
    //任务终止时，总发电量
    public void taskStudentChargeSum(Long taskId,Long userId){
        //taskStudent总发电量
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.setUserId(userId);
        taskTurbine.setTaskId(taskId);
        List<TaskTurbineDto> taskTurbineDtos = iTaskTurbineService.selectTaskTurbineListByStudent(taskTurbine);
        Long chargeSum = taskTurbineDtos.stream().map(TaskTurbine::gettCharge).collect(Collectors.summingLong(Long::longValue));
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(taskId);
        taskStudent.setUserId(userId);
        taskStudent.setTaskCharge(chargeSum);
        //执行次数修改为 0
        //taskStudent.setTaskCount(0);
        int i = iTaskStudentService.updateTaskStudent(taskStudent);
    }

    //风机发电
    public void turbineWindCharge(Long tId,Long taskId,Long userId,Date date){
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.settId(tId);
        taskTurbine.setTaskId(taskId);
        taskTurbine.setUserId(userId);
        TaskStudent taskStudent1 = new TaskStudent();
        taskStudent1.setTaskId(taskId);
        taskStudent1.setUserId(userId);
        TaskStudent taskStudent = iTaskStudentService.selectTaskStudentByUserId(taskStudent1);
        LocalDateTime simulationChargeEndTime = DateUtils.getSimulationChargeTime(date);
        LocalDateTime simulationChargeStartTime = DateUtils.getSimulationChargeTime(taskStudent.getTaskSimulateTime());
        Duration duration = Duration.between(simulationChargeStartTime, simulationChargeEndTime);
        Integer ratePower = Integer.parseInt(iTurbineWindService.selectTurbineWindByTId(tId).gettRatePower());
        Long hours = duration.toHours();
        //ratePower  kwh
        Integer turbineCharge =Math.round(ratePower * hours.intValue()) ;
        System.out.println("turbineCharge"+turbineCharge);
        TaskTurbine taskTurbineCharge = iTaskTurbineService.selectTaskTurbineByTId(tId, taskId, userId);
        //发电做累加
        if (taskTurbineCharge.getfState()==2){
            taskTurbine.settCharge(taskTurbineCharge.gettCharge()+turbineCharge.longValue());
            int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        }
        if (taskTurbineCharge.getmState()==3){
            //注意Long类型相加时，不能为空，会报空指针错误——类比一下进行数学方面计算时,其变量不能为空
            taskTurbine.settCharge(taskTurbineCharge.gettCharge()+turbineCharge.longValue());
            int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        }else if (taskTurbineCharge.getmState()==1){
            //需要保养但可以运行 发电效率是80%
            double charge = (taskTurbineCharge.gettCharge() + turbineCharge.longValue()) * 0.8;
            taskTurbine.settCharge(Math.round(charge));
            int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        }
    }
    public List<TaskTurbine> selectTaskTurbineFault(Long taskId,Long userId){
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.setTaskId(taskId);
        taskTurbine.setUserId(userId);
        taskTurbine.setfState(2);
        List<TaskTurbine> taskTurbines = iTaskTurbineService.selectTaskTurbineListSimulation(taskTurbine);
        return taskTurbines;
    }
    public List<TaskTurbine> selectTaskTurbineMaintain(Long taskId,Long userId){
        TaskTurbine taskTurbineMaintain = new TaskTurbine();
        taskTurbineMaintain.setTaskId(taskId);
        taskTurbineMaintain.setUserId(userId);
        taskTurbineMaintain.setmState(3);
        List<TaskTurbine> taskTurbineMaintains = iTaskTurbineService.selectTaskTurbineListSimulation(taskTurbineMaintain);
        return taskTurbineMaintains;
    }

    public void handleFault(TaskTurbine taskTurbine,Fault fault){
        System.out.println("Device fault occurred.");
        taskTurbine.setfId(fault.getfId());
        //0 未开始工作  1 故障停机  2 正常工作
        taskTurbine.setfState(1);
        int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        //生成维修单
        RepairOrder repairOrder = new RepairOrder();
        BeanUtils.copyProperties(taskTurbine,repairOrder);
        repairOrder.setrCreateTime(DateUtils.getNowDate());
        repairOrder.setrState(0);
        repairOrder.setrType(1);
        //生成故障维修单
        int flag = repairOrderService.insertRepairOrder(repairOrder);
    }

    public void handleMaintain(TaskTurbine taskTurbine, Maintain maintain){
        System.out.println("Device fault occurred.");
        taskTurbine.setmId(maintain.getmId());
         //0 未开始工作 1需要保养且正常工作  2  保养不工作  3 正常工作
        taskTurbine.setmState(1);
        iTaskTurbineService.updateTaskTurbine(taskTurbine);
        RepairOrder repairOrder = new RepairOrder();
        BeanUtils.copyProperties(taskTurbine,repairOrder);

        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(taskTurbine.getTaskId());
        taskStudent.setUserId(taskTurbine.getUserId());
        TaskStudent taskStudent1 = iTaskStudentService.selectTaskStudentByUserId(taskStudent);
        repairOrder.setrCreateTime(taskStudent1.getTaskSimulateTime());
        repairOrder.setrState(0);
        repairOrder.setrType(2);
        //生成保养维修单
        int i = repairOrderService.insertRepairOrder(repairOrder);
    }

    public int TaskCounts(Long taskId, Long userId){
        System.out.println("taskId"+taskId+"userId"+userId);
        TaskStudent taskStudent2 = new TaskStudent();
        taskStudent2.setTaskId(taskId);
        taskStudent2.setUserId(userId);
        TaskStudent taskStudent = iTaskStudentService.selectTaskStudentByUserId(taskStudent2);
        TaskStudent taskStudent1 = new TaskStudent();
        BeanUtils.copyProperties(taskStudent,taskStudent1);
        //任务每执行一次就累加1
        taskStudent1.setTaskCount(taskStudent.getTaskCount()+1);
        iTaskStudentService.updateTaskStudent(taskStudent1);
        return taskStudent1.getTaskCount();
    }
    //学生任务仿真模拟时间
    public Date simulationTimes(Integer taskCount,Integer runSpeed,Long taskId, Long userId){
        Integer simulationTimeSeconds = taskCount * runSpeed;
        TaskStudent taskStudent1 = new TaskStudent();
        taskStudent1.setTaskId(taskId);
        taskStudent1.setUserId(userId);
        TaskStudent taskStudent = iTaskStudentService.selectTaskStudentByUserId(taskStudent1);
        // 定义DateTimeFormatter格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        LocalDateTime taskSimulateTime = LocalDateTime.parse(taskStudent.getTaskSimulateTime().toString(),formatter);
        Duration duration = Duration.ofSeconds(simulationTimeSeconds.longValue());
        LocalDateTime newTime = taskSimulateTime.plus(duration);
        System.out.println("newTime"+newTime);
        return DateUtils.getSimulationTimes(newTime);
    }
}
