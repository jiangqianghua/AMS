CREATE DATABASE IF NOT EXISTS ams default charset utf8 COLLATE utf8_general_ci;
--部门表
CREATE TABLE IF NOT EXISTS `sys_dept`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '部门id',
	`name` varchar(20) NOT NULL DEFAULT '' COMMENT '部门名称',
	`parent_id` INT NOT NULL DEFAULT 0 COMMENT '上级部门id',
	`level` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '部门层级',
	`seq` INT NOT NULL DEFAULT 0 COMMENT '部门在当前层级下的顺序,由小到大',
	`remark` VARCHAR(200) DEFAULT '' COMMENT '备注',
	`operator`	varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	PRIMARY KEY (`id`)
) COMMENT="部门表";


CREATE TABLE IF NOT EXISTS `sys_user`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`username` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户名',
	`telephone` VARCHAR(13) NOT NULL DEFAULT '' COMMENT '电话',
	`mail` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '邮箱',
	`password` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '密码',
	`dept_id` INT NOT NULL DEFAULT 0 COMMENT '部门id',
	`status` INT NOT NULL DEFAULT 1 COMMENT '1正常 0冻结 2删除', 
	`remark` VARCHAR(200) DEFAULT '' COMMENT '备注',
	`operator` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	PRIMARY KEY (`id`)
) COMMENT='用户列表';


CREATE TABLE IF NOT EXISTS `sys_acl_module`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
	`name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限模块名称',
	`parent_id` INT NOT NULL DEFAULT 0 COMMENT '上级模块id',
	`level` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '模块层级',
	`status` INT NOT NULL DEFAULT 1 COMMENT '1正常 0冻结', 
	`seq` INT NOT NULL DEFAULT 0 COMMENT '模块在当前层级下的顺序,由小到大',
	`remark` VARCHAR(200) DEFAULT '' COMMENT '备注',
	`operator`	varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	PRIMARY KEY (`id`)
) COMMENT="权限模块表";


CREATE TABLE IF NOT EXISTS `sys_acl`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
	`code` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '权限码',
	`name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
	`acl_module_id` INT NOT NULL DEFAULT 0 COMMENT '权限所属权限模块id',
	`url` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '请求的url，可以是正则',
	`type` INT NOT NULL DEFAULT 1 COMMENT '1 菜单 2按钮 3其他', 
	`status` INT NOT NULL DEFAULT 1 COMMENT '1正常 0冻结', 
	`seq` INT NOT NULL DEFAULT 0 COMMENT '权限在当前层级下的顺序,由小到大',
	`remark` VARCHAR(200) DEFAULT '' COMMENT '备注',
	`operator`	varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	PRIMARY KEY (`id`)
) COMMENT="权限表";


CREATE TABLE IF NOT EXISTS `sys_role`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '角色id',
	`name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
	`type` INT NOT NULL DEFAULT 1 COMMENT '1 管理员 2其他',
	`status` INT NOT NULL DEFAULT 1 COMMENT '1 正常 0 冻结',
	`remark` VARCHAR(200) DEFAULT '' COMMENT '备注',
	`operator`	varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	PRIMARY KEY (`id`)
) COMMENT="角色表";


CREATE TABLE IF NOT EXISTS `sys_role_user`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
	`role_id` INT NOT NULL COMMENT '角色id',
	`user_id` INT NOT NULL COMMENT '用户id',
	`operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	PRIMARY KEY (`id`)
) COMMENT="角色用户关系表";


CREATE TABLE IF NOT EXISTS `sys_role_acl`(	
	`id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
	`role_id` INT NOT NULL COMMENT '角色id',
	`acl_id` INT NOT NULL COMMENT '角色id',
	`operator`	varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	PRIMARY KEY (`id`)
) COMMENT="角色权限关系表";


CREATE TABLE IF NOT EXISTS `sys_log`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
	`type` INT NOT NULL DEFAULT 0 COMMENT '权限更新类型1部门 2 用户 3 权限模块 4 权限 5 角色 6角色用户关系 7角色权限关系',
	`target_id` INT NOT NULL COMMENT '基于type指定后的的对象id',
	`old_value` TEXT COMMENT '旧值',
	`new_value` TEXT COMMENT '新值',
	`operator`	varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
	`operator_time` datetime NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
	`operator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者',
	`status` INT NULL DEFAULT 0 COMMENT '当前是否复原过 0没有  1复原过',
	PRIMARY KEY (`id`)
) COMMENT="操作日志表";


