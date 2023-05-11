package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangementOperator对象 tl_arrangement_operator
 * 
 * @author JianDa
 * @date 2023-05-09
 */
@TableName(value = "tl_arrangement_operator")
public class ArrangementOperator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划编号 */
    private Long aId;

    /** 维修工编号 */
    private Long oId;

    public void setaId(Long aId) 
    {
        this.aId = aId;
    }

    public Long getaId() 
    {
        return aId;
    }
    public void setoId(Long oId) 
    {
        this.oId = oId;
    }

    public Long getoId() 
    {
        return oId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aId", getaId())
            .append("oId", getoId())
            .toString();
    }
}
