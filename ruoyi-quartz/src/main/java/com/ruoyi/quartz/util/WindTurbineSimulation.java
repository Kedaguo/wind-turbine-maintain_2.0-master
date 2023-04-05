package com.ruoyi.quartz.util;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

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
    private ITurbineWindService iTurbineWindService;

    @Resource
    private ITurbineFaultService iTurbineFaultService;

    @Resource
    private IRepairOrderService repairOrderService;

    private Random random = new Random();

    private double lambda = 0;

    private Integer SecondsOfYear = 31536000;

    private Integer MinutesOfYear = 525600;

    private Integer HoursOfYear = 8760;

    private Integer DayOfYear = 365;

    private Integer Month = 12;

    public void deviceSimulation(Long taskId,Long userId){
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.setTaskId(taskId);
        taskTurbine.setUserId(userId);
        taskTurbine.setmState(3);
        taskTurbine.setfState(2);
        List<TaskTurbine> taskTurbines = iTaskTurbineService.selectTaskTurbineListSimulation(taskTurbine);
        for (TaskTurbine taskTurbine1 :taskTurbines){
            List<Fault> faults = iFaultService.selectFaultList(null);
            for (Fault fault:faults){
                lambda = Double.parseDouble(fault.getfFrequencyPerYear());
                double rand = random.nextDouble();
                //指数分布函数-按每小时发生故障指数分布
                double probability = (1 - Math.exp(-lambda))/HoursOfYear;
                System.out.println("rand"+rand+"lambda"+lambda);
                if (rand<=probability){
                    handleFault(taskTurbine1,fault);
                    //发生故障后跳出循环
                    break;
                }
            }
            List<Maintain> maintains = iMaintainService.selectMaintainList(null);
            for (Maintain maintain:maintains){
                lambda = Double.parseDouble(maintain.getmFrequencyPerYear());
                double rand = random.nextDouble();
                //指数分布函数-按每年发生保养指数分布
                double probability = (1 - Math.exp(-lambda))/HoursOfYear;
                System.out.println("rand"+rand+"lambda"+lambda);
                if (rand<=probability){
                    handleMaintain(taskTurbine1,maintain);
                    //发生故障后跳出循环
                    break;
                }
            }
        }
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
        repairOrder.setrCreateTime(DateUtils.getNowDate());
        repairOrder.setrState(0);
        repairOrder.setrType(2);
        int i = repairOrderService.insertRepairOrder(repairOrder);
    }
}
