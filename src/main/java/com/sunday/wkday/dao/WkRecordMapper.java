package com.sunday.wkday.dao;

import com.sunday.wkday.entity.WkRecord;
import com.sunday.wkday.entity.WkRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WkRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    long countByExample(WkRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int deleteByExample(WkRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int insert(WkRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int insertSelective(WkRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    List<WkRecord> selectByExample(WkRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    WkRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") WkRecord record, @Param("example") WkRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") WkRecord record, @Param("example") WkRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(WkRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wk_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(WkRecord record);
}