package com.scc.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.scc.model.Dog;

public class UpperCasingWriter extends BeanPropertyWriter {
    
    BeanPropertyWriter _writer;
    
    public UpperCasingWriter(BeanPropertyWriter w) {
        super(w);
        _writer = w;
    }
 
    @Override
    public void serializeAsField(Object bean, JsonGenerator gen, 
      SerializerProvider prov) throws Exception {
        String value = ((Dog) bean).getName();
        value = (value == null) ? "" : value.toLowerCase();
        gen.writeStringField("name", value);
        gen.writeBooleanField("hidden", true);
    }

}
