CREATE TABLE `pvmts_alarm_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `point_id` int(11) NOT NULL COMMENT '测点id',
  `user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `value_one` double DEFAULT NULL COMMENT '报警数值 1级',
  `value_two` double DEFAULT NULL COMMENT '报警数值 2级',
  `value_three` double DEFAULT NULL COMMENT '报警数值 3级',
  `type` int(11) NOT NULL COMMENT '1大于报警数值报警,2小于报警数值报警,3等于报警数值报警 4不等于报警数值报警',
  `message` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '报警信息模板',
  `forbidden` int(11) DEFAULT NULL COMMENT '1 被禁用',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
