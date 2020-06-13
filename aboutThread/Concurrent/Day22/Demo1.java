package aboutThread.Concurrent.Day22;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class Demo1 {
    static Unsafe unsafe;

    static{
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        System.out.println(unsafe);
    }
}