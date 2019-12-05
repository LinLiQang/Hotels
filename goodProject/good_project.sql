/*
Navicat MySQL Data Transfer

Source Server         : Study
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : good_project

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2019-12-05 10:42:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `cid` int(4) NOT NULL AUTO_INCREMENT,
  `comment` varchar(100) DEFAULT NULL,
  `commentTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `uid` int(11) DEFAULT NULL,
  `rid` varchar(10) DEFAULT NULL,
  `oid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `uid` (`uid`),
  KEY `rid` (`rid`),
  KEY `oid` (`oid`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `room` (`rid`),
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '房间干净整洁，住着很舒适', '2019-12-03 00:00:00', '6', '104', '1I681F1I444M919D86F77RFE98KTBV17');
INSERT INTO `comments` VALUES ('2', '很不错，不愧是豪华版！', '2019-07-05 00:00:00', '11', '108', 'CP2D92528BLLQ60MRWTMNSY77O017Q88');
INSERT INTO `comments` VALUES ('3', '双人房住着很舒服', '2019-01-16 00:00:00', '10', '106', 'EBGO12FHU3DN3KWRAPGWBEY6OAD12128');
INSERT INTO `comments` VALUES ('4', '豪华房就是不一样，床又大又舒服', '2019-07-05 00:00:00', '8', '108', 'EEL2R4I3VX8852GY1CR3DY00N930O87P');
INSERT INTO `comments` VALUES ('5', '不知道豪华双人房是什么感觉，但是这个普通双人房住着挺不错的', '2019-05-07 00:00:00', '14', '205', 'JV231A83D4545EO595W6WNF36901EU1X');
INSERT INTO `comments` VALUES ('6', '小家庭入住必推荐豪华双人房', '2019-10-11 00:00:00', '15', '208', 'LGL41VJR475R19RRUN8KP1W7XU11OR4D');
INSERT INTO `comments` VALUES ('7', '绝对物超所值，不贵住着舒服', '2019-11-04 00:00:00', '6', '104', 'LN72519TB46D923501LG7861L6FE75SW');
INSERT INTO `comments` VALUES ('8', '这豪华单人房还行吧', '2019-12-08 00:00:00', '12', '302', 'M95793Q61EA59HAW02C7VP835A8B9G7E');
INSERT INTO `comments` VALUES ('9', '一个字，赞', '2019-01-16 00:00:00', '10', '106', 'N88669AAC7576863DU1XCD29F7XP0KSE');
INSERT INTO `comments` VALUES ('10', '很不错，推荐', '2019-05-11 00:00:00', '11', '301', 'Q2040855UT1FR413IGUJR7235I1J394D');
INSERT INTO `comments` VALUES ('11', '情侣入住必备', '2019-03-07 00:00:00', '11', '202', 'U2W376ES938I36FNI3A8873UNG3NO4RT');
INSERT INTO `comments` VALUES ('12', '这种舒适感，只能说谁试谁知道', '2019-01-05 00:00:00', '6', '106', 'W159HEP9O92774U01YKXS8QQ8M2A7365');
INSERT INTO `comments` VALUES ('13', '有机会再来这边接着订这家的客房', '2018-07-06 00:00:00', '2', '102', 'W91Y7MKSJA4M59568AYRIV22WP5Q4RQD');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(36) NOT NULL,
  `startTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ordersPrice` decimal(10,0) DEFAULT NULL,
  `ordersStatus` int(4) DEFAULT NULL,
  `rid` varchar(10) DEFAULT NULL,
  `uid` int(4) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `rid` (`rid`),
  KEY `uid` (`uid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `room` (`rid`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1I681F1I444M919D86F77RFE98KTBV17', '2019-12-05 09:11:51', '2019-12-03 00:00:00', '129', '0', '104', '6');
INSERT INTO `orders` VALUES ('3I1QXPE514LY571D5DA45631P0G32UL3', '2019-12-05 09:11:47', '2019-06-06 00:00:00', '399', '1', '201', '10');
INSERT INTO `orders` VALUES ('50HV6Q1C809JD0W19HV579WVRPUV6256', '2019-12-05 09:11:45', '2019-07-05 00:00:00', '129', '2', '104', '6');
INSERT INTO `orders` VALUES ('7P26Y1951J775Y09822LE68M4V3M957X', '2019-12-05 09:11:41', '2019-01-16 00:00:00', '199', '1', '206', '15');
INSERT INTO `orders` VALUES ('8FAK8B5C2AGTB5QD3DQ2AHMSCAG5P0CY', '2019-12-05 09:11:40', '2019-07-10 00:00:00', '199', '1', '105', '5');
INSERT INTO `orders` VALUES ('9442S03QLWAR69G1B5EYTR46M08D408O', '2019-12-05 09:11:38', '2019-02-22 00:00:00', '129', '2', '103', '3');
INSERT INTO `orders` VALUES ('CP2D92528BLLQ60MRWTMNSY77O017Q88', '2019-12-05 09:11:34', '2019-07-05 00:00:00', '299', '0', '108', '11');
INSERT INTO `orders` VALUES ('D98TGQY38C6186OE55058Q3F89DGUIO8', '2019-12-05 09:11:32', '2019-03-06 00:00:00', '129', '1', '104', '4');
INSERT INTO `orders` VALUES ('EBGO12FHU3DN3KWRAPGWBEY6OAD12128', '2019-12-05 09:11:30', '2019-01-16 00:00:00', '199', '0', '106', '10');
INSERT INTO `orders` VALUES ('EEL2R4I3VX8852GY1CR3DY00N930O87P', '2019-12-05 09:11:28', '2019-07-05 00:00:00', '299', '0', '108', '8');
INSERT INTO `orders` VALUES ('H4T8C81L80IE7039627OTFJD4OIY1247', '2019-12-05 09:11:26', '2019-07-08 00:00:00', '129', '1', '203', '12');
INSERT INTO `orders` VALUES ('JA738L273AR5V121M50E0GR8F86FMT5M', '2019-12-05 09:11:24', '2019-08-09 00:00:00', '199', '1', '105', '5');
INSERT INTO `orders` VALUES ('JV231A83D4545EO595W6WNF36901EU1X', '2019-12-05 09:11:22', '2019-05-07 00:00:00', '199', '0', '205', '14');
INSERT INTO `orders` VALUES ('LGL41VJR475R19RRUN8KP1W7XU11OR4D', '2019-12-05 09:11:20', '2019-10-11 00:00:00', '299', '0', '208', '15');
INSERT INTO `orders` VALUES ('LN72519TB46D923501LG7861L6FE75SW', '2019-12-05 09:11:18', '2019-11-04 00:00:00', '129', '0', '104', '6');
INSERT INTO `orders` VALUES ('M95793Q61EA59HAW02C7VP835A8B9G7E', '2019-12-05 09:11:16', '2019-12-08 00:00:00', '399', '0', '302', '12');
INSERT INTO `orders` VALUES ('N88669AAC7576863DU1XCD29F7XP0KSE', '2019-12-05 09:11:12', '2019-01-16 00:00:00', '199', '0', '106', '10');
INSERT INTO `orders` VALUES ('O1UOFDYRGV069AX5V6S4RB8W4VP880P2', '2019-12-05 09:11:09', '2019-06-07 00:00:00', '129', '2', '303', '14');
INSERT INTO `orders` VALUES ('Q2040855UT1FR413IGUJR7235I1J394D', '2019-12-05 09:11:07', '2019-05-11 00:00:00', '399', '0', '301', '11');
INSERT INTO `orders` VALUES ('S778202984TRF51MLW55G3V5X3M146G4', '2019-12-05 09:11:05', '2019-04-12 00:00:00', '129', '2', '204', '13');
INSERT INTO `orders` VALUES ('U2W376ES938I36FNI3A8873UNG3NO4RT', '2019-12-05 09:11:02', '2019-03-07 00:00:00', '399', '0', '202', '11');
INSERT INTO `orders` VALUES ('W159HEP9O92774U01YKXS8QQ8M2A7365', '2019-12-05 09:11:00', '2019-01-05 00:00:00', '199', '0', '106', '6');
INSERT INTO `orders` VALUES ('W91Y7MKSJA4M59568AYRIV22WP5Q4RQD', '2018-07-05 00:00:00', '2018-07-06 00:00:00', '399', '0', '102', '2');

-- ----------------------------
-- Table structure for `room`
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `rid` varchar(10) NOT NULL,
  `roomPrice` decimal(10,0) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `roomStatus` int(2) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('101', '399', '3', '2');
INSERT INTO `room` VALUES ('102', '399', '3', '1');
INSERT INTO `room` VALUES ('103', '129', '1', '1');
INSERT INTO `room` VALUES ('104', '129', '1', '1');
INSERT INTO `room` VALUES ('105', '199', '2', '1');
INSERT INTO `room` VALUES ('106', '199', '2', '2');
INSERT INTO `room` VALUES ('107', '299', '4', '1');
INSERT INTO `room` VALUES ('108', '299', '4', '1');
INSERT INTO `room` VALUES ('201', '399', '3', '1');
INSERT INTO `room` VALUES ('202', '399', '3', '2');
INSERT INTO `room` VALUES ('203', '129', '1', '1');
INSERT INTO `room` VALUES ('204', '129', '1', '1');
INSERT INTO `room` VALUES ('205', '199', '2', '2');
INSERT INTO `room` VALUES ('206', '199', '2', '1');
INSERT INTO `room` VALUES ('207', '299', '4', '1');
INSERT INTO `room` VALUES ('208', '299', '4', '1');
INSERT INTO `room` VALUES ('301', '399', '3', '1');
INSERT INTO `room` VALUES ('302', '399', '3', '1');
INSERT INTO `room` VALUES ('303', '129', '1', '1');
INSERT INTO `room` VALUES ('304', '129', '1', '1');
INSERT INTO `room` VALUES ('305', '199', '2', '1');
INSERT INTO `room` VALUES ('306', '199', '2', '1');
INSERT INTO `room` VALUES ('307', '299', '4', '2');
INSERT INTO `room` VALUES ('308', '299', '4', '1');
INSERT INTO `room` VALUES ('401', '399', '3', '1');
INSERT INTO `room` VALUES ('402', '399', '3', '0');
INSERT INTO `room` VALUES ('403', '129', '1', '1');
INSERT INTO `room` VALUES ('404', '129', '1', '1');
INSERT INTO `room` VALUES ('405', '199', '2', '1');
INSERT INTO `room` VALUES ('406', '199', '2', '1');
INSERT INTO `room` VALUES ('407', '299', '4', '1');
INSERT INTO `room` VALUES ('408', '299', '4', '1');

-- ----------------------------
-- Table structure for `room_img`
-- ----------------------------
DROP TABLE IF EXISTS `room_img`;
CREATE TABLE `room_img` (
  `imgid` int(4) NOT NULL AUTO_INCREMENT,
  `rid` varchar(10) DEFAULT NULL,
  `firstImg` varchar(200) DEFAULT NULL,
  `secondImg` varchar(200) DEFAULT NULL,
  `thirdImg` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`imgid`),
  KEY `rid` (`rid`),
  CONSTRAINT `room_img_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `room` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_img
-- ----------------------------
INSERT INTO `room_img` VALUES ('1', '101', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('2', '102', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('3', '103', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('4', '104', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('5', '105', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('6', '106', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('7', '107', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('8', '108', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('9', '201', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('10', '202', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('11', '203', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('12', '204', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('13', '205', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('14', '206', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('15', '207', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('16', '208', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('17', '301', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('18', '302', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('19', '303', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('20', '304', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('21', '305', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('22', '306', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('23', '307', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('24', '308', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('25', '401', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('26', '402', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('27', '403', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('28', '404', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('29', '405', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('30', '406', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('31', '407', 'tupian1', 'tupian2', 'tupian3');
INSERT INTO `room_img` VALUES ('32', '408', 'tupian1', 'tupian2', 'tupian3');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `userStatus` int(5) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '管理员', '男', '123456', '13654876548', '3');
INSERT INTO `user` VALUES ('2', 'xiaolongnv', '小龙女', '女', '123456', '14365217896', '1');
INSERT INTO `user` VALUES ('3', 'xiaoqiao', '小乔', '女', '123456', '15799801425', '1');
INSERT INTO `user` VALUES ('4', 'zhangwuji', '张无忌', '男', '123456', '17898725489', '2');
INSERT INTO `user` VALUES ('5', 'lvxiaobu', '吕小布', '男', '123456', '18301597468', '1');
INSERT INTO `user` VALUES ('6', 'wangzhaojun', '王昭君', '女', '123456', '13598745866', '2');
INSERT INTO `user` VALUES ('7', 'xiangwentian', '向问天', '男', '123456', '13448897979', '1');
INSERT INTO `user` VALUES ('8', 'linghuchong', '令狐冲', '男', '123456', '15466987463', '1');
INSERT INTO `user` VALUES ('9', 'zuihouyici', '最后一次', '男', '123456', '13254602405', '0');
INSERT INTO `user` VALUES ('10', 'zhangsanfeng', '张三丰', '男', '123456', '18302102546', '1');
INSERT INTO `user` VALUES ('11', 'xiangyu', '项羽', '男', '123456', '18836205547', '2');
INSERT INTO `user` VALUES ('12', 'jiangxiaobai', '江小白', '男', '123456', '13433605205', '1');
INSERT INTO `user` VALUES ('13', 'zhouyu', '周瑜', '男', '123456', '18932025468', '1');
INSERT INTO `user` VALUES ('14', 'zhaozilong', '赵子龙', '男', '123456', '17852131400', '2');
INSERT INTO `user` VALUES ('15', 'yangguo', '杨过', '男', '123456', '15153415489', '1');
