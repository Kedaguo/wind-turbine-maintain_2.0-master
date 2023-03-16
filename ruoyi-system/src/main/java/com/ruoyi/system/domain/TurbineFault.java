package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * turbineFault对象 tl_turbine_fault
 * 
 * @author 赵建达
 * @date 2023-03-14
 */
public class TurbineFault extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 风机编号 */
    private Long tId;

    /** 故障编号 */
    private Long fId;

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
