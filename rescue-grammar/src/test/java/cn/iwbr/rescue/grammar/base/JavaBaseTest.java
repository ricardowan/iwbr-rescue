package cn.iwbr.rescue.grammar.base;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
public class JavaBaseTest {

    @Test
    public void ExplicitTypeCasting() {
        short i = 1;
        i = (short) (i + 1);
        i += 1;

        Person student = new Student();
        System.out.println(student.getName());

        Person person = new Person();
        person.setGender("male");
        int age = 10;

        Map map1 = new HashMap<>();
        map1.put("name", person);
        map1.put("age", age);

        Map map2 = new HashMap<>();
        map2.putAll(map1);

        person.setGender("female");
        age = age + 2;

        System.out.println(((Person) map1.get("name")).getGender());
        System.out.println(((Person) map2.get("name")).getGender());
        System.out.println(map1.get("age"));
        System.out.println(map2.get("age"));

    }

    @Test
    public void test01() {
//        String str1 = new StringBuilder("jaa").append("va").toString();
//        String str2 = str1.intern();
//        System.out.println(str1 == str2);
//
//        String str3 = new StringBuilder("hello").append("world").toString();
//        String str4 = str3.intern();
//        System.out.println(str3 == str4);

        String str6 = "hello world";
        String str5 = "hello world";

        System.out.println(str5 == str6);

        String str7 = new String("hello world");
        String str8 = new String("hello world");

        System.out.println(str7 == str8);

        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = "a" + "b";
        String s5 = s1 + s2;
        String s6 = s1 + "b";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s5 == s6);
    }

    @Test
    public void ListTest(){
        List list = new ArrayList();
        List test = new LinkedList();
        test.add("adsad");

        Map<String, String> map = new HashMap();
        map.put("name","12jhasd");
        map.put("age","adadsad");
        map.put("gender","8723jhs");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println();

        Map<String, String> map1 = new LinkedHashMap();
        map1.put("name","12jhasd");
        map1.put("age","adadsad");
        map1.put("gender","8723jhs");

        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println();

        Map<String, String> map2 = new ConcurrentHashMap<>();
        map2.put("name","12jhasd");
        map2.put("age","adadsad");
        map2.put("gender","8723jhs");
        map2.put("genderdd",null);

        // ConcurrentHashMap 不建议使用这种遍历方式
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        // ConcurrentHashMap 使用弱一致性的遍历方法：迭代器可能不会反映最新的修改，而是在某一时刻之前的状态
        Iterator<Map.Entry<String, String>> iterator = map2.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + "=" + next.getValue());
        }
    }

    public class Person extends AbstractClassTest{

        private String gender;

        @Override
        public String getName() {
            return "null";
        }

        @Override
        public String getSomeThing() {
            return null;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public class Student extends Person implements InterfaceTest{
        @Override
        public String getAddress() {
            return null;
        }

        @Override
        public String getName() {
            String parentName = super.getName();
            return parentName + "?";
        }
    }

    public abstract class AbstractClassTest {

        public abstract String getName ();

        public abstract String getSomeThing();

        public String getAge() {
            return null;
        }
    }

    public interface InterfaceTest {

        int age = 10;

        String getAddress();

        default void getHome() {
            System.out.println("name is ricardowang");
        }

        static void getNewHome(){
            System.out.println("new home");
        }
    }

}
