package com.sunday.wkday.dao;

import com.sunday.wkday.entity.WkMemberExt;
import com.sunday.wkday.entity.WkMemberProjectDetailExt;
import com.sunday.wkday.entity.WkMemberProjectExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WkMemberExtMapper {

    List<WkMemberExt> selectMemberDetail(String projectNo);

    List<WkMemberExt> selectBatchMemberDetail(@Param("projectNoList") List<String> projectNoList);

    List<WkMemberProjectExt> selectProjectDetail(String userId, Long minId, Integer step);

    List<WkMemberProjectDetailExt> selectProjectDetailByNo(String projectNo);
}
