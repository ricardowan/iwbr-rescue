package cn.iwbr.rescue.grammar.algorithm;

import cn.iwbr.rescue.grammar.datastructure.MyArrayHashMap;
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

    @Test
    public void test(){
        // 静态数组
        int[] array = new int[5];
        // 动态数组
        List<String> list = new ArrayList<>();

        // 基于链表实现的单向队列
        Queue linkedList = new LinkedList<>();
        linkedList.offer("hello");
        linkedList.peek();
        linkedList.poll();

        // 基于数组实现的双端队列，能实现队列操作和栈操作
        ArrayDeque deque = new ArrayDeque();
        // 队列操作
        // 将sss加入到队列尾部
        deque.offer("sss");
        // 移除并返回队列头部元素
        deque.poll();
        // 查看队列头部元素，但不移除它
        deque.peek();

        // 栈操作
        // 将sss压入栈顶
        deque.push("sss");
        // 将栈顶元素弹出
        deque.pop();
        // 查看栈顶元素但不弹出
        deque.peek();

        // Hash 结构
        Map map = new HashMap();
        // HashMap+双向链表实现的Hash结构，能够记录插入顺序
        // 底层是一个HashMap+双向链表，Map中存储的是key和链表节点，链表中存储节点信息
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        // HashMap+数组实现的Hash结构
        // 底层是一个HashMap+数组，数组中存储值，Map中存储的是key和key在数组中的索引
        MyArrayHashMap arrayHashMap = new MyArrayHashMap();
        // 使用HashMap实现的集合
        // 底层是一个HashMap,HashMap里面以集合元素为key，值是一个默认的空对象（常用来去重，因为map的key不能重复）
        HashSet hashSet = new HashSet();

        // 树结构
        // 基于二叉搜索树实现的map结构，将键值对存储在二叉搜索树的节点里面
        TreeMap treeMap = new TreeMap();
        treeMap.put("key", "key");
        // 基于TreeMap实现的集合，
        // 底层是一个TreeMap,TreeMap里面以集合元素为key，值是一个默认的空对象（常用来去重，因为map的key不能重复）
        TreeSet treeSet = new TreeSet();
        treeSet.add("");

        // 堆（二叉堆）
        // 拥有特殊性质的完全二叉树，有完全二叉树的所有特性并且所有节点的值均小于等于或者大于等于其左右子树的所有节点的值
        // 作用：首先是一种很有用的数据结构优先级队列（Priority Queue），第二是一种排序方法堆排序（Heap Sort）
        // 堆排序：将一组乱序的值一个个压到栈中，然后再挨个取出就能得到排好序的值
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(1);
        priorityQueue.peek();
        priorityQueue.poll();
    }


}
