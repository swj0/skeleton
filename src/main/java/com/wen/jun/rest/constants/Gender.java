package com.wen.jun.rest.constants;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableMap;

import java.util.EnumSet;

/**
 * 性别
 */
@JsonDeserialize(using = Gender.JsonDeser.class)
public enum Gender implements ViewEnum {
    unknow("未知", 0),
    man("男", 1),
    woman("女", 2),
    ;

    public static class JsonDeser extends BaseViewEnumJsonDeser<Gender> {}

    /**
     * 用于做映射. 名称要固定
     */
    public static final ImmutableMap<Short, Gender> dataMap;
    static {
        ImmutableMap.Builder<Short, Gender> builder = ImmutableMap.<Short, Gender>builder();
        EnumSet<Gender> set = EnumSet.allOf(Gender.class);
        for (Gender item : set) {
            builder.put(item.sval(), item);
        }
        dataMap = builder.build();
    }

    private String cnName;
    private short shortVal;

    Gender(String cnName, Integer sv) {
        this.cnName = cnName;
        this.shortVal = sv.shortValue();
    }

    @Override
    public String cnName() {
        return cnName;
    }

    @Override
    public Short sval() {
        return shortVal;
    }
}
