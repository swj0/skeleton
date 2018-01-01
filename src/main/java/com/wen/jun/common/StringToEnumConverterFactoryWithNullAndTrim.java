package com.wen.jun.common;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.List;

/**
 * 从spring的 org.springframework.core.convert.support.StringToEnumConverterFactory 拷贝过来
 * 因为我们要加入特别的逻辑, 也就是转换错误时, 返回null
 */
public class StringToEnumConverterFactoryWithNullAndTrim implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        if (enumType == null) {
            throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");
        }
        return new StringToEnum(enumType);
    }


    private class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            source = source.trim();
            if (source.length() == 0) {
                // It's an empty enum identifier: reset the enum value to null.
                return null;
            }

            List<T> enumList = EnumUtils.getEnumList(enumType);
            for (T t : enumList) {
                if (t.name().equalsIgnoreCase(source)) {
                    return t;
                }
            }

            return null;
        }
    }
}
