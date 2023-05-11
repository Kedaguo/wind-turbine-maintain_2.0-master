package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangementOperator对象 tl_arrangement_operator
 * 
 * @author JianDa
 * @date 2023-04-13
 */
public class ArrangementOperator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划关联维修工编号 */
    private Long aoId;

    /** 维修单编号 */
    @Excel(name = "维修单编号")
    private Long rId;

    /** 维修人员编号 */
    @Excel(name = "维修人员编号")
    private Long oId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    public void setAoId(Long aoId) 
    {
        this.aoId = aoId;
    }

    public Long getAoId() 
    {
        return aoId;
    }
    public void setrId(Long rId) 
    {
        this.rId = rId;
    }

    public Long getrId() 
    {
        return rId;
    }
    public void setoId(Long oId) 
    {
        this.oId = oId;
    }

    public Long getoId() 
    {
        return oId;
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
            .append("aoId", getAoId())
            .append("rId", getrId())
            .append("oId", getoId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .toString();
    }
}
