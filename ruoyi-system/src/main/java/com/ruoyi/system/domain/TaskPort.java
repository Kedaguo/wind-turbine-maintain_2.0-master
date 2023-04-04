package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskPort对象 tl_task_port
 * 
 * @author JianDa
 * @date 2023-04-03
 */
@TableName("tl_task_port")
public class TaskPort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 港口编号 */
    private Long pId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

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
            .append("pId", getpId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .toString();
    }
}
