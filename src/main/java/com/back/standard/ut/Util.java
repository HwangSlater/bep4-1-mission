package com.back.standard.ut;

import org.springframework.util.ReflectionUtils;

public class Util {
    public static class reflection {
        public static void setField(Object obj, String fieldName, Object value) {
            try {
                var field = obj.getClass().getDeclaredField(fieldName);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, obj, value);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }
}