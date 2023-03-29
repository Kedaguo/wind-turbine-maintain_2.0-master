package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * turbineFault对象 tl_turbine_fault
 * 
 * @author 赵建达
 * @date 2023-03-14
 */
@TableName(value = "tl_turbine_fault")
public class TurbineFault extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 风机编号 */
    private Long tId;

    /** 故障编号 */
    private Long fId;
    /** 发生故障时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date faultTime;

    public Date getFaultTime () {
        return faultTime;
    }

    public void setFaultTime (Date faultTime) {
        this.faultTime = faultTime;
    }
    public void settId(Long tId) 
    {
        this.tId = tId;
    }

    public Long gettId() 
    {
        return tId;
    }
    public void setfId(Long fId) 
    {
        this.fId = fId;
    }

    public Long getfId() 
    {
        return fId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tId", gettId())
            .append("fId", getfId())
            .toString();
    }
}
