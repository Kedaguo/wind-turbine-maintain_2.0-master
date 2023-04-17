package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.Arrangement;
import lombok.Data;

import java.util.List;

@Data
public class ArrangementVo extends Arrangement {


    //维修单顺序
    private List<Long> arrangementRepairIds;

    private Long oId;

}
