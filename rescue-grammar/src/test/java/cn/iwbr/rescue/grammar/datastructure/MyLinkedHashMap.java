package cn.iwbr.rescue.grammar.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyLinkedHashMap<k,v> {

    public MyLinkedHashMap() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    private static class Node<k,v>{
        k key;
        v val;
        Node<k,v> next;
        Node<k,v> prev;

        public Node(k k,v v){
            this.key = k;
            this.val = v;
        }
    }

    // 链表(虚拟节点)
    private final Node<k, v> head, tail;

    // 哈希表
    private final Map<k, Node<k,v>> map = new HashMap<>();

    // 查询
    public v get(k key) {
        if (map.containsKey(key)) {
            Node<k, v> node = map.get(key);
            return node.val;
        }
        return null;
    }

    // 添加/修改
    public void put(k key, v value) {
        if (!map.containsKey(key)) {
            Node node = new Node(key, value);
            addToLast(node);
            map.put(key, node);
            return;
        }
        map.get(key).val = value;
    }
    // 删除
    public void remove(k key){
        if (!map.containsKey(key)) {
            return;
        }
        // 从哈希表和链表中移除
        Node<k, v> node = map.get(key);
        map.remove(key);

        removeNode(node);
    }
    // 获取keys(按照插入顺序返回)
    public List<k> keys() {
        List<k> list = new ArrayList<>();
        for (Node<k,v> n = head.next ; n!=tail; n = n.next){
            list.add(n.key);
        }
        return list;
    }

    // 获取size
    public int size() {
        return map.size();
    }
    // 将新节点加入到链表的尾节点里面
    private void addToLast(Node node) {
        // tail <> node <> tail.prev
        Node<k, v> prev = tail.prev;

        node.next = tail;
        node.prev = prev;

        tail.prev = node;
        prev.next = node;
    }

    private void removeNode(Node node){
        Node next = node.next;
        Node prev = node.prev;

        prev.next = next;
        next.prev = prev;

        node.next = node.prev = null;
    }

    public static void main(String[] args) {
        MyLinkedHashMap<String, Integer> map = new MyLinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);

        System.out.println(map.keys()); // [a, b, c, d, e]
        map.remove("c");
        System.out.println(map.keys()); // [a, b, d, e]
    }
}
