/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : chihuo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-06-25 10:16:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `operate` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for ts_user
-- ----------------------------
DROP TABLE IF EXISTS `ts_user`;
CREATE TABLE `ts_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `a_status` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `head_icon` varchar(255) DEFAULT NULL,
  `login_num` varchar(255) NOT NULL,
  `login_pwd` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` bigint(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3o780o9ph6pxq5bv7f9jl9phe` (`login_num`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_user
-- ----------------------------
INSERT INTO `ts_user` VALUES ('1', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test00', 'test', 'test0', '13438336891', null);
INSERT INTO `ts_user` VALUES ('2', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test11', 'test', 'test1', '13438336891', null);
INSERT INTO `ts_user` VALUES ('3', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test22', 'test', 'test2', '13438336891', null);
INSERT INTO `ts_user` VALUES ('4', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test33', 'test', 'test3', '13438336891', null);
INSERT INTO `ts_user` VALUES ('5', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test44', 'test', 'test4', '13438336891', null);
INSERT INTO `ts_user` VALUES ('6', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test55', 'test', 'test5', '13438336891', null);
INSERT INTO `ts_user` VALUES ('7', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test66', 'test', 'test6', '13438336891', null);
INSERT INTO `ts_user` VALUES ('8', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test77', 'test', 'test7', '13438336891', null);
INSERT INTO `ts_user` VALUES ('9', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test88', 'test', 'test8', '13438336891', null);
INSERT INTO `ts_user` VALUES ('10', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test99', 'test', 'test9', '13438336891', null);
INSERT INTO `ts_user` VALUES ('11', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1010', 'test', 'test10', '13438336891', null);
INSERT INTO `ts_user` VALUES ('12', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1111', 'test', 'test11', '13438336891', null);
INSERT INTO `ts_user` VALUES ('13', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1212', 'test', 'test12', '13438336891', null);
INSERT INTO `ts_user` VALUES ('14', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1313', 'test', 'test13', '13438336891', null);
INSERT INTO `ts_user` VALUES ('15', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1414', 'test', 'test14', '13438336891', null);
INSERT INTO `ts_user` VALUES ('16', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1515', 'test', 'test15', '13438336891', null);
INSERT INTO `ts_user` VALUES ('17', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1616', 'test', 'test16', '13438336891', null);
INSERT INTO `ts_user` VALUES ('18', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1717', 'test', 'test17', '13438336891', null);
INSERT INTO `ts_user` VALUES ('19', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1818', 'test', 'test18', '13438336891', null);
INSERT INTO `ts_user` VALUES ('20', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test1919', 'test', 'test19', '13438336891', null);
INSERT INTO `ts_user` VALUES ('21', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2020', 'test', 'test20', '13438336891', null);
INSERT INTO `ts_user` VALUES ('22', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2121', 'test', 'test21', '13438336891', null);
INSERT INTO `ts_user` VALUES ('23', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2222', 'test', 'test22', '13438336891', null);
INSERT INTO `ts_user` VALUES ('24', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2323', 'test', 'test23', '13438336891', null);
INSERT INTO `ts_user` VALUES ('25', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2424', 'test', 'test24', '13438336891', null);
INSERT INTO `ts_user` VALUES ('26', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2525', 'test', 'test25', '13438336891', null);
INSERT INTO `ts_user` VALUES ('27', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2626', 'test', 'test26', '13438336891', null);
INSERT INTO `ts_user` VALUES ('28', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2727', 'test', 'test27', '13438336891', null);
INSERT INTO `ts_user` VALUES ('29', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2828', 'test', 'test28', '13438336891', null);
INSERT INTO `ts_user` VALUES ('30', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test2929', 'test', 'test29', '13438336891', null);
INSERT INTO `ts_user` VALUES ('31', '2016-11-01 22:53:46', '1', '超级管理员', '1', '2016-11-01 22:53:46', '1', '超级管理员', null, '213213213@qq.com', null, 'test3030', 'test', 'test30', '13438336891', null);

-- ----------------------------
-- Table structure for ts_user_system_personality
-- ----------------------------
DROP TABLE IF EXISTS `ts_user_system_personality`;
CREATE TABLE `ts_user_system_personality` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_user_system_personality
-- ----------------------------

-- ----------------------------
-- Table structure for t_ch_snacks
-- ----------------------------
DROP TABLE IF EXISTS `t_ch_snacks`;
CREATE TABLE `t_ch_snacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `shop` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ch_snacks
-- ----------------------------

-- ----------------------------
-- Table structure for t_ch_snack_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_ch_snack_menu`;
CREATE TABLE `t_ch_snack_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  `taste` varchar(255) DEFAULT NULL,
  `snacksnacks_id` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9n67tjl0hl36x03qsc6sq0e74` (`snacksnacks_id`),
  CONSTRAINT `FK_9n67tjl0hl36x03qsc6sq0e74` FOREIGN KEY (`snacksnacks_id`) REFERENCES `t_ch_snacks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ch_snack_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_ch_taste
-- ----------------------------
DROP TABLE IF EXISTS `t_ch_taste`;
CREATE TABLE `t_ch_taste` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `level_des` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ch_taste
-- ----------------------------

-- ----------------------------
-- Table structure for t_ch_user_chihuo_personality
-- ----------------------------
DROP TABLE IF EXISTS `t_ch_user_chihuo_personality`;
CREATE TABLE `t_ch_user_chihuo_personality` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ch_user_chihuo_personality
-- ----------------------------

-- ----------------------------
-- Table structure for t_ch_user_taste
-- ----------------------------
DROP TABLE IF EXISTS `t_ch_user_taste`;
CREATE TABLE `t_ch_user_taste` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` bigint(20) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ch_user_taste
-- ----------------------------
