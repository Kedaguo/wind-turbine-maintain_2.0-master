package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 故障信息对象 tl_fault
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
public class Fault extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 故障编号 */
    private Long fId;

    /** 故障频率 */
    @Excel(name = "故障频率")
    private String fFrequencyPerYear;

    /** 维修成本 */
    @Excel(name = "维修成本")
    private Long fConsumableCost;

    /** 船只类型 */
    @Excel(name = "船只类型")
    private Long fBoatModel;

    /** 准备时间/天 */
    @Excel(name = "准备时间/天")
    private Long fPrepareDuration;

    /** 维修时间 */
    @Excel(name = "维修时间")
    private String fMaintainDuration;

    /** 维修人员数量 */
    @Excel(name = "维修人员数量")
    private Long fPeopleNumber;

    /** 故障类型 */
    @Excel(name = "故障类型")
    private Long fFaultType;

    /** 故障设备 */
    @Excel(name = "故障设备")
    private String fFaultDevice;

    /** 故障 */
    @Excel(name = "故障")
    private String fFaultfre;

    public void setfId(Long fId) 
    {
        this.fId = fId;
    }

    public Long getfId() 
    {
        return fId;
    }
    public void setfFrequencyPerYear(String fFrequencyPerYear) 
    {
        this.fFrequencyPerYear = fFrequencyPerYear;
    }

    public String getfFrequencyPerYear() 
    {
        return fFrequencyPerYear;
    }
    public void setfConsumableCost(Long fConsumableCost) 
    {
        this.fConsumableCost = fConsumableCost;
    }

    public Long getfConsumableCost() 
    {
        return fConsumableCost;
    }
    public void setfBoatModel(Long fBoatModel) 
    {
        this.fBoatModel = fBoatModel;
    }

    public Long getfBoatModel() 
    {
        return fBoatModel;
    }
    public void setfPrepareDuration(Long fPrepareDuration) 
    {
        this.fPrepareDuration = fPrepareDuration;
    }

    public Long getfPrepareDuration() 
    {
        return fPrepareDuration;
    }
    public void setfMaintainDuration(String fMaintainDuration) 
    {
        this.fMaintainDuration = fMaintainDuration;
    }

    public String getfMaintainDuration() 
    {
        return fMaintainDuration;
    }
    public void setfPeopleNumber(Long fPeopleNumber) 
    {
        this.fPeopleNumber = fPeopleNumber;
    }

    public Long getfPeopleNumber() 
    {
        return fPeopleNumber;
    }
    public void setfFaultType(Long fFaultType) 
    {
        this.fFaultType = fFaultType;
    }

    public Long getfFaultType() 
    {
        return fFaultType;
    }
    public void setfFaultDevice(String fFaultDevice) 
    {
        this.fFaultDevice = fFaultDevice;
    }

    public String getfFaultDevice() 
    {
        return fFaultDevice;
    }
    public void setfFaultfre(String fFaultfre) 
    {
        this.fFaultfre = fFaultfre;
    }

    public String getfFaultfre() 
    {
        return fFaultfre;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fId", getfId())
            .append("fFrequencyPerYear", getfFrequencyPerYear())
            .append("fConsumableCost", getfConsumableCost())
            .append("fBoatModel", getfBoatModel())
            .append("fPrepareDuration", getfPrepareDuration())
            .append("fMaintainDuration", getfMaintainDuration())
            .append("fPeopleNumber", getfPeopleNumber())
            .append("fFaultType", getfFaultType())
            .append("fFaultDevice", getfFaultDevice())
            .append("fFaultfre", getfFaultfre())
            .toString();
    }
}
