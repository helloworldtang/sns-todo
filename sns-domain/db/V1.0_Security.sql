
-- ----------------------------
-- Table structure for sns_role
-- ----------------------------
DROP TABLE IF EXISTS `sns_role`;
CREATE TABLE `sns_role` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sns_role
-- ----------------------------
INSERT INTO `sns_role` VALUES ('1', 'ROLE_ADMIN', 'admin');
INSERT INTO `sns_role` VALUES ('2', 'ROLE_USER', 'user');

-- ----------------------------
-- Table structure for sns_user
-- ----------------------------
DROP TABLE IF EXISTS `sns_user`;
CREATE TABLE `sns_user` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `type` tinyint(2) unsigned NOT NULL,
  `account_enabled` tinyint(1) NOT NULL DEFAULT '1',
  `account_expired` datetime NOT NULL,
  `credentials_expired` datetime NOT NULL,
  `account_locked` tinyint(1) NOT NULL DEFAULT '0',
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

-- ----------------------------
-- Records of sns_user
-- ----------------------------

-- ----------------------------
-- Table structure for sns_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sns_user_role`;
CREATE TABLE `sns_user_role` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(19) unsigned NOT NULL,
  `role_id` bigint(19) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sns_user_role
-- ----------------------------