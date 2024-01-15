INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '*/30 * * * * *', 'autoSceneNameTask', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );	
	

INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '*/30 * * * * *', 'autoSceneNameLoadTask', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );	
	
	
INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '{"editPreposTime": 1,"prefix":"AD-M3/DEVICE-01-","open":"5501A40000A5","close":"5501A50000A4"}', 'deviceConfig', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );
	
	
INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '{"frontPrefix":"AD-M3/LIGHT-FRONT-","rearPrefix":"AD-M3/LIGHT-REAR-","ambPrefix":"AD-M3/LIGHT-AMB-","downPrefix":"AD-M3/LIGHT-DOWN-","oneTouchPrefix":"AD-M3/LIGHT-ONETOUCH-","open":"ON","close":"OFF"}', 'lightConfig', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );
	
	
INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '{"prefix":"AD-M3/LED-01-","open":"ON","close":"OFF"}', 'LedConfig', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );