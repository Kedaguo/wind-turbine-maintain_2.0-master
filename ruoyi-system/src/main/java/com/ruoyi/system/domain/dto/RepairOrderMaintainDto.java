package com.ruoyi.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.TurbineWind;
import lombok.Data;

import java.util.Date;

@Data
public class RepairOrderMaintainDto extends TurbineWind {

    /** 维修单编号 */
    private Long rId;

    /** 任务编号 */
    private Long taskId;

    /** 用户编号 */
    private String userName;

    /** 派单状态 */
    private Integer rState;

    /**  发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rCreateTime;

    /** 每年保养频率 */
    private String mFrequencyPerYear;

    /** 保养成本 */
    private Long mConsumableCost;

    /** 保养时间 */
    private String mMaintainDuration;

    /** 保养人员 */
    private Long mPeopleNumber;

    /** 保养类型 */
    private Long mFaultType;

    /** 保养设备 */
    private String mFaultDevice;

}
