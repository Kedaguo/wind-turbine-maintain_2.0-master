package com.ruoyi.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.RepairOrder;
import com.ruoyi.system.domain.TurbineWind;
import lombok.Data;

import java.util.Date;

@Data
public class RepairOrderFaultDto extends TurbineWind {
    /** 维修单编号 **/
    private Long rId;

    /** 维修单类型 保养or故障  1 故障  2 保养 **/
    private Integer rType;

    /** 任务编号 */
    private Long taskId;

    /** 用户学号 */
    private String userName;

    /** 派单状态 */
    private Integer rState;

    /**  发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rCreateTime;

    /** 维修成本 */
    private Long fConsumableCost;

    /** 维修时间 */
    private String fMaintainDuration;

    /** 维修人员数量 */
    private Long fPeopleNumber;

    /** 故障类型 */
    private Long fFaultType;

    /** 故障设备 */
    private String fFaultDevice;

}
