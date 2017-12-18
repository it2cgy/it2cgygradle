/*
Navicat MySQL Data Transfer

Source Server         : 192.168.101.222
Source Server Version : 50624
Source Host           : 192.168.101.222:3306
Source Database       : longieb_db

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-09-26 18:15:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pvmts_task
-- ----------------------------
DROP TABLE IF EXISTS `pvmts_task`;
CREATE TABLE `pvmts_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_time` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `default_time` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
