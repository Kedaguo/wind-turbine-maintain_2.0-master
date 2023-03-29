package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * taskMapper接口
 * 
 * @author 赵建达
 * @date 2023-03-24
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task>
{
    /**
     * 查询task
     * 
     * @param taskId task主键
     * @return task
     */
    public Task selectTaskByTaskId(Long taskId);

    /**
     * 查询task列表
     * 
     * @param task task
     * @return task集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增task
     * 
     * @param task task
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改task
     * 
     * @param task task
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除task
     * 
     * @param taskId task主键
     * @return 结果
     */
    public int deleteTaskByTaskId(Long taskId);

    /**
     * 批量删除task
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByTaskIds(Long[] taskIds);
}
