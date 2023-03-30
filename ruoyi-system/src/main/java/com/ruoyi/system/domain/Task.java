package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * task对象 tl_task
 * 
 * @author JianDa
 * @date 2023-03-27
 */
@TableName(value = "tl_task")
public class Task
{
    private static final long serialVersionUID = 1L;

    /** 任务编号 */
    private Long taskId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date taskStartTime;

    /** 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date taskEndTime;

    /** 任务发布时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    @Excel(name = "任务发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss.SSS")
    private String taskCreateTime;

    /** 发布人 */
    @Excel(name = "发布人")
    private String taskCreateBy;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setTaskStartTime(Date taskStartTime) 
    {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskStartTime() 
    {
        return taskStartTime;
    }
    public void setTaskEndTime(Date taskEndTime) 
    {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskEndTime() 
    {
        return taskEndTime;
    }
//    public void setTaskCreateTime(Date taskCreateTime)
//    {
//        this.taskCreateTime = taskCreateTime;
//    }
//
//    public Date getTaskCreateTime()
//    {
//        return taskCreateTime;
//    }

    public String getTaskCreateTime () {
        return taskCreateTime;
    }

    public void setTaskCreateTime (String taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public void setTaskCreateBy(String taskCreateBy)
    {
        this.taskCreateBy = taskCreateBy;
    }

    public String getTaskCreateBy() 
    {
        return taskCreateBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("taskName", getTaskName())
            .append("taskStartTime", getTaskStartTime())
            .append("taskEndTime", getTaskEndTime())
            .append("taskCreateTime", getTaskCreateTime())
            .append("taskCreateBy", getTaskCreateBy())
            .toString();
    }
}
