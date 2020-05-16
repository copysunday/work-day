package com.sunday.wkday.entity;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wk_diary
 */
public class WkDiary {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.diary_no
     *
     * @mbg.generated
     */
    private String diaryNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.project_no
     *
     * @mbg.generated
     */
    private String projectNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * Database Column Remarks:
     *   工作天
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.wk_date
     *
     * @mbg.generated
     */
    private String wkDate;

    /**
     * Database Column Remarks:
     *   日记内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.diary
     *
     * @mbg.generated
     */
    private String diary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_diary.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_diary
     *
     * @mbg.generated
     */
    public WkDiary(Long id, String diaryNo, String projectNo, String userId, String wkDate, String diary, Date createTime, Date updateTime) {
        this.id = id;
        this.diaryNo = diaryNo;
        this.projectNo = projectNo;
        this.userId = userId;
        this.wkDate = wkDate;
        this.diary = diary;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_diary
     *
     * @mbg.generated
     */
    public WkDiary() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.id
     *
     * @return the value of wk_diary.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.id
     *
     * @param id the value for wk_diary.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.diary_no
     *
     * @return the value of wk_diary.diary_no
     *
     * @mbg.generated
     */
    public String getDiaryNo() {
        return diaryNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.diary_no
     *
     * @param diaryNo the value for wk_diary.diary_no
     *
     * @mbg.generated
     */
    public void setDiaryNo(String diaryNo) {
        this.diaryNo = diaryNo == null ? null : diaryNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.project_no
     *
     * @return the value of wk_diary.project_no
     *
     * @mbg.generated
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.project_no
     *
     * @param projectNo the value for wk_diary.project_no
     *
     * @mbg.generated
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.user_id
     *
     * @return the value of wk_diary.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.user_id
     *
     * @param userId the value for wk_diary.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.wk_date
     *
     * @return the value of wk_diary.wk_date
     *
     * @mbg.generated
     */
    public String getWkDate() {
        return wkDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.wk_date
     *
     * @param wkDate the value for wk_diary.wk_date
     *
     * @mbg.generated
     */
    public void setWkDate(String wkDate) {
        this.wkDate = wkDate == null ? null : wkDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.diary
     *
     * @return the value of wk_diary.diary
     *
     * @mbg.generated
     */
    public String getDiary() {
        return diary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.diary
     *
     * @param diary the value for wk_diary.diary
     *
     * @mbg.generated
     */
    public void setDiary(String diary) {
        this.diary = diary == null ? null : diary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.create_time
     *
     * @return the value of wk_diary.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.create_time
     *
     * @param createTime the value for wk_diary.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_diary.update_time
     *
     * @return the value of wk_diary.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_diary.update_time
     *
     * @param updateTime the value for wk_diary.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}