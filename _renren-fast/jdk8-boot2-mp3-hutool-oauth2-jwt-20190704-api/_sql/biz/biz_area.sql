/*
Navicat MySQL Data Transfer

Source Server         : locahost（本地）
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : renren_fast

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-07-24 08:50:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_area
-- ----------------------------
DROP TABLE IF EXISTS `biz_area`;
CREATE TABLE `biz_area` (
  `area_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '城市ID',
  `name` varchar(255) NOT NULL COMMENT '地区名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级地区id',
  `code` varchar(255) DEFAULT NULL COMMENT '通用编码',
  `custom_code` varchar(255) DEFAULT NULL COMMENT '自定义编码',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0 否; 1: 是)',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of biz_area
-- ----------------------------

-- ----------------------------
-- Table structure for biz_company
-- ----------------------------
DROP TABLE IF EXISTS `biz_company`;
CREATE TABLE `biz_company` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司ID',
  `company_name` varchar(255) NOT NULL,
  `area_id` bigint(20) NOT NULL COMMENT '公司所在地区',
  `sign_person` varchar(255) NOT NULL COMMENT '签约人',
  `sign_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '签约时间',
  `gmt_create` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of biz_company
-- ----------------------------
INSERT INTO `biz_company` VALUES ('1', '豪大大鸡排', '1', '豪大大', '2019-07-23 20:23:57', '2019-07-23 20:23:59', '2019-07-23 20:24:03');

-- ----------------------------
-- Table structure for biz_company_complaint
-- ----------------------------
DROP TABLE IF EXISTS `biz_company_complaint`;
CREATE TABLE `biz_company_complaint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `complaint_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of biz_company_complaint
-- ----------------------------

-- ----------------------------
-- Table structure for biz_company_violation
-- ----------------------------
DROP TABLE IF EXISTS `biz_company_violation`;
CREATE TABLE `biz_company_violation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `violation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of biz_company_violation
-- ----------------------------

-- ----------------------------
-- Table structure for biz_complaint
-- ----------------------------
DROP TABLE IF EXISTS `biz_complaint`;
CREATE TABLE `biz_complaint` (
  `complaint_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reporter_name` varchar(255) NOT NULL,
  `reporter_company` varchar(255) NOT NULL COMMENT '投诉人工作单位',
  `mobile` varchar(20) NOT NULL COMMENT '投诉人手机号',
  `content` text NOT NULL COMMENT '投诉内容',
  `attachment_url` varchar(255) DEFAULT NULL COMMENT '附件地址',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of biz_complaint
-- ----------------------------

-- ----------------------------
-- Table structure for biz_violation
-- ----------------------------
DROP TABLE IF EXISTS `biz_violation`;
CREATE TABLE `biz_violation` (
  `violation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `treat_method` varchar(255) NOT NULL COMMENT '处理办法',
  `description` varchar(255) NOT NULL COMMENT '违约情况描述',
  `violation_date` datetime NOT NULL COMMENT '违约日期',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示到网站(0: 否; 1: 是 )',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0: 否；1: 是)',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`violation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of biz_violation
-- ----------------------------
