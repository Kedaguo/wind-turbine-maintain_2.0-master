package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangement对象 tl_arrangement
 * 
 * @author JianDa
 * @date 2023-04-13
 */
public class Arrangement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划编号 */
    private Long aId;

    /** 规划名称 */
    @Excel(name = "规划名称")
    private String aName;

    /** 始点 */
    @Excel(name = "始点")
    private String startLocation;

    /** 终点 */
    @Excel(name = "终点")
    private String endLocation;

    /** 开始仿真模拟时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始仿真模拟时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束仿真模拟时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束仿真模拟时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setaId(Long aId) 
    {
        this.aId = aId;
    }

    public Long getaId() 
    {
        return aId;
    }
    public void setaName(String aName) 
    {
        this.aName = aName;
    }

    public String getaName() 
    {
        return aName;
    }
    public void setStartLocation(String startLocation) 
    {
        this.startLocation = startLocation;
    }

    public String getStartLocation() 
    {
        return startLocation;
    }
    public void setEndLocation(String endLocation) 
    {
        this.endLocation = endLocation;
    }

    public String getEndLocation() 
    {
        return endLocation;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aId", getaId())
            .append("aName", getaName())
            .append("startLocation", getStartLocation())
            .append("endLocation", getEndLocation())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .toString();
    }
}
