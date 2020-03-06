package org.general.system.common.data.entity;

import lombok.Data;

/**
 * 系统用户表 t_system_user
 *
 * @author eason
 * @date 2020-03-06
 */
@Data
public class SystemUser extends BaseEntity {

	/** 主键 */
	private Long id;

	/** 用户登录名 */
	private String username;

	/** 密码 */
	private String password;

	/** md5密码盐 */
	private String solt;

	/** 状态(1-正常,2-冻结) */
	private Integer status;

}
