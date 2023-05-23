package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.Arrangement;
import lombok.Data;

import java.util.List;

@Data
public class SysJobVo {


    private String invokeTarget;

    private Long jobId;

    private String status;

    private Long taskId;

}
