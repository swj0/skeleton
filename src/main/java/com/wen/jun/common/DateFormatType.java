package com.wen.jun.common;

import java.util.Arrays;
import java.util.List;

/**
 */
public enum DateFormatType {
    /** yyyy-MM-dd HH:mm:ss */
    DATE_FORMAT_STR("yyyy-MM-dd HH:mm:ss"),

    /** yyyy-MM-dd HH:mm */
    DATE_MINUTE_FORMAT_STR("yyyy-MM-dd HH:mm"),

    /** MM/dd/yyyy HH:mm:ss */
    DATE_USA_STYLE("MM/dd/yyyy HH:mm:ss"),

    /** yyyy年MM月dd日 HH时mm分ss秒 */
    DATE_FORMAT_STR_CHINA("yyyy年MM月dd日 HH时mm分ss秒"),

    /** yyyy年MM月dd日 HH点 */
    DATE_FORMAT_STR_CHINA_HOUR("yyyy年MM月dd日 HH点"),

    /** yy年MM月dd日 HH点 */
    DATE_FORMAT_STR_CHINA_HOUR_YY("yy年MM月dd日 HH点"),

    /** yyyyMMddHHmmss */
    SIMPLE_DATE_TIME_FORMAT_STR("yyyyMMddHHmmss"),

    /** yyyy-MM-dd */
    SIMPLE_DATE_FORMAT_STR("yyyy-MM-dd"),

    /** yyyyMMdd */
    SIMPLE_DATE_FORMAT("yyyyMMdd"),

    /** yy年MM月dd日 */
    SIMPLE_DATE_FORMAT_STR_YY("yy年MM月dd日"),

    /** yyyy年MM月dd日 */
    SIMPLE_DATE_FORMAT_STR_DAY("yyyy年MM月dd日"),

    /** MM月dd日 */
    SIMPLE_DATE_FORMAT_STR_MD("MM月dd日"),

    /** yyyy/MM/dd */
    SIMPLE_DATE_FORMAT_VIRGULE_STR("yyyy/MM/dd"),

    /** HH:mm:ss MM-dd */
    MONTH_DAY_HOUR_MINUTE_SECOND("HH:mm:ss MM-dd"),

    /** HH:mm:ss */
    HOUR_MINUTE_SECOND("HH:mm:ss"),

    /** HH:mm */
    HOUR_MINUTE("HH:mm");

    private String value;
    private DateFormatType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    /**
     * 前台可能用到的日期风格! 现在用到的风格有:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;yyyy-MM-dd HH:mm:ss<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;yyyy-MM-dd HH:mm<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;yyyy-MM-dd<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;yyyy/MM/dd<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;yyyyMMdd<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;HH:mm:ss<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;HH:mm<br>
     */
    public static List<DateFormatType> formatTypes() {
        return Arrays.asList(DATE_FORMAT_STR,
                DATE_MINUTE_FORMAT_STR,
                SIMPLE_DATE_FORMAT_STR,
                SIMPLE_DATE_FORMAT_VIRGULE_STR,
                SIMPLE_DATE_FORMAT,
                HOUR_MINUTE_SECOND,
                HOUR_MINUTE);
    }
}
