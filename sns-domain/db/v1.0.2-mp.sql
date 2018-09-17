CREATE TABLE `mp_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) NOT NULL,
  `unionid` varchar(50) NOT NULL,
  `openid` varchar(50) NOT NULL,
  `subscribe` tinyint(1) unsigned NOT NULL COMMENT '1:关注；0：取消关注',
  `subscribe_scene` varchar(100) NOT NULL DEFAULT '' COMMENT '返回用户关注的渠道来源：ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他',
  `qr_scene` varchar(255) DEFAULT '' COMMENT 'qr_scene 二维码扫码场景（开发者自定义）.',
  `qr_scene_str` varchar(255) DEFAULT NULL,
  `count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户关注或取关操作的次数',
  `nickname` varchar(255) DEFAULT '' COMMENT '呢称',
  `head_img_url` varchar(600) DEFAULT '' COMMENT '用户头像',
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  `sex_desc` varchar(50) DEFAULT '' COMMENT '性别描述信息：男、女、未知等.',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关注的用户信息';

