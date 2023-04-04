package com.ruoyi.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.TaskPerson;
import lombok.Data;

import java.util.Date;

@Data
public class TaskPersonDto extends TaskPerson {

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

}
