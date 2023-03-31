package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskStudent对象 tl_task_student
 * 
 * @author JianDa
 * @date 2023-03-28
 */
@TableName(value = "tl_task_student")
public class TaskStudent
{
    private static final long serialVersionUID = 1L;

    /** 学生编号 */
    @Excel(name = "学生编号")
    private Long userId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 任务成本 */
    @Excel(name = "任务成本")
    private Long taskCost;

    /** 总发电量 */
    @Excel(name = "总发电量")
    private Long taskCharge;

    /** 任务时间 */
    @Excel(name = "任务时间")
    private String taskTime;

    /** 任务成绩 */
    @Excel(name = "任务成绩")
    private Long taskPoints;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Long taskState;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setTaskCost(Long taskCost) 
    {
        this.taskCost = taskCost;
    }

    public Long getTaskCost() 
    {
        return taskCost;
    }
    public void setTaskCharge(Long taskCharge) 
    {
        this.taskCharge = taskCharge;
    }

    public Long getTaskCharge() 
    {
        return taskCharge;
    }
    public void setTaskTime(String taskTime) 
    {
        this.taskTime = taskTime;
    }

    public String getTaskTime() 
    {
        return taskTime;
    }
    public void setTaskPoints(Long taskPoints) 
    {
        this.taskPoints = taskPoints;
    }

    public Long getTaskPoints() 
    {
        return taskPoints;
    }
    public void setTaskState(Long taskState) 
    {
        this.taskState = taskState;
    }

    public Long getTaskState() 
    {
        return taskState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("taskId", getTaskId())
            .append("taskCost", getTaskCost())
            .append("taskCharge", getTaskCharge())
            .append("taskTime", getTaskTime())
            .append("taskPoints", getTaskPoints())
            .append("taskState", getTaskState())
            .toString();
    }
}
