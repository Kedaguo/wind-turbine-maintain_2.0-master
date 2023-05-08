package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * rand对象 tl_rand
 * 
 * @author JianDa
 * @date 2023-05-06
 */
public class Rand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 随机数编号 */
    private Long randId;

    /** 随机数 */
    @Excel(name = "随机数")
    private String rand;

    public Long getRandId () {
        return randId;
    }

    public void setRandId (Long randId) {
        this.randId = randId;
    }

    public String getRand () {
        return rand;
    }

    public void setRand (String rand) {
        this.rand = rand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("randId", getRandId())
            .append("rand", getRand())
            .toString();
    }
}
