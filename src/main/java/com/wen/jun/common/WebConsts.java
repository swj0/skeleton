package com.wen.jun.common;


import java.util.Date;

/**
 *
 */
public interface WebConsts {
    /*会议可邀请人员的倍数*/
    int MEET_CAN_INVITATION_MULTIPLE = 2;

    /** 默认第1页的数值 */
    int DEFAULT_FIRST_PAGE_NO = 1;

    /** 默认分页大小 */
    int DEFAULT_PAGE_SIZE = 15;

    /** 分页时传的页码的参数名称 */
    String PAGE_PARAM = "page";

    /** 分页时传的每页数据条数的参数名称 */
    String PAGE_SIZE_PARAM = "limit";

    /** 分页时传的排序字段的参数名称 */
    String SORT_PARAM = "sort";

    /** 分页时传的排序方向(是升序还是降序)的参数名称 */
    String SORT_DIR_PARAM = "order";

    /** 往request放分页对象的key值 */
    String PGK = "com.wen.jun.common.WebConsts.PageBoundsKey";

    /** 往request放userId对象的key值 */
    String U_ID_KEY = "com.wen.jun.common.WebConsts.UserIdGroupKey";

    /** 当前用户的 key */
    String CUR_USER_KEY = "curUser";

    /** redirect的前缀*/
    String  REDIRECT_URL_PREFIX = "redirect:";

    Date MIN_DATE = DateUtil.parseSpecifiedDate("1979-10-10");
    Date MAX_DATE = DateUtil.parseSpecifiedDate("2038-10-10");

    /**用于sql的id in 查询时要有值才不会sql报错. 这里搞个不存在的值*/
    Integer INVALIDATE_ID = Integer.MIN_VALUE;

    Byte SYS_VALID = (byte)1;
    Byte SYS_INVALID = (byte)0;

    //后台任务处理 默认条数 【数据库 limit】
    Integer DAEMON_GENERATE_TASK_DEFAULT = 1;//后台 生成任务   默认一次处理条数
    Integer SYSTEM_MESSAGE_DEFAULT = 100;//站内信 默认一次处理条数
    Integer SM_MESSAGE_DEFAULT = 100;//短信 默认一次处理条数
    Integer MAIL_MESSAGE_DEFAULT = 100 ;//邮件  默认一次处理条数
    Integer WECHAT_MESSAGE_DEFAULT = 100;//微信 默认一次处理条数

    String MAIL_TEMPLATE_FILE_NAME = "email-template.html";//邮件模板文件名称 【只包含固定的头与尾】


    public static final int BEFORE_MEET_INTERVAL_306_DEFAULT = 30 ;//会议开始前 多久 提醒   默认30分钟
    //-----------
    /**
     * An empty immutable {@code Object} array.
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    /**
     * An empty immutable {@code Class} array.
     */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    /**
     * An empty immutable {@code String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    /**
     * An empty immutable {@code long} array.
     */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    /**
     * An empty immutable {@code Long} array.
     */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /**
     * An empty immutable {@code Integer} array.
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    /**
     * An empty immutable {@code short} array.
     */
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    /**
     * An empty immutable {@code Short} array.
     */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    /**
     * An empty immutable {@code byte} array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * An empty immutable {@code Byte} array.
     */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    /**
     * An empty immutable {@code double} array.
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    /**
     * An empty immutable {@code Double} array.
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    /**
     * An empty immutable {@code float} array.
     */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    /**
     * An empty immutable {@code Float} array.
     */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    /**
     * An empty immutable {@code boolean} array.
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    /**
     * An empty immutable {@code Boolean} array.
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    /**
     * An empty immutable {@code char} array.
     */
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    /**
     * An empty immutable {@code Character} array.
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    //--------------
}
