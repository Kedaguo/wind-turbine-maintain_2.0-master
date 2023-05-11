package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangementBort对象 tl_arrangement_port
 * 
 * @author JianDa
 * @date 2023-04-13
 */
public class ArrangementPort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划关联港口编号 */
    private Long apId;

    /** 规划编号 */
    @Excel(name = "规划编号")
    private Long aId;

    /** 港口编号 */
    @Excel(name = "港口编号")
    private Long pId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    public void setApId(Long apId) 
    {
        this.apId = apId;
    }

    public Long getApId() 
    {
        return apId;
    }
    public void setaId(Long aId) 
    {
        this.aId = aId;
    }

    public Long getaId() 
    {
        return aId;
    }
    public void setpId(Long pId) 
    {
        this.pId = pId;
    }

    public Long getpId() 
    {
        return pId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("apId", getApId())
            .append("aId", getaId())
            .append("pId", getpId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .toString();
    }
}
