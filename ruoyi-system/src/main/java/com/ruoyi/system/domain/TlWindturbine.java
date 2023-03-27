package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 故障风机监控对象 tl_windturbine
 * 
 * @author 张存
 * @date 2023-03-12
 */
public class TlWindturbine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 风机编号 */
    private Long id;

    /** 故障码 */
    @Excel(name = "故障码")
    private Long faultCode;

    /** 故障信息   */
    @Excel(name = "故障信息")
    private String faultInformation;

    @Excel(name = "保养时间")
    private String maintainTime;

    public String getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(String maintainTime) {
        this.maintainTime = maintainTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFaultCode(Long faultCode) 
    {
        this.faultCode = faultCode;
    }

    public Long getFaultCode() 
    {
        return faultCode;
    }
    public void setFaultInformation(String faultInformation) 
    {
        this.faultInformation = faultInformation;
    }

    public String getFaultInformation() 
    {
        return faultInformation;
    }

    @Override
    public String toString() {
        return "TlWindturbine{" +
                "id=" + id +
                ", faultCode=" + faultCode +
                ", faultInformation='" + faultInformation + '\'' +
                ", maintainTime='" + maintainTime + '\'' +
                '}';
    }
}
