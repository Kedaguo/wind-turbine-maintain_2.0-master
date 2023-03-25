package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TaskTurbine;

/**
 * taskTurbineService接口
 * 
 * @author JianDa
 * @date 2023-03-25
 */
public interface ITaskTurbineService 
{
    /**
     * 查询taskTurbine
     * 
     * @param tId taskTurbine主键
     * @return taskTurbine
     */
    public TaskTurbine selectTaskTurbineByTId(Long tId);

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
     * 批量删除taskTurbine
     * 
     * @param tIds 需要删除的taskTurbine主键集合
     * @return 结果
     */
    public int deleteTaskTurbineByTIds(Long[] tIds);

    /**
     * 删除taskTurbine信息
     * 
     * @param tId taskTurbine主键
     * @return 结果
     */
    public int deleteTaskTurbineByTId(Long tId);
}
