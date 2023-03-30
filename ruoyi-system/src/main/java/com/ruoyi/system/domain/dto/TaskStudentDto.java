package com.ruoyi.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.TaskStudent;
import lombok.Data;

import java.util.Date;

@Data
public class TaskStudentDto extends TaskStudent {
    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd yyyy-MM-dd HH:mm")
    private Date taskStartTime;

    /** 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd yyyy-MM-dd HH:mm")
    private Date taskEndTime;

    /** 任务发布时间 */
    @Excel(name = "任务发布时间", width = 30, dateFormat = "yyyy-MM-dd yyyy-MM-dd HH:mm")
    private String taskCreateTime;

    /** 发布人 */
    @Excel(name = "发布人")
    private String taskCreateBy;
}
