package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Boat;
import com.ruoyi.system.domain.TaskBoat;
import com.ruoyi.system.domain.dto.TaskBoatDto;

/**
 * taskBoatService接口
 * 
 * @author JianDa
 * @date 2023-03-25
 */
public interface ITaskBoatService 
{
    /**
     * 查询taskBoat
     * 
     * @param
     * @return taskBoat
     */
    public TaskBoat selectOneTaskBoat(TaskBoat taskBoat);


    public List<TaskBoatDto> selectTaskBoatDtoByUserId(Long taskId,Long userId);

    /**
     * 查询taskBoat列表
     * 
     * @param taskBoat taskBoat
     * @return taskBoat集合
     */
    public List<Boat> selectTaskBoatList(TaskBoat taskBoat);
    public Integer selectTaskBoatListSize(TaskBoat taskBoat);
    /**
     * 新增taskBoat
     * 
     * @param taskBoat taskBoat
     * @return 结果
     */
    public int insertTaskBoat(TaskBoat taskBoat);

    /**
     * 修改taskBoat
     * 
     * @param taskBoat taskBoat
     * @return 结果
     */
    public int updateTaskBoat(TaskBoat taskBoat);

    /**
     * 批量删除taskBoat
     * 
     * @param bIds 需要删除的taskBoat主键集合
     * @return 结果
     */
    public int deleteTaskBoatByBIds(Long[] bIds);

    /**
     * 删除taskBoat信息
     * 
     * @param bId taskBoat主键
     * @return 结果
     */
    public int deleteTaskBoatByBId(Long bId);
}
