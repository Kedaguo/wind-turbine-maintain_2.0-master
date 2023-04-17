package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangementBoat对象 tl_arrangement_boat
 * 
 * @author JianDa
 * @date 2023-04-14
 */
public class ArrangementBoat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划编号 */
    private Long aId;

    /** 船舶编号 */
    private Long bId;

    public void setaId(Long aId) 
    {
        this.aId = aId;
    }

    public Long getaId() 
    {
        return aId;
    }
    public void setbId(Long bId) 
    {
        this.bId = bId;
    }

    public Long getbId() 
    {
        return bId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aId", getaId())
            .append("bId", getbId())
            .toString();
    }
}
