-- ���ӱ�������
INSERT INTO pvmts_menus (
	`id`,
	`name`,
	`create_time`,
	`update_time`
)
VALUES
	(
		'26',
		'��������',
		'2017-12-01 13:26:24',
		'2017-12-01 13:26:27'
	);


-- ����Ĭ������ ��˾����Ա

INSERT INTO pvmts_roles_menu(
	`role_id`,
	`menu_id`,
	`handle_premission`,
	`create_time`,
	`update_time`
)
VALUES
	(
		'2',
		'26',
		'1',
		'2017-12-01 13:49:29',
		'2017-12-01 13:49:31'
	);

