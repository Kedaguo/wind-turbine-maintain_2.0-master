package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * boat对象 tl_boat
 * 
 * @author JianDa
 * @date 2023-03-31
 */
@TableName("tl_boat")
public class Boat
{
    private static final long serialVersionUID = 1L;

    /** 船舶编号 */
    private Long bId;

    /** 船舶模型 */
    @Excel(name = "船舶模型")
    private String bModel;

    /** 船舶速度 */
    @Excel(name = "船舶速度")
    private String bSpeed;

    /** 船舶容量 */
    @Excel(name = "船舶容量")
    private String bCapacity;

    /** 风速 */
    @Excel(name = "风速")
    private String bWindSpeed;

    /** 海浪 */
    @Excel(name = "海浪")
    private String bWaveHeight;

    /** 每年成本 */
    @Excel(name = "每年成本")
    private String bAnnualCost;

    /** 每小时成本 */
    @Excel(name = "每小时成本")
    private String bHourCost;

    /** 船舶类型 */
    @Excel(name = "船舶类型")
    private Integer bType;

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
    public void setbType(Integer bType) 
    {
        this.bType = bType;
    }

    public Integer getbType() 
    {
        return bType;
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
            .append("bType", getbType())
            .append("bLongitude", getbLongitude())
            .append("bLatitude", getbLatitude())
            .toString();
    }
}
