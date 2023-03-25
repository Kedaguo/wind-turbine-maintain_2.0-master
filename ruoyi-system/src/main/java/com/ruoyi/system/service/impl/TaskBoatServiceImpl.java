package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskBoatMapper;
import com.ruoyi.system.domain.TaskBoat;
import com.ruoyi.system.service.ITaskBoatService;

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

    /**
     * 查询taskBoat列表
     * 
     * @param taskBoat taskBoat
     * @return taskBoat
     */
    @Override
    public List<TaskBoat> selectTaskBoatList(TaskBoat taskBoat)
    {
        return taskBoatMapper.selectTaskBoatList(taskBoat);
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
