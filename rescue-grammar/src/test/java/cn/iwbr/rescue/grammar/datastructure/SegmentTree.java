package cn.iwbr.rescue.grammar.datastructure;

import java.util.function.BinaryOperator;

/**
 * @description: 线段树
 * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
 * @date: 2025/02/25
 */
public class SegmentTree {

    private final SegmentNode root;

    private final BinaryOperator<Integer> merge;

    public SegmentTree(int[] nums, BinaryOperator<Integer> merge) {
        this.merge = merge;
        this.root = build(nums, 0, nums.length - 1);
    }

    class SegmentNode {
        // 节点的区间
        int l, r;
        // 区间[l,r]的聚合值（和、最大值、最小值等）
        int mergeValue;
        // 左右节点
        SegmentNode left, right;

        public SegmentNode(int mergeValue,int l, int r) {
            this.mergeValue = mergeValue;
            this.l = l;
            this.r = r;
        }
    }

    /**
     * 将数组中的元素构建成线段树，并返回根节点
     *
     * @param nums nums
     * @param l    l
     * @param r    右
     * @return {@link SegmentNode }
     */
    private SegmentNode build(int[] nums, int l, int r){
        // 如果只有一个元素则直接返回
        if (l == r) {
            return new SegmentNode(nums[l], l,r);
        }

        // 否则从中间开始递归构建左右子树
        int mid = l + (r - l) / 2;
        SegmentNode left = build(nums, l, mid);
        SegmentNode right = build(nums, mid + 1, r);

        // 根据左右子树的聚合值构建当前节点
        SegmentNode node = new SegmentNode(merge.apply(left.mergeValue, right.mergeValue), l, r);
        node.left = left;
        node.right = right;
        return node;
    }

    /**
     * 范围查询
     *
     * @param l l
     * @param r 右
     * @return int
     */
    public int query(int l, int r) {
        return query(root, l, r);
    }

    /**
     * 查询
     *
     * @param node 节点
     * @param l    l
     * @param r    右
     * @return int
     */
    private int query(SegmentNode node, int l, int r) {
        // 判断临界条件
        if(r < l){
            throw new IllegalArgumentException("r must not be greater than l");
        }

        // 如果匹配到区间了则返回当前节点的聚合值
        if(node.l == l && node.r == r){
            return node.mergeValue;
        }

        // 未直接命中区间，需要继续向下查找
        int mid = node.l + (node.r - node.l) / 2;
        if (mid >= r) {
            // node.l <= l <= r <= mid
            // 如果区间的最大值小于中位值，则证明区间在当前节点的左子树
            return query(node.left, l, r);
        } else if(mid < l){
            // mid < l <= r < node.right
            // 如果区间的最小值大于中位值，则证明区间在当前节点的右子树
            return query(node.right, l, r);
        } else {
            // node.l <= l <= mid <= r <= node.r;
            // 否则就是这个区间跨越了左右子树
            // 将查询区间拆分成 [l, mid] 和 [mid + 1, r] 两部分，分别向左右子树查询，最后将值加起来
            return merge.apply(query(node.left, l, mid), query(node.right, mid+1, r));
        }
    }

    /**
     * 更新值
     */
    public void update(int index, int value){
        update(root, index, value);
    }

    /**
     * 更新值
     * 思路：线段树的特点是所有的元素都存储在叶子节点，那么只要找到叶子节点就找到了
     *
     * @param node  节点
     * @param index 索引
     * @param value 值
     */
    private void update(SegmentNode node, int index, int value){
        if (node.l == node.r) {
            node.mergeValue = value;
            return;
        }

        int mid = node.l + (node.r - node.l) / 2;
        if (index <= mid) {
            // 如果较小则从左边树找
            update(node.left, index, value);
        } else {
            // 如果较大则从右边树找
            update(node.right, index, value);
        }
        // 后序更新，左右子树的聚合值更新完后要更新当前节点的聚合值
        node.mergeValue = merge.apply(node.left.mergeValue, node.right.mergeValue);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        // 示例，创建一棵求和线段树
        SegmentTree st = new SegmentTree(arr, (a, b) -> a + b);

        System.out.println(st.query(1, 3)); // 3 + 5 + 7 = 15
        st.update(2, 10);
        System.out.println(st.query(1, 3)); // 3 + 10 + 7 = 20
    }
}
