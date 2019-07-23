/*
 * Copyright 2018 Aletheia Ware LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aletheiaware.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CommonUtils {

    public static final String TAG = "AletheiaWare";

    public static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private CommonUtils() {}

    public static String sizeToString(long size) {
        if (size <= 1024) {
            return String.format("%dbytes", size);
        }
        String unit = "";
        double s = size;
        if (s >= 1024) {
            s /= 1024;
            unit = "Kb";
        }
        if (s >= 1024) {
            s /= 1024;
            unit = "Mb";
        }
        if (s >= 1024) {
            s /= 1024;
            unit = "Gb";
        }
        if (s >= 1024) {
            s /= 1024;
            unit = "Tb";
        }
        if (s >= 1024) {
            s /= 1024;
            unit = "Pb";
        }
        return String.format("%.2f%s", s, unit);
    }

    public static String timeToString(long nanos) {
        return FORMATTER.format(new Date(nanos / 1000000));
    }

    public static byte[] encodeBase64(byte[] data) {
        try {
            return java.util.Base64.getEncoder().encode(data);
        } catch (java.lang.NoClassDefFoundError e) {
            try {
                // Android doesn't have java.util.Base64, try android.util.Base64
                Class<?> c = Class.forName("android.util.Base64");
                java.lang.reflect.Method m = c.getDeclaredMethod("encode", byte[].class, int.class);
                return (byte[]) m.invoke(null, data, 0);
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                ex.printStackTrace();
                throw e; // Throw original exception
            }
        }
    }

    public static byte[] encodeBase64URL(byte[] data) {
        try {
            return java.util.Base64.getUrlEncoder().withoutPadding().encode(data);
        } catch (java.lang.NoClassDefFoundError e) {
            try {
                // Android doesn't have java.util.Base64, try android.util.Base64
                Class<?> c = Class.forName("android.util.Base64");
                int urlSafe = c.getDeclaredField("URL_SAFE").getInt(null);
                int noWrap = c.getDeclaredField("NO_WRAP").getInt(null);
                int noPadding = c.getDeclaredField("NO_PADDING").getInt(null);
                int flag = urlSafe | noWrap | noPadding;
                java.lang.reflect.Method m = c.getDeclaredMethod("encode", byte[].class, int.class);
                return (byte[]) m.invoke(null, data, (Integer) flag);
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchFieldException | NoSuchMethodException ex) {
                ex.printStackTrace();
                throw e; // Throw original exception
            }
        }
    }

    public static byte[] decodeBase64(byte[] base64) {
        try {
            return java.util.Base64.getDecoder().decode(base64);
        } catch (java.lang.NoClassDefFoundError e) {
            try {
                // Android doesn't have java.util.Base64, try android.util.Base64
                Class<?> c = Class.forName("android.util.Base64");
                java.lang.reflect.Method m = c.getDeclaredMethod("decode", byte[].class, int.class);
                return (byte[]) m.invoke(null, base64, 0);
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                ex.printStackTrace();
                throw e; // Throw original exception
            }
        }
    }

    public static byte[] decodeBase64URL(byte[] base64) {
        try {
            return java.util.Base64.getUrlDecoder().decode(base64);
        } catch (java.lang.NoClassDefFoundError e) {
            try {
                // Android doesn't have java.util.Base64, try android.util.Base64
                Class<?> c = Class.forName("android.util.Base64");
                int urlSafe = c.getDeclaredField("URL_SAFE").getInt(null);
                int noWrap = c.getDeclaredField("NO_WRAP").getInt(null);
                int noPadding = c.getDeclaredField("NO_PADDING").getInt(null);
                int flag = urlSafe | noWrap | noPadding;
                java.lang.reflect.Method m = c.getDeclaredMethod("decode", byte[].class, int.class);
                return (byte[]) m.invoke(null, base64, (Integer) flag);
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchFieldException | NoSuchMethodException ex) {
                ex.printStackTrace();
                throw e; // Throw original exception
            }
        }
    }

    public static byte[] readFile(File file) throws FileNotFoundException, IOException {
        byte[] data = null;
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                data = new byte[in.available()];
                in.read(data);
            }
        }
        return data;
    }

    public static void writeFile(File file, byte[] data) throws FileNotFoundException, IOException {
        file.createNewFile();
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(data);
            out.flush();
        }
    }

    public static class Pair<A, B> {

        public A a;
        public B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}