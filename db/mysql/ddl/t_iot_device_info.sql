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
-- Table structure for t_iot_device_info
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_device_info`;
CREATE TABLE `t_iot_device_info`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `DEVICE_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备ID',
   `DEVICE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备名称',
   `DEVICE_MODEL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备型号',
   `DEVICE_BRAND` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备品牌',
   `DEVICE_IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备IP',
   `DEVICE_PORT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备端口',
   `DEVICE_STATE` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备状态：1：在线、2：下线',
   `DEVICE_ROOM` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备所在会议室',
   `DEVICE_AREA` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '设备区域',
   `INCREASE_TIME` datetime  NOT NULL COMMENT '添加时间',
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备基本信息表';