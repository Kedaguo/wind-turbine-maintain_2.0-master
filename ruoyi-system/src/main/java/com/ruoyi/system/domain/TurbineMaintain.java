package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * turbineMaintain对象 tl_turbine_maintain
 * 
 * @author 赵建达
 * @date 2023-03-14
 */
public class TurbineMaintain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 风机编号 */
    private Long tId;

    /** 保养编号 */
    private Long mId;

    public void settId(Long tId) 
    {
        this.tId = tId;
    }

    public Long gettId() 
    {
        return tId;
    }
    public void setmId(Long mId) 
    {
        this.mId = mId;
    }

    public Long getmId() 
    {
        return mId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tId", gettId())
            .append("mId", getmId())
            .toString();
    }
}
