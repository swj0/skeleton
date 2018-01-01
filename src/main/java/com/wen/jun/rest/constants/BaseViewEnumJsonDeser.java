package com.wen.jun.rest.constants;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 用于 ViewEnum 类型的json反序列化, 通过 反射 泛型对象的泛型化参数 及 在ViewEnum里定义了 dataMap 数据映射
 */
public class BaseViewEnumJsonDeser<T> extends JsonDeserializer<T> {

    public static final String TYPE_NAME_PRE = "class ";

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        //请参考文章:  http://www.cnblogs.com/whitewolf/p/4355541.html
        //获取泛型对象的泛型化参数
        Type superclassType = getClass().getGenericSuperclass();
        if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            return null;
        }
        Type type = ((ParameterizedType) superclassType).getActualTypeArguments()[0];
        //得到 泛型对象的泛型化参数 的 class 对象
        String className = type.toString();
        if (className.startsWith(TYPE_NAME_PRE)) {
            className = className.substring(TYPE_NAME_PRE.length());
        }
        short sv = p.getShortValue();
        Class<?> aClass = null;
        try {
            aClass = Class.forName(className);
            //获取 static dataMap 里的值, 要求在定义ViewEnum里一定要定义这类型
            ImmutableMap<Short, T> dataMap = (ImmutableMap<Short, T>) aClass.getField("dataMap").get(null);
            T obj = dataMap.get(sv);
            if (obj == null) {
            }
            return obj;
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
