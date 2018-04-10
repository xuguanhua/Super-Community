/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : community_recruit

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-04-09 21:34:23
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
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '社团注册时间',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '表示社团的审核状态；0表示未审核，1表示审核中，2表示审核通过，3表示审核未通过',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of community_account
-- ----------------------------
INSERT INTO `community_account` VALUES ('4', '15070745433', '勤工助学中心', '12345', '0', null, '2018-04-09 17:12:32', '0');
INSERT INTO `community_account` VALUES ('5', '15070745433', '滑轮社', '12345', '0', null, '2018-04-09 17:13:16', '0');

-- ----------------------------
-- Table structure for `community_messages`
-- ----------------------------
DROP TABLE IF EXISTS `community_messages`;
CREATE TABLE `community_messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cid` int(11) NOT NULL COMMENT '社团id',
  `message` varchar(255) NOT NULL COMMENT '消息内容',
  `readed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经查看；0表示没有查看，1表示已经查看',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息的创建时间',
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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `department_id` int(10) NOT NULL COMMENT '部门id',
  `community_id` int(10) NOT NULL COMMENT '社团id',
  `department_des` varchar(500) NOT NULL DEFAULT '这是一个伟大的部门' COMMENT '社团部门简介',
  `department_name` varchar(16) NOT NULL COMMENT '社团部门名称',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '部门创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '1', '4', '这是一个伟大的部门', '设计部', '2018-04-09 21:22:29');
INSERT INTO `department` VALUES ('2', '2', '4', '这是一个伟大的部门', '办公室', '2018-04-09 21:22:36');
INSERT INTO `department` VALUES ('3', '3', '4', '这是一个伟大的部门', '研发部', '2018-04-09 21:22:50');
INSERT INTO `department` VALUES ('4', '1', '5', '这是一个伟大的部门', '设计部', '2018-04-09 21:23:56');
INSERT INTO `department` VALUES ('5', '2', '5', '这是一个伟大的部门', '办公室', '2018-04-09 21:24:05');
INSERT INTO `department` VALUES ('6', '3', '5', '这是一个伟大的部门', '研发部', '2018-04-09 21:24:15');

-- ----------------------------
-- Table structure for `recruit`
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `student_id` varchar(12) NOT NULL COMMENT '学生学号',
  `community_id` int(10) NOT NULL COMMENT '社团id',
  `name` varchar(12) NOT NULL COMMENT '学生姓名',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `nation` varchar(4) NOT NULL DEFAULT '汉' COMMENT '所属民族',
  `birthday` datetime(6) NOT NULL COMMENT '出生年月',
  `native_place` varchar(8) NOT NULL DEFAULT '江西南昌' COMMENT '籍贯',
  `political_affiliation` varchar(8) NOT NULL DEFAULT '共青团员' COMMENT '政治面貌',
  `college` varchar(24) NOT NULL DEFAULT '信息工程学院' COMMENT '所属学院',
  `major` varchar(24) NOT NULL DEFAULT '计算机科学与技术' COMMENT '所属班级',
  `phone` varchar(13) NOT NULL DEFAULT '该报名者没有填写' COMMENT '联系电话',
  `department1` varchar(16) NOT NULL DEFAULT '该报名者没有填写' COMMENT '部门一',
  `department2` varchar(16) NOT NULL DEFAULT '该报名者没有填写' COMMENT '部门二',
  `department3` varchar(16) NOT NULL DEFAULT '该报名者没有填写' COMMENT '部门三',
  `speciality` varchar(100) NOT NULL DEFAULT '该报名者没有填写' COMMENT '特长及兴趣爱好',
  `experience` varchar(300) NOT NULL DEFAULT '该报名者没有填写' COMMENT '简单的成长经历',
  `times_id` int(10) NOT NULL DEFAULT '1' COMMENT '招新场次id',
  `message_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否需要发送message;0不需要，1待发送，2发送成功，3发送失败',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '学生的申请时间',
  `state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '招新状态：0，报名表提交成功，审核中；1，审核通',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit
-- ----------------------------
INSERT INTO `recruit` VALUES ('1', '6103115022', '4', '李四', '男', '汉', '2018-06-01 00:00:00.000000', '江西赣州', '共青团员', '信息工程学院', '计算机科学与技术', '15070734571', '设计部', '研发部', '办公室', '暂时没有', '暂时没有', '1', '0', '2018-04-09 19:16:20', '0');
INSERT INTO `recruit` VALUES ('2', '6103115023', '4', '张三', '男', '汉', '2018-04-09 20:55:02.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 20:55:08', '0');
INSERT INTO `recruit` VALUES ('3', '6103115024', '5', '老王', '男', '汉', '2018-04-09 20:56:36.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 20:56:43', '0');
INSERT INTO `recruit` VALUES ('4', '6103115025', '5', '木兰', '女', '汉', '2018-04-10 20:57:14.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 20:57:18', '0');
INSERT INTO `recruit` VALUES ('5', '6103115026', '4', '李白', '男', '汉', '2018-04-10 20:57:41.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 20:57:47', '0');
INSERT INTO `recruit` VALUES ('6', '6103115027', '5', '韩信', '男', '汉', '2018-04-10 20:58:06.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 20:58:13', '0');
INSERT INTO `recruit` VALUES ('7', '6103115028', '5', '庄周', '男', '汉', '2018-04-09 20:59:00.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 20:59:13', '0');
INSERT INTO `recruit` VALUES ('8', '6103115029', '5', '典韦', '男', '汉', '2018-04-09 21:16:31.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 21:16:33', '0');
INSERT INTO `recruit` VALUES ('9', '6103115030', '5', '张辽', '男', '汉', '2018-04-09 21:16:56.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 21:16:59', '0');
INSERT INTO `recruit` VALUES ('10', '6103115031', '4', '王昭君', '女', '汉', '2018-04-10 21:17:25.000000', '江西南昌', '共青团员', '信息工程学院', '计算机科学与技术', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '该报名者没有填写', '1', '0', '2018-04-09 21:17:28', '0');

-- ----------------------------
-- Table structure for `recruit_times`
-- ----------------------------
DROP TABLE IF EXISTS `recruit_times`;
CREATE TABLE `recruit_times` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '招新场次id',
  `community_id` int(12) NOT NULL COMMENT '社团id',
  `interviewers_ph` int(5) NOT NULL DEFAULT '0' COMMENT '表示每小时面试人数',
  `interview_date` datetime NOT NULL COMMENT '表示面试时间',
  `interview_address` varchar(100) NOT NULL DEFAULT '南昌大学' COMMENT '面试地点',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '招新的创建时间',
  `endtime` datetime(6) NOT NULL COMMENT '招新结束时间',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '招新状态，1表示招新仍在继续，0表示招新结束',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit_times
-- ----------------------------
