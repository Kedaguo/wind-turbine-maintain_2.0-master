package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangementBoat对象 tl_arrangement_boat
 * 
 * @author JianDa
 * @date 2023-04-13
 */
public class ArrangementBoat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划船舶编号 */
    private Long abId;

    /** 规划编号 */
    @Excel(name = "规划编号")
    private Long aId;

    /** 船舶变化 */
    @Excel(name = "船舶变化")
    private Long bId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    public void setAbId(Long abId) 
    {
        this.abId = abId;
    }

    public Long getAbId() 
    {
        return abId;
    }
    public void setaId(Long aId) 
    {
        this.aId = aId;
    }

    public Long getaId() 
    {
        return aId;
    }
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
            .append("abId", getAbId())
            .append("aId", getaId())
            .append("bId", getbId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .toString();
    }
}
