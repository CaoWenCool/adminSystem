/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : beetl

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-21 16:00:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_system_logs`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_logs`;
CREATE TABLE `tb_system_logs` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `is_aes` int(11) DEFAULT NULL,
  `response_code` int(10) DEFAULT NULL,
  `module` varchar(20) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `remote_ip` varchar(20) DEFAULT NULL,
  `request_body` varchar(50) DEFAULT NULL,
  `request_id` varchar(20) DEFAULT NULL,
  `request_method` varchar(20) DEFAULT NULL,
  `request_params` varchar(50) DEFAULT NULL,
  `request_data` varchar(255) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `user_id` bigint(10) DEFAULT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `request_time` datetime DEFAULT NULL,
  `response_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_system_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_role`;
CREATE TABLE `tb_system_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `extra_table` varchar(20) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_role
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_system_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_role_permission`;
CREATE TABLE `tb_system_role_permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `is_menu` int(10) DEFAULT NULL,
  `is_point` int(10) DEFAULT NULL,
  `permission_id` bigint(11) DEFAULT NULL,
  `role_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_system_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `age` int(3) DEFAULT NULL,
  `is_use` int(2) DEFAULT NULL,
  `is_admin` int(2) DEFAULT NULL,
  `department` varchar(10) DEFAULT NULL,
  `engish_name` varchar(10) DEFAULT NULL,
  `img_url` varchar(30) DEFAULT NULL,
  `job_no` varchar(30) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `nick_nmae` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `role_name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_user
-- ----------------------------
INSERT INTO `tb_system_user` VALUES ('1', null, null, null, null, null, null, null, 'admin', null, 'MDBGOTgzQjVGOUYwQkUwOEYxRTdBRkRCN0VFOEMwQkI=', null, null, null, null, null, null);
