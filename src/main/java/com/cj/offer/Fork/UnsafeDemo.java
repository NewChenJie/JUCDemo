package com.cj.offer.Fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author : chenjie
 * @date : 2019-06-27 10:12
 * @describe :
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnsafeDemo {
    private Integer age;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe my = Unsafe.getUnsafe();
        System.out.println(my);

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println(unsafe);

        UnsafeDemo user = UnsafeDemo.builder().age(18).build();
        Field field = UnsafeDemo.class.getDeclaredField("age");
        long sexOffset = unsafe.objectFieldOffset(field);
        Integer expect = 18;
        Integer update = 18;
        if (unsafe.compareAndSwapObject(user, sexOffset, expect, update)) {
            System.out.println("更改成功！");
        } else {
            System.out.println("更改失败！");
        }
        System.out.println(user.getAge());
    }
}
