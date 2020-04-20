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
@Data
@ToString(callSuper = true, exclude = {"createTime"})
public class PermissionVO {

    private Long parentId;

    private int type;

    private String permission;



    private String code;

    private String path;

    private String component;

    private String redirect;

    private String alwaysShow;

    private String name;

    private Meta meta;

    @Data
    public static class Meta {

        private String title;

        private String icon;

    }

    List<PermissionVO> children;

    //List<PermissionVO> button;
}
