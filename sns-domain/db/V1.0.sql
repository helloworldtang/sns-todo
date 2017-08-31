/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : sns-todo-dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-08-31 22:53:55
*/
CREATE DATABASE `sns-todo-dev`  DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sns_todo_detail
-- ----------------------------
DROP TABLE IF EXISTS `sns_todo_detail`;
CREATE TABLE `sns_todo_detail` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `type` bigint(19) NOT NULL,
  `digest` varchar(200) DEFAULT NULL,
  `content` varchar(300) NOT NULL,
  `finished` tinyint(1) NOT NULL,
  `create_ip` varchar(50) DEFAULT NULL,
  `update_ip` varchar(50) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `create_date` date NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sns_todo_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sns_todo_type
-- ----------------------------
DROP TABLE IF EXISTS `sns_todo_type`;
CREATE TABLE `sns_todo_type` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `detail` varchar(100) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `create_ip` varchar(50) DEFAULT NULL,
  `update_ip` varchar(50) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sns_todo_type
-- ----------------------------

