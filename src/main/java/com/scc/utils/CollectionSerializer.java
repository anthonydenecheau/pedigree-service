package com.scc.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.scc.model.Hidden;

public class CollectionSerializer extends JsonSerializer<List<? extends Hidden>> {

    private JsonSerializer<Object> defaultSerializer;

    public CollectionSerializer(final JsonSerializer<Object> serializer) {
        defaultSerializer = serializer;
    }
    
    @Override
    public void serialize(List<? extends Hidden> value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException {
        if (value.get(0).isHidden())
            return;
        defaultSerializer.serialize(value, jgen, provider);
    }

    @Override
    public boolean isEmpty(final SerializerProvider provider, final List<? extends Hidden> value) {
        return (value == null || value.get(0).isHidden());
    }
    
}