/*
 Navicat Premium Data Transfer

 Source Server         : my-test-db
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost
 Source Database       : work_day

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : utf-8

 Date: 02/02/2020 18:59:55 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `wk_member`
-- ----------------------------
DROP TABLE IF EXISTS `wk_member`;
CREATE TABLE `wk_member` (
  `id` bigint(9) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT NULL,
  `project_no` varchar(32) DEFAULT NULL,
  `user_type` tinyint(4) unsigned zerofill DEFAULT NULL COMMENT '0申请中 1观察者 2管理员',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_uid_pjno` (`user_id`,`project_no`),
  KEY `idx_pjno` (`project_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- ----------------------------
--  Table structure for `wk_op_log`
-- ----------------------------
DROP TABLE IF EXISTS `wk_op_log`;
CREATE TABLE `wk_op_log` (
  `id` bigint(9) NOT NULL AUTO_INCREMENT,
  `op_user_id` varchar(32) DEFAULT NULL,
  `project_no` varchar(32) DEFAULT '',
  `record_no` varchar(32) DEFAULT '',
  `op_type` tinyint(2) DEFAULT NULL COMMENT '1新增 2修改 3删除',
  `detail` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_record_no` (`record_no`),
  KEY `idx_pjno` (`project_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- ----------------------------
--  Table structure for `wk_project`
-- ----------------------------
DROP TABLE IF EXISTS `wk_project`;
CREATE TABLE `wk_project` (
  `id` bigint(9) NOT NULL AUTO_INCREMENT,
  `project_no` varchar(32) DEFAULT NULL,
  `project_name` varchar(50) DEFAULT NULL,
  `project_admin` varchar(32) DEFAULT NULL,
  `sub_admin` varchar(256) DEFAULT NULL,
  `project_status` tinyint(2) DEFAULT '0' COMMENT '1正常 -1关闭',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_pj_no` (`project_no`),
  KEY `idx_pj_admin` (`project_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- ----------------------------
--  Table structure for `wk_record`
-- ----------------------------
DROP TABLE IF EXISTS `wk_record`;
CREATE TABLE `wk_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `project_no` varchar(32) DEFAULT '',
  `record_no` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `wk_hour` decimal(5,2) DEFAULT NULL,
  `wk_date` varchar(10) DEFAULT NULL COMMENT '工作天',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_record_no` (`record_no`),
  UNIQUE KEY `idx_uid_pjno_wkdate` (`project_no`,`wk_date`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

DROP TABLE IF EXISTS `wk_diary`;
CREATE TABLE `wk_diary` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `diary_no` varchar(32) DEFAULT '',
  `project_no` varchar(32) DEFAULT '',
  `user_id` varchar(32) DEFAULT NULL,
  `wk_date` varchar(10) DEFAULT NULL COMMENT '工作天',
  `diary` varchar(500) DEFAULT '' COMMENT '日记内容',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_diary_no` (`diary_no`),
  KEY `idx_uid_date_pjno` (`user_id`,`project_no`,`wk_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- ----------------------------
--  Table structure for `wk_user`
-- ----------------------------
DROP TABLE IF EXISTS `wk_user`;
CREATE TABLE `wk_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `user_token` varchar(32) DEFAULT '' COMMENT '盐值，每次重新登陆刷新',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT 'token过期时间',
  `user_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_name` varchar(50) DEFAULT '',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_wker_id` (`user_id`),
  UNIQUE KEY `unq_usertoken` (`user_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

SET FOREIGN_KEY_CHECKS = 1;
