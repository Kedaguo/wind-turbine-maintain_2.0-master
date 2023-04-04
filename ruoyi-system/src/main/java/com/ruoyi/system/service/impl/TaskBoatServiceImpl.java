package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.Boat;
import com.ruoyi.system.domain.dto.TaskBoatDto;
import com.ruoyi.system.mapper.BoatMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskBoatMapper;
import com.ruoyi.system.domain.TaskBoat;
import com.ruoyi.system.service.ITaskBoatService;

import javax.annotation.Resource;

/**
 * taskBoatService业务层处理
 * 
 * @author JianDa
 * @date 2023-03-25
 */
@Service
public class TaskBoatServiceImpl implements ITaskBoatService 
{
    @Autowired
    private TaskBoatMapper taskBoatMapper;

    @Resource
    private BoatMapper boatMapper;


    /**
     * 查询taskBoat
     * 
     * @param bId taskBoat主键
     * @return taskBoat
     */
    @Override
    public TaskBoat selectTaskBoatByBId(Long bId)
    {
        return taskBoatMapper.selectTaskBoatByBId(bId);
    }

    @Override
    public List<TaskBoatDto> selectTaskBoatDtoByUserId (Long taskId,Long userId) {
        TaskBoat taskBoat = new TaskBoat();
        taskBoat.setTaskId(taskId);
        taskBoat.setUserId(userId);
        List<TaskBoat> taskBoats = taskBoatMapper.selectTaskBoatList(taskBoat);
        ArrayList<TaskBoatDto> taskBoatDtos = new ArrayList<>();
        for (TaskBoat taskBoat1:taskBoats){
            Boat boat = boatMapper.selectBoatByBId(taskBoat1.getbId());
            TaskBoatDto taskBoatDto = new TaskBoatDto();
            BeanUtils.copyProperties(boat,taskBoatDto);
            BeanUtils.copyProperties(taskBoat1,taskBoatDto);
            taskBoatDtos.add(taskBoatDto);
        }
        return taskBoatDtos;
    }

    /**
     * 查询taskBoat列表
     * 
     * @param taskBoat taskBoat
     * @return taskBoat
     */
    @Override
    public List<Boat> selectTaskBoatList(TaskBoat taskBoat)
    {
        List<TaskBoat> taskBoats = taskBoatMapper.selectTaskBoatList(taskBoat);
        List<Long> boatIds = taskBoats.stream().map(TaskBoat::getbId).collect(Collectors.toList());
        return boatMapper.selectBatchIds(boatIds);

    }

    /**
     * 新增taskBoat
     * 
     * @param taskBoat taskBoat
     * @return 结果
     */
    @Override
    public int insertTaskBoat(TaskBoat taskBoat)
    {
        return taskBoatMapper.insertTaskBoat(taskBoat);
    }

    /**
     * 修改taskBoat
     * 
     * @param taskBoat taskBoat
     * @return 结果
     */
    @Override
    public int updateTaskBoat(TaskBoat taskBoat)
    {
        return taskBoatMapper.updateTaskBoat(taskBoat);
    }

    /**
     * 批量删除taskBoat
     * 
     * @param bIds 需要删除的taskBoat主键
     * @return 结果
     */
    @Override
    public int deleteTaskBoatByBIds(Long[] bIds)
    {
        return taskBoatMapper.deleteTaskBoatByBIds(bIds);
    }

    /**
     * 删除taskBoat信息
     * 
     * @param bId taskBoat主键
     * @return 结果
     */
    @Override
    public int deleteTaskBoatByBId(Long bId)
    {
        return taskBoatMapper.deleteTaskBoatByBId(bId);
    }
}
