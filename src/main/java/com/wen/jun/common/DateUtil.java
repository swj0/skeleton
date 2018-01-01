package com.wen.jun.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.wen.jun.common.DateFormatType.*;

/**
 */
public class DateUtil extends DateUtils {

    /**
     * 返回当前时间, 提取出来,可能以后在测试时方便使用mock的方式进行测试. 也就是加入一个中间层
     */
    public static Date now() {
        return new Date();
    }

    public static String getCurrentDate4Log(){
        return formatDate(now(), DATE_FORMAT_STR);
    }

    /**
     * 获取当前时间日期的字符串
     */
    public static String getCurrentDateStr(DateFormatType dateFormatType) {
        return formatDate(now(), dateFormatType);
    }

    /**
     * 懒人专用日期格式化方法
     *
     * @param date 日期对象，如果为NULL则直接返回null
     * @param patterns 可选的日期表达式，不填写默认使用：yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date, String... patterns) {
        if (date == null) {
            return "";
        }
        String pat = DateFormatType.DATE_FORMAT_STR.getValue();
        if (patterns.length > 0 && patterns[0] != null) {
            pat = patterns[0];
        }

        return DateFormatUtils.format(date, pat);
    }

    /**
     * 时间、日期格式化成字符串
     */
    public static String formatDate(Date date, DateFormatType dateFormatType) {
        if (date == null) {
            return "";
        }

        if (dateFormatType == null) {
            throw new RuntimeException("格式化参数不能为空");
        }
        return DateFormatUtils.format(date, dateFormatType.getValue());
    }

    /**
     * 验证字符串是不是指定的某些样式, 若是, 则返回时间对象, 否则返回 null
     *
     * @param source 时间的字符串表现形式
     */
    public static Date parseSpecifiedDate(String source) {
        if (StringUtils.isBlank(source)) return null;

        List<DateFormatType> formatTypes = DateFormatType.formatTypes();
        for (DateFormatType formatType : formatTypes) {
            try {
                Date date = parseDate(source, formatType.getValue());
                if (date != null) return date;
            } catch (ParseException e) {
                // ignore
            }
        }
        return null;
    }

    /**
     * 解析一个日期
     *
     * @param source 日期字符窜，例如：2014-02-01
     * @param patterns 格式表达式，默认：yyyy-MM-dd HH:mm:ss. 可以写多个, 会尝试选用合适的格式
     * @return 转换后的Date对象
     * @throws java.text.ParseException 如果解析出错
     */
    public static Date parseDate(String source, String... patterns) throws ParseException {
        if (StringUtils.isEmpty(source)) {
            throw new RuntimeException("空字符串不能进行日期解析");
        }
        if (patterns.length == 0) {
            return DateUtils.parseDate(source, DateFormatType.DATE_FORMAT_STR.getValue());
        }
        return DateUtils.parseDate(source, patterns);
    }

    /**
     * 从字符串解析成时间、日期
     */
    public static Date parseDate(String dateStr, DateFormatType... dateFormatType) throws ParseException {
        String[] patterns = new String[dateFormatType.length];
        for (int i = 0; i < dateFormatType.length; i++) {
            patterns[i] = dateFormatType[i].getValue();
        }

        return parseDate(dateStr, patterns);
    }

    /**
     * 获取一个日期所在天的最开始的时间（00:00:00 00），对日期段查询尤其有用
     */
    public static Date getDayStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00 000");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        try {
            return sdf2.parse(sdf1.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取一个日期所在天的最晚的时间（23:59:59 999），对日期段查询尤其有用
     */
    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59 999");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        try {
            return sdf2.parse(sdf1.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 取得指定日期N天后的日期
     *
     * @param days 正数为days天后，负数为days天前
     */
    public static Date addDays(Date date, int days) {
        return DateUtils.addDays(date, days);
    }
}
