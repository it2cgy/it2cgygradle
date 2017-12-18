/*==============================================================*/
/* Table: pvmts_third_equipments                                */
/*==============================================================*/
create table pvmts_third_equipments
(
   id                   int not null auto_increment,
   equipment_type       int,
   number               varchar(64),
   equipment_id         int,
   power_station_id     int,
   primary key (id)
);

/*==============================================================*/
/* Table: pvmts_third_points                                    */
/*==============================================================*/
create table pvmts_third_points
(
   id                   int not null auto_increment,
   point_type           varchar(64),
   equipment_id         int,
   name                 varchar(64),
   equipment_type       int,
   point_id             int,
   primary key (id)
);
/*逆变器*/
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('1', '3', '#6-NBQ1', '5', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('2', '3', '#6-NBQ4', '8', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('3', '3', '#8-NBQ1', '11', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('4', '3', '#9-NBQ1', '16', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('5', '3', '#9-NBQ2', '17', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('6', '3', '#9-NBQ3', '18', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('7', '3', '#9-NBQ4', '19', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES ('8', '3', '#9-NBQ7', '22', '6');
/*直流表*/
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#6-CL1-DC', '11', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#6-CL4-DC', '17', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#8-CL1-DC', '23', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL1-DC', '33', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL2-DC', '35', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL3-DC', '37', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL4-DC', '49', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL7-DC', '45', '6');
/*交流表*/
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#6-CL1-AC', '12', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#6-CL4-AC', '18', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#8-CL1-AC', '24', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL1-AC', '34', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL2-AC', '36', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL3-AC', '38', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL4-AC', '40', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '5', '#9-CL7-AC', '46', '6');
/*IV多通道设备*/
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '10', 'Module207', '15', '6');
INSERT INTO `pvmts_third_equipments` (`id`, `equipment_type`, `number`, `equipment_id`, `power_station_id`) VALUES (NULL, '10', 'Module208', '16', '6');


/*气象站测点*/
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_17', '1', '背板温度17', '8', '1708240218');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_18', '1', '背板温度18', '8', '1708240219');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_19', '1', '背板温度19', '8', '1708240220');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_20', '1', '背板温度20', '8', '1708240221');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_21', '1', '背板温度21', '8', '1708240222');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_22', '1', '背板温度22', '8', '1708240223');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_23', '1', '背板温度23', '8', '1708240224');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_24', '1', '背板温度24', '8', '1708240225');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_25', '1', '背板温度25', '8', '1708240226');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_26', '1', '背板温度26', '8', '1708240227');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_27', '1', '背板温度27', '8', '1708240228');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_28', '1', '背板温度28', '8', '1708240229');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_29', '1', '背板温度29', '8', '1708240230');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_30', '1', '背板温度30', '8', '1708240231');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_31', '1', '背板温度31', '8', '1708240232');
INSERT INTO `pvmts_third_points` (`id`, `point_type`, `equipment_id`, `name`, `equipment_type`, `point_id`) VALUES (NULL, 'TemperatureOfModule_32', '1', '背板温度32', '8', '1708240233');

/**
 * 第三方用户
 */
INSERT INTO `pvmts_user` (`id`, `username`, `password`, `sex`, `nickname`, `mobile`, `telephone`, `headshot`, `email`, `create_time`, `update_time`, `status`, `company_id`, `wxheadurl`, `wxsex`, `wxnickname`, `wxopenid`, `wxunionid`, `create_userid`, `input_company`, `is_delete`) VALUES ('499', 'ds001', 'e10adc3949ba59abbe56e057f20f883e', NULL, '第三方', NULL, '', 'http://test.yunengzhe.com:846/tools/file/download/1355', '', '2017-08-08 11:18:37', '2017-08-18 16:03:09', '0', '1', NULL, NULL, NULL, NULL, NULL, '357', '', '0');
INSERT INTO `pvmts_user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`) VALUES ('235', '499', '4', '2017-08-08 15:20:08', '2017-08-08 15:20:08');
INSERT INTO `pvmts_roles` (`id`, `role_name`, `role_type`, `create_time`, `update_time`, `remarks`, `company_id`) VALUES ('4', '第三方', '0', '2017-08-31 13:57:34', '2017-08-31 13:57:37', NULL, '1');