package com.mingguo.wu.infra.enums;

/**
 * Created by wumingguo on 2015/10/2.
 */
public enum WeekEnum implements ICollectionEnum<WeekEnum> {
    SUNDAY(0x1),
    MONDAY(0x2),
    TUESDAY(0x4),
    WEDNESDAY(0x8),
    THURSDAY(0x10),
    FRIDAY(0x20),
    SATURDAY(0x40);

    private int code;

    WeekEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
