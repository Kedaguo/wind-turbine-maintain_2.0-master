package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskPerson对象 tl_task_person
 * 
 * @author JianDa
 * @date 2023-03-25
 */
public class TaskPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修人员编号 */
    private Long pId;

    /** 任务编号 */
    private Long taskId;

    /** 待命维修人员数量 */
    @Excel(name = "待命维修人员数量")
    private Long pWaitNum;

    /** 出海维修人员数量 */
    @Excel(name = "出海维修人员数量")
    private Long pWorkNum;

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
    public void setpWaitNum(Long pWaitNum) 
    {
        this.pWaitNum = pWaitNum;
    }

    public Long getpWaitNum() 
    {
        return pWaitNum;
    }
    public void setpWorkNum(Long pWorkNum) 
    {
        this.pWorkNum = pWorkNum;
    }

    public Long getpWorkNum() 
    {
        return pWorkNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pId", getpId())
            .append("taskId", getTaskId())
            .append("pWaitNum", getpWaitNum())
            .append("pWorkNum", getpWorkNum())
            .toString();
    }
}
