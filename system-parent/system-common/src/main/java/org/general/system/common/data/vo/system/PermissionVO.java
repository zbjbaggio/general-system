package org.general.system.common.data.vo.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.general.system.common.data.entity.system.SystemPermission;

import java.util.List;

/**
 * 描述：
 * Created by jay on 2017-12-13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString(callSuper = true, exclude = {"createTime"})
public class PermissionVO extends SystemPermission {

    List<PermissionVO> children;

    //List<PermissionVO> button;
}
