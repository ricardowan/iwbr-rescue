package cn.iwbr.rescue.grammar.jvm;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JvmTest {

    @Test
    public void ClassTest(){
        ArrayList<String> arrayList = new ArrayList();
        System.out.println(arrayList.getClass());
        ArrayList<Integer> test = test();
        System.out.println(test.getClass());
        List<Integer> integers = Arrays.asList(12);
        System.out.println(integers.getClass());
        TestClass testClass = new TestClass();
        List test1 = testClass.test();
        System.out.println(test1.getClass());
    }

    private ArrayList<Integer> test() {
        return new ArrayList<Integer>();
    }

    public class TestClass{

        public List test () {
            return new ArrayList();
        }

    }

}
