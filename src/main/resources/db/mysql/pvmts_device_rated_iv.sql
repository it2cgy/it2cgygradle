/*
Navicat MySQL Data Transfer

Source Server         : 192.168.101.222
Source Server Version : 50624
Source Host           : 192.168.101.222:3306
Source Database       : longieb_db

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-11-30 10:01:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pvmts_device_rated_iv
-- ----------------------------
DROP TABLE IF EXISTS `pvmts_device_rated_iv`;
CREATE TABLE `pvmts_device_rated_iv` (
  `id` varchar(255) NOT NULL,
  `equipment_id` varchar(255) DEFAULT NULL,
  `equipment_type` varchar(255) DEFAULT NULL,
  `equipment_description` varchar(255) DEFAULT NULL,
  `rated_power` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
