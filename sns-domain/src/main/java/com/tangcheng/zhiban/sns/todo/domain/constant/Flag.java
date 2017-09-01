package com.tangcheng.zhiban.sns.todo.domain.constant;

/**
 * Created by tangcheng on 8/27/2017.
 */
public class Flag {

    public static class UniversalFlag {
        public static final byte NORMAL = 0;
        public static final byte PENDING = -1;
        public static final byte DELETE = -2;

    }

    public static final class UserRoleFlag {
        public static final long ADMIN = 1;
        public static final long USER = 2;
    }

    public static final class UserTypeFlag {
        public static final byte SIGN_UP = 1;
        public static final byte TWITTER = 2;
        public static final byte FACEBOOK = 3;
    }


}
