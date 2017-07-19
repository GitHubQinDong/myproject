/*
MySQL Data Transfer
Source Host: localhost
Source Database: flowershop
Target Host: localhost
Target Database: flowershop
Date: 2017/7/14 17:15:25
*/
DROP DATABASE IF EXISTS `flowershop`;
CREATE DATABASE `flowershop`;
USE `flowershop`;
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cartid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `flowerid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `orderid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cartid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for catalog
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `catalogid` int(11) NOT NULL AUTO_INCREMENT,
  `catalogname` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`catalogid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for flower
-- ----------------------------
DROP TABLE IF EXISTS `flower`;
CREATE TABLE `flower` (
  `flowerid` int(11) NOT NULL AUTO_INCREMENT,
  `flowername` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `picture` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `catalogid` int(11) DEFAULT NULL,
  PRIMARY KEY (`flowerid`),
  KEY `FK_flower` (`catalogid`),
  CONSTRAINT `FK_flower` FOREIGN KEY (`catalogid`) REFERENCES `catalog` (`catalogid`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `subtime` datetime DEFAULT NULL,
  `totalmoney` float DEFAULT '0',
  `telphone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `cart` VALUES ('32', '1', '15', '1', '1');
INSERT INTO `cart` VALUES ('37', '1', '27', '1', '1');
INSERT INTO `cart` VALUES ('38', '1', '2', '2', '1');
INSERT INTO `cart` VALUES ('39', '1', '33', '1', '2');
INSERT INTO `cart` VALUES ('42', '1', '34', '1', '2');
INSERT INTO `cart` VALUES ('43', '1', '1', '1', '2');
INSERT INTO `catalog` VALUES ('1', '红玫瑰');
INSERT INTO `catalog` VALUES ('2', '白玫瑰');
INSERT INTO `catalog` VALUES ('3', '粉玫瑰');
INSERT INTO `catalog` VALUES ('4', '紫玫瑰');
INSERT INTO `catalog` VALUES ('5', '蓝玫瑰');
INSERT INTO `catalog` VALUES ('6', '香槟玫瑰');
INSERT INTO `catalog` VALUES ('7', '蛋糕');
INSERT INTO `catalog` VALUES ('8', '服装');
INSERT INTO `catalog` VALUES ('9', '车');
INSERT INTO `catalog` VALUES ('10', '钻石');
INSERT INTO `catalog` VALUES ('11', '电子设备');
INSERT INTO `flower` VALUES ('1', '醉爱-红玫瑰（9支）', '119', 'h1.jpg', '1');
INSERT INTO `flower` VALUES ('2', '香水百合玫瑰', '129', 'h2.jpg', '1');
INSERT INTO `flower` VALUES ('3', '唯爱红玫瑰', '126', 'h3.jpg', '1');
INSERT INTO `flower` VALUES ('4', '梦巴黎红玫瑰', '116', 'h4.jpg', '1');
INSERT INTO `flower` VALUES ('5', '思念红玫瑰', '139', 'h5.jpg', '1');
INSERT INTO `flower` VALUES ('6', '愿得一人心玫瑰', '136', 'h6.jpg', '1');
INSERT INTO `flower` VALUES ('7', '约见', '115', 'l8.jpg', '2');
INSERT INTO `flower` VALUES ('8', '相知', '199', 'b2.jpg', '2');
INSERT INTO `flower` VALUES ('9', '相依相伴', '176', 'b3.jpg', '2');
INSERT INTO `flower` VALUES ('10', '初遇见', '159', 'b4.jpg', '6');
INSERT INTO `flower` VALUES ('11', '心醉', '169', 'b5.jpg', '6');
INSERT INTO `flower` VALUES ('12', '夏之恋', '187', 'b6.jpg', '2');
INSERT INTO `flower` VALUES ('13', '粉红回忆', '201', 'f1.jpg', '3');
INSERT INTO `flower` VALUES ('14', ' 暖', '220', 'f2.jpg', '3');
INSERT INTO `flower` VALUES ('15', '纯真年代', '216', 'b1.jpg', '4');
INSERT INTO `flower` VALUES ('17', '蓝魅', '139', 'l2.jpg', '5');
INSERT INTO `flower` VALUES ('18', '蓝色醉爱', '145', 'l3.jpg', '5');
INSERT INTO `flower` VALUES ('19', '至尊玫瑰', '127', 'l7.jpg', '5');
INSERT INTO `flower` VALUES ('20', '一生一世', '123', 'h7.jpg', '1');
INSERT INTO `flower` VALUES ('21', '海洋之心', '223', 'b7.jpg', '2');
INSERT INTO `flower` VALUES ('22', '一往情深', '440', 'h8.jpg', '1');
INSERT INTO `flower` VALUES ('23', '蓝色妖姬', '123', 'l4.jpg', '5');
INSERT INTO `flower` VALUES ('24', '一片真情', '450', 'g1.jpg', '6');
INSERT INTO `flower` VALUES ('25', '烂漫满屋', '340', 'g2.jpg', '6');
INSERT INTO `flower` VALUES ('26', '苏格兰风情', '201', 'f3.jpg', '3');
INSERT INTO `flower` VALUES ('27', '真爱如初', '203', 'b8.jpg', '2');
INSERT INTO `flower` VALUES ('28', '心心相印', '204', 'b9.jpg', '2');
INSERT INTO `flower` VALUES ('29', '阳光海岸', '109', 'g3.jpg', '6');
INSERT INTO `flower` VALUES ('30', '戴安娜', '112', 'f4.jpg', '3');
INSERT INTO `flower` VALUES ('31', '浅紫罗兰', '134', 'g4.jpg', '6');
INSERT INTO `flower` VALUES ('32', '芳华', '114', 'f5.jpg', '3');
INSERT INTO `flower` VALUES ('33', '美丽出漾', '156', 'f7.jpg', '3');
INSERT INTO `flower` VALUES ('34', '爱如初恋', '421', 'g5.jpg', '6');
INSERT INTO `flower` VALUES ('35', '萌动的心', '167', 'g6.jpg', '6');
INSERT INTO `flower` VALUES ('36', '海之恋', '156', 'l5.jpg', '5');
INSERT INTO `flower` VALUES ('37', '天空之城', '568', 'l6.jpg', '4');
INSERT INTO `flower` VALUES ('38', '梦之蓝', '332', 'l9.jpg', '5');
INSERT INTO `flower` VALUES ('39', '台式机惠普HPLv2011', '1000', 'com.jpg', '11');
INSERT INTO `orders` VALUES ('1', '2017-07-14 17:01:55', '3.14', '123123', 'aaaaaaaaaaa', '1');
INSERT INTO `orders` VALUES ('2', '2017-07-14 17:09:14', '1373', '15244682204', '道里区', '1');
INSERT INTO `user` VALUES ('1', 'chengxx', '123123', 'customer');
