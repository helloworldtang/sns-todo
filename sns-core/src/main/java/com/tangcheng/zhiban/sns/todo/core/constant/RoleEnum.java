package com.tangcheng.zhiban.sns.todo.core.constant;

/**
 * @author tangcheng
 * 2017/12/06
 */
public enum RoleEnum {
    ADMIN(1L),
    USER(2L);

    private long roleId;

    RoleEnum(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public static long[] adminRoles() {
        return new long[]{ADMIN.getRoleId(), USER.getRoleId()};
    }

    public static long[] userRoles() {
        return new long[]{USER.getRoleId()};
    }
}
