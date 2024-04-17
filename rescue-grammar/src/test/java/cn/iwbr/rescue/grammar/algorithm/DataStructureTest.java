package cn.iwbr.rescue.grammar.algorithm;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 数据结构测试
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/03/22
 */
@SpringBootTest
public class DataStructureTest {

    @Test
    public void stackTest() {
        Stack<String> stack = new Stack();
        stack.push("wang");
        stack.push("wbr");
        stack.push("nihao");
        stack.pop();
        stack.peek();

        ArrayDeque<String> arrayDeque = new ArrayDeque();
        arrayDeque.push("wang");
        arrayDeque.push("wbr");
        arrayDeque.push("nihao");
        arrayDeque.poll();
        arrayDeque.peek();

        arrayDeque.getFirst();
        arrayDeque.pollLast();

    }

    @Test
    public void groupAnagrams() {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Map<String, List<String>> resultMap = new HashMap();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String value = new String(charArray);
            List<String> stringList = resultMap.getOrDefault(value, new ArrayList<>());
            stringList.add(str);
            resultMap.put(value, stringList);
        }
        //Arrays.stream(strs).collect(Collectors.groupingBy(s -> Arrays.toString(s.codePoints().sorted().toArray()))).values();
        //return new ArrayList<List<String>>(resultMap.values());
    }

    @Test
    public void longestConsecutive() {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        Arrays.sort(nums);

        int key = 1;
        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            List strList = new ArrayList<>();
            strList.add(value);
            boolean flag = false;
            if (i == 0) {
                flag = value + 1 == nums[i + 1];
            } else if (i == nums.length - 1) {
                flag = value - 1 == nums[i - 1];
            } else {
                flag = value + 1 == nums[i + 1] || value - 1 == nums[i - 1];
            }

            if (flag) {
                strList.addAll(resultMap.getOrDefault(key, new ArrayList<>()));
            } else {
                key += 1;
            }
            resultMap.put(key, strList);
        }

        List<List<Integer>> collect = resultMap.values().stream().sorted(Comparator.comparing(s -> s.size())).collect(Collectors.toList());
        System.out.println(collect.get(0).size());
    }


}
