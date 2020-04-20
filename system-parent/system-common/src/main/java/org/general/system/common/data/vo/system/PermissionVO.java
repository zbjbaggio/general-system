package org.general.system.common.data.vo.system;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * Created by jay on 2017-12-13.
 */
@Data
@ToString(callSuper = true, exclude = {"createTime"})
public class PermissionVO {

    private Long id;

    private Long parentId;

    private int type;

    private String permission;

    //private String code;

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

        // 页面拥有的权限
        private List<String> permission = new ArrayList<>();

    }

    List<PermissionVO> children;

    //List<PermissionVO> button;
}
