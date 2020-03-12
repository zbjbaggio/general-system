package org.general.system.common.data.entity.system;

import lombok.Data;
import org.general.system.common.data.entity.BaseEntity;

/**
 * 角色表 t_system_role
 *
 * @author eason
 * @date 2020-03-06
 */
@Data
public class SystemRole extends BaseEntity {

	/** 主键 */
	private Long id;

	/** 角色名称 */
	private String name;

	/** 描述 */
	private String description;

}
