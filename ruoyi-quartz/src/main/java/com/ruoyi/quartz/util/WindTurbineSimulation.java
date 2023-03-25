package com.ruoyi.quartz.util;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Fault;
import com.ruoyi.system.domain.TurbineFault;
import com.ruoyi.system.domain.TurbineWind;
import com.ruoyi.system.service.IFaultService;
import com.ruoyi.system.service.ITurbineFaultService;
import com.ruoyi.system.service.ITurbineWindService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Configuration
public class WindTurbineSimulation {


    @Resource
    private IFaultService iFaultService;

    @Resource
    private ITurbineWindService iTurbineWindService;

    @Resource
    private ITurbineFaultService iTurbineFaultService;

    private Random random = new Random();

    private double lambda = 0;

    public void deviceSimulation(){
        TurbineWind turbineWind1 = new TurbineWind();
        turbineWind1.settFaultState(3l);
        List<TurbineWind> turbineWinds = iTurbineWindService.selectTurbineWindList(turbineWind1);
//        System.out.println("turbineWinds"+turbineWinds);
        for (TurbineWind turbineWind:turbineWinds){
            List<Fault> faults = iFaultService.selectFaultList(null);
            for (Fault fault:faults){
                lambda = Double.parseDouble(fault.getfFrequencyPerYear());
                double rand = random.nextDouble();
                //指数分布函数-按每年发生故障指数分布
                double probability = 1 - Math.exp(-lambda);
                System.out.println("rand"+rand+"lambda"+lambda);
                if (rand<=probability){
                    handleFault(turbineWind,fault);
                    //发生故障后跳出循环
                    break;
                }
            }
        }
    }

    public void handleFault(TurbineWind turbineWind,Fault fault){
        System.out.println("Device fault occurred.");
        TurbineFault turbineFault = new TurbineFault();
        turbineFault.setfId(fault.getfId());
        turbineFault.settId(turbineWind.gettId());
        turbineFault.setFaultTime(DateUtils.getNowDate());
        //设备故障绑定
        iTurbineFaultService.insertTurbineFault(turbineFault);
        turbineWind.settFaultState(1l);
        //风机发生故障停机
        iTurbineWindService.updateTurbineWind(turbineWind);
    }


}
