package cn.iwbr.rescue.grammar.algorithm;

import lombok.Data;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description: 查找算法
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/03/21
 */
@SpringBootTest
public class QueryAlgorithmTest {
    int maxSum = Integer.MIN_VALUE;

    @Test
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    @Data
    class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
    }
}
