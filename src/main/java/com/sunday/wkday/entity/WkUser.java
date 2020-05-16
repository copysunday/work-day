package com.sunday.wkday.entity;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wk_user
 */
public class WkUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   用户ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.open_id
     *
     * @mbg.generated
     */
    private String openId;

    /**
     * Database Column Remarks:
     *   盐值，每次重新登陆刷新
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.user_token
     *
     * @mbg.generated
     */
    private String userToken;

    /**
     * Database Column Remarks:
     *   token过期时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.expire_time
     *
     * @mbg.generated
     */
    private Date expireTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   头像地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wk_user.avatar_url
     *
     * @mbg.generated
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_user
     *
     * @mbg.generated
     */
    public WkUser(Long id, String openId, String userToken, Date expireTime, String userId, Date createTime, Date updateTime, String userName, String avatarUrl) {
        this.id = id;
        this.openId = openId;
        this.userToken = userToken;
        this.expireTime = expireTime;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_user
     *
     * @mbg.generated
     */
    public WkUser() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.id
     *
     * @return the value of wk_user.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.id
     *
     * @param id the value for wk_user.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.open_id
     *
     * @return the value of wk_user.open_id
     *
     * @mbg.generated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.open_id
     *
     * @param openId the value for wk_user.open_id
     *
     * @mbg.generated
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.user_token
     *
     * @return the value of wk_user.user_token
     *
     * @mbg.generated
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.user_token
     *
     * @param userToken the value for wk_user.user_token
     *
     * @mbg.generated
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken == null ? null : userToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.expire_time
     *
     * @return the value of wk_user.expire_time
     *
     * @mbg.generated
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.expire_time
     *
     * @param expireTime the value for wk_user.expire_time
     *
     * @mbg.generated
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.user_id
     *
     * @return the value of wk_user.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.user_id
     *
     * @param userId the value for wk_user.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.create_time
     *
     * @return the value of wk_user.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.create_time
     *
     * @param createTime the value for wk_user.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.update_time
     *
     * @return the value of wk_user.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.update_time
     *
     * @param updateTime the value for wk_user.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.user_name
     *
     * @return the value of wk_user.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.user_name
     *
     * @param userName the value for wk_user.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wk_user.avatar_url
     *
     * @return the value of wk_user.avatar_url
     *
     * @mbg.generated
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wk_user.avatar_url
     *
     * @param avatarUrl the value for wk_user.avatar_url
     *
     * @mbg.generated
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}