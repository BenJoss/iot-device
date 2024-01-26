INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '*/30 * * * * *', 'autoSceneNameTask', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );	
	

INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '*/30 * * * * *', 'autoSceneNameLoadTask', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );	
	
	
INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '{"count": 2,"editPreposTime": 1,"prefix":"AD-M3/ALL-01-","open":"000","close":"001"}', 'deviceConfig', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );
	
	
INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '{"count": 2,"prefix":"AD-M3/ALL-01-","oneTouchOpen":"018","oneTouchClose":"019","frontOpen":"014","frontClose":"015","rearOpen":"016","rearClose":"017","ambOpen":"012","ambClose":"013","downOpen":"010","downClose":"011"}', 'lightConfig', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );
	
	
INSERT INTO `iot_meeting`.`t_iot_config` ( `CON_VALUE`, `CON_KEY`, `CON_ORDER`, `CREATE_TIME`, `UPDATE_TIME`, `BACK_COL1`, `BACK_COL2`, `BACK_COL3`, `BACK_COL4`, `BACK_COL5`, `BACK_COL6` )
VALUES
	( '{"count": 2,"prefix":"AD-M3/ALL-01-","open":"030","close":"031"}', 'LedConfig', 1, NOW(), NOW(), NULL, NULL, NULL, NULL, NULL, NULL );