package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * boat对象 tl_boat
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
public class Boat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 船只编号 */
    private Long bId;

    /** 船只模型 */
    @Excel(name = "船只模型")
    private String bModel;

    /** 船只速度 */
    @Excel(name = "船只速度")
    private String bSpeed;

    /** 船只容量 */
    @Excel(name = "船只容量")
    private String bCapacity;

    /** 风速 */
    @Excel(name = "风速")
    private String bWindSpeed;

    /** 海浪气象 */
    @Excel(name = "海浪气象")
    private String bWaveHeight;

    /** 每年成本 */
    @Excel(name = "每年成本")
    private String bAnnualCost;

    /** 每小时成本 */
    @Excel(name = "每小时成本")
    private String bHourCost;

    /** 船只数量 */
    @Excel(name = "船只数量")
    private Long bSubshipNum;

    /** 船只类型 */
    @Excel(name = "船只类型")
    private Long bType;

    /** 出海作业 */
    @Excel(name = "出海作业")
    private Long bState;

    /** 工作类型 */
    @Excel(name = "工作类型")
    private Long bWorkState;

    /** 出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bStartTime;

    /** 返回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "返回时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bEndTime;

    /** 经度 */
    @Excel(name = "经度")
    private String bLongitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String bLatitude;

    public void setbId(Long bId) 
    {
        this.bId = bId;
    }

    public Long getbId() 
    {
        return bId;
    }
    public void setbModel(String bModel) 
    {
        this.bModel = bModel;
    }

    public String getbModel() 
    {
        return bModel;
    }
    public void setbSpeed(String bSpeed) 
    {
        this.bSpeed = bSpeed;
    }

    public String getbSpeed() 
    {
        return bSpeed;
    }
    public void setbCapacity(String bCapacity) 
    {
        this.bCapacity = bCapacity;
    }

    public String getbCapacity() 
    {
        return bCapacity;
    }
    public void setbWindSpeed(String bWindSpeed) 
    {
        this.bWindSpeed = bWindSpeed;
    }

    public String getbWindSpeed() 
    {
        return bWindSpeed;
    }
    public void setbWaveHeight(String bWaveHeight) 
    {
        this.bWaveHeight = bWaveHeight;
    }

    public String getbWaveHeight() 
    {
        return bWaveHeight;
    }
    public void setbAnnualCost(String bAnnualCost) 
    {
        this.bAnnualCost = bAnnualCost;
    }

    public String getbAnnualCost() 
    {
        return bAnnualCost;
    }
    public void setbHourCost(String bHourCost) 
    {
        this.bHourCost = bHourCost;
    }

    public String getbHourCost() 
    {
        return bHourCost;
    }
    public void setbSubshipNum(Long bSubshipNum) 
    {
        this.bSubshipNum = bSubshipNum;
    }

    public Long getbSubshipNum() 
    {
        return bSubshipNum;
    }
    public void setbType(Long bType) 
    {
        this.bType = bType;
    }

    public Long getbType() 
    {
        return bType;
    }
    public void setbState(Long bState) 
    {
        this.bState = bState;
    }

    public Long getbState() 
    {
        return bState;
    }
    public void setbWorkState(Long bWorkState) 
    {
        this.bWorkState = bWorkState;
    }

    public Long getbWorkState() 
    {
        return bWorkState;
    }
    public void setbStartTime(Date bStartTime) 
    {
        this.bStartTime = bStartTime;
    }

    public Date getbStartTime() 
    {
        return bStartTime;
    }
    public void setbEndTime(Date bEndTime) 
    {
        this.bEndTime = bEndTime;
    }

    public Date getbEndTime() 
    {
        return bEndTime;
    }
    public void setbLongitude(String bLongitude) 
    {
        this.bLongitude = bLongitude;
    }

    public String getbLongitude() 
    {
        return bLongitude;
    }
    public void setbLatitude(String bLatitude) 
    {
        this.bLatitude = bLatitude;
    }

    public String getbLatitude() 
    {
        return bLatitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bId", getbId())
            .append("bModel", getbModel())
            .append("bSpeed", getbSpeed())
            .append("bCapacity", getbCapacity())
            .append("bWindSpeed", getbWindSpeed())
            .append("bWaveHeight", getbWaveHeight())
            .append("bAnnualCost", getbAnnualCost())
            .append("bHourCost", getbHourCost())
            .append("bSubshipNum", getbSubshipNum())
            .append("bType", getbType())
            .append("bState", getbState())
            .append("bWorkState", getbWorkState())
            .append("bStartTime", getbStartTime())
            .append("bEndTime", getbEndTime())
            .append("bLongitude", getbLongitude())
            .append("bLatitude", getbLatitude())
            .toString();
    }
}
