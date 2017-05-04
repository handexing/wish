/*
Navicat MySQL Data Transfer

Source Server         : vipsnacks
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : wish

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-05-04 14:47:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `PSW` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '小强', '123456');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '标题',
  `CONTENT` text COMMENT '内容',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('4', 'hahaha', '# this is title111', '2017-05-02 09:37:29', '2017-05-02 09:38:50', '0');
INSERT INTO `article` VALUES ('5', 'this is test', ':bowtie::smile:\n\n# fsdf\n\n![输入图片说明](http://localhost:8888/wish/upload/image/2017-05-02/cb7bb9a4-6c83-4e6d-b323-5de505d3ebda.jpg \"在这里输入图片标题\")', '2017-05-02 09:42:22', '2017-05-02 10:08:38', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) NOT NULL COMMENT '父ID',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `STATUS` int(11) DEFAULT '1' COMMENT '状态',
  `CREATE_TIME` datetime DEFAULT NULL,
  `HREF` varchar(200) DEFAULT NULL COMMENT '请求地址',
  `ICON` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`ID`,`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '系统管理', '0', '2017-04-19 17:59:10', '', '&#xe631;');
INSERT INTO `menu` VALUES ('3', '1', '用户列表', '0', '2017-04-20 13:25:00', 'user/userPage', '&#xe612;');
INSERT INTO `menu` VALUES ('7', '1', '菜单列表', '0', '2017-04-20 14:13:36', 'menu/menuPage', '&#xe628;');
INSERT INTO `menu` VALUES ('9', '1', '角色管理', '0', '2017-04-24 09:41:00', 'role/rolePage', '&#xe62e;');
INSERT INTO `menu` VALUES ('12', '0', '文章管理', '0', '2017-04-28 10:29:42', '', '&#xe630;');
INSERT INTO `menu` VALUES ('13', '12', '文章列表', '0', '2017-04-28 10:31:01', 'article/articlePage', '&#xe636;');
INSERT INTO `menu` VALUES ('14', '0', '功能杂项', '0', '2017-05-02 10:30:57', '', '&#xe622;');
INSERT INTO `menu` VALUES ('15', '14', '二维码', '0', '2017-05-02 10:31:20', 'qrcode/qrcodePage', '&#xe650;');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '0', '2017-04-19 17:56:02');
INSERT INTO `role` VALUES ('2', 'test', '0', '2017-04-24 10:41:24');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('125', '2', '3', '2017-04-27 17:20:32');
INSERT INTO `role_menu` VALUES ('126', '2', '1', '2017-04-27 17:20:32');
INSERT INTO `role_menu` VALUES ('141', '1', '3', '2017-05-03 11:00:47');
INSERT INTO `role_menu` VALUES ('142', '1', '7', '2017-05-03 11:00:47');
INSERT INTO `role_menu` VALUES ('143', '1', '9', '2017-05-03 11:00:47');
INSERT INTO `role_menu` VALUES ('144', '1', '1', '2017-05-03 11:00:47');

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '1', '1', '2017-04-19 17:55:02');
INSERT INTO `role_user` VALUES ('2', '2', '2', '2017-04-24 14:29:10');
INSERT INTO `role_user` VALUES ('5', '1', '5', '2017-04-27 17:42:29');
INSERT INTO `role_user` VALUES ('6', '1', '6', '2017-04-28 09:47:25');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `NAME` varchar(100) DEFAULT NULL COMMENT '姓名',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '密码',
  `ACCOUNT` varchar(64) DEFAULT NULL COMMENT '账号',
  `PHONE` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '新建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '超级超级小管理', '827ccb0eea8a706c4c34a16891f84e7b', '12345', '110', '0', '2017-04-19 17:13:40');
INSERT INTO `user` VALUES ('2', 'handx', '827ccb0eea8a706c4c34a16891f84e7b', '123456', '110', '0', '2017-04-20 14:28:54');
