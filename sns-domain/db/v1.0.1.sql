ALTER TABLE `sns_user`
MODIFY COLUMN `type`  tinyint(2) UNSIGNED NOT NULL AFTER `id`,
ADD COLUMN `third_part_id`  varchar(150) NULL AFTER `id`,
ADD COLUMN `nick_name`  varchar(200) NULL AFTER `type`,
ADD INDEX `idx_third_part_id_type` (`third_part_id`, `type`) ;

ALTER TABLE `sns_user`
ADD COLUMN `bio`  varchar(500) NULL AFTER `nick_name`;

ALTER TABLE `sns_todo_detail`
CHANGE COLUMN `user_name` `username`  varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL AFTER `user_id`;
