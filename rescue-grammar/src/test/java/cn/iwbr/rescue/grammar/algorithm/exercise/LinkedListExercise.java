package cn.iwbr.rescue.grammar.algorithm.exercise;

import java.util.PriorityQueue;

/**
 * @description: 链表算法相关练习题
 * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
 * @date: 2025/03/03
 */
public class LinkedListExercise {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 合并两个升序链表
     * @param list1
     * @param list2
     * @return {@link ListNode }
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1), p = head;
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                p.next = list2;
                list2 = list2.next;
            } else {
                p.next = list1;
                list1 = list1.next;
            }

            p = p.next;
        }
        if (list1 != null) {
            p = list1;
        }
        if (list2 != null) {
            p = list2;
        }
        return head.next;
    }

    /**
     * 合并两个升序链表（第二种写法）
     * @param list1
     * @param list2
     * @return {@link ListNode }
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1), p = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(2, (o1, o2) -> o1.val - o2.val);

        if (list1 != null) {
            queue.offer(list1);
        }
        if (list2 != null) {
            queue.offer(list2);
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return head.next;
    }

    /**
     * 合并多个升序链表
     * @param lists
     * @return {@link ListNode }
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode head = new ListNode(-1), p = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);

        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return head.next;
    }

    /**
     * 拆分链表
     * @param head
     * @param x
     * @return {@link ListNode }
     */
    public ListNode partition(ListNode head, int x) {
        ListNode min = new ListNode(-1), p = min;
        ListNode max = new ListNode(-1), q = max;

        while (head != null) {
            if (head.val < x) {
                p.next = head;
                p = p.next;
            } else {
                q.next = head;
                q = q.next;
            }

            // 不能直接 head = head.next，否则会形成环，会报错
            ListNode node = head.next;
            head.next = null;
            head = node;
        }

        p.next = max.next;
        return min.next;
    }

    /**
     * 移除链表倒数第N个节点
     * @param head
     * @param n
     * @return {@link ListNode }
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
        ListNode x = findFromEnd(dummy, n + 1);
        // 删掉倒数第 n 个节点
        x.next = x.next.next;
        return dummy.next;
    }

    /**
     * 查找单链表的中点（n/2）
     * 快慢指针：
     * 1. 定义一个快指针fast、一个慢指针slow，初始时都是head
     * 2. 每当慢指针 slow 前进一步，快指针 fast 就前进两步，这样，当 fast 走到链表末尾时，slow 就指向了链表中点
     * @param head
     * @return {@link ListNode }
     */
    public ListNode middleNode(ListNode head){
        ListNode fast = head,slow = head;
        while (fast != null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 判断单向链表是否有环
     * 使用快慢指针，如果快指针和慢指针相遇了，就证明有环
     * @param head
     * @return {@link ListNode }
     */
    public Boolean hasCycle(ListNode head){
        ListNode fast = head,slow = head;
        while (fast != null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断单向链表是否有环，如果有环返回环的起点
     * 快慢指针判断是否有环，如果有环，这时候将fast或者slow任一置为head，再次相遇的点就是起点
     * @param head
     * @return {@link ListNode }
     */
    public ListNode detectCycle(ListNode head){
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // 到这里有两种可能：break或者不满足上面的while条件，其中后者不是我们要的，后者证明没有环
        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }

        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 判断两个单向链表是否相交
     * 思路：基本思路是遍历某个链表去跟另外一个比，直到找到相等的
     * 问题：两个链表的长短不一，难以保证都同时遍历到同一个节点
     * 解决：链表A遍历完了从头开始遍历B，链表B遍历完了从头开始遍历A，如果有相交的肯定能找到
     * @param headA
     * @param headB
     * @return {@link ListNode }
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while (p != q) {
            if (p == null) {
                p = headB;
            } else {
                p = p.next;
            }

            if (q == null) {
                q = headA;
            } else {
                q = q.next;
            }
        }
        return p;
    }

    // 返回链表的倒数第 k 个节点
    private ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
        return p2;
    }
}
