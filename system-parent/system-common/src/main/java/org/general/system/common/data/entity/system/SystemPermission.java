package org.general.system.common.data.entity.system;

import lombok.Data;
import org.general.system.common.data.entity.BaseEntity;

/**
 * 菜单权限表 t_system_permission
 *
 * @author eason
 * @date 2020-03-06
 */
@Data
public class SystemPermission extends BaseEntity {

	/** 主键 */
	private Long id;
	/** 父类权限id */
	private Long parentId;
	/** 名称 */
	private String name;
	/** 编号用于菜单结构显示，2位一层 0102，9901 */
	private String code;
	/** 菜单图标 */
	private String icon;
	/** 类型 0 菜单 1 按钮 */
	private Integer type;

}
