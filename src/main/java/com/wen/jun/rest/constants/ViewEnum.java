package com.wen.jun.rest.constants;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 常量枚举实现的接口
 */
@JsonSerialize(using = ViewEnumJsonSerializer.class)
public interface ViewEnum {
    /**
     * 状态的中文描述
     */
    String cnName();

    /**
     * 返回数据库里的存储值
     */
    Short sval();
}
