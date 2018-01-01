package com.wen.jun.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;

/**
 * 从org.springframework.core.convert.support.StringToNumberConverterFactory 拷贝过来, 如果不能转换数字, 则使用0代替
 */
public class StringToNumberConverterFactoryWithZero implements ConverterFactory<String, Number> {
    public static final String DEFAULT_VALUE = "0";

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<T>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.length() == 0) {
                return null;
            }
            try {
                return NumberUtils.parseNumber(source, this.targetType);
            }catch (IllegalArgumentException e){
                return NumberUtils.parseNumber(DEFAULT_VALUE, this.targetType);
            }
        }
    }
}