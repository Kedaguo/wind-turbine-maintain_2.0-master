package com.ruoyi.system.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.TaskOperator;
import lombok.Data;

@Data
public class TaskOperatorDto extends TaskOperator {

    /** 维修类型 */
    private Integer oType;

    /** 维修工每年薪资 */
    private Long oAnnualsalar;

    /** 普通时薪 */
    private Long oCommonhourlywage;

    /** 加班时薪 */
    private Long oOvertimehourlywage;
}
