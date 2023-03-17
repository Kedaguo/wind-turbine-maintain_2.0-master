package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小组分配风机对象 sys_group_turbine
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
public class SysGroupTurbine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 小组编号 */
    private Long groupId;

    /** 风机编号 */
    private Long tId;

    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void settId(Long tId) 
    {
        this.tId = tId;
    }

    public Long gettId() 
    {
        return tId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupId", getGroupId())
            .append("tId", gettId())
            .toString();
    }
}
