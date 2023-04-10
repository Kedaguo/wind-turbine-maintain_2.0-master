package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * maintain对象 tl_maintain
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
@TableName(value = "tl_maintain")
public class Maintain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 保养编号 */
    private Long mId;

    /** 每年保养频率 */
    @Excel(name = "每年保养频率")
    private String mFrequencyPerYear;

    /** 保养成本 */
    @Excel(name = "保养成本")
    private Long mConsumableCost;

    /** 船只类型 */
    @Excel(name = "船只类型")
    private Long mBoatModel;

    /** 保养准备时间 */
    @Excel(name = "保养准备时间")
    private String mPrepareDuration;

    /** 保养时间 */
    @Excel(name = "保养时间")
    private String mMaintainDuration;

    /** 保养人员 */
    @Excel(name = "保养人员")
    private Long mPeopleNumber;

    /** 保养类型 */
    @Excel(name = "保养类型")
    private Long mFaultType;

    /** 保养设备 */
    @Excel(name = "保养设备")
    private String mFaultDevice;

    /** 下一次保养时间 */
    @Excel(name = "下一次保养时间")
    private String mFaultfre;

    /** 保养单生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保养单生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date mCreateTime;

    public void setmId(Long mId) 
    {
        this.mId = mId;
    }

    public Long getmId() 
    {
        return mId;
    }
    public void setmFrequencyPerYear(String mFrequencyPerYear) 
    {
        this.mFrequencyPerYear = mFrequencyPerYear;
    }

    public String getmFrequencyPerYear() 
    {
        return mFrequencyPerYear;
    }
    public void setmConsumableCost(Long mConsumableCost) 
    {
        this.mConsumableCost = mConsumableCost;
    }

    public Long getmConsumableCost() 
    {
        return mConsumableCost;
    }
    public void setmBoatModel(Long mBoatModel) 
    {
        this.mBoatModel = mBoatModel;
    }

    public Long getmBoatModel() 
    {
        return mBoatModel;
    }
    public void setmPrepareDuration(String mPrepareDuration) 
    {
        this.mPrepareDuration = mPrepareDuration;
    }

    public String getmPrepareDuration() 
    {
        return mPrepareDuration;
    }
    public void setmMaintainDuration(String mMaintainDuration) 
    {
        this.mMaintainDuration = mMaintainDuration;
    }

    public String getmMaintainDuration() 
    {
        return mMaintainDuration;
    }
    public void setmPeopleNumber(Long mPeopleNumber) 
    {
        this.mPeopleNumber = mPeopleNumber;
    }

    public Long getmPeopleNumber() 
    {
        return mPeopleNumber;
    }
    public void setmFaultType(Long mFaultType) 
    {
        this.mFaultType = mFaultType;
    }

    public Long getmFaultType() 
    {
        return mFaultType;
    }
    public void setmFaultDevice(String mFaultDevice) 
    {
        this.mFaultDevice = mFaultDevice;
    }

    public String getmFaultDevice() 
    {
        return mFaultDevice;
    }
    public void setmFaultfre(String mFaultfre) 
    {
        this.mFaultfre = mFaultfre;
    }

    public String getmFaultfre() 
    {
        return mFaultfre;
    }
    public void setmCreateTime(Date mCreateTime) 
    {
        this.mCreateTime = mCreateTime;
    }

    public Date getmCreateTime() 
    {
        return mCreateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mId", getmId())
            .append("mFrequencyPerYear", getmFrequencyPerYear())
            .append("mConsumableCost", getmConsumableCost())
            .append("mBoatModel", getmBoatModel())
            .append("mPrepareDuration", getmPrepareDuration())
            .append("mMaintainDuration", getmMaintainDuration())
            .append("mPeopleNumber", getmPeopleNumber())
            .append("mFaultType", getmFaultType())
            .append("mFaultDevice", getmFaultDevice())
            .append("mFaultfre", getmFaultfre())
            .append("mCreateTime", getmCreateTime())
            .toString();
    }
}
