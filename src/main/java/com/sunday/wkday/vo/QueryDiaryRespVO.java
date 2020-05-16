package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
@Data
public class QueryDiaryRespVO {

    private List<String> wkDateList;

    private List<List<DiaryDetailVO>> diaryLists;

}
