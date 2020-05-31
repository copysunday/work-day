package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
@Data
public class UpdateDiaryReqVO extends BaseReq {
    @NotBlank
    private String diaryNo;
    @NotBlank
    private String diary;
}
