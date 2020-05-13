package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class RecordDetailVO {

    /**
     * 用户ID
     */
    @NotBlank
    private String userId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工时，小时
     */
    @NotBlank
    @Min(value = 0, message = "工时最小为0")
    @Max(value = 24,message = "工时最大为24")
    @Digits(integer=2, fraction=2, message = "工时必须为数字，且最多2位小数")
    private String wkHour;


}
