package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.TurbineWind;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TurbineWindVo extends TurbineWind {

   private Long mId;
   private Long fId;

}
