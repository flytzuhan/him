package com.shadow.him.common.serial;

import com.google.gson.Gson;

public class JsonSerializer {

    private static Gson INSTANCE;

    private static Gson getInstance() {
        if (INSTANCE == null) {
            synchronized (JsonSerializer.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Gson();
                }
            }
        }

        return INSTANCE;
    }

    public static String serializer(Object obj) {
        return getInstance().toJson(obj);
    }

    public static <T> T deserializer(String val, Class<T> clazz) {
        return getInstance().fromJson(val, clazz);
    }
}
