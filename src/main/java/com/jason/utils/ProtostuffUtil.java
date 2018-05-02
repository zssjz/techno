package com.jason.utils;

import com.dyuproject.protostuff.Schema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 高性能序列化与反序列化
 * Created by BNC on 2018/5/2.
 */
public class ProtostuffUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

//    private static
}
