package com.wen.jun.common;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by tomwen on 2015/5/4.
 */
public class JsonResult {
    /** 是否成功 */
    private boolean success = false;

    /** 消息 */
    private String msg = StringUtils.EMPTY;

    /** 数据 */
    private Map<String, Object> data = Maps.newHashMap();

    public JsonResult() {}
    /** 以成功标志来构造 */
    public JsonResult(boolean success) {
        this.success = success;
    }
    /** 以消息来构造 */
    public JsonResult(String msg) {
        this.msg = msg;
    }
    /** 以成功标志和消息来构造 */
    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResult(F.T2<Boolean,String> t2){
        this.success = t2._1;
        this.msg = t2._2;
    }

    public JsonResult setByT2(F.T2<Boolean,String> t2){
        this.success = t2._1;
        this.msg = t2._2;
        return this;
    }

    /** 返回 json 对象 */
    public String toJson() {
        return JsonUtil.objectToJson(this);
    }

    /** 加入数据项 */
    public JsonResult addData(String name, Object value) {
        data.put(name, value);
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public JsonResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public JsonResult setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}
