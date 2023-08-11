DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) COMMENT '父選單ID，一级選單为0',
  `name` varchar(128) COMMENT '選單名称',
  `url` varchar(256) COMMENT '選單URL',
  `perms` varchar(500) COMMENT '授權(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint COMMENT '类型   0：目錄   1：選單   2：按钮',
  `icon` varchar(256) COMMENT '選單圖',
  `order_num` int COMMENT '排序號',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL COMMENT '使用者名稱',
  `nickname` varchar(128) COMMENT '暱稱',
  `password` varchar(128) COMMENT '密碼',
  `salt` varchar(32) COMMENT '盐',
  `email` varchar(64) COMMENT 'email',
  `mobile` varchar(32) COMMENT '手機號碼',
  `status` tinyint COMMENT '状态  0：停用   1：正常',
  `create_time` datetime COMMENT '建立時間',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`username`),
  KEY `email` (`email`),
  KEY `mobile` (`mobile`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统使用者';

DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '使用者ID',
  `token` varchar(128) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '建立時間',
  `update_time` datetime DEFAULT NULL COMMENT '變更时間',
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户Token';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COMMENT '使用者名稱',
  `remark` varchar(256) COMMENT '備註',
  `create_time` datetime COMMENT '建立時間',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='群組';

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '使用者ID',
  `role_id` bigint(20) NOT NULL COMMENT '群組ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='使用者与群組對應關聯';

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '使用者ID',
  `menu_id` bigint(20) NOT NULL COMMENT '選單ID',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='使用者与選單對應關聯';

DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`key` varchar(128) COMMENT 'key',
	`value` text COMMENT 'value',
	`status` tinyint DEFAULT 1 COMMENT '狀態   0：隐藏   1：顯示',
	`remark` varchar(256) COMMENT '備註',
	PRIMARY KEY (`id`),
	UNIQUE INDEX (`key`),
	KEY `status` (`status`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) COMMENT '使用者名稱',
  `operation` varchar(128) COMMENT '使用者操作',
  `method` varchar(256) COMMENT '請求方法',
  `params` text COMMENT '請求参数',
  `ip` varchar(64) COMMENT 'IP',
  `time` bigint COMMENT '執行時間(毫秒)',
  `create_time` datetime COMMENT '建立時間',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8mb4 COMMENT='系统日誌';

DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` text COMMENT '標題',
  `user_id` bigint(20) COMMENT '使用者ID',
  `path` varchar(512) COMMENT '路徑',
  `mime_type` varchar(128) COMMENT 'mime',
  `suffix` varchar(32) COMMENT '附件',
  `create_time` datetime COMMENT '建立时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `mime_type` (`mime_type`)
) ENGINE=`InnoDB` DEFAULT CHARSET=utf8mb4 COMMENT='附件';



CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '部門名稱',
  `parent_id` varchar(128) COMMENT '上级部門編號',
  `order_Num` varchar(128) COMMENT '排序',
  `status` tinyint COMMENT '状态  0：停用   1：正常',
  `create_time` datetime COMMENT '建立時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部門管理';

insert into `sys_user` (`id`, `username`, `nickname`, `password`, `salt`, `status`)values ('1', 'admin', NULL, '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d','YzcmCZNvbXocrsz9dm8e','1');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('1','0','系统管理',NULL,NULL,'0','fa fa-cogs','0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('2','1','使用者管理','modules/sys/user.html',NULL,'1','fa fa-user','1');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('3','2','查看',NULL,'sys:user:list,sys:user:info','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('4','2','新增',NULL,'sys:user:save,sys:role:select,sys:dept:select,sys:dept:list','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('5','2','修改',NULL,'sys:user:update,sys:role:select,sys:dept:select,sys:dept:list','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('6','2','删除',NULL,'sys:user:delete','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('12','1','群組管理','modules/sys/role.html',NULL,'1','fa fa-user-secret','3');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('13','12','查看',NULL,'sys:role:list,sys:role:info','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('14','12','新增',NULL,'sys:role:save,sys:menu:list','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('15','12','修改',NULL,'sys:role:update,sys:menu:list','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('16','12','删除',NULL,'sys:role:delete','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('17','1','選單管理','modules/sys/menu.html',NULL,'1','fa fa-th-list','4');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('18','17','查看',NULL,'sys:menu:list,sys:menu:info','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('19','17','新增',NULL,'sys:menu:save,sys:menu:select','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('20','17','修改',NULL,'sys:menu:update,sys:menu:select','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('21','17','删除',NULL,'sys:menu:delete','2',NULL,'0');

insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('32','1','系统日誌','modules/sys/log.html','sys:log:list','1','fa fa-pencil','7');




insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('40','1','部門管理','modules/sys/dept.html',NULL,'1','fa fa-user','1');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('41','40','查看',NULL,'sys:dept:list,sys:user:info','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('42','40','新增',NULL,'sys:dept:save,sys:role:select,sys:dept:select,sys:dept:list','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('43','40','修改',NULL,'sys:dept:update,sys:dept:list','2',NULL,'0');
insert into `sys_menu` (`id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('44','40','删除',NULL,'sys:dept:delete','2',NULL,'0');