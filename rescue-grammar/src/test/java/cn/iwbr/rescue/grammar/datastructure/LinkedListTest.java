package cn.iwbr.rescue.grammar.datastructure;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinkedListTest {

    class DoublyListNode {
        int val;
        DoublyListNode next, prev;
        DoublyListNode(int x) { val = x; }
    }

    DoublyListNode createDoublyLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        DoublyListNode head = new DoublyListNode(arr[0]);
        DoublyListNode cur = head;
        // for 循环迭代创建双链表
        for (int i = 1; i < arr.length; i++) {
            DoublyListNode newNode = new DoublyListNode(arr[i]);
            cur.next = newNode;
            newNode.prev = cur;
            cur = cur.next;
        }
        return head;
    }

    @Test
    public void test(){
        // 创建一条双链表
        DoublyListNode head = createDoublyLinkedList(new int[]{1, 2, 3, 4, 5});

        DoublyListNode tail = head;
        // 先走到链表的最后一个节点
        while (tail.next != null) {
            tail = tail.next;
        }

        // 在双链表尾部插入新节点 6
        DoublyListNode newNode = new DoublyListNode(6);
        tail.next = newNode;
        newNode.prev = tail;
        // 更新尾节点引用
        tail = newNode;
    }




}
