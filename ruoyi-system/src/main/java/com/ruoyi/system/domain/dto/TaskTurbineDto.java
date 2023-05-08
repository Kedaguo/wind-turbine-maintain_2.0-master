package com.ruoyi.system.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.TaskTurbine;
import lombok.Data;

@Data
public class TaskTurbineDto extends TaskTurbine {

    private String fMaintainDuration;
    private String mMaintainDuration;
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

    /** 品牌 */
    @Excel(name = "品牌")
    private String tBrand;

    /** 经度 */
    @Excel(name = "经度")
    private String tLongitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String tLatitude;

}
