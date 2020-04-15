package com.scc.utils;

import javax.inject.Singleton;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.quarkus.jackson.ObjectMapperCustomizer;

/**
 * @author adenecheau
 * Serialization de l'objet selon la valeur de la propriété hidden définie sur ce même objet
 * Voir annotation @Role
 */
@Singleton
public class RegisterCustomModuleCustomizer implements ObjectMapperCustomizer {

    public void customize(ObjectMapper mapper) {
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.registerModule(new SimpleModule() {

            private static final long serialVersionUID = 1L;

            @Override
            public void setupModule(final SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public JsonSerializer<?> modifySerializer(final SerializationConfig config,
                            final BeanDescription beanDesc, final JsonSerializer<?> serializer) {
                        //System.out.println("modifySerializer "+beanDesc.getBeanClass().getName());
                        if (Hidable.class.isAssignableFrom(beanDesc.getBeanClass())) {
                            return new HidableSerializer((JsonSerializer<Object>) serializer);
                        }
                        return serializer;
                    }
                    
                    @SuppressWarnings("unchecked")
                    @Override
                    public JsonSerializer<?> modifyCollectionSerializer(SerializationConfig config,
                            CollectionType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {                        
                        //System.out.println("modifyCollectionSerializer "+beanDesc.getBeanClass().getName());
                        return new CollectionSerializer((JsonSerializer<Object>) serializer);
                    }
                    
                });
            }
        });
    }

}
