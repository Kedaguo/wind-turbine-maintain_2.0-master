package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangementRepair对象 tl_arrangement_repair
 * 
 * @author JianDa
 * @date 2023-04-13
 */
public class ArrangementRepair extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划关联维修单编号 */
    private Long arId;

    /** 规划编号 */
    @Excel(name = "规划编号")
    private Long aId;

    /** 维修单编号 */
    @Excel(name = "维修单编号")
    private Long rId;

    /** 路径维修顺序 */
    @Excel(name = "路径维修顺序")
    private Long sequence;

    public void setArId(Long arId) 
    {
        this.arId = arId;
    }

    public Long getArId() 
    {
        return arId;
    }
    public void setaId(Long aId) 
    {
        this.aId = aId;
    }

    public Long getaId() 
    {
        return aId;
    }
    public void setrId(Long rId) 
    {
        this.rId = rId;
    }

    public Long getrId() 
    {
        return rId;
    }
    public void setSequence(Long sequence) 
    {
        this.sequence = sequence;
    }

    public Long getSequence() 
    {
        return sequence;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("arId", getArId())
            .append("aId", getaId())
            .append("rId", getrId())
            .append("sequence", getSequence())
            .toString();
    }
}
