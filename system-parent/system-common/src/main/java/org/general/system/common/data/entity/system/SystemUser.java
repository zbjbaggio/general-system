package org.general.system.common.data.entity.system;

import lombok.Data;
import org.general.system.common.constants.SystemUserStatus;
import org.general.system.common.data.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * 系统用户表 t_system_user
 *
 * @author eason
 * @date 2020-03-06
 */
@Data
public class SystemUser extends BaseEntity {

	/** 用户登录名 */
	@NotEmpty(groups = {Add.class, Modify.class})
	private String username;

	/** 密码 */
	@NotEmpty(groups = {Add.class, Modify.class})
	private String password;

	/** md5密码盐 */
	private String solt;

	/**
	 * 状态(1-正常,2-冻结)
	 * {@link SystemUserStatus}
	 * */
	private Integer status;

	public interface Add {

	}

	public interface Modify {

	}


}
