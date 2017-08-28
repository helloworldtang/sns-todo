/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : sns-todo-dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-08-28 21:28:19
*/
CREATE DATABASE `sns-todo-dev`  DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sns-todo-detail
-- ----------------------------
DROP TABLE IF EXISTS `sns-todo-detail`;
CREATE TABLE `sns-todo-detail` (
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
-- Table structure for sns-todo-type
-- ----------------------------
DROP TABLE IF EXISTS `sns-todo-type`;
CREATE TABLE `sns-todo-type` (
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
-- Table structure for sns-user
-- ----------------------------
DROP TABLE IF EXISTS `sns-user`;
CREATE TABLE `sns-user` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) unsigned NOT NULL,
  `username` varchar(200) NOT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `password` varchar(300) NOT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `create_ip` varchar(50) DEFAULT NULL,
  `update_ip` varchar(50) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `todo_count` int(11) DEFAULT NULL,
  `last_login_ip` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
