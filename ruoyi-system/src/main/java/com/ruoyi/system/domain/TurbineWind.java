package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * windTurbine对象 tl_turbine_wind
 * 
 * @author 赵建达
 * @date 2023-03-15
 */
public class TurbineWind extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long tId;

    /** 模型 */
    @Excel(name = "模型")
    private Long tModel;

    /** 价格 */
    @Excel(name = "价格")
    private Long tPrice;

    /** 功率 */
    @Excel(name = "功率")
    private String tRatePower;

    /** 功率速度曲线 */
    @Excel(name = "功率速度曲线")
    private Long tPowerVelocityCurve;

    /** 高度 */
    @Excel(name = "高度")
    private Long tHeight;

    /** 寿命 */
    @Excel(name = "寿命")
    private Long tLifetime;

    /** 成本 */
    @Excel(name = "成本")
    private Long tCost;

    /** 经度 */
    @Excel(name = "经度")
    private String tLongitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String tLatitude;

    /** 品牌 */
    @Excel(name = "品牌")
    private String tBrand;

    /** 故障状态 */
    @Excel(name = "故障状态")
    private Long tFaultState;

    /** 保养状态 */
    @Excel(name = "保养状态")
    private Long tMaintainState;



    public void settId(Long tId)
    {
        this.tId = tId;
    }

    public Long gettId() 
    {
        return tId;
    }
    public void settModel(Long tModel) 
    {
        this.tModel = tModel;
    }

    public Long gettModel() 
    {
        return tModel;
    }
    public void settPrice(Long tPrice) 
    {
        this.tPrice = tPrice;
    }

    public Long gettPrice() 
    {
        return tPrice;
    }
    public void settRatePower(String tRatePower) 
    {
        this.tRatePower = tRatePower;
    }

    public String gettRatePower() 
    {
        return tRatePower;
    }
    public void settPowerVelocityCurve(Long tPowerVelocityCurve) 
    {
        this.tPowerVelocityCurve = tPowerVelocityCurve;
    }

    public Long gettPowerVelocityCurve() 
    {
        return tPowerVelocityCurve;
    }
    public void settHeight(Long tHeight) 
    {
        this.tHeight = tHeight;
    }

    public Long gettHeight() 
    {
        return tHeight;
    }
    public void settLifetime(Long tLifetime) 
    {
        this.tLifetime = tLifetime;
    }

    public Long gettLifetime() 
    {
        return tLifetime;
    }
    public void settCost(Long tCost) 
    {
        this.tCost = tCost;
    }

    public Long gettCost() 
    {
        return tCost;
    }
    public void settLongitude(String tLongitude) 
    {
        this.tLongitude = tLongitude;
    }

    public String gettLongitude() 
    {
        return tLongitude;
    }
    public void settLatitude(String tLatitude) 
    {
        this.tLatitude = tLatitude;
    }

    public String gettLatitude() 
    {
        return tLatitude;
    }
    public void settBrand(String tBrand) 
    {
        this.tBrand = tBrand;
    }

    public String gettBrand() 
    {
        return tBrand;
    }
    public void settFaultState(Long tFaultState) 
    {
        this.tFaultState = tFaultState;
    }

    public Long gettFaultState() 
    {
        return tFaultState;
    }
    public void settMaintainState(Long tMaintainState) 
    {
        this.tMaintainState = tMaintainState;
    }

    public Long gettMaintainState() 
    {
        return tMaintainState;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tId", gettId())
            .append("tModel", gettModel())
            .append("tPrice", gettPrice())
            .append("tRatePower", gettRatePower())
            .append("tPowerVelocityCurve", gettPowerVelocityCurve())
            .append("tHeight", gettHeight())
            .append("tLifetime", gettLifetime())
            .append("tCost", gettCost())
            .append("tLongitude", gettLongitude())
            .append("tLatitude", gettLatitude())
            .append("tBrand", gettBrand())
            .append("tFaultState", gettFaultState())
            .append("tMaintainState", gettMaintainState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
