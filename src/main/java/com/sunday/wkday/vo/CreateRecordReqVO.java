package com.sunday.wkday.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateRecordReqVO {

    /**
     * 项目ID
     */
    @NotBlank
    private String projectNo;

    /**
     * 工作日 e.g 2019-12-10
     */
    @NotBlank
    @Length(min=10, max=10)
    private String wkDate;

    /**
     * 操作人token
     */
    @NotBlank
    private String userToken;

    /**
     * 记录列表
     */
    @NotNull
    private List<RecordDetailVO> recordDetails;
}
