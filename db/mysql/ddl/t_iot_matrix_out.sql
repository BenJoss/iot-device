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
-- Table structure for t_iot_matrix_out
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_matrix_out`;
CREATE TABLE `t_iot_matrix_out`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `ROOM_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '会议室ID',
   `SCENE_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '场景名称',
   `LED_SWITCH` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT 'LED开关',
   `MONI_TOR` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '监视器',
   `TV_LEFT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '电视左',
   `TV_RIGHT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '电视右',
   `EN_CODER` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '编码器',
   `TENCENT_MEET` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '腾讯会议',
   `TERMINAL_1` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '国网终端1',
   `TERMINAL_2` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '国网终端2',
   `GROUP_1` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '集团终端1',
   `GROUP_2` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '集团终端2',
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NULL COMMENT '更新时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`) USING BTREE,
   KEY DEVICE_ID_KEY (DEVICE_ID),
   KEY DEVICE_NAME_IP_AREA_KEY (DEVICE_NAME,DEVICE_IP,DEVICE_ROOM)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '矩阵输出信息表';