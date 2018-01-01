package com.wen.jun.common;


import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把字符串转为Date. 因为这里我们要用多种格式去格式化它. 如果不能转为正确的格式, 则返回null
 * 加入了这个后, 使用spring提供的 org.springframework.format.annotation.DateTimeFormat 就无效了, 因为他优化生效了.
 * Created by canal.wen on 2014/12/26.
 */
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source == null) {
            return null;
        }
        return DateUtil.parseSpecifiedDate(source);
    }
}
