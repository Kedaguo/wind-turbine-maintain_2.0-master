package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskTurbine对象 tl_task_turbine
 * 
 * @author JianDa
 * @date 2023-03-28
 */
@TableName(value = "tl_task_turbine")
public class TaskTurbine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 风机编号 */
    private Long tId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 故障编号 */
    @Excel(name = "故障编号")
    private Long fId;

    /** 保养编号 */
    @Excel(name = "保养编号")
    private Long mId;

    /** 故障状态  0 未开始工作 1 保养正常工作  2 保养不工作   3正常工作 4 维修中 */
    @Excel(name = "故障状态")
    private Integer fState;

    /** 保养状态 0 未开始工作 1 故障停机  2 正常工作  4 维修中 */
    @Excel(name = "保养状态")
    private Integer mState;

    /** 发电量 */
    @Excel(name = "发电量")
    private Long tCharge;

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
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
    public void setfId(Long fId) 
    {
        this.fId = fId;
    }

    public Long getfId() 
    {
        return fId;
    }
    public void setmId(Long mId) 
    {
        this.mId = mId;
    }

    public Long getmId() 
    {
        return mId;
    }
    public void setfState(Integer fState) 
    {
        this.fState = fState;
    }

    public Integer getfState() 
    {
        return fState;
    }
    public void setmState(Integer mState) 
    {
        this.mState = mState;
    }

    public Integer getmState() 
    {
        return mState;
    }
    public void settCharge(Long tCharge) 
    {
        this.tCharge = tCharge;
    }

    public Long gettCharge() 
    {
        return tCharge;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tId", gettId())
            .append("taskId", getTaskId())
            .append("fId", getfId())
            .append("mId", getmId())
            .append("fState", getfState())
            .append("mState", getmState())
            .append("tCharge", gettCharge())
            .toString();
    }
}
