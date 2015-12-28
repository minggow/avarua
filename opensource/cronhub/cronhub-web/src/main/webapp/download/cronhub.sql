CREATE TABLE `tb_schedule_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_type` tinyint(2) NOT NULL COMMENT '1:管理员',
  `department` varchar(50) NOT NULL DEFAULT '' COMMENT '部门',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `daemon_id` bigint(20) unsigned NOT NULL COMMENT '执行此任务daemon执行器的id',
  `user_id` varchar(100) NOT NULL DEFAULT '' COMMENT '用户id',
  `cron_exp` varchar(16) NOT NULL COMMENT 'crontab表达式',
  `shell_cmd` varchar(1000) NOT NULL COMMENT '运行的命令,原始命令,未替换参数前',
  `must_replace_cmd` tinyint(1) NOT NULL COMMENT 'bool.是否需要替换`撇号中的命令为执行结果',
  `run_mode` tinyint(1) NOT NULL COMMENT '0--被动模式,1--主动模式',
  `run_start_reportaddress` varchar(500) DEFAULT NULL COMMENT 'cmd命令开始执行的汇报地址',
  `run_end_reportaddress` varchar(500) DEFAULT NULL COMMENT 'cmd命令结束执行的汇报地址',
  `is_process_node` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0--不是流程中的一个节点,1--是流程中的一个节点',
  `is_process_chain` tinyint(1) DEFAULT '0' COMMENT '0--不是流程链task,1--是流程链task',
  `process_tasks` text COMMENT '流程链模式下的子链条task id集合',
  `comment` text NOT NULL COMMENT '简介',
  `operate_uid` bigint(20) DEFAULT '-1' COMMENT '操作人的id号',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `is_redo` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0--不重新执行此任务,1--重新执行此任务',
  `end_redo_times` int(11) NOT NULL DEFAULT '0' COMMENT '截止重新执行次数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_task_done` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL,
  `real_cmd` varchar(1000) DEFAULT NULL,
  `exit_code` int(10) NOT NULL,
  `complete_success` tinyint(1) DEFAULT NULL,
  `start_datetime` datetime NOT NULL,
  `end_datetime` datetime NOT NULL,
  `exec_type` int(10) NOT NULL,
  `exec_return_str` longtext,
  `current_redo_times` int(11) DEFAULT NULL,
  `on_processing` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=507 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_task_record_done` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL COMMENT '该task任务是哪个task id执行的结果',
  `real_cmd` varchar(1000) DEFAULT NULL COMMENT '被替换参数为现场时间后后真实的命令',
  `exit_code` int(10) NOT NULL COMMENT '完成的返回值。0--成功，其他都--失败',
  `complete_success` tinyint(1) DEFAULT NULL COMMENT '完成的返回状态。1--成功，0--失败',
  `start_datetime` datetime NOT NULL COMMENT '任务开始时间(如果是自动重执行时,每次执行不修改起始时间)',
  `end_datetime` datetime NOT NULL COMMENT '任务结束时间',
  `exec_type` int(10) NOT NULL COMMENT '执行类型,0--crontab执行，1--手动重执行,2--自动重执行,3--当场执行等',
  `exec_return_str` longtext COMMENT '执行后的外部进程字符串返回结果。',
  `current_redo_times` int(11) DEFAULT NULL COMMENT '当前第几次自动重试执行',
  `on_processing` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'bool是否正在执行中,0--没有正在执行,1--正在手动/自动执行',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1839 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_task_record_undo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL COMMENT '该任务执行者的task_id号码',
  `real_cmd` varchar(1000) DEFAULT NULL COMMENT '被替换参数为现场时间后后真实的命令',
  `run_status` int(4) NOT NULL COMMENT '运行状态---0 进行中...',
  `start_datetime` datetime NOT NULL COMMENT '执行开始时间',
  `exec_type` int(10) NOT NULL COMMENT '执行类型,0--crontab执行，1--手动重执行...',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12683 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_task_undo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL,
  `real_cmd` varchar(1000) DEFAULT NULL,
  `run_status` int(4) NOT NULL,
  `start_datetime` datetime NOT NULL,
  `exec_type` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8