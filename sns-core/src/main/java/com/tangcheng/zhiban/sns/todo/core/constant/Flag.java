package com.tangcheng.zhiban.sns.todo.core.constant;

/**
 * Created by tangcheng on 8/27/2017.
 */
public class Flag {

    public static final class BizLogFlag {
        public static final String WARN_CHECK = "Please check.";

    }

    public static class UniversalFlag {
        public static final byte NORMAL = 0;
        public static final byte PENDING = -1;
        public static final byte DELETE = -2;

    }

    public static final class UserTypeFlag {
        public static final byte SIGN_UP = 1;
        public static final byte GITHUB = 2;
        public static final byte QQ = 3;
        public static final byte SINA = 4;
        public static final byte TWITTER = 5;
        public static final byte FACEBOOK = 6;
    }


}
