package cn.iwbr.rescue.grammar.base;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JavaBaseTest {

    @Test
    public void ExplicitTypeCasting(){
        short i = 1;
        i = (short) (i + 1);
        i += 1;

        Person student = new Student();
        System.out.println(student.getName());

        Person person = new Person();
        person.setGender("male");
        int age = 10;

        Map map1 = new HashMap<>();
        map1.put("name",person);
        map1.put("age",age);

        Map map2 = new HashMap<>();
        map2.putAll(map1);

        person.setGender("female");
        age = age + 2;

        System.out.println(((Person) map1.get("name")).getGender());
        System.out.println(((Person) map2.get("name")).getGender());
        System.out.println(map1.get("age"));
        System.out.println(map2.get("age"));

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
