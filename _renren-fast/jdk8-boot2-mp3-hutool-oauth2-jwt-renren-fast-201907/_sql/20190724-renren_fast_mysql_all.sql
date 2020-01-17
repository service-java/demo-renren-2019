/*
Navicat MySQL Data Transfer
Source Server         : locahost（本地）
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : renren_fast
Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001
Date: 2019-07-24 08:55:11
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
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
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
  `company_name` varchar(255) NOT NULL COMMENT '公司名称',
  `area_id` bigint(20) NOT NULL COMMENT '公司所在地区ID',
  `sign_person` varchar(255) NOT NULL COMMENT '签约人',
  `sign_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '签约时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` bigint(20) NOT NULL COMMENT '公司ID',
  `complaint_id` bigint(20) NOT NULL COMMENT '投诉id',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '违约id',
  `company_id` bigint(20) NOT NULL COMMENT '公司id',
  `violation_id` bigint(20) NOT NULL COMMENT '违约id',
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
  `complaint_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '投诉id',
  `reporter_name` varchar(255) NOT NULL COMMENT '投诉人',
  `reporter_company` varchar(255) NOT NULL COMMENT '投诉人工作单位',
  `mobile` varchar(20) NOT NULL COMMENT '投诉人手机号',
  `content` text NOT NULL COMMENT '投诉内容',
  `attachment_url` varchar(255) DEFAULT NULL COMMENT '附件地址',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
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
  `violation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '违约id',
  `treat_method` varchar(255) NOT NULL COMMENT '处理办法',
  `description` varchar(255) NOT NULL COMMENT '违约情况描述',
  `violation_date` datetime NOT NULL COMMENT '违约日期',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示到网站(0: 否; 1: 是 )',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0: 否；1: 是)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`violation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of biz_violation
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016BBBC17BE07874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'DESKTOP-SK775161563885268154', '1563886637556', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1563886800000', '-1', '5', 'WAITING', 'CRON', '1562230730000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016BBBC17BE07874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'renren', '0 0/30 * * * ?', '0', '参数测试', '2019-07-04 14:52:28');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'renren', '0', null, '1', '2019-07-04 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('2', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('3', '1', 'testTask', 'renren', '0', null, '0', '2019-07-09 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('4', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('5', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('6', '1', 'testTask', 'renren', '0', null, '0', '2019-07-09 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('7', '1', 'testTask', 'renren', '0', null, '2', '2019-07-09 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('8', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('9', '1', 'testTask', 'renren', '0', null, '4', '2019-07-09 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('10', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('11', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('12', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('13', '1', 'testTask', 'renren', '0', null, '1', '2019-07-09 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('14', '1', 'testTask', 'renren', '0', null, '3', '2019-07-10 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('15', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('16', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('17', '1', 'testTask', 'renren', '0', null, '0', '2019-07-10 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('18', '1', 'testTask', 'renren', '0', null, '3', '2019-07-10 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('19', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('20', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('21', '1', 'testTask', 'renren', '0', null, '2', '2019-07-10 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('22', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('23', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('24', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('25', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('26', '1', 'testTask', 'renren', '0', null, '0', '2019-07-10 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('27', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('28', '1', 'testTask', 'renren', '0', null, '0', '2019-07-10 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('29', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('30', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('31', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('32', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('33', '1', 'testTask', 'renren', '0', null, '1', '2019-07-10 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('34', '1', 'testTask', 'renren', '0', null, '11', '2019-07-11 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('35', '1', 'testTask', 'renren', '0', null, '2', '2019-07-11 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('36', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('37', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('38', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('39', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('40', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('41', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('42', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('43', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('44', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('45', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('46', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('47', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('48', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('49', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('50', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('51', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('52', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('53', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('54', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('55', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('56', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('57', '1', 'testTask', 'renren', '0', null, '2', '2019-07-11 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('58', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('59', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('60', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('61', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('62', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('63', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('64', '1', 'testTask', 'renren', '0', null, '2', '2019-07-11 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('65', '1', 'testTask', 'renren', '0', null, '14', '2019-07-11 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('66', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('67', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('68', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('69', '1', 'testTask', 'renren', '0', null, '2', '2019-07-11 17:30:01');
INSERT INTO `schedule_job_log` VALUES ('70', '1', 'testTask', 'renren', '0', null, '9', '2019-07-11 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('71', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('72', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('73', '1', 'testTask', 'renren', '0', null, '14', '2019-07-11 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('74', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('75', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('76', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('77', '1', 'testTask', 'renren', '0', null, '0', '2019-07-11 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('78', '1', 'testTask', 'renren', '0', null, '1', '2019-07-11 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('79', '1', 'testTask', 'renren', '0', null, '9', '2019-07-18 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('80', '1', 'testTask', 'renren', '0', null, '2', '2019-07-19 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('81', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('82', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 12:43:20');
INSERT INTO `schedule_job_log` VALUES ('83', '1', 'testTask', 'renren', '0', null, '2', '2019-07-19 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('84', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('85', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('86', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('87', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('88', '1', 'testTask', 'renren', '0', null, '0', '2019-07-19 16:30:01');
INSERT INTO `schedule_job_log` VALUES ('89', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('90', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('91', '1', 'testTask', 'renren', '0', null, '2', '2019-07-19 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('92', '1', 'testTask', 'renren', '0', null, '0', '2019-07-19 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('93', '1', 'testTask', 'renren', '0', null, '1', '2019-07-19 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('94', '1', 'testTask', 'renren', '0', null, '31', '2019-07-21 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('95', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 00:00:01');
INSERT INTO `schedule_job_log` VALUES ('96', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('97', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('98', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('99', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('100', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('101', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('102', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('103', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 13:00:00');
INSERT INTO `schedule_job_log` VALUES ('104', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('105', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('106', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('107', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('108', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 16:00:03');
INSERT INTO `schedule_job_log` VALUES ('109', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 16:30:01');
INSERT INTO `schedule_job_log` VALUES ('110', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('111', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('112', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('113', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('114', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('115', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('116', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('117', '1', 'testTask', 'renren', '0', null, '2', '2019-07-22 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('118', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('119', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('120', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 23:00:00');
INSERT INTO `schedule_job_log` VALUES ('121', '1', 'testTask', 'renren', '0', null, '1', '2019-07-22 23:30:00');
INSERT INTO `schedule_job_log` VALUES ('122', '1', 'testTask', 'renren', '0', null, '92', '2019-07-23 00:00:00');
INSERT INTO `schedule_job_log` VALUES ('123', '1', 'testTask', 'renren', '0', null, '9', '2019-07-23 00:30:00');
INSERT INTO `schedule_job_log` VALUES ('124', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 01:00:00');
INSERT INTO `schedule_job_log` VALUES ('125', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 01:30:00');
INSERT INTO `schedule_job_log` VALUES ('126', '1', 'testTask', 'renren', '0', null, '2', '2019-07-23 02:00:00');
INSERT INTO `schedule_job_log` VALUES ('127', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 02:30:00');
INSERT INTO `schedule_job_log` VALUES ('128', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 03:00:00');
INSERT INTO `schedule_job_log` VALUES ('129', '1', 'testTask', 'renren', '0', null, '2', '2019-07-23 03:30:00');
INSERT INTO `schedule_job_log` VALUES ('130', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 04:00:00');
INSERT INTO `schedule_job_log` VALUES ('131', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 04:30:00');
INSERT INTO `schedule_job_log` VALUES ('132', '1', 'testTask', 'renren', '0', null, '4', '2019-07-23 05:00:00');
INSERT INTO `schedule_job_log` VALUES ('133', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 05:30:00');
INSERT INTO `schedule_job_log` VALUES ('134', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 06:00:00');
INSERT INTO `schedule_job_log` VALUES ('135', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 06:30:00');
INSERT INTO `schedule_job_log` VALUES ('136', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 07:00:00');
INSERT INTO `schedule_job_log` VALUES ('137', '1', 'testTask', 'renren', '0', null, '2', '2019-07-23 07:30:00');
INSERT INTO `schedule_job_log` VALUES ('138', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 08:00:00');
INSERT INTO `schedule_job_log` VALUES ('139', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 08:30:00');
INSERT INTO `schedule_job_log` VALUES ('140', '1', 'testTask', 'renren', '0', null, '3', '2019-07-23 09:00:00');
INSERT INTO `schedule_job_log` VALUES ('141', '1', 'testTask', 'renren', '0', null, '25', '2019-07-23 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('142', '1', 'testTask', 'renren', '0', null, '0', '2019-07-23 10:00:01');
INSERT INTO `schedule_job_log` VALUES ('143', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('144', '1', 'testTask', 'renren', '0', null, '2', '2019-07-23 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('145', '1', 'testTask', 'renren', '0', null, '2', '2019-07-23 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('146', '1', 'testTask', 'renren', '0', null, '2', '2019-07-23 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('147', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 17:00:01');
INSERT INTO `schedule_job_log` VALUES ('148', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('149', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('150', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 18:30:00');
INSERT INTO `schedule_job_log` VALUES ('151', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 19:00:00');
INSERT INTO `schedule_job_log` VALUES ('152', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 19:30:00');
INSERT INTO `schedule_job_log` VALUES ('153', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 20:00:00');
INSERT INTO `schedule_job_log` VALUES ('154', '1', 'testTask', 'renren', '0', null, '1', '2019-07-23 20:30:00');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('262a3b5b-16af-418b-892e-47e59aca2108', 'yepy4', '2019-07-18 14:20:20');
INSERT INTO `sys_captcha` VALUES ('4296bc20-7043-42e5-84e3-6f3381fc85ca', 'bcbp6', '2019-07-18 14:06:25');
INSERT INTO `sys_captcha` VALUES ('6a307da2-9005-415c-81b0-1bd7dddcc2d4', 'd3nnf', '2019-07-18 14:29:07');
INSERT INTO `sys_captcha` VALUES ('6b65ed6b-00fa-4a7a-878b-79b972b5a26e', 'xfaey', '2019-07-19 16:38:01');
INSERT INTO `sys_captcha` VALUES ('6bc99e84-9565-4ad0-841b-9ac43aec3ef2', 'nemf2', '2019-07-19 15:37:07');
INSERT INTO `sys_captcha` VALUES ('73d72bc4-23e2-4e83-871c-02393737c706', 'mdgn5', '2019-07-23 20:38:18');
INSERT INTO `sys_captcha` VALUES ('73e4ec81-132a-4688-8930-9a0de9ea47b7', '34m24', '2019-07-18 14:21:57');
INSERT INTO `sys_captcha` VALUES ('743c3f41-2fc4-4787-8b8d-564c056d4105', 'en555', '2019-07-18 14:09:52');
INSERT INTO `sys_captcha` VALUES ('8320c899-c8ba-4628-8bea-d7efc7095272', 'n3fne', '2019-07-23 14:13:20');
INSERT INTO `sys_captcha` VALUES ('8320c899-c8ba-4628-8bea-d7efc709527f', '834yg', '2019-07-23 14:13:01');
INSERT INTO `sys_captcha` VALUES ('a355f5c7-8db1-4c57-8772-8ea2f434942e', 'mna68', '2019-07-23 14:19:33');
INSERT INTO `sys_captcha` VALUES ('a3fb3781-46f8-4f3b-8dc9-57df4e0f8536', 'w4d4f', '2019-07-19 11:11:27');
INSERT INTO `sys_captcha` VALUES ('a7f2b6ea-afc4-4305-8d99-8924f62bf9cc', 'xgw4', '2019-07-23 20:42:00');
INSERT INTO `sys_captcha` VALUES ('aa9d1737-7887-4479-8668-6d6f87e237b3', '3x26f', '2019-07-19 12:06:31');
INSERT INTO `sys_captcha` VALUES ('aac51523-5c1b-40bf-818b-46275b422d38', 'ew4xd', '2019-07-23 14:12:49');
INSERT INTO `sys_captcha` VALUES ('c1e6e8e7-8328-4b96-82c0-1adc6beb3e7b', 'xxyam', '2019-07-23 20:39:04');
INSERT INTO `sys_captcha` VALUES ('c72757af-4ccd-4bf3-8d89-bea97e611b00', 'gn6fn', '2019-07-18 14:15:49');
INSERT INTO `sys_captcha` VALUES ('d08c3b4f-3b96-4bc5-851a-a425eb48d242', 'm7dpw', '2019-07-18 14:20:12');
INSERT INTO `sys_captcha` VALUES ('d1fb1e60-14c0-4717-8084-ff7d30ed5119', 'eyacb', '2019-07-19 15:35:45');
INSERT INTO `sys_captcha` VALUES ('d6ce7225-5451-4299-8fbe-23ac15ca5f54', 'x5ff5', '2019-07-18 14:03:21');
INSERT INTO `sys_captcha` VALUES ('da5016e5-c46c-417f-84de-4494aca546ba', '58n3p', '2019-07-18 14:50:51');
INSERT INTO `sys_captcha` VALUES ('e60bc347-0e2f-4f67-8090-6133f54d6929', '62wn6', '2019-07-11 08:42:37');
INSERT INTO `sys_captcha` VALUES ('f6b9c93f-1dc0-40a3-8f96-9a8480db1e11', 'gew5c', '2019-07-23 20:39:42');
INSERT INTO `sys_captcha` VALUES ('ff3a94c0-ff94-40a6-8808-a549f7c63e7b', 'wfnyn', '2019-07-19 12:07:44');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- 这一句必须初始化, 业务上暂时没处理好
INSERT INTO `sys_config` (`param_key`, `param_value`, `status`, `remark`) VALUES ('CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '保存角色', 'SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"云盘上传者\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[30,-666666,1],\"createTime\":\"Jul 4, 2019 2:53:46 PM\"}]', '206', '0:0:0:0:0:0:0:1', '2019-07-04 14:53:46');
INSERT INTO `sys_log` VALUES ('2', 'admin', '保存用户', 'SysUserController.save()', '[{\"userId\":2,\"username\":\"luo0412\",\"password\":\"55f98ae267e63ece32ebe35464f75430edc216e58be22b77ba3bfaae528f95cc\",\"salt\":\"UMPCEDPHefTT3ftctr2C\",\"email\":\"1095847440@qq.com\",\"mobile\":\"17816869505\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jul 4, 2019 2:54:23 PM\"}]', '71', '0:0:0:0:0:0:0:1', '2019-07-04 14:54:24');
INSERT INTO `sys_log` VALUES ('3', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":2,\"username\":\"luo0412\",\"password\":\"55f98ae267e63ece32ebe35464f75430edc216e58be22b77ba3bfaae528f95cc\",\"salt\":\"UMPCEDPHefTT3ftctr2C\",\"email\":\"1095847440@qq.com\",\"mobile\":\"17816869505\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '15', '0:0:0:0:0:0:0:1', '2019-07-04 14:54:44');
INSERT INTO `sys_log` VALUES ('4', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":0,\"roleIdList\":[],\"createUserId\":1}]', '60', '0:0:0:0:0:0:0:1', '2019-07-10 14:35:30');
INSERT INTO `sys_log` VALUES ('5', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root2@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', '270', '0:0:0:0:0:0:0:1', '2019-07-19 12:24:05');
INSERT INTO `sys_log` VALUES ('6', 'admin', '立即执行任务', 'ScheduleJobController.run()', '[[1]]', '797', '0:0:0:0:0:0:0:1', '2019-07-19 12:43:20');
INSERT INTO `sys_log` VALUES ('7', 'admin', '保存用户', 'SysUserController.save()', '[{\"userId\":3,\"username\":\"wyz\",\"password\":\"208fbd0cb8fd36601ca374ab22f6a9679d2864a8540237c2b81746d98ddb978d\",\"salt\":\"ocJ1hEE1KXhnCMtdTclm\",\"email\":\"1095847440@qq.com\",\"mobile\":\"17816869505\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jul 19, 2019 4:04:15 PM\"}]', '220', '0:0:0:0:0:0:0:1', '2019-07-19 16:04:16');
INSERT INTO `sys_log` VALUES ('8', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":3,\"username\":\"wyz\",\"salt\":\"ocJ1hEE1KXhnCMtdTclm\",\"email\":\"1095847440@qq.com\",\"mobile\":\"17816869505\",\"status\":0,\"roleIdList\":[1],\"createUserId\":1}]', '31', '0:0:0:0:0:0:0:1', '2019-07-19 16:06:56');
INSERT INTO `sys_log` VALUES ('9', 'admin', '删除用户', 'SysUserController.delete()', '[[3]]', '15', '0:0:0:0:0:0:0:1', '2019-07-19 16:07:27');
INSERT INTO `sys_log` VALUES ('10', 'admin', '保存角色', 'SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"普通管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[15,16,17,29,30,-666666,1,2],\"createTime\":\"Jul 19, 2019 4:09:10 PM\"}]', '83', '0:0:0:0:0:0:0:1', '2019-07-19 16:09:10');
INSERT INTO `sys_log` VALUES ('11', 'admin', '保存用户', 'SysUserController.save()', '[{\"userId\":4,\"username\":\"wyz\",\"password\":\"493f3bbf1d58885754b7049dceea079e457e1bbbb815a231a500129775672fd3\",\"salt\":\"t5yfDbq84dClDkGnyTfn\",\"email\":\"1095847440@qq.com\",\"mobile\":\"17816869505\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1,\"createTime\":\"Jul 19, 2019 4:09:52 PM\"}]', '12', '0:0:0:0:0:0:0:1', '2019-07-19 16:09:53');
INSERT INTO `sys_log` VALUES ('12', 'admin', '删除用户', 'SysUserController.delete()', '[[4]]', '153846', '0:0:0:0:0:0:0:1', '2019-07-19 16:12:38');
INSERT INTO `sys_log` VALUES ('13', 'admin', '保存用户', 'SysUserController.save()', '[{\"userId\":5,\"username\":\"wyz\",\"password\":\"6087c12e1a601db8e43c8cea700e082300dfe5f12f2e74e9f89e1063d9b10c29\",\"salt\":\"1FrVEZXwySuoKLukk5jx\",\"email\":\"1095847440@qq.com\",\"mobile\":\"17816869505\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1,\"createTime\":\"Jul 19, 2019 4:13:15 PM\"}]', '14', '0:0:0:0:0:0:0:1', '2019-07-19 16:13:15');
INSERT INTO `sys_log` VALUES ('14', 'admin', '删除用户', 'SysUserController.delete()', '[[5]]', '5511', '0:0:0:0:0:0:0:1', '2019-07-19 16:18:30');
INSERT INTO `sys_log` VALUES ('15', 'admin', '修改密码', 'SysUserController.password()', '[{\"password\":\"admin\",\"newPassword\":\"123456\"}]', '39', '0:0:0:0:0:0:0:1', '2019-07-19 16:31:31');
INSERT INTO `sys_log` VALUES ('16', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":2,\"username\":\"luo0412\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root2@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '16', '0:0:0:0:0:0:0:1', '2019-07-19 16:36:47');
INSERT INTO `sys_log` VALUES ('17', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":2,\"username\":\"luo0412\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root2@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1,2],\"createUserId\":1}]', '13', '0:0:0:0:0:0:0:1', '2019-07-19 16:36:52');
INSERT INTO `sys_log` VALUES ('18', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":2,\"username\":\"luo0412\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root2@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '16', '0:0:0:0:0:0:0:1', '2019-07-19 16:48:46');
INSERT INTO `sys_log` VALUES ('19', 'admin', '修改用户', 'SysUserController.update()', '[{\"userId\":2,\"username\":\"luo0412\",\"password\":\"cdac762d0ba79875489f6a8b430fa8b5dfe0cdd81da38b80f02f33328af7fd4a\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root2@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '13', '0:0:0:0:0:0:0:1', '2019-07-19 16:49:02');
INSERT INTO `sys_log` VALUES ('20', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":4,\"parentId\":1,\"name\":\"菜单(权限)管理\",\"url\":\"sys/menu\",\"type\":1,\"icon\":\"menu\",\"orderNum\":3}]', '11', '0:0:0:0:0:0:0:1', '2019-07-19 16:52:41');
INSERT INTO `sys_log` VALUES ('21', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":4,\"parentId\":1,\"name\":\"菜单/权限管理\",\"url\":\"sys/menu\",\"type\":1,\"icon\":\"menu\",\"orderNum\":3}]', '5', '0:0:0:0:0:0:0:1', '2019-07-19 16:53:04');
INSERT INTO `sys_log` VALUES ('22', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"后台管理系统\",\"type\":0,\"icon\":\"system\",\"orderNum\":0}]', '4', '0:0:0:0:0:0:0:1', '2019-07-19 16:55:22');
INSERT INTO `sys_log` VALUES ('23', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":0}]', '4', '0:0:0:0:0:0:0:1', '2019-07-19 16:55:39');
INSERT INTO `sys_log` VALUES ('24', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":31,\"parentId\":1,\"name\":\"地区\",\"url\":\"biz/area\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '15', '0:0:0:0:0:0:0:1', '2019-07-23 20:11:21');
INSERT INTO `sys_log` VALUES ('25', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":36,\"parentId\":1,\"name\":\"公司\",\"url\":\"biz/company\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '5', '0:0:0:0:0:0:0:1', '2019-07-23 20:11:32');
INSERT INTO `sys_log` VALUES ('26', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":41,\"parentId\":1,\"name\":\"投诉\",\"url\":\"biz/complaint\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '6', '0:0:0:0:0:0:0:1', '2019-07-23 20:11:41');
INSERT INTO `sys_log` VALUES ('27', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":46,\"parentId\":1,\"name\":\"违约\",\"url\":\"biz/violation\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2019-07-23 20:11:51');
INSERT INTO `sys_log` VALUES ('28', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":46,\"parentId\":1,\"name\":\"违约\",\"url\":\"biz/violation\",\"type\":1,\"icon\":\"tixing\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2019-07-23 20:12:22');
INSERT INTO `sys_log` VALUES ('29', 'admin', '修改角色', 'SysRoleController.update()', '[{\"roleId\":2,\"roleName\":\"普通管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[15,16,17,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,-666666,1,2]}]', '242', '0:0:0:0:0:0:0:1', '2019-07-23 20:15:28');
INSERT INTO `sys_log` VALUES ('30', 'admin', '保存角色', 'SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"投诉违约审查员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,-666666,1],\"createTime\":\"Jul 23, 2019 8:16:19 PM\"}]', '43', '0:0:0:0:0:0:0:1', '2019-07-23 20:16:19');
INSERT INTO `sys_log` VALUES ('31', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":31,\"parentId\":1,\"name\":\"地区\",\"url\":\"biz/area\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":6}]', '18', '0:0:0:0:0:0:0:1', '2019-07-23 20:26:03');
INSERT INTO `sys_log` VALUES ('32', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":36,\"parentId\":1,\"name\":\"公司\",\"url\":\"biz/company\",\"type\":1,\"icon\":\"shouye\",\"orderNum\":6}]', '6', '0:0:0:0:0:0:0:1', '2019-07-23 20:26:16');
INSERT INTO `sys_log` VALUES ('33', 'admin', '修改菜单', 'SysMenuController.update()', '[{\"menuId\":41,\"parentId\":1,\"name\":\"投诉\",\"url\":\"biz/complaint\",\"type\":1,\"icon\":\"bianji\",\"orderNum\":6}]', '8', '0:0:0:0:0:0:0:1', '2019-07-23 20:26:27');
INSERT INTO `sys_log` VALUES ('34', 'admin', '保存用户', 'SysUserController.save()', '[{\"userId\":6,\"username\":\"yz\",\"password\":\"f3d627c2c04b41f13cab99dcd6b9a57d5752e5b040509bcc3eaa7db7cf4aad4d\",\"salt\":\"ORavXlxgMsSumT94xG5b\",\"email\":\"1095847440@qq.com\",\"mobile\":\"17816869505\",\"status\":1,\"roleIdList\":[3],\"createUserId\":1,\"createTime\":\"Jul 23, 2019 8:32:42 PM\"}]', '211', '0:0:0:0:0:0:0:1', '2019-07-23 20:32:42');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单/权限管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', null, '1', 'sql', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'oss/oss', 'sys:oss:all', '1', 'oss', '6');
INSERT INTO `sys_menu` VALUES ('31', '1', '地区', 'biz/area', null, '1', 'daohang', '6');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'biz:area:list,biz:area:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'biz:area:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'biz:area:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'biz:area:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('36', '1', '公司', 'biz/company', null, '1', 'shouye', '6');
INSERT INTO `sys_menu` VALUES ('37', '36', '查看', null, 'biz:company:list,biz:company:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'biz:company:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'biz:company:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'biz:company:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('41', '1', '投诉', 'biz/complaint', null, '1', 'bianji', '6');
INSERT INTO `sys_menu` VALUES ('42', '41', '查看', null, 'biz:complaint:list,biz:complaint:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('43', '41', '新增', null, 'biz:complaint:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('44', '41', '修改', null, 'biz:complaint:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('45', '41', '删除', null, 'biz:complaint:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('46', '1', '违约', 'biz/violation', null, '1', 'tixing', '6');
INSERT INTO `sys_menu` VALUES ('47', '46', '查看', null, 'biz:violation:list,biz:violation:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('48', '46', '新增', null, 'biz:violation:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('49', '46', '修改', null, 'biz:violation:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('50', '46', '删除', null, 'biz:violation:delete', '2', null, '6');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'oss类型(0: 本地服务器; 1: 七牛云; 2: 阿里云; 3: 腾讯云)',
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='文件上传';



-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '云盘上传者', '', '1', '2019-07-04 14:53:46');
INSERT INTO `sys_role` VALUES ('2', '普通管理员', '', '1', '2019-07-19 16:09:10');
INSERT INTO `sys_role` VALUES ('3', '投诉违约审查员', '', '1', '2019-07-23 20:16:19');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '-666666');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('12', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('13', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('14', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('15', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('16', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('17', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('18', '2', '32');
INSERT INTO `sys_role_menu` VALUES ('19', '2', '33');
INSERT INTO `sys_role_menu` VALUES ('20', '2', '34');
INSERT INTO `sys_role_menu` VALUES ('21', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('22', '2', '36');
INSERT INTO `sys_role_menu` VALUES ('23', '2', '37');
INSERT INTO `sys_role_menu` VALUES ('24', '2', '38');
INSERT INTO `sys_role_menu` VALUES ('25', '2', '39');
INSERT INTO `sys_role_menu` VALUES ('26', '2', '40');
INSERT INTO `sys_role_menu` VALUES ('27', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('28', '2', '42');
INSERT INTO `sys_role_menu` VALUES ('29', '2', '43');
INSERT INTO `sys_role_menu` VALUES ('30', '2', '44');
INSERT INTO `sys_role_menu` VALUES ('31', '2', '45');
INSERT INTO `sys_role_menu` VALUES ('32', '2', '46');
INSERT INTO `sys_role_menu` VALUES ('33', '2', '47');
INSERT INTO `sys_role_menu` VALUES ('34', '2', '48');
INSERT INTO `sys_role_menu` VALUES ('35', '2', '49');
INSERT INTO `sys_role_menu` VALUES ('36', '2', '50');
INSERT INTO `sys_role_menu` VALUES ('37', '2', '-666666');
INSERT INTO `sys_role_menu` VALUES ('38', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('39', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('40', '3', '31');
INSERT INTO `sys_role_menu` VALUES ('41', '3', '32');
INSERT INTO `sys_role_menu` VALUES ('42', '3', '33');
INSERT INTO `sys_role_menu` VALUES ('43', '3', '34');
INSERT INTO `sys_role_menu` VALUES ('44', '3', '35');
INSERT INTO `sys_role_menu` VALUES ('45', '3', '36');
INSERT INTO `sys_role_menu` VALUES ('46', '3', '37');
INSERT INTO `sys_role_menu` VALUES ('47', '3', '38');
INSERT INTO `sys_role_menu` VALUES ('48', '3', '39');
INSERT INTO `sys_role_menu` VALUES ('49', '3', '40');
INSERT INTO `sys_role_menu` VALUES ('50', '3', '41');
INSERT INTO `sys_role_menu` VALUES ('51', '3', '42');
INSERT INTO `sys_role_menu` VALUES ('52', '3', '43');
INSERT INTO `sys_role_menu` VALUES ('53', '3', '44');
INSERT INTO `sys_role_menu` VALUES ('54', '3', '45');
INSERT INTO `sys_role_menu` VALUES ('55', '3', '46');
INSERT INTO `sys_role_menu` VALUES ('56', '3', '47');
INSERT INTO `sys_role_menu` VALUES ('57', '3', '48');
INSERT INTO `sys_role_menu` VALUES ('58', '3', '49');
INSERT INTO `sys_role_menu` VALUES ('59', '3', '50');
INSERT INTO `sys_role_menu` VALUES ('60', '3', '-666666');
INSERT INTO `sys_role_menu` VALUES ('61', '3', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '55f98ae267e63ece32ebe35464f75430edc216e58be22b77ba3bfaae528f95cc', 'UMPCEDPHefTT3ftctr2C', '1095847440@qq.com', '17816869505', '1', '1', '2019-07-04 14:54:23');
INSERT INTO `sys_user` VALUES ('2', 'luo0412', 'cdac762d0ba79875489f6a8b430fa8b5dfe0cdd81da38b80f02f33328af7fd4a', 'YzcmCZNvbXocrsz9dm8e', 'root2@xyz.com', '13612345678', '1', '1', '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('4', '3', '1');
INSERT INTO `sys_user_role` VALUES ('5', '4', '2');
INSERT INTO `sys_user_role` VALUES ('6', '5', '2');
INSERT INTO `sys_user_role` VALUES ('11', '2', '1');
INSERT INTO `sys_user_role` VALUES ('12', '6', '3');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';



-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
