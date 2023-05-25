package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.dto.TaskTurbineDto;
import com.ruoyi.system.mapper.FaultMapper;
import com.ruoyi.system.mapper.MaintainMapper;
import com.ruoyi.system.mapper.TurbineWindMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskTurbineMapper;
import com.ruoyi.system.service.ITaskTurbineService;

import javax.annotation.Resource;

/**
 * taskTurbineService业务层处理
 * 
 * @author JianDa
 * @date 2023-03-25
 */
@Service
public class TaskTurbineServiceImpl implements ITaskTurbineService 
{
    @Resource
    private TaskTurbineMapper taskTurbineMapper;

    @Resource
    private TurbineWindMapper turbineWindMapper;

    @Resource
    private FaultMapper faultMapper;

    @Resource
    private MaintainMapper maintainMapper;

    /**
     * 查询taskTurbine
     * 
     * @param tId taskTurbine主键
     * @return taskTurbine
     */
    @Override
    public TaskTurbine selectTaskTurbineByTId(Long tId,Long taskId,Long userId)
    {
        TaskTurbine taskTurbine = new TaskTurbine();
        taskTurbine.settId(tId);
        taskTurbine.setUserId(userId);
        taskTurbine.setTaskId(taskId);
        return taskTurbineMapper.selectTaskTurbineByTId(taskTurbine);
    }

    @Override
    public List<TaskTurbineDto> selectTaskTurbineListByStudent (TaskTurbine taskTurbine) {
        List<TaskTurbine> taskTurbines = taskTurbineMapper.selectTaskTurbineList(taskTurbine);
        ArrayList<TaskTurbineDto> taskTurbineDtos = new ArrayList<>();
        for (TaskTurbine taskTurbine1:taskTurbines){
            TurbineWind turbineWind = turbineWindMapper.selectTurbineWindByTId(taskTurbine1.gettId());
            TaskTurbineDto taskTurbineDto = new TaskTurbineDto();
            if (taskTurbine1.getmState()== 2 || taskTurbine1.getmState() == 1){
                Maintain maintain = maintainMapper.selectMaintainByMId(taskTurbine1.getmId());
                taskTurbineDto.setMMaintainDuration(maintain.getmMaintainDuration());
            }
            if (taskTurbine1.getfState() == 1){
                Fault fault = faultMapper.selectFaultByFId(taskTurbine1.getfId());
                taskTurbineDto.setFMaintainDuration(fault.getfMaintainDuration());
            }
            BeanUtils.copyProperties(turbineWind,taskTurbineDto);
            BeanUtils.copyProperties(taskTurbine1,taskTurbineDto);
            taskTurbineDtos.add(taskTurbineDto);
        }
        return taskTurbineDtos;
    }

    /**
     * 查询taskTurbine列表
     * 
     * @param taskTurbine taskTurbine
     * @return taskTurbine
     */
    @Override
    public List<TaskTurbineDto> selectTaskTurbineList(TaskTurbine taskTurbine)
    {
        List<TaskTurbine> taskTurbines = taskTurbineMapper.selectTaskTurbineList(taskTurbine);
        ArrayList<TaskTurbineDto> taskTurbineDtos = new ArrayList<>();
        for (TaskTurbine taskTurbine1:taskTurbines){
            TaskTurbineDto taskTurbineDto = new TaskTurbineDto();
            TurbineWind turbineWind = turbineWindMapper.selectTurbineWindByTId(taskTurbine1.gettId());
            BeanUtils.copyProperties(turbineWind,taskTurbineDto);
            BeanUtils.copyProperties(taskTurbine1,taskTurbineDto);
            taskTurbineDtos.add(taskTurbineDto);
        }
        return taskTurbineDtos;
    }

    @Override
    public List<TaskTurbine> selectTaskTurbineListSimulation (TaskTurbine taskTurbine) {
        return taskTurbineMapper.selectTaskTurbineList(taskTurbine);
    }

    /**
     * 新增taskTurbine
     * 
     * @param taskTurbine taskTurbine
     * @return 结果
     */
    @Override
    public int insertTaskTurbine(TaskTurbine taskTurbine)
    {
        return taskTurbineMapper.insertTaskTurbine(taskTurbine);
    }

    /**
     * 修改taskTurbine
     * 
     * @param taskTurbine taskTurbine
     * @return 结果
     */
    @Override
    public int updateTaskTurbine(TaskTurbine taskTurbine)
    {
        return taskTurbineMapper.updateTaskTurbine(taskTurbine);
    }

    /**
     * 批量删除taskTurbine
     * 
     * @param tIds 需要删除的taskTurbine主键
     * @return 结果
     */
    @Override
    public int deleteTaskTurbineByTIds(Long[] tIds)
    {
        return taskTurbineMapper.deleteTaskTurbineByTIds(tIds);
    }

    /**
     * 删除taskTurbine信息
     * 
     * @param tId taskTurbine主键
     * @return 结果
     */
    @Override
    public int deleteTaskTurbineByTId(Long tId)
    {
        return taskTurbineMapper.deleteTaskTurbineByTId(tId);
    }
}
