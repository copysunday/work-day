package com.sunday.wkday.vo;

import lombok.Data;

import java.util.List;

@Data
public class GetLogListRespVO {

    /**
     * 日期 09-01
     */
    private String simpleDate;

    /**
     * 最小id
     */
    private Long minId;

    /**
     * 总数
     */
    private Integer count;

    /**
     * 日志分组
     */
    private List<LogDetail> logDetails;

    @Data
    public static class LogDetail {
        /**
         * 时间 23:21
         */
        private String simpleTime;

        /**
         * 日志详情
         */
        private String detail;
    }
}
