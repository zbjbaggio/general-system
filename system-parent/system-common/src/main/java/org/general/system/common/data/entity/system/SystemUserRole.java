package org.general.system.common.data.entity.system;

import lombok.Data;
import org.general.system.common.data.entity.BaseEntity;

/**
 * 用户角色表 t_system_user_role
 *
 * @author eason
 * @date 2020-03-06
 */
@Data
public class SystemUserRole extends BaseEntity {

	/** 用户id */
	private Long userId;

	/** 角色id */
	private Long roleId;

}
