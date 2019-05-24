/*
Navicat MySQL Data Transfer

Source Server         : loocalhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : gener

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-05-24 13:44:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '会员号',
  `tag` varchar(100) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '标签',
  `phone` varchar(100) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci COMMENT='用户表 ';

-- ----------------------------
-- Records of sys_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_code_generator
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_generator`;
CREATE TABLE `sys_code_generator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_no` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '编号 系统根据时间自动生成',
  `name` varchar(100) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '名字',
  `title` varchar(100) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '标题',
  `status` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci COMMENT='代码生成';

-- ----------------------------
-- Records of sys_code_generator
-- ----------------------------
