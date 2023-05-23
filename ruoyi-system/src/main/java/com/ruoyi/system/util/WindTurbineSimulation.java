package com.ruoyi.system.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.dto.TaskTurbineDto;
import com.ruoyi.system.mapper.ArrangementMapper;
import com.ruoyi.system.mapper.RepairOrderMapper;
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
        Date date = simulationTimes(runSpeed, taskId, userId);
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
        //出海作业
        arrangementRunning(taskId,userId,date);
        //终止仿真模拟
        simulationJobFinish(date,userId,taskId);
    }
    //任务规划
    public void arrangementRunning(Long taskId,Long userId,Date date){
        //查询arrangement
        Arrangement arrangement = new Arrangement();
        arrangement.setTaskId(taskId);
        arrangement.setUserId(userId);
        //0 未开始  1进行中  2结束
        arrangement.setaState(0);
        List<Arrangement> arrangements = iArrangementService.selectArrangementList(arrangement);
        if (arrangements !=null){
            //路径规划逐步开始
            for (Arrangement arrangement1:arrangements){
                //规划的出海时间大于现在仿真时间
                if (arrangement1.getStartTime().compareTo(date) >= 0){
                    //更新船舶出海作业状态
                    updateBoatWork(arrangement1,taskId,userId);
                    //更新维修员工出海作业状态
                    updateOperatorWork(arrangement1,taskId,userId);








                }






            }

        }
    }
    //更新维修员工状态-出海作业
    public void updateOperatorWork(Arrangement arrangement,Long taskId,Long userId){
        ArrangementOperator arrangementOperator = new ArrangementOperator();
        arrangementOperator.setaId(arrangement.getaId());
        List<ArrangementOperator> arrangementOperators = iArrangementOperatorService.selectArrangementOperatorList(arrangementOperator);
        for (ArrangementOperator arrangementOperator1:arrangementOperators){
            TaskOperator taskOperator = new TaskOperator();
            taskOperator.setTaskId(taskId);
            taskOperator.setUserId(userId);
            taskOperator.setoId(arrangementOperator1.getoId());
//            待命 1  等待条件出海作业  2  出海作业 3
            taskOperator.setoState(3l);
            int flag = iTaskOperatorService.updateTaskOperator(taskOperator);
        }
    }
    public void updateBoatWork(Arrangement arrangement,Long taskId,Long userId){
        //船舶出海作业
        ArrangementBoat arrangementBoat = new ArrangementBoat();
        arrangementBoat.setaId(arrangement.getaId());
        List<ArrangementBoat> arrangementBoats = iArrangementBoatService.selectArrangementBoatList(arrangementBoat);
        for (ArrangementBoat arrangementBoat1:arrangementBoats){
            TaskBoat taskBoat = new TaskBoat();
            taskBoat.setTaskId(taskId);
            taskBoat.setUserId(userId);
            taskBoat.setbId(arrangementBoat1.getbId());
            //出海作业
            taskBoat.setbState(2);
            //    0  待命
            //    1  等待条件允许出海
            //    2  去风场/返回码头
            //    3  风机之间穿梭，把工人放下
            //    4  风机之间穿梭，把工人接上
            //    5  等待工人施工完成
            //    6  去风机/返回抛锚点
            taskBoat.setbWorkState(2);
            int flag = iTaskBoatService.updateTaskBoat(taskBoat);

        }
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
        //计算每一次执行所发电量
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
            double charge = taskTurbineCharge.gettCharge() + (turbineCharge.longValue() * 0.8) ;
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

        //生成故障维修单(condition 1 ：如果有相同风机发生保养了那么维修单就需要合并)
        QueryWrapper<RepairOrder> repairOrderQueryWrapper = new QueryWrapper<>();
        repairOrderQueryWrapper.eq("userId",taskTurbine.getUserId())
                .eq("taskId",taskTurbine.getTaskId())
                .eq("tId",taskTurbine.gettId())
                .eq("rState",0);
        RepairOrder repairOrder1 = repairOrderMapper.selectOne(repairOrderQueryWrapper);
        if (repairOrder1==null){
            repairOrder.setrType(1);
            int flag = repairOrderService.insertRepairOrder(repairOrder);
        }else {
            repairOrder.setrType(3);
            int flag = repairOrderService.insertRepairOrder(repairOrder);
        }

    }

    public void handleMaintain(TaskTurbine taskTurbine, Maintain maintain){
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
        TaskStudent taskStudent1 = iTaskStudentService.selectTaskStudentByUserId(taskStudent);
        repairOrder.setrCreateTime(taskStudent1.getTaskSimulateTime());
        repairOrder.setrState(0);
        //生成故障维修单(condition 1 ：如果有相同风机发生保养了那么维修单就需要合并)
        QueryWrapper<RepairOrder> repairOrderQueryWrapper = new QueryWrapper<>();
        repairOrderQueryWrapper.eq("userId",taskTurbine.getUserId())
                .eq("taskId",taskTurbine.getTaskId())
                .eq("tId",taskTurbine.gettId())
                .eq("rState",0);
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
    public Date simulationTimes(Integer runSpeed,Long taskId, Long userId){
        //总的时间
//        Integer simulationTimeSeconds = taskCount * runSpeed;
        Integer simulationTimeSeconds = runSpeed;
        TaskStudent taskStudent1 = new TaskStudent();
        taskStudent1.setTaskId(taskId);
        taskStudent1.setUserId(userId);
        TaskStudent taskStudent = iTaskStudentService.selectTaskStudentByUserId(taskStudent1);
        // 定义DateTimeFormatter格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        LocalDateTime taskSimulateTime = LocalDateTime.parse(taskStudent.getTaskSimulateTime().toString(),formatter);
        Duration duration = Duration.ofSeconds(simulationTimeSeconds.longValue());
//        Duration duration = Duration.ofHours(simulationTimeSeconds.longValue());
        LocalDateTime newTime = taskSimulateTime.plus(duration);
        System.out.println("newTime"+newTime);
        return DateUtils.getSimulationTimes(newTime);
    }
}
