ALTER TABLE `sns_user`
MODIFY COLUMN `type`  tinyint(2) UNSIGNED NOT NULL AFTER `id`,
ADD COLUMN `third_part_id`  varchar(150) NULL AFTER `id`,
ADD COLUMN `nick_name`  varchar(200) NULL AFTER `type`,
ADD INDEX `idx_third_part_id_type` (`third_part_id`, `type`) ;

ALTER TABLE `sns_user`
ADD COLUMN `bio`  varchar(500) NULL AFTER `nick_name`;


