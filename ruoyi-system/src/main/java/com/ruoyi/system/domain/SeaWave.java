package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * seaWave对象 tl_sea_wave
 * 
 * @author JianDa
 * @date 2023-04-21
 */
@TableName("tl_sea_wave")
public class SeaWave
{
    private static final long serialVersionUID = 1L;

    /** 气象编号 */
    private Long sId;

    /** 模拟时间 */
    @Excel(name = "模拟时间")
    private Long stSimulationTime;

    /** 海浪时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "海浪时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date stTime;

    /** 风速 */
    @Excel(name = "风速")
    private String stWindSpeed;

    /** 风机转速 */
    @Excel(name = "风机转速")
    private String stWindSpeedTurbine;

    /** 海浪高度 */
    @Excel(name = "海浪高度")
    private String stWaveHeight;

    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }
    public void setStSimulationTime(Long stSimulationTime) 
    {
        this.stSimulationTime = stSimulationTime;
    }

    public Long getStSimulationTime() 
    {
        return stSimulationTime;
    }
    public void setStTime(Date stTime) 
    {
        this.stTime = stTime;
    }

    public Date getStTime() 
    {
        return stTime;
    }
    public void setStWindSpeed(String stWindSpeed) 
    {
        this.stWindSpeed = stWindSpeed;
    }

    public String getStWindSpeed() 
    {
        return stWindSpeed;
    }
    public void setStWindSpeedTurbine(String stWindSpeedTurbine) 
    {
        this.stWindSpeedTurbine = stWindSpeedTurbine;
    }

    public String getStWindSpeedTurbine() 
    {
        return stWindSpeedTurbine;
    }
    public void setStWaveHeight(String stWaveHeight) 
    {
        this.stWaveHeight = stWaveHeight;
    }

    public String getStWaveHeight() 
    {
        return stWaveHeight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("stSimulationTime", getStSimulationTime())
            .append("stTime", getStTime())
            .append("stWindSpeed", getStWindSpeed())
            .append("stWindSpeedTurbine", getStWindSpeedTurbine())
            .append("stWaveHeight", getStWaveHeight())
            .toString();
    }
}
