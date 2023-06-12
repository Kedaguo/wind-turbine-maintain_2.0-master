package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TaskOperator;
import com.ruoyi.system.domain.dto.TaskOperatorDto;

/**
 * taskOperatorService接口
 * 
 * @author JianDa
 * @date 2023-04-07
 */
public interface ITaskOperatorService 
{
    /**
     * 查询taskOperator
     * 
     * @param oId taskOperator主键
     * @return taskOperator
     */
    public TaskOperator selectTaskOperatorByOId(Long oId);

    /**
     * 查询taskOperator列表
     * 
     * @param taskOperator taskOperator
     * @return taskOperator集合
     */
    public List<TaskOperator> selectTaskOperatorList(TaskOperator taskOperator);

    public List<TaskOperatorDto> selectTaskOperatorByUser(TaskOperator taskOperator);
    /**
     * 新增taskOperator
     * 
     * @param taskOperator taskOperator
     * @return 结果
     */
    public int insertTaskOperator(TaskOperator taskOperator);

    /**
     * 修改taskOperator
     * 
     * @param taskOperator taskOperator
     * @return 结果
     */
    public int updateTaskOperator(TaskOperator taskOperator);

    /**
     * 批量删除taskOperator
     * 
     * @param oIds 需要删除的taskOperator主键集合
     * @return 结果
     */
    public int deleteTaskOperatorByOIds(Long[] oIds);

    /**
     * 删除taskOperator信息
     * 
     * @param oId taskOperator主键
     * @return 结果
     */
    public int deleteTaskOperatorByOId(Long oId);
}
