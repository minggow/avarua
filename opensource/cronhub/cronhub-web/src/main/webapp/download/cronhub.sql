CREATE TABLE `tb_schedule_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����id',
  `user_id` varchar(50) NOT NULL COMMENT '�û�id',
  `user_name` varchar(50) NOT NULL COMMENT '�û���',
  `user_type` tinyint(2) NOT NULL COMMENT '1:����Ա',
  `department` varchar(50) NOT NULL DEFAULT '' COMMENT '����',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '�绰',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `daemon_id` bigint(20) unsigned NOT NULL COMMENT 'ִ�д�����daemonִ������id',
  `user_id` varchar(100) NOT NULL DEFAULT '' COMMENT '�û�id',
  `cron_exp` varchar(16) NOT NULL COMMENT 'crontab���ʽ',
  `shell_cmd` varchar(1000) NOT NULL COMMENT '���е�����,ԭʼ����,δ�滻����ǰ',
  `must_replace_cmd` tinyint(1) NOT NULL COMMENT 'bool.�Ƿ���Ҫ�滻`Ʋ���е�����Ϊִ�н��',
  `run_mode` tinyint(1) NOT NULL COMMENT '0--����ģʽ,1--����ģʽ',
  `run_start_reportaddress` varchar(500) DEFAULT NULL COMMENT 'cmd���ʼִ�еĻ㱨��ַ',
  `run_end_reportaddress` varchar(500) DEFAULT NULL COMMENT 'cmd�������ִ�еĻ㱨��ַ',
  `is_process_node` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0--���������е�һ���ڵ�,1--�������е�һ���ڵ�',
  `is_process_chain` tinyint(1) DEFAULT '0' COMMENT '0--����������task,1--��������task',
  `process_tasks` text COMMENT '������ģʽ�µ�������task id����',
  `comment` text NOT NULL COMMENT '���',
  `operate_uid` bigint(20) DEFAULT '-1' COMMENT '�����˵�id��',
  `update_time` datetime DEFAULT NULL COMMENT '�޸�����',
  `is_redo` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0--������ִ�д�����,1--����ִ�д�����',
  `end_redo_times` int(11) NOT NULL DEFAULT '0' COMMENT '��ֹ����ִ�д���',
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
  `task_id` bigint(20) NOT NULL COMMENT '��task�������ĸ�task idִ�еĽ��',
  `real_cmd` varchar(1000) DEFAULT NULL COMMENT '���滻����Ϊ�ֳ�ʱ������ʵ������',
  `exit_code` int(10) NOT NULL COMMENT '��ɵķ���ֵ��0--�ɹ���������--ʧ��',
  `complete_success` tinyint(1) DEFAULT NULL COMMENT '��ɵķ���״̬��1--�ɹ���0--ʧ��',
  `start_datetime` datetime NOT NULL COMMENT '����ʼʱ��(������Զ���ִ��ʱ,ÿ��ִ�в��޸���ʼʱ��)',
  `end_datetime` datetime NOT NULL COMMENT '�������ʱ��',
  `exec_type` int(10) NOT NULL COMMENT 'ִ������,0--crontabִ�У�1--�ֶ���ִ��,2--�Զ���ִ��,3--����ִ�е�',
  `exec_return_str` longtext COMMENT 'ִ�к���ⲿ�����ַ������ؽ����',
  `current_redo_times` int(11) DEFAULT NULL COMMENT '��ǰ�ڼ����Զ�����ִ��',
  `on_processing` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'bool�Ƿ�����ִ����,0--û������ִ��,1--�����ֶ�/�Զ�ִ��',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1839 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_task_record_undo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL COMMENT '������ִ���ߵ�task_id����',
  `real_cmd` varchar(1000) DEFAULT NULL COMMENT '���滻����Ϊ�ֳ�ʱ������ʵ������',
  `run_status` int(4) NOT NULL COMMENT '����״̬---0 ������...',
  `start_datetime` datetime NOT NULL COMMENT 'ִ�п�ʼʱ��',
  `exec_type` int(10) NOT NULL COMMENT 'ִ������,0--crontabִ�У�1--�ֶ���ִ��...',
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