package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.Port;
import lombok.Data;

@Data
public class TaskPortDto extends Port {

    private Long taskId;

    private Long userId;

}
