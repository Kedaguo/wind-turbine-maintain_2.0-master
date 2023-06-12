package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.system.domain.Task;
import org.springframework.transaction.annotation.Transactional;

/**
 * taskService接口
 * 
 * @author 赵建达
 * @date 2023-03-24
 */
public interface ITaskService
{
    /**
     * 查询task
     * 
     * @param taskId task主键
     * @return task
     */
    public Task selectTaskByTaskId(Long taskId);
    /*
    根据教师查询任务
     */
    public List<Task> selectTaskListByTeacher(String username);


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
    public AjaxResult insertTask(Task task, LoginUser loginUser);

    /**
     * 修改task
     * 
     * @param task task
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 批量删除task
     * 
     * @param taskIds 需要删除的task主键集合
     * @return 结果
     */
    public int deleteTaskByTaskIds(Long[] taskIds);

    /**
     * 删除task信息
     * 
     * @param taskId task主键
     * @return 结果
     */
    public int deleteTaskByTaskId(Long taskId);
}
