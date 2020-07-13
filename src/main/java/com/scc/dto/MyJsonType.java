package com.scc.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyJsonType implements UserType {

    private static final Logger LOG = Logger.getLogger(MyJsonType.class);
    
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

    @Override
    public Class<Dog> returnedClass() {
        return Dog.class;
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        
        /*
         * com.oracle.svm.core.jdk.UnsupportedFeatureError: ObjectOutputStream.writeObject() at com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:101) at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:68) at com.scc.model.MyJsonType.deepCopy(MyJsonType.java:46) at org.hibernate.type.CustomType.deepCopy(CustomType.java:190
         * 
        try {
            
            LOG.info("deepCopy");
            
            // use serialization to create a deep copy
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            oos.flush();
            oos.close();
            bos.close();
            
            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bais).readObject();
            
        } catch (ClassNotFoundException | IOException ex) {
            LOG.error(" > Erreur : deepCopy");
            throw new HibernateException(ex);
        }
        */

        ObjectMapper mapper = new ObjectMapper();
        Dog deepCopy = null;
        try {
            deepCopy =  mapper.readValue(mapper.writeValueAsString(value), returnedClass());
        } catch (JsonProcessingException e) {
            LOG.error(" > Erreur : deepCopy " + e.getMessage());
        }
        return deepCopy;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) this.deepCopy(value);
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
        return this.deepCopy(cached);
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return this.deepCopy(original);
    }
    
    @Override
    public boolean equals(final Object obj1, final Object obj2) throws HibernateException {
        if (obj1 == null) {
            return obj2 == null;
        }
        return obj1.equals(obj2);
    }

    @Override
    public int hashCode(final Object obj) throws HibernateException {
        return obj.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet arg0, String[] arg1, SharedSessionContractImplementor arg2, Object arg3)
            throws HibernateException, SQLException {

        final String cellContent = arg0.getString(arg1[0]);
        if (cellContent == null) {
            return null;
        }
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(cellContent.getBytes("UTF-8"), returnedClass());
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert String to Invoice: " + ex.getMessage(), ex);
        }
        
    }
    
    @Override
    public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2, SharedSessionContractImplementor arg3)
            throws HibernateException, SQLException {

        if (arg1 == null) {
            arg0.setNull(arg2, Types.OTHER);
            return;
        }
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final StringWriter w = new StringWriter();
            mapper.writeValue(w, arg1);
            w.flush();
            arg0.setObject(arg2, w.toString(), Types.OTHER);
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert Invoice to String: " + ex.getMessage(), ex);
        }
        
    }

}