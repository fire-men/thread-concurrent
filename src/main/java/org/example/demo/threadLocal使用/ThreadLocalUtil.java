package org.example.demo.threadLocal使用;

import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description
 */
public class ThreadLocalUtil implements Serializable {
    private static final ThreadLocal local = new ThreadLocal();

    public static void set(Object value) {
        local.set(value);
    }

    public static Object get() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }
}
