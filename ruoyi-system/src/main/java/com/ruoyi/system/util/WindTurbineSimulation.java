package com.ruoyi.system.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.dto.TaskTurbineDto;
import com.ruoyi.system.mapper.ArrangementMapper;
import com.ruoyi.system.mapper.RepairOrderMapper;
import com.ruoyi.system.mapper.SeaWaveMapper;
import com.ruoyi.system.mapper.TaskTurbineMapper;
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
    private RepairOrderMapper repairOrderMapper;

    @Resource
    private ITurbineWindService iTurbineWindService;

    @Resource
    private ITaskStudentService iTaskStudentService;

    @Resource
    private IRandService iRandService;

    @Resource
    private IArrangementService iArrangementService;

    @Resource
    private IArrangementBoatService iArrangementBoatService;

    @Resource
    private IArrangementOperatorService iArrangementOperatorService;

    @Resource
    private ITaskBoatService iTaskBoatService;

    @Resource
    private ITaskOperatorService iTaskOperatorService;

    @Resource
    private ISeaWaveService iSeaWaveService;

    @Resource
    private SeaWaveMapper seaWaveMapper;

    @Resource
    private TaskTurbineMapper taskTurbineMapper;

    private Random random = new Random();

    private double lambda = 0;

    private Integer SecondsOfYear = 31536000;

    private Integer MinutesOfYear = 525600;

    private Integer HoursOfYear = 8760;

    private Integer DayOfYear = 365;

    private Integer Month = 12;

    private Integer SecondsOfHour = 3600;

    public void  deviceSimulation(Long taskId,Long userId,Integer runSpeed) throws Exception{
        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(taskId);
        taskStudent.setUserId(userId);
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.setTaskId(taskId);
        taskTurbine.setUserId(userId);
        System.out.println("taskId"+taskId+"userId"+userId+"runSpeed"+runSpeed);
        //Simulation Time
        Integer taskCount = TaskCounts(taskStudent);
        Integer randCount = 1;
        //模拟仿真时间已经修改-taskStudent
        Date date = simulationTimes(runSpeed,taskStudent);
        //风机根据海浪天气信息进行暂停
        updateWindTurbineStateBySeaWave(taskTurbine,date);
        List<TaskTurbine> taskTurbineFaults = selectTaskTurbineFault(taskTurbine);
        for (TaskTurbine taskTurbineFault :taskTurbineFaults){
            //风机发电
            turbineWindCharge(taskTurbineFault.gettId(),taskStudent,taskTurbine,date);
            List<Fault> faults = iFaultService.selectFaultList(null);
            for (Fault fault:faults){
                lambda = Double.parseDouble(fault.getfFrequencyPerYear());
                double rand = taskRandFaultPlus(taskStudent);
                //指数分布函数-按每小时发生故障指数分布
                double probability = (1 - Math.exp(-lambda))/DayOfYear;
                System.out.println("rand"+rand+"lambda"+lambda);
                if (rand<=probability){
                    handleFault(taskTurbineFault,fault,date);
                    //发生故障后跳出循环
                    break;
                }
            }

        }
        List<TaskTurbine> taskTurbineMaintains = selectTaskTurbineMaintain(taskId, userId);
        for (TaskTurbine taskTurbineMaintain:taskTurbineMaintains){
            //风机发电
            turbineWindCharge(taskTurbineMaintain.gettId(),taskStudent,taskTurbine,date);
            System.out.println("taskTurbineMaintain"+taskTurbineMaintain);
            List<Maintain> maintains = iMaintainService.selectMaintainList(null);
            for (Maintain maintain:maintains){
                lambda = Double.parseDouble(maintain.getmFrequencyPerYear());
//                double rand = random.nextDouble();
                double rand = taskRandMaintainPlus(taskStudent);
                //指数分布函数-按每年发生保养指数分布
                double probability = (1 - Math.exp(-lambda))/DayOfYear;
                System.out.println("rand"+rand+"probability"+probability);
                if (rand<=probability){
                    handleMaintain(taskTurbineMaintain,maintain,date);
                    //发生故障后跳出循环
                    break;
                }
            }
        }
        //出海作业
//        arrangementRunning(taskId,userId,date);
        //终止仿真模拟
        simulationJobFinish(date,userId,taskId);
    }
    //浪高Max=1.8m  风速最高  12 m/s  最小 4m/s
    public void updateWindTurbineStateBySeaWave(TaskTurbine taskTurbine,Date date){
        Date date1 = DateUtils.saveYearMonthDayHour(date);
        QueryWrapper<SeaWave> seaWaveQueryWrapper = new QueryWrapper<>();
        seaWaveQueryWrapper.eq("st_time",date1);
        List<SeaWave> seaWaves = seaWaveMapper.selectList(seaWaveQueryWrapper);
        if (seaWaves.size() != 0){
            Double waveHeight =Double.parseDouble(seaWaves.get(0).getStWaveHeight());
            Double windSpeed =Double.parseDouble(seaWaves.get(0).getStWindSpeed());
            if (waveHeight<=1.8 && windSpeed<= 12 && windSpeed >= 4){
                taskTurbine.setfState(0);
                taskTurbine.setmState(0);
                List<TaskTurbine> taskTurbines = iTaskTurbineService.selectTaskTurbineListSimulation(taskTurbine);
                for (TaskTurbine taskTurbine1 : taskTurbines){
                    taskTurbine1.setmState(3);
                    taskTurbine1.setfState(2);
                    taskTurbineMapper.updateTaskTurbine(taskTurbine1);
                }
            }else{
                taskTurbine.setmState(3);
                taskTurbine.setfState(2);
                List<TaskTurbine> taskTurbines = iTaskTurbineService.selectTaskTurbineListSimulation(taskTurbine);
                for (TaskTurbine taskTurbine1 : taskTurbines){
                    taskTurbine1.setmState(0);
                    taskTurbine1.setfState(0);
                    taskTurbineMapper.updateTaskTurbine(taskTurbine1);
                }
            }
        }

    }
    //task_rand_fault次数++
    public double taskRandFaultPlus(TaskStudent taskStudent){
        //task_rand_fault 次数
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
    public double taskRandMaintainPlus(TaskStudent taskStudent){
        //task_rand_fault 次数
        TaskStudent taskStudent1 = iTaskStudentService.selectTaskStudentByUserId(taskStudent);
        Integer taskRandMaintain = taskStudent1.getTaskRandMaintain();
        if(taskRandMaintain == 10000){
            taskRandMaintain=1;
        }
        taskStudent1.setTaskRandMaintain(taskRandMaintain+1);
        int flag= iTaskStudentService.updateTaskStudent(taskStudent1);
        Rand rand = iRandService.selectRandByRandId(taskRandMaintain.longValue());
        System.out.println("rand"+rand+"taskRandMaintain"+taskRandMaintain);
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
            taskStudent.setTaskState(3l);
            int flag = iTaskStudentService.updateTaskStudent(taskStudent);
        }

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
    public void turbineWindCharge(Long tId,TaskStudent taskStudent1,TaskTurbine taskTurbine,Date date){
        taskTurbine.settId(tId);
        TaskStudent taskStudent = iTaskStudentService.selectTaskStudentByUserId(taskStudent1);
        //计算每一次执行所发电量
        LocalDateTime simulationChargeEndTime = DateUtils.getSimulationChargeTime(date);
        LocalDateTime simulationChargeStartTime = DateUtils.getSimulationChargeTime(taskStudent.getTaskSimulateTime());
        Duration duration = Duration.between(simulationChargeStartTime, simulationChargeEndTime);
        Integer ratePower = Integer.parseInt(iTurbineWindService.selectTurbineWindByTId(tId).gettRatePower());
        double minutes = duration.toMinutes();
        double hours = minutes /60;
        //ratePower  kwh
        Integer turbineCharge =(int)Math.round(ratePower * hours) ;
        System.out.println("turbineCharge"+turbineCharge+"hours"+hours+"simulationChargeEndTime"+simulationChargeEndTime+"simulationChargeStartTime"+simulationChargeStartTime);
        TaskTurbine taskTurbineCharge = iTaskTurbineService.selectTaskTurbineByTId(tId, taskTurbine.getTaskId(), taskTurbine.getUserId());
        //发电做累加
        if (taskTurbineCharge.getfState()==2){
            taskTurbine.settCharge(taskTurbineCharge.gettCharge()+turbineCharge);
            int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        }
        if (taskTurbineCharge.getmState()==3){
            //注意Long类型相加时，不能为空，会报空指针错误——类比一下进行数学方面计算时,其变量不能为空
            taskTurbine.settCharge(taskTurbineCharge.gettCharge()+turbineCharge);
            int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        }else if (taskTurbineCharge.getmState()==1){
            //需要保养但可以运行 发电效率是80%
            double charge = taskTurbineCharge.gettCharge() + (turbineCharge * 0.8) ;
            taskTurbine.settCharge(Math.round(charge));
            int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        }
    }
    public List<TaskTurbine> selectTaskTurbineFault(TaskTurbine taskTurbine){
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

    public void handleFault(TaskTurbine taskTurbine,Fault fault,Date date){
        System.out.println("Device fault occurred.");
        taskTurbine.setfId(fault.getfId());
        //0 未开始工作  1 故障停机  2 正常工作
        taskTurbine.setfState(1);
        int i = iTaskTurbineService.updateTaskTurbine(taskTurbine);
        //生成维修单
        RepairOrder repairOrder = new RepairOrder();
        BeanUtils.copyProperties(taskTurbine,repairOrder);
        repairOrder.setrCreateTime(date);
        repairOrder.setrState(0);
        //生成故障维修单(condition 1 ：如果有相同风机发生保养了那么维修单就需要合并)
        QueryWrapper<RepairOrder> repairOrderQueryWrapper = new QueryWrapper<>();
        repairOrderQueryWrapper.eq("user_id",taskTurbine.getUserId())
                .eq("task_id",taskTurbine.getTaskId())
                .eq("t_id",taskTurbine.gettId())
                .eq("r_state",0);
        RepairOrder repairOrder1 = repairOrderMapper.selectOne(repairOrderQueryWrapper);
        if (repairOrder1==null){
            repairOrder.setrType(1);
            int flag = repairOrderService.insertRepairOrder(repairOrder);
            System.out.println("repairOrder"+repairOrder1);
        }else {
            repairOrder.setrType(3);
            int flag = repairOrderService.insertRepairOrder(repairOrder);
        }

    }

    public void handleMaintain(TaskTurbine taskTurbine, Maintain maintain,Date date){
        System.out.println("Device fault occurred.");
        taskTurbine.setmId(maintain.getmId());
         //0 未开始工作 1需要保养且正常工作  2  保养不工作  3 正常工作
        taskTurbine.setmState(2);
        iTaskTurbineService.updateTaskTurbine(taskTurbine);
        RepairOrder repairOrder = new RepairOrder();
        BeanUtils.copyProperties(taskTurbine,repairOrder);

        TaskStudent taskStudent = new TaskStudent();
        taskStudent.setTaskId(taskTurbine.getTaskId());
        taskStudent.setUserId(taskTurbine.getUserId());
        repairOrder.setrCreateTime(date);
        repairOrder.setrState(0);
        //生成故障维修单(condition 1 ：如果有相同风机发生保养了那么维修单就需要合并)
        QueryWrapper<RepairOrder> repairOrderQueryWrapper = new QueryWrapper<>();
        repairOrderQueryWrapper.eq("user_id",taskTurbine.getUserId())
                .eq("task_id",taskTurbine.getTaskId())
                .eq("t_id",taskTurbine.gettId())
                .eq("r_state",0);
        RepairOrder repairOrder1 = repairOrderMapper.selectOne(repairOrderQueryWrapper);
        if (repairOrder1 == null){
            repairOrder.setrType(2);
            //生成保养维修单
            int i = repairOrderService.insertRepairOrder(repairOrder);
        }else{
            repairOrder.setrType(3);
            //生成保养维修单
            int i = repairOrderService.insertRepairOrder(repairOrder);
        }


    }

    public int TaskCounts(TaskStudent taskStudent2){
        TaskStudent taskStudent = iTaskStudentService.selectTaskStudentByUserId(taskStudent2);
        TaskStudent taskStudent1 = new TaskStudent();
        BeanUtils.copyProperties(taskStudent,taskStudent1);
        //任务每执行一次就累加1
        taskStudent1.setTaskCount(taskStudent.getTaskCount()+1);
        iTaskStudentService.updateTaskStudent(taskStudent1);
        return taskStudent1.getTaskCount();
    }
    //学生任务仿真模拟时间
    public Date simulationTimes(Integer runSpeed,TaskStudent taskStudent1){
        //总的时间
//        Integer simulationTimeSeconds = taskCount * runSpeed;
        Integer simulationTimeSeconds = runSpeed;
        TaskStudent taskStudent = iTaskStudentService.selectTaskStudentByUserId(taskStudent1);
        // 定义DateTimeFormatter格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        LocalDateTime taskSimulateTime = LocalDateTime.parse(taskStudent.getTaskSimulateTime().toString(),formatter);
        Duration duration = Duration.ofSeconds(simulationTimeSeconds.longValue());
        LocalDateTime newTime = taskSimulateTime.plus(duration);
        Date simulationTimes = DateUtils.getSimulationTimes(newTime);
        //更新用户模拟时间
        taskStudent.setTaskSimulateTime(simulationTimes);
        int i = iTaskStudentService.updateTaskStudent(taskStudent);
        return simulationTimes;
    }
}
