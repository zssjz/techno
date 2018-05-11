package com.jason.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 序列化与反序列化
 * Created by Jason on 2018/5/2.
 */
public class ProtostuffUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    /**
     * 获取schema
     * @param clas
     * @param <T>
     * @return
     */
    private static <T> Schema<T> getSchema(Class<T> clas) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clas);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clas);
            if (schema != null) {
                cachedSchema.put(clas, schema);
            }
        }
        return schema;
    }

    /**
     * 反序列化
     * @param data
     * @param clas
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> clas) {
        try {
            T obj = objenesis.newInstance(clas);
            Schema<T> schema = getSchema(clas);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj) {
        Class<T> clas = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clas);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }
}
