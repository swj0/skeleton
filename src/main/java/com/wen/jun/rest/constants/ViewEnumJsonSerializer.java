package com.wen.jun.rest.constants;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * ViewEnum类型的序列化
 */
public class ViewEnumJsonSerializer extends JsonSerializer<ViewEnum> {

    @Override
    public void serialize(ViewEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeNumber(value.sval());
    }
}
