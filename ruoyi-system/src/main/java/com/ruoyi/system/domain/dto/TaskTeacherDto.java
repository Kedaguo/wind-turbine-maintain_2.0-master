package com.ruoyi.system.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.domain.TaskStudent;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TaskTeacherDto{

    /** 任务编号 */
    private Long taskId;

    private String taskCreateBy;

    private String userName;

    private String nickName;

    /** 任务成本 */
    private Long taskCost;

    /** 总发电量 */
    private Long taskCharge;

    /** 任务时间 */
    private String taskTime;

    /** 任务成绩 */
    private Long taskPoints;

    /** 任务状态 */
    private Long taskState;

    private List<TaskTeacherDto> children;

}
