package com.wen.jun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class JsonUtil {

    public static void  main(String[] args){

        String jsonStr = "{\"message\":\"error\",\"errors\":[\"Sendcloud has exception : 552 {\\\"code\\\":-5,\\\"message\\\":\\\"Request quota exceeded\\\"}\\n\"]}";
        Map m = jsonStr2Map(jsonStr);

        System.out.println(m);


    }

    /**
     * json 字符串 转 map 对象
     *
     * @param jsonStr
     * @return
     */
    public static Map jsonStr2Map(String jsonStr){
        try {
            return configObjectMapper().readValue(jsonStr, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 定义一个,以后有模块时可以扩展
     */
    public static class HJObjectMapper extends ObjectMapper {
        public HJObjectMapper() {
            //registerModule(new GuavaModule());
            //registerModule(new JodaModule());
            // 日期不用 utc 方式显示, utc 是一个整数值表示, 在用于传输时可节省字符及内存表示
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // 不确定值的枚举返回 null
            configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            // 不确定的属性项上失败
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 时间格式
            setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            // 空值不序列化
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }

    public static class PageListJsonSerializer extends JsonSerializer<PageList> {
        ObjectMapper mapper;

        public PageListJsonSerializer(){
            mapper = new ObjectMapper();
        }

        public PageListJsonSerializer(ObjectMapper mapper){
            this.mapper = mapper;
        }
        @Override
        public void serialize(PageList value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            Map<String,Object> map = new HashMap<>();
            Paginator paginator = value.getPaginator();
            map.put("items" , new ArrayList<>(value));
            map.put("totalCount", paginator.getTotalCount());
            map.put("totalPages", paginator.getTotalPages());
            map.put("curPage", paginator.getPage());
            mapper.writeValue(jgen, map);
        }
    }

    /**
     * 定义一个扩展. 注意这里是继承至 PageListJsonMapper. 因为我们要使用mybaits的分页
     */
    public static class HJObjectMapperWithMybatisPage extends ObjectMapper {
        public HJObjectMapperWithMybatisPage() {
            SimpleModule module = new SimpleModule("PageListJSONModule", new Version(1, 0, 0, null, null, null));
            module.addSerializer(PageList.class, new PageListJsonSerializer(this));
            registerModule(module);

            //registerModule(new GuavaModule());
            //registerModule(new JodaModule());
            // 日期不用 utc 方式显示, utc 是一个整数值表示, 在用于传输时可节省字符及内存表示
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // 不确定值的枚举返回 null
            configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            // 不确定的属性项上失败
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 时间格式
            setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            // 空值不序列化
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }

    //在classpath是否有 mybatis的分页类
    private static final boolean mybatisPagePluginPresent = ClassUtils.isPresent("com.github.miemiedev.mybatis.paginator.jackson2.PageListJsonMapper", JsonUtil.class.getClassLoader());

    //定义全局的一个即可
    public static final ObjectMapper objectMapper;

    /*会输出null*/
    public static final ObjectMapper objectMapperWithNull;

    static {
        if (mybatisPagePluginPresent) {
            objectMapper = new HJObjectMapperWithMybatisPage();
            objectMapperWithNull = new HJObjectMapperWithMybatisPage();
        } else {
            objectMapper = new HJObjectMapper();
            objectMapperWithNull = new HJObjectMapper();
        }

        objectMapperWithNull.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    public static ObjectMapper configObjectMapper() {
        return objectMapper;
    }

    /**
     * 把对象转成json字符串,不包含null字段
     */
    public static String objectToJson(Object o) {
        try {
            return configObjectMapper().writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException("不能序列化对象为Json", e);
        }
    }
    
    /**
     * 把对象转成json字符串,不包含null字段，格式化
     */
    public static String objectToJsonExcludeNullPretty(Object o) {
        try {
            return configObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException("不能序列化对象为Json", e);
        }
    }

    /**
     * 把对象转成json字符串,包含null字段(也就是所有字段)
     */
    public static String objectToJsonFull(Object o) {
        try {
            return objectMapperWithNull.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException("不能序列化对象为Json", e);
        }
    }

    public static String object2JsonPretty(Object o) {
        try {
            return objectMapperWithNull.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("不能序列化对象为Json", e);
        }
    }

    /**
     * 将 json 字段串转换为对象.
     *
     * @param json  字符串
     * @param clazz 需要转换为的类
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return configObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, e);
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
     * @see #createCollectionType(Class, Class...)
     */
    public static <T> T json2Object(String json, JavaType javaType){
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return (T) configObjectMapper().readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, e);
        }
    }

    /**
     * 构造泛型的Collection Type如:
     * ArrayList<MyBean>, 则调用createCollectionType(ArrayList.class,MyBean.class)
     * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
     */
    public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return configObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 输出JSONP格式数据.
     */
    public static String toJsonP(String functionName, Object object) {
        return objectToJson(new JSONPObject(functionName, object));
    }

    /**
     * 当JSON里只含有Bean的部分属性时，更新一个已存在Bean，只覆盖该部分的属性.
     */
    @SuppressWarnings("unchecked")
    public static <T> T update(String json, T object) {
        try {
            return (T) configObjectMapper().readerForUpdating(object).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException("将json字符串更新object对象的属性时发生错误: json字符串为=" + json, e);
        }
    }
}
