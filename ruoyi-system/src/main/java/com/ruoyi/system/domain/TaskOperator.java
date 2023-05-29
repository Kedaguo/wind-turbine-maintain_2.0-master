package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskOperator对象 tl_task_operator
 * 
 * @author JianDa
 * @date 2023-04-07
 */
@TableName("tl_task_operator")
public class TaskOperator
{
    private static final long serialVersionUID = 1L;

    private Long taskId;

    private Long oId;

    private Long userId;

    /** 待命 1  出海  2 */
    @Excel(name = "待命 1  出海  2")
    private Long oState;

    /** 工作时间 */
    @Excel(name = "工作时间")
    private Long oWorkTime;

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
    public void setoState(Long oState) 
    {
        this.oState = oState;
    }

    public Long getoState() 
    {
        return oState;
    }
    public void setoWorkTime(Long oWorkTime) 
    {
        this.oWorkTime = oWorkTime;
    }

    public Long getoWorkTime() 
    {
        return oWorkTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oId", getoId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("oState", getoState())
            .append("oWorkTime", getoWorkTime())
            .toString();
    }
}
