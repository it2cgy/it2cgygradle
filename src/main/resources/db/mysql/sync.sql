CREATE TABLE `pvmts_syncdata_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '同步标示名称',
  `start_date` datetime DEFAULT NULL COMMENT '同步的数据开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '同步的数据结束时间',
  `status` int(11) DEFAULT NULL COMMENT '状态 0 同步中 1 同步完成',
  `user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
