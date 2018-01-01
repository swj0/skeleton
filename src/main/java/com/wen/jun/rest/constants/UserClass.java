package com.wen.jun.rest.constants;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableMap;

import java.util.EnumSet;

/**
 * 用户类型
 * SELECT class_name, item_id AS 编码, item_value AS 中文含义 FROM sys_dict WHERE class_code='user_class'
 */
@JsonDeserialize(using = UserClass.JsonDeser.class)
public enum UserClass implements ViewEnum {
    InManager("内部用户(管理员)", 101),//RSC 管理员
    InUser("内部用户(普通)", 102),//RSC 普通用户  CMS系统使用
    OutOrgManager("外部企业用户(管理员)", 201),//机构用户 管理员 （发会）
    OutOrgUser("外部企业用户(普通)", 202),//机构用户 普通用户 （发会）
    OutUser("外部普通用户", 301);//普通用户

    public static class JsonDeser extends BaseViewEnumJsonDeser<UserClass> {}

    /**
     * 用于做映射. 名称要固定
     */
    public static final ImmutableMap<Short, UserClass> dataMap;
    static {
        ImmutableMap.Builder<Short, UserClass> builder = ImmutableMap.<Short, UserClass>builder();
        EnumSet<UserClass> set = EnumSet.allOf(UserClass.class);
        for (UserClass item : set) {
            builder.put(item.sval(), item);
        }
        dataMap = builder.build();
    }

    private String cnName;
    private short shortVal;

    UserClass(String cnName, Integer sv) {
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
