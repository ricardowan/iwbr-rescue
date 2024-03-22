package cn.iwbr.rescue.grammar.algorithm;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @description: 数据结构测试
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/03/22
 */
@SpringBootTest
public class DataStructureTest {

    @Test
    public void stackTest(){
        Stack<String> stack = new Stack();
        stack.push("wang");
        stack.push("wbr");
        stack.push("nihao");

        ArrayDeque<String> arrayDeque = new ArrayDeque();
        arrayDeque.push("wang");
        arrayDeque.push("wbr");
        arrayDeque.push("nihao");

    }


}
