package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
@Data
public class AddDiaryReqVO extends BaseReq {

    @NotBlank
    private String projectNo;
    @NotBlank
    private String wkDate;
    @NotBlank
    private String diary;
}
