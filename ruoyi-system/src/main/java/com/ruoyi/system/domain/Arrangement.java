package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * arrangement对象 tl_arrangement
 * 
 * @author JianDa
 * @date 2023-04-14
 */
@TableName(value = "tl_arrangement")
public class Arrangement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规划编号 */
    private Long aId;

    /** 港口编号 */
    @Excel(name = "港口编号")
    private Long pId;

    /** 维修人员数量 */
    @Excel(name = "维修人员数量")
    private Long oNum;

    /** 船舶数量 */
    @Excel(name = "船舶数量")
    private Long bId;

    /** 船舶类型 */
    @Excel(name = "船舶类型")
    private Integer bType;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 规划名称 */
    @Excel(name = "规划名称")
    private String aName;

    /** 规划状态(0 未开始  1进行中  2结束) */
    @Excel(name = "规划状态")
    private Integer aState;

    /** 始点 */
    @Excel(name = "始点")
    private String startLocation;

    /** 终点 */
    @Excel(name = "终点")
    private String endLocation;

    /** 开始仿真模拟时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始仿真模拟时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束仿真模拟时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束仿真模拟时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 发布时间 */
    @Excel(name = "发布时间")
    private String aCreateTime;

    public Integer getaState () {
        return aState;
    }

    public void setaState (Integer aState) {
        this.aState = aState;
    }

    public void setaId(Long aId)
    {
        this.aId = aId;
    }

    public Long getaId() 
    {
        return aId;
    }
    public void setpId(Long pId) 
    {
        this.pId = pId;
    }

    public Long getpId() 
    {
        return pId;
    }
    public void setoNum(Long oNum) 
    {
        this.oNum = oNum;
    }

    public Long getoNum() 
    {
        return oNum;
    }

    public Long getbId () {
        return bId;
    }

    public void setbId (Long bId) {
        this.bId = bId;
    }

    public void setbType(Integer bType)
    {
        this.bType = bType;
    }

    public Integer getbType() 
    {
        return bType;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setaName(String aName) 
    {
        this.aName = aName;
    }

    public String getaName() 
    {
        return aName;
    }
    public void setStartLocation(String startLocation) 
    {
        this.startLocation = startLocation;
    }

    public String getStartLocation() 
    {
        return startLocation;
    }
    public void setEndLocation(String endLocation) 
    {
        this.endLocation = endLocation;
    }

    public String getEndLocation() 
    {
        return endLocation;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setaCreateTime(String aCreateTime) 
    {
        this.aCreateTime = aCreateTime;
    }

    public String getaCreateTime() 
    {
        return aCreateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aId", getaId())
            .append("pId", getpId())
            .append("oNum", getoNum())
            .append("bId", getbId())
            .append("bType", getbType())
            .append("userId", getUserId())
            .append("taskId", getTaskId())
            .append("aName", getaName())
            .append("startLocation", getStartLocation())
            .append("endLocation", getEndLocation())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("aCreateTime", getaCreateTime())
            .toString();
    }
}
