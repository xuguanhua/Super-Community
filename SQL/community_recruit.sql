/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : community_recruit

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-03-26 10:09:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `community_account`
-- ----------------------------
DROP TABLE IF EXISTS `community_account`;
CREATE TABLE `community_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '社团用户id',
  `community_phone` varchar(13) NOT NULL COMMENT '社团账号，即社团负责人电话',
  `community_name` varchar(24) NOT NULL COMMENT '社团名称',
  `password` varchar(13) NOT NULL COMMENT '登录密码',
  `password_error` int(2) NOT NULL DEFAULT '0' COMMENT '输错密码的次数',
  `login_time` datetime(6) DEFAULT NULL COMMENT '上次登录的时间',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '表示社团的审核状态；0表示未审核，1表示审核中，2表示审核通过，3表示审核未通过',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of community_account
-- ----------------------------

-- ----------------------------
-- Table structure for `community_messages`
-- ----------------------------
DROP TABLE IF EXISTS `community_messages`;
CREATE TABLE `community_messages` (
  `id` int(11) NOT NULL COMMENT 'id',
  `cid` int(11) NOT NULL COMMENT '社团id',
  `message` varchar(255) NOT NULL COMMENT '消息内容',
  `readed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经查看；0表示没有查看，1表示已经查看',
  `create_at` datetime NOT NULL COMMENT '消息的创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of community_messages
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(10) NOT NULL COMMENT '部门id',
  `community_id` int(10) NOT NULL COMMENT '社团id',
  `department_name` varchar(16) NOT NULL COMMENT '社团部门名称',
  `create_at` datetime DEFAULT NULL COMMENT '部门创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for `recruit`
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `id` varchar(12) NOT NULL COMMENT '学生学号',
  `community_id` int(10) NOT NULL COMMENT '社团id',
  `name` varchar(12) NOT NULL COMMENT '学生姓名',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `nation` varchar(4) NOT NULL COMMENT '所属民族',
  `birthday` datetime(6) NOT NULL COMMENT '出生年月',
  `native_place` varchar(8) NOT NULL COMMENT '籍贯',
  `political_affiliation` varchar(8) NOT NULL COMMENT '政治面貌',
  `college` varchar(24) NOT NULL COMMENT '所属学院',
  `major` varchar(24) NOT NULL COMMENT '所属班级',
  `phone` varchar(13) NOT NULL COMMENT '联系电话',
  `department1` varchar(16) NOT NULL COMMENT '部门一',
  `department2` varchar(16) NOT NULL COMMENT '部门二',
  `department3` varchar(16) NOT NULL COMMENT '部门三',
  `speciality` varchar(100) NOT NULL COMMENT '特长及兴趣爱好',
  `experience` varchar(300) NOT NULL COMMENT '简单的成长经历',
  `times_id` int(10) NOT NULL COMMENT '招新场次id',
  `message_state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否需要发送message;0不需要，1待发送，2发送成功，3发送失败',
  `create_at` datetime NOT NULL COMMENT '学生的申请时间',
  `state` tinyint(2) NOT NULL COMMENT '招新状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit
-- ----------------------------

-- ----------------------------
-- Table structure for `recruit_times`
-- ----------------------------
DROP TABLE IF EXISTS `recruit_times`;
CREATE TABLE `recruit_times` (
  `id` int(12) NOT NULL COMMENT '招新场次id',
  `community_id` int(12) NOT NULL COMMENT '社团id',
  `interviewers_ph` int(5) NOT NULL DEFAULT '0' COMMENT '表示每小时面试人数',
  `interview_date` datetime NOT NULL COMMENT '表示面试时间',
  `interview_address` varchar(100) NOT NULL COMMENT '面试地点',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '招新的创建时间',
  `endtime` datetime(6) NOT NULL COMMENT '招新结束时间',
  `state` tinyint(1) unsigned NOT NULL COMMENT '招新状态，1表示招新仍在继续，0表示招新结束',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit_times
-- ----------------------------