package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.TaskPerson;

/**
 * taskPersonMapper接口
 * 
 * @author JianDa
 * @date 2023-03-25
 */
public interface TaskPersonMapper extends BaseMapper<TaskPerson>
{
    /**
     * 查询taskPerson
     * 
     * @param pId taskPerson主键
     * @return taskPerson
     */
    public TaskPerson selectTaskPersonByPId(Long pId);

    /**
     * 查询taskPerson列表
     * 
     * @param taskPerson taskPerson
     * @return taskPerson集合
     */
    public List<TaskPerson> selectTaskPersonList(TaskPerson taskPerson);

    /**
     * 新增taskPerson
     * 
     * @param taskPerson taskPerson
     * @return 结果
     */
    public int insertTaskPerson(TaskPerson taskPerson);

    /**
     * 修改taskPerson
     * 
     * @param taskPerson taskPerson
     * @return 结果
     */
    public int updateTaskPerson(TaskPerson taskPerson);

    /**
     * 删除taskPerson
     * 
     * @param pId taskPerson主键
     * @return 结果
     */
    public int deleteTaskPersonByPId(Long pId);

    /**
     * 批量删除taskPerson
     * 
     * @param pIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskPersonByPIds(Long[] pIds);
}
