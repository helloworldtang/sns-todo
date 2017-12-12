package com.tangcheng.zhiban.sns.todo.core.constant;

/**
 * @author tangcheng
 * 2017/12/12
 */
public enum GenderEnum {
    Male("M"),
    Female("F"),
    Transgender("T"),
    Unknown("U"),;

    private String shorthand;

    GenderEnum(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getShorthand() {
        return shorthand;
    }
}
