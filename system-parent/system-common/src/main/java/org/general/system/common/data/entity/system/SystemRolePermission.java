package org.general.system.common.data.entity.system;

import lombok.Data;
import org.general.system.common.data.entity.BaseEntity;

/**
 * 角色权限表 t_system_role_permission
 *
 * @author eason
 * @date 2020-03-05
 */
@Data
public class SystemRolePermission extends BaseEntity {

	/** 主键 */
	private Long id;
	/**  */
	private Long roleId;
	/**  */
	private Long permissionId;

}
