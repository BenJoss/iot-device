/*
 Navicat Premium Data Transfer

 Source Server         : 10.31.0.101
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : 10.31.0.101:3306
 Source Schema         : iot_meeting

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001
DEVICE
 Date: 11/08/2023 14:51:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_iot_light_room
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_light_room`;
CREATE TABLE `t_iot_light_room`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `ROOM_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '会议室ID',
   `SCENE_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '场景名称',
   `FRONT_LIGHT` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '前排灯',
   `REAR_LIGHT` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '后排灯',
   `AMB_LIGHT` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '氛围灯',
   `DOWN_LIGHT` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '筒灯',
   `LIGHT_ONE_TOUCH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '灯光一键开关',
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NULL COMMENT '更新时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`) USING BTREE,
   KEY ROOM_ID_KEY (ROOM_ID)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会议室灯光信息表';