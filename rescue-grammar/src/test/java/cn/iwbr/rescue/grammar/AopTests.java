package cn.iwbr.rescue.grammar;

import cn.iwbr.rescue.grammar.entity.Staff;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: AOP切片编程测试
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2024/01/12
 */
@Slf4j
@SpringBootTest
public class AopTests {

    /**
     * Java反射测试
     */
    @Test
    public void ReflectTest() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        // ****************** 构建Class实例 ****************** //
        // 获取类对象的第一种方式（这种方式在实际开发中应用较多）
        Class clazz = Class.forName("cn.iwbr.rescue.grammar.entity.Staff");
        // 获取类对象的第二种方式
        Class pClass2 = Staff.class;
        // 获取类对象的第三种方式
        Class pClass3 = new Staff().getClass();

        // ****************** 根据Class实例获取类的所有信息 ****************** //
        // 根据名称获取Public修饰的字段包括继承自父类的字段
        Field field = clazz.getField("test");
        // 会返回所有Public修饰的字段包括继承自父类的字段
        Field[] fields = clazz.getFields();
        // 根据名称获取当前类的任一字段（不管是什么修饰符修饰的），无法获取继承自父类的字段
        Field declaredField = clazz.getDeclaredField("workNo");
        // 获取当前类的所有字段（不管是什么修饰符修饰的），无法获取继承自父类的字段
        Field[] declaredFields = clazz.getDeclaredFields();

        // 获取方法
        // 根据名称获取所有Public修饰的方法包括继承自父类的方法
        Method method = clazz.getMethod("getTest");
        Method[] methods = clazz.getMethods();
        // 根据名称获取当前类所有的方法（不管是什么修饰符修饰的）
        Method declaredMethod = clazz.getDeclaredMethod("getPosition");
        Method[] declaredMethods = clazz.getDeclaredMethods();

        // 获取父类与直接实现接口
        Class superclass = clazz.getSuperclass();
        Class[] interfaces = clazz.getInterfaces();

        // 获取构造函数
        Constructor con = clazz.getConstructor(boolean.class);
        Constructor constructor = clazz.getConstructor(String.class, String.class, String.class, double.class);
        // 使用构造函数新建对象
        Staff staff = (Staff) con.newInstance(true);
        Staff staff1 = (Staff) constructor.newInstance("M200084", "中级开发工程师", "政务开发一部", 12000);

        // 获取字段值
        Field check = staff.getClass().getDeclaredField("workNo");
        // 设置成私有的属性，根据Field对象是没法获取值的，想获取值只有两个方法：一个是设置setAccessible=true，一个是通过public修饰的get方法获取。
        check.setAccessible(true);
        System.out.println("check：" + check.get(staff));

        // 执行方法
        Method method1 = staff1.getClass().getMethod("getWorkNo");
        method1.setAccessible(true);
        System.out.println("workNo：" + method1.invoke(staff1));
    }
}
