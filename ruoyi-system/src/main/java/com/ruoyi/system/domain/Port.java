package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 港口信息对象 tl_port
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
public class Port extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 港口编号 */
    private Long pId;

    /** 码头名称 */
    @Excel(name = "码头名称")
    private String pPortname;

    /** 经度 */
    @Excel(name = "经度")
    private String pLongitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String pLatitude;

    /** 每天成本 */
    @Excel(name = "每天成本")
    private Long pCostperday;

    /** 成本 */
    @Excel(name = "成本")
    private Long pFixdcost;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long pCostPerVessalCall;

    /** 天气限制 */
    @Excel(name = "天气限制")
    private Long pWeatherLimit;

    /** 港口深度 */
    @Excel(name = "港口深度")
    private Long pDepthPort;

    /** 船只交通控制 */
    @Excel(name = "船只交通控制")
    private String pTrafficControl;

    public void setpId(Long pId) 
    {
        this.pId = pId;
    }

    public Long getpId() 
    {
        return pId;
    }
    public void setpPortname(String pPortname) 
    {
        this.pPortname = pPortname;
    }

    public String getpPortname() 
    {
        return pPortname;
    }
    public void setpLongitude(String pLongitude) 
    {
        this.pLongitude = pLongitude;
    }

    public String getpLongitude() 
    {
        return pLongitude;
    }
    public void setpLatitude(String pLatitude) 
    {
        this.pLatitude = pLatitude;
    }

    public String getpLatitude() 
    {
        return pLatitude;
    }
    public void setpCostperday(Long pCostperday) 
    {
        this.pCostperday = pCostperday;
    }

    public Long getpCostperday() 
    {
        return pCostperday;
    }
    public void setpFixdcost(Long pFixdcost) 
    {
        this.pFixdcost = pFixdcost;
    }

    public Long getpFixdcost() 
    {
        return pFixdcost;
    }
    public void setpCostPerVessalCall(Long pCostPerVessalCall) 
    {
        this.pCostPerVessalCall = pCostPerVessalCall;
    }

    public Long getpCostPerVessalCall() 
    {
        return pCostPerVessalCall;
    }
    public void setpWeatherLimit(Long pWeatherLimit) 
    {
        this.pWeatherLimit = pWeatherLimit;
    }

    public Long getpWeatherLimit() 
    {
        return pWeatherLimit;
    }
    public void setpDepthPort(Long pDepthPort) 
    {
        this.pDepthPort = pDepthPort;
    }

    public Long getpDepthPort() 
    {
        return pDepthPort;
    }
    public void setpTrafficControl(String pTrafficControl) 
    {
        this.pTrafficControl = pTrafficControl;
    }

    public String getpTrafficControl() 
    {
        return pTrafficControl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pId", getpId())
            .append("pPortname", getpPortname())
            .append("pLongitude", getpLongitude())
            .append("pLatitude", getpLatitude())
            .append("pCostperday", getpCostperday())
            .append("pFixdcost", getpFixdcost())
            .append("pCostPerVessalCall", getpCostPerVessalCall())
            .append("pWeatherLimit", getpWeatherLimit())
            .append("pDepthPort", getpDepthPort())
            .append("pTrafficControl", getpTrafficControl())
            .toString();
    }
}
