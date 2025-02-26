package cn.iwbr.rescue.grammar.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MyArrayHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    // 存储 key 和 key 在 arr 中的索引
    private final HashMap<K, Integer> map = new HashMap<>();

    // 真正存储 key-value 的数组
    private final ArrayList<Node<K, V>> arr = new ArrayList<>();

    private final Random r = new Random();

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        // 获取 key 在 map 中的索引
        int index = map.get(key);
        return arr.get(index).val;
    }

    public void put(K key, V val) {
        if (containsKey(key)) {
            // 修改
            int i = map.get(key);
            Node<K, V> node = arr.get(i);
            node.val = val;
            return;
        }

        // 新增
        arr.add(new Node<>(key, val));
        map.put(key, arr.size() - 1);
    }

    public void remove(K key) {
        if (!map.containsKey(key)) {
            return;
        }
        int index = map.get(key);
        Node<K, V> node = arr.get(index);

        // 下面的操作都是为了避免数组搬运数据，降低操作的时间复杂度
        // 1. 最后一个元素 e 和第 index 个元素 node 换位置
        Node<K, V> e = arr.get(arr.size() - 1);
        arr.set(index, e);
        arr.set(arr.size() - 1, node);

        // 2. 修改 map 中 e.key 对应的索引
        map.put(e.key, index);

        // 3. 在数组中删除最后一个元素
        arr.remove(arr.size() - 1);

        // 4. 在 map 中删除 node.key
        map.remove(node.key);
    }

    // 随机弹出一个键
    public K randomKey() {
        int n = arr.size();
        int randomIndex = r.nextInt(n);
        return arr.get(randomIndex).key;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }

    public static void main(String[] args) {
        MyArrayHashMap<Integer, Integer> map = new MyArrayHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);

        System.out.println(map.get(1));
        System.out.println(map.randomKey());

        map.remove(4);
        System.out.println(map.randomKey());
        System.out.println(map.randomKey());
    }
}
