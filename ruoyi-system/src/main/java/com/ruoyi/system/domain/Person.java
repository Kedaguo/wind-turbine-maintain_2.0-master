package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * person对象 tl_person
 * 
 * @author 赵建达
 * @date 2023-03-24
 */
public class Person extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员编号 */
    private Long id;

    /** 工作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pWorkTime;

    /** 薪资 */
    @Excel(name = "薪资")
    private Long pSalary;

    /** 人员总数 */
    @Excel(name = "人员总数")
    private Long pNum;

    /** 人员状态 */
    @Excel(name = "人员状态")
    private Integer pState;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setpWorkTime(Date pWorkTime) 
    {
        this.pWorkTime = pWorkTime;
    }

    public Date getpWorkTime() 
    {
        return pWorkTime;
    }
    public void setpSalary(Long pSalary) 
    {
        this.pSalary = pSalary;
    }

    public Long getpSalary() 
    {
        return pSalary;
    }
    public void setpNum(Long pNum) 
    {
        this.pNum = pNum;
    }

    public Long getpNum() 
    {
        return pNum;
    }
    public void setpState(Integer pState) 
    {
        this.pState = pState;
    }

    public Integer getpState() 
    {
        return pState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pWorkTime", getpWorkTime())
            .append("pSalary", getpSalary())
            .append("pNum", getpNum())
            .append("pState", getpState())
            .toString();
    }
}
