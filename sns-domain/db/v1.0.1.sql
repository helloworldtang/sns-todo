ALTER TABLE `sns_user`
MODIFY COLUMN `type`  tinyint(2) UNSIGNED NOT NULL AFTER `id`,
ADD COLUMN `third_part_id`  varchar(150) NULL AFTER `id`,
ADD COLUMN `nick_name`  varchar(200) NULL AFTER `type`,
ADD INDEX `idx_third_part_id_type` (`third_part_id`, `type`) ;

ALTER TABLE `sns_user`
ADD COLUMN `bio`  varchar(500) NULL AFTER `nick_name`;

ALTER TABLE `sns_todo_detail`
CHANGE COLUMN `user_name` `username`  varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL AFTER `user_id`;

ALTER TABLE `sns_user`
MODIFY COLUMN `icon`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `nick_name`,
CHANGE COLUMN `sex` `gender`  varchar(5) NULL DEFAULT NULL AFTER `icon`,
MODIFY COLUMN `mobile`  varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `gender`,
MODIFY COLUMN `email`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL AFTER `bio`,
MODIFY COLUMN `location`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `email`,
MODIFY COLUMN `todo_count`  int(11) NULL DEFAULT NULL AFTER `salt`;

