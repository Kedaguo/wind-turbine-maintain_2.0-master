package com.ruoyi.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.Boat;
import com.ruoyi.system.domain.TaskBoat;
import lombok.Data;

import java.util.Date;

@Data
public class TaskBoatDto extends TaskBoat {

    /** 船舶模型 */
    private String bModel;

    /** 船舶速度 */
    private String bSpeed;

    /** 船舶容量 */
    private String bCapacity;

    /** 风速 */
    private String bWindSpeed;

    /** 海浪 */
    private String bWaveHeight;

    /** 每年成本 */
    private String bAnnualCost;

    /** 每小时成本 */
    private String bHourCost;

    /** 船舶类型 */
    private Integer bType;

}
