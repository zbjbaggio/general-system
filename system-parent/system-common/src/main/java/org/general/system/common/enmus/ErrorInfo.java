package org.general.system.common.enmus;


/**
 * 错误信息
 * Created by eason on 2017-2-22.
 */
public enum ErrorInfo {
    SUCCESS(200, "ok"),
    ERROR(500, "系统异常"),
    PARAMS_ERROR(50001, "参数错误！"),
    LOGIN_ERROR(50002, "用户名或密码错误！"),
    MOBILE_LOGIN_ERROR(50002, "会员号或者密码错误！"),
    LOGIN_AGAIN(50003, "重新登录！"),
    NO_LOGIN(50004, "未登录！"),
    NO_AUTHORITY(50005, "未受权！"),
    USER_NAME_SAME(50006, "用户名重复！"),
    REGISTER_ERROR(50007, "用户注册失败！" ),
    USER_LOCKED(50008, "该用户已被锁定，请联系管理员！" ),
    USER_UNACTIVATED(50009, "该用户还未审核通过请耐心等待！"),
    USER_NO_LOGIN(50010, "该用户已冻结请1个小时后再试！"),
    ADD_ERROR(50011, "新增失败！"),
    STATUS_ERROR(50012, "修改状态失败！"),
    DELETE_ERROR(50013, "删除失败！"),
    NAME_SAME(50014, "名称重复！"),
    UPDATE_ERROR(50015, "修改失败！"),
    PASSWORD_ERROR(50016, "密码错误！"),
    NO_SAME(50017, "编号重复！"),
    PAY_ERROR(50018, "支付失败！"),
    ORDER_ERROR(50019, "下单失败，请重新下单!"),
    NO_ERROR(50020, "编号不正确！"),
    BUTTON_ERROR(50021, "菜单有子菜单不能有按钮！"),
    MEMBER_NO_ERROR(50022, "会员编号重复！"),
    ORDER_UPDATE_ERROR(50023, "订单已经有快递信息不能修改！"),
    MEMBER_NO_NAME_ERROR(50024, "用户名和会员号不一致!"),
    CARD_SAME(50025, "填写的付款码之间重复!"),
    CARD_SAME2(50026, "填写的付款码与历史数据重复!"),
    ;

    private int value;

    private String name;

    ErrorInfo(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
