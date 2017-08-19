package com.rusher;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ReflectionUtils {

    public static Class getSuperClassGenericType(Class clazz) {
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        if (type instanceof Class) {
            return getSuperClassGenericType((Class) type);
        }
        throw new RuntimeException(clazz.getName() + " should be parameterized!");
    }

    public static Class getSuperClassGenericType(Type type) {
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        if (type instanceof Class) {
            return getSuperClassGenericType((Class) type);
        }
        throw new RuntimeException(type.getClass().getName() + " should be parameterized!");
    }
}
