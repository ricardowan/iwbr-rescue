package cn.iwbr.rescue.grammar.algorithm.exercise;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

    /**
     * 删除链表中的重复值
     * 思路：转换成链表拆分，拆分成重复元素的链表与不重复元素的链表
     *
     * @param head 封头
     * @return {@link ListNode }
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 将其转换为链表拆分问题，拆分成一个重复的链表一个不重复的链表
        ListNode dummyUniq = new ListNode(-200), pUniq = dummyUniq;
        ListNode dummyDup = new ListNode(-200), pDup = dummyDup;

        while (head != null) {
            if (head.val == pDup.val || (head.next != null && head.val == head.next.val)) {
                // 有重复元素则直接放到重复元素队列里面
                // 这里之所以能这么判断，是因为题目里说了链表里面元素有序，否则不能这么写
                pDup.next = head;
                pDup = pDup.next;
            } else {
                pUniq.next = head;
                pUniq = pUniq.next;
            }
            head = head.next;
            // 断开与原链表的连接
            pDup.next = null;
            pUniq.next = null;
        }
        return dummyUniq.next;
    }

    //==================================== 丑数 ====================================//

    /**
     * 判断一个数是否是丑数
     * 题目：丑数 就是只包含质因数 2、3 和 5 的 正 整数
     * @param n n
     * @return boolean
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        // / 是除法，整数做除法的话会舍弃小数位，浮点做除法会保留小数位，如果结果是1表示两个数相等或者相近
        // % 是取模，获取余数，如果余数是0表示可以整除
        //
        while (n % 2 == 0){
            n /= 2;
        }
        while (n % 3 == 0){
            n /= 3;
        }
        while (n % 5 == 0){
            n /= 5;
        }
        return n == 1;
    }

    /**
     * 查找丑数（能被2、3、5整除的数）
     * 规则：如果一个数 x 是丑数，那么 x * 2, x * 3, x * 5 都一定是丑数
     * 思路：可以将问题分解成三类丑数的三个链表，2的丑数、3的丑数、5的丑数，最后将三个链表合并起来
     *
     * @param n n
     * @return int
     */
    public int nthUglyNumber(int n) {
        // 可以理解为三个指向有序链表头结点的指针
        int p2 = 1, p3 = 1, p5 = 1;
        // 可以理解为三个有序链表的头节点的值
        int product2 = 1, product3 = 1, product5 = 1;
        // 可以理解为最终合并的有序链表（结果链表）
        int[] ugly = new int[n + 1];
        // 可以理解为结果链表上的指针
        int p = 1;

        // 开始合并三个有序链表
        while (p <= n) {
            // 取三个链表的最小结点
            int min = Math.min(Math.min(product2, product3), product5);
            // 接到结果链表上
            ugly[p] = min;
            p++;
            // 前进对应有序链表上的指针
            if (min == product2) {
                product2 = 2 * ugly[p2];
                p2++;
            }
            if (min == product3) {
                product3 = 3 * ugly[p3];
                p3++;
            }
            if (min == product5) {
                product5 = 5 * ugly[p5];
                p5++;
            }
        }
        // 返回第 n 个丑数
        return ugly[n];
    }

    /**
     * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第 n 个丑数
     *
     * @param n n
     * @param a a
     * @param b b
     * @param c c
     * @return int
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        // 题目说本题结果在 [1, 2 * 10^9] 范围内，
        // 所以就按照这个范围初始化两端都闭的搜索区间
        int left = 1, right = (int) 2e9;
        // 搜索左侧边界的二分搜索
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f(mid, a, b, c) < n) {
                // [1..mid] 中的元素个数不足 n，所以目标在右侧
                left = mid + 1;
            } else {
                // [1..mid] 中的元素个数大于 n，所以目标在左侧
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 超级丑数
     * nthUglyNumber方法的变体，由于不知道数组里会有几个元素，所以无法通过取最小值来实现，所以引入了优先队列
     *
     * @param n      n
     * @param primes 素数
     * @return int
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        // 优先队列中装三元组 int[] {product, prime, pi}
        // 其中 product 代表链表节点的值，prime 是计算下一个节点所需的质数因子，pi 代表链表上的指针
        PriorityQueue<int []> queue = new PriorityQueue<>((a, b) ->{ return a[0] - b[0]; });

        for (int i = 0; i < primes.length; i++) {
            queue.offer(new int [] {1, primes[i] ,1});
        }

        int[] ugly = new int[n+1];
        int p = 1;
        while(p <= n){
            // 取三个链表的最小结点
            int[] pair = queue.poll();
            int product = pair[0];
            int prime = pair[1];
            int index = pair[2];

            // 避免结果链表出现重复元素
            if (product != ugly[p - 1]) {
                // 接到结果链表上
                ugly[p] = product;
                p++;
            }

            // 生成下一个节点加入优先级队列
            int[] nextPair = new int[]{ugly[index] * prime, prime, index + 1};
            queue.offer(nextPair);

        }
        return ugly[n];
    }

    //==================================== 第 K 小 ====================================//

    /**
     * 有序矩阵中第 K 小的元素
     *
     * @param matrix 矩阵
     * @param k      k
     * @return int
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix.length == 0) {
            return 0;
        }
        // 先将多链表合并
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                queue.offer(matrix[i][j]);
            }
        }
        // 然后用快慢指针从合并后的链表中找出第k个
        PriorityQueue<Integer> queuep = new PriorityQueue<>();
        queuep.addAll(queue);
        for (int i = 0; i < k; i++) {
            queuep.poll();
        }
        int num = queue.peek();
        while (!queuep.isEmpty()){
            queuep.poll();
            num = queue.poll();
        }
        return num;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int []> queue = new PriorityQueue<>((a, b) ->{ return (a[0] + a[1]) - (b[0] + b[1]); });
        for (int i = 0; i < nums1.length; i++) {
            queue.offer(new int[] {nums1[i], nums2[0], 0});
        }

        List<List<Integer>> lists = new ArrayList<>();
        while (!queue.isEmpty() && k > 0) {
            int[] poll = queue.poll();
            k--;

            int a = poll[0];
            int b = poll[1];
            int i = poll[2];

            if (i + 1 < nums2.length) {
                queue.offer(new int[]{a, nums2[i + 1], i + 1});
            }

            lists.add(new ArrayList<>(Arrays.asList(a, b)));
        }
        return lists;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-100), p = result;

        ArrayDeque<ListNode> deque = new ArrayDeque<>();
        ArrayDeque<ListNode> deque1 = new ArrayDeque<>();
        while (l1 != null) {
            deque.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            deque1.push(l2);
            l2 = l2.next;
        }
        int a = deque.size();
        int b = deque1.size();
        ArrayDeque<ListNode> deque2 = new ArrayDeque<>();
        boolean add = false;
        if (deque1.size() > deque.size()) {
            while (deque1.size() > 0) {
                ListNode pop = deque1.pop();
                int total = pop.val;
                if (deque.size() > 0) {
                    ListNode pop1 = deque.pop();
                    total = pop.val + pop1.val;
                }
                if (add) {
                    total += 1;
                }
                if (total >= 10) {
                    total = total % 10;
                    add = true;
                } else {
                    add = false;
                }
                deque2.push(new ListNode(total));
            }
        } else {
            while (deque.size() > 0) {
                ListNode pop = deque.pop();
                int total = pop.val;
                if (deque1.size() > 0) {
                    ListNode pop1 = deque1.pop();
                    total = pop.val + pop1.val;
                }
                if (add) {
                    total += 1;
                }
                if (total >= 10) {
                    total = total % 10;
                    add = true;
                } else {
                    add = false;
                }
                deque2.push(new ListNode(total));
            }
        }

        if (add) {
            ListNode peek = deque2.peek();
            if ((a == b) || peek.val == 0) {
                deque2.push(new ListNode(1));
            } else {
                int val = peek.val + 1;
                if (val >= 10) {
                    val = val % 10;
                }
                peek.val = val;
                deque2.push(new ListNode(1));
            }
        }

        while (deque2.size() > 0) {
            p.next = deque2.pop();
            p = p.next;
        }

        return result.next;
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

    // 计算 [1..num] 之间有多少个能够被 a 或 b 或 c 整除的数字
    long f(int num, int a, int b, int c) {
        long setA = num / a, setB = num / b, setC = num / c;
        long setAB = num / lcm(a, b);
        long setAC = num / lcm(a, c);
        long setBC = num / lcm(b, c);
        long setABC = num / lcm(lcm(a, b), c);
        // 集合论定理：A + B + C - A ∩ B - A ∩ C - B ∩ C + A ∩ B ∩ C
        return setA + setB + setC - setAB - setAC - setBC + setABC;
    }

    // 计算最大公因数（辗转相除/欧几里得算法）
    long gcd(long a, long b) {
        if (a < b) {
            // 保证 a > b
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // 最小公倍数
    long lcm(long a, long b) {
        // 最小公倍数就是乘积除以最大公因数
        return a * b / gcd(a, b);
    }

    @Test
    public void test(){
        addTwoNumbers(new ListNode(3, new ListNode(7)), new ListNode(9, new ListNode(2)));
    }

    /**
     * 计算基金成本价
     */
    @Test
    public void calculateFundCostPrice(){
        Map<String, double[]> primes = new LinkedHashMap<>();
        primes.put("001410", new double[] {4.1120, 10241.59, 2490.66});
        primes.put("005311", new double[] {1.8307, 11617.59, 6345.98});
        primes.put("022385", new double[] {1.1655, 10637.04, 9126.59});
        primes.put("018125", new double[] {2.1204, 11523.91, 5434.78});
        primes.put("015283", new double[] {1.4897, 11258.32, 7557.44});
        primes.put("017512", new double[] {1.6447, 11284.39, 6861.06});
        primes.put("014881", new double[] {1.1841, 10955.78, 9252.41});
        primes.put("011840", new double[] {1.0750, 10440.95, 9712.51});
        primes.put("006614", new double[] {1.1578, 11437.32, 9878.49});
        primes.put("020336", new double[] {1.5737, 10625.80, 6752.11});
        primes.put("009881", new double[] {0.6787, 10020.67, 14764.51});
        primes.put("012769", new double[] {1.2844, 9552.99, 7437.71});
        primes.put("004753", new double[] {0.8635, 9899.61, 11464.52});
        primes.put("013172", new double[] {0.8394, 11149.29, 13282.45});
        primes.put("008282", new double[] {1.4628, 9915.27, 6778.28});
        primes.put("020624", new double[] {1.6085, 10194.58, 6337.94});
        primes.put("020459", new double[] {1.3336, 10153.03, 7613.25});
        primes.put("002170", new double[] {3.4100, 10121.39, 2968.15});
        primes.put("017526", new double[] {1.351, 9989.65, 7394.26});
        primes.put("007301", new double[] {2.2321, 10100.01, 4524.89});
        primes.put("015968", new double[] {1.2377, 10011.32, 8088.65});

        // Map的key是基金代码或者基金名称
        // 数组中有三个double类型的值:
        // 第一个当前净值（currentPrice）、第二个是当前的持有额（currentHolding）、第三个是当前持有份额（currentPortion）
        for (String code : primes.keySet()) {
            double[] doubles = primes.get(code);
            // 当前净值
            double currentPrice = doubles[0];
            // 当前的持有额
            double currentHolding = doubles[1];
            // 当前持有份额
            double currentPortion = doubles[2];
            // 持有收益
            double currentIncome = currentHolding - 10000;
            // 计算成本价
            double costPrice = currentPrice;
            if (currentIncome > 0) {
                costPrice = currentPrice - (currentIncome / currentPortion);
            } else if (currentIncome < 0) {
                costPrice = currentPrice + (currentIncome / currentPortion);
            }
            //System.out.println("基金：" + code + "的成本价为：" + costPrice);
            System.out.println("基金：" + code + "的成本价为：" + new BigDecimal(costPrice).setScale(4, RoundingMode.HALF_UP));
        }
    }
}
