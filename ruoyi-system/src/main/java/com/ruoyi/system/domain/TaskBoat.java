package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskBoat对象 tl_task_boat
 * 
 * @author JianDa
 * @date 2023-03-25
 */
@TableName(value = "tl_task_boat")
public class TaskBoat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 船舶编号 */
    private Long bId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 任务编号 */
    private Long taskId;

    /** 出海状态 */
    @Excel(name = "出海状态")
    private Integer bState;

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    /** 工作状态 */
    @Excel(name = "工作状态")
    private Integer bWorkState;

    /** 出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bStartTime;

    /** 返回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "返回时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bEndTime;

    /** 经度 */
    @Excel(name = "经度")
    private Double bLongitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private Double bLatitude;

    public void setbId(Long bId) 
    {
        this.bId = bId;
    }

    public Long getbId() 
    {
        return bId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setbState(Integer bState) 
    {
        this.bState = bState;
    }

    public Integer getbState() 
    {
        return bState;
    }
    public void setbWorkState(Integer bWorkState) 
    {
        this.bWorkState = bWorkState;
    }

    public Integer getbWorkState() 
    {
        return bWorkState;
    }
    public void setbStartTime(Date bStartTime) 
    {
        this.bStartTime = bStartTime;
    }

    public Date getbStartTime() 
    {
        return bStartTime;
    }
    public void setbEndTime(Date bEndTime) 
    {
        this.bEndTime = bEndTime;
    }

    public Date getbEndTime() 
    {
        return bEndTime;
    }

    public Double getbLongitude () {
        return bLongitude;
    }

    public void setbLongitude (Double bLongitude) {
        this.bLongitude = bLongitude;
    }

    public Double getbLatitude () {
        return bLatitude;
    }

    public void setbLatitude (Double bLatitude) {
        this.bLatitude = bLatitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bId", getbId())
            .append("taskId", getTaskId())
            .append("bState", getbState())
            .append("userId", getUserId())
            .append("bWorkState", getbWorkState())
            .append("bStartTime", getbStartTime())
            .append("bEndTime", getbEndTime())
            .append("bLongitude", getbLongitude())
            .append("bLatitude", getbLatitude())
            .toString();
    }


}
