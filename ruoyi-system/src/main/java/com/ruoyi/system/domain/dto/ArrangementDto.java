package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.Arrangement;
import lombok.Data;

@Data
public class ArrangementDto extends Arrangement {
    //用户学号
    private String userName;

    private String pPortName;
}
