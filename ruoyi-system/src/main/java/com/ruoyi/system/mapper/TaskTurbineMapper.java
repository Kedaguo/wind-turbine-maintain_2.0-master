package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.TaskStudent;
import com.ruoyi.system.domain.TaskTurbine;

/**
 * taskTurbineMapper接口
 * 
 * @author JianDa
 * @date 2023-03-25
 */
public interface TaskTurbineMapper extends BaseMapper<TaskTurbine>
{
    /**
     * 查询taskTurbine
     * 
     * @param tId taskTurbine主键
     * @return taskTurbine
     */
    public TaskTurbine selectTaskTurbineByTId(TaskTurbine taskTurbine);

    /**
     * 查询taskTurbine列表
     * 
     * @param taskTurbine taskTurbine
     * @return taskTurbine集合
     */
    public List<TaskTurbine> selectTaskTurbineList(TaskTurbine taskTurbine);

    /**
     * 新增taskTurbine
     * 
     * @param taskTurbine taskTurbine
     * @return 结果
     */
    public int insertTaskTurbine(TaskTurbine taskTurbine);

    /**
     * 修改taskTurbine
     * 
     * @param taskTurbine taskTurbine
     * @return 结果
     */
    public int updateTaskTurbine(TaskTurbine taskTurbine);

    /**
     * 删除taskTurbine
     * 
     * @param tId taskTurbine主键
     * @return 结果
     */
    public int deleteTaskTurbineByTId(Long tId);

    /**
     * 批量删除taskTurbine
     * 
     * @param tIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskTurbineByTIds(Long[] tIds);
}
