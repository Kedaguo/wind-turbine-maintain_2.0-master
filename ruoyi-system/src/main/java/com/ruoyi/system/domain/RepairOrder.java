package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * repairOrder对象 tl_repair_order
 * 
 * @author JianDa
 * @date 2023-04-04
 */
@TableName("tl_repair_order")
public class RepairOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修单编号 */
    private Long rId;

    /** 风机编号 */
    @Excel(name = "风机编号")
    private Long tId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 派单状态 */
    @Excel(name = "派单状态")
    private Integer rState;

    /** 维修单类型 */
    @Excel(name = "维修单类型")
    private Integer rType;

    /**  发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = " 发生时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rCreateTime;

    public Integer getrType () {
        return rType;
    }

    public void setrType (Integer rType) {
        this.rType = rType;
    }

    public void setrId(Long rId)
    {
        this.rId = rId;
    }

    public Long getrId() 
    {
        return rId;
    }
    public void settId(Long tId) 
    {
        this.tId = tId;
    }

    public Long gettId() 
    {
        return tId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setrState(Integer rState) 
    {
        this.rState = rState;
    }

    public Integer getrState() 
    {
        return rState;
    }
    public void setrCreateTime(Date rCreateTime) 
    {
        this.rCreateTime = rCreateTime;
    }

    public Date getrCreateTime() 
    {
        return rCreateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rId", getrId())
            .append("tId", gettId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("rState", getrState())
            .append("rCreateTime", getrCreateTime())
            .toString();
    }
}
