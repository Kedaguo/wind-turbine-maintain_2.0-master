package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.Operator;
import com.ruoyi.system.domain.TaskOperator;

/**
 * taskOperatorMapper接口
 * 
 * @author JianDa
 * @date 2023-04-07
 */
public interface TaskOperatorMapper extends BaseMapper<TaskOperator>
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
     * 删除taskOperator
     * 
     * @param oId taskOperator主键
     * @return 结果
     */
    public int deleteTaskOperatorByOId(Long oId);

    /**
     * 批量删除taskOperator
     * 
     * @param oIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskOperatorByOIds(Long[] oIds);
}
