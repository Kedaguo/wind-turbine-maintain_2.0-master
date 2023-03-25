package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.domain.TaskBoat;

/**
 * taskBoatMapper接口
 * 
 * @author JianDa
 * @date 2023-03-25
 */
public interface TaskBoatMapper extends BaseMapper<TaskBoat>
{
    /**
     * 查询taskBoat
     * 
     * @param bId taskBoat主键
     * @return taskBoat
     */
    public TaskBoat selectTaskBoatByBId(Long bId);

    /**
     * 查询taskBoat列表
     * 
     * @param taskBoat taskBoat
     * @return taskBoat集合
     */
    public List<TaskBoat> selectTaskBoatList(TaskBoat taskBoat);

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
     * 删除taskBoat
     * 
     * @param bId taskBoat主键
     * @return 结果
     */
    public int deleteTaskBoatByBId(Long bId);

    /**
     * 批量删除taskBoat
     * 
     * @param bIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskBoatByBIds(Long[] bIds);
}
