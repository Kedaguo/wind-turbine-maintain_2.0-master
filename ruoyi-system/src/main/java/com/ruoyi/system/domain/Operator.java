package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * operator对象 tl_operator
 * 
 * @author JianDa
 * @date 2023-04-07
 */
@TableName("tl_operator")
public class Operator
{
    private static final long serialVersionUID = 1L;

    /** 维修工编号 */
    private Long oId;

    /** 维修类型 */
    @Excel(name = "维修类型")
    private Integer oType;

    /** 维修工每年薪资 */
    @Excel(name = "维修工每年薪资")
    private Long oAnnualsalar;

    /** 普通时薪 */
    @Excel(name = "普通时薪")
    private Long oCommonhourlywage;

    /** 加班时薪 */
    @Excel(name = "加班时薪")
    private Long oOvertimehourlywage;

    public void setoId(Long oId) 
    {
        this.oId = oId;
    }

    public Long getoId() 
    {
        return oId;
    }
    public void setoType(Integer oType) 
    {
        this.oType = oType;
    }

    public Integer getoType() 
    {
        return oType;
    }
    public void setoAnnualsalar(Long oAnnualsalar) 
    {
        this.oAnnualsalar = oAnnualsalar;
    }

    public Long getoAnnualsalar() 
    {
        return oAnnualsalar;
    }
    public void setoCommonhourlywage(Long oCommonhourlywage) 
    {
        this.oCommonhourlywage = oCommonhourlywage;
    }

    public Long getoCommonhourlywage() 
    {
        return oCommonhourlywage;
    }
    public void setoOvertimehourlywage(Long oOvertimehourlywage) 
    {
        this.oOvertimehourlywage = oOvertimehourlywage;
    }

    public Long getoOvertimehourlywage() 
    {
        return oOvertimehourlywage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oId", getoId())
            .append("oType", getoType())
            .append("oAnnualsalar", getoAnnualsalar())
            .append("oCommonhourlywage", getoCommonhourlywage())
            .append("oOvertimehourlywage", getoOvertimehourlywage())
            .toString();
    }
}
