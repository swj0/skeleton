package com.wen.jun.rest.cfg.db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.google.common.collect.ImmutableMap;
import com.wen.jun.rest.constants.ViewEnum;



public class ViewEnumTypeHandler<T> extends BaseTypeHandler<ViewEnum> {
	
    private Class<? extends ViewEnum> targetClass;

    public ViewEnumTypeHandler(Class<? extends ViewEnum> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ViewEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setShort(i, parameter.sval());
    }

    @Override
    public ViewEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        short s = rs.getShort(columnName);
        return short2Object(s);
    }

    @Override
    public ViewEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        short s = rs.getShort(columnIndex);
        return short2Object(s);
    }

    @Override
    public ViewEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        short s = cs.getShort(columnIndex);
        return short2Object(s);
    }

    protected ViewEnum short2Object(short s){
        try {
            ImmutableMap<Short, ViewEnum> dataMap = (ImmutableMap<Short, ViewEnum>) targetClass.getField("dataMap").get(null);
            return dataMap.get(s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}























