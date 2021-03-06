/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : gateway

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 09/12/2021 16:13:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名',
  `uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '转发地址',
  `predicates` json NOT NULL COMMENT '访问路径',
  `filters` json NOT NULL COMMENT '过滤',
  `order_num` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `creator_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标记。1删除，0未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES (1, 'test', 'http://www.baidu.com', '[{\"args\": {\"_genkey_0\": \"/test/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"_genkey_0\": \"1\"}, \"name\": \"StripPrefix\"}]', '9', NULL, '2021-12-06 15:25:39', '', '2021-12-09 16:04:39', '测试', '0');
INSERT INTO `gateway_route` VALUES (2, 'test', 'http://www.baidu.com', '[{\"args\": {\"pattern\": \"/test/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"_genkey_0\": \"1\"}, \"name\": \"StripPrefix\"}]', '45', NULL, '2021-12-06 15:25:39', '', '2021-12-09 12:11:05', '测试', '0');

SET FOREIGN_KEY_CHECKS = 1;
