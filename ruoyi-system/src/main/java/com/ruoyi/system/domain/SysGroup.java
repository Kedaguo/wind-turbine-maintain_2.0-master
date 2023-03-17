package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小组管理对象 sys_group
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
public class SysGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 小组id */
    private Long groupId;

    /** 小组编号 */
    @Excel(name = "小组编号")
    private Long groupName;

    /** 总成绩 */
    @Excel(name = "总成绩")
    private Long groupPoints;

    /** 成员数量 */
    @Excel(name = "成员数量")
    private Long groupNumber;

    /** 总时间 */
    @Excel(name = "总时间")
    private Long groupTime;

    /** 总成本 */
    @Excel(name = "总成本")
    private Long groupCost;

    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setGroupName(Long groupName) 
    {
        this.groupName = groupName;
    }

    public Long getGroupName() 
    {
        return groupName;
    }
    public void setGroupPoints(Long groupPoints) 
    {
        this.groupPoints = groupPoints;
    }

    public Long getGroupPoints() 
    {
        return groupPoints;
    }
    public void setGroupNumber(Long groupNumber) 
    {
        this.groupNumber = groupNumber;
    }

    public Long getGroupNumber() 
    {
        return groupNumber;
    }
    public void setGroupTime(Long groupTime) 
    {
        this.groupTime = groupTime;
    }

    public Long getGroupTime() 
    {
        return groupTime;
    }
    public void setGroupCost(Long groupCost) 
    {
        this.groupCost = groupCost;
    }

    public Long getGroupCost() 
    {
        return groupCost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .append("groupPoints", getGroupPoints())
            .append("groupNumber", getGroupNumber())
            .append("groupTime", getGroupTime())
            .append("groupCost", getGroupCost())
            .toString();
    }
}
