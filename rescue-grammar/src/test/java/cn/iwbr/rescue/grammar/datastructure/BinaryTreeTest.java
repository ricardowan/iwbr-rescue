package cn.iwbr.rescue.grammar.datastructure;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 二叉树测试
 * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
 * @date: 2025/02/08
 */
@SpringBootTest
public class BinaryTreeTest {

    /**
     * @description: 基本的二叉树节点
     * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
     * @date: 2025/02/08
     */
    class TreeNode {
        int val;
        TreeNode left, right;
    }

    /**
     * @description: 带权重的树节点
     * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
     * @date: 2025/02/08
     */
    class TreeNodePro {
        int val;
        int weight;
        TreeNodePro left, right;
    }

    /**
     * @description: 多叉树节点
     * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
     * @date: 2025/02/08
     */
    class MultiTreeNode {
        int val;
        List<MultiTreeNode> children;
    }

    /**
     * 递归遍历（DFS）
     * 写法固定，是前序中序还是后序取决于代码写的位置
     * 常用来解决查询所有路径的问题
     *
     * @param root 根
     */
    void traverseDfs(TreeNode root) {
        if (root == null) {
            return;
        }

        //        // 前序位置
        //        traverse(root.left);
        //        // 中序位置
        //        traverse(root.right);
        //        // 后序位置

        System.out.println("val：" + root.val);
        traverseDfs(root.left);
        System.out.println("val：" + root.val);
        traverseDfs(root.right);
        System.out.println("val：" + root.val);
    }

    /**
     * 递归遍历（DFS）
     * 写法固定，是前序中序还是后序取决于代码写的位置
     * 常用来解决查询所有路径的问题
     *
     * @param root 根
     */
    void traverseDfs(TreeNode root, ArrayDeque path, List<ArrayDeque> paths) {
        if (root == null) {
            return;
        }

        // 前序位置进入节点时，把节点添加到path
        path.push(root.val);

        // 如果找到叶子节点就意味着找到了一条路径
        if (root.left == null && root.right == null) {
            paths.add(path);
        }

        // 递归遍历左右节点
        traverseDfs(root.left, path, paths);
        traverseDfs(root.left, path, paths);

        // 后序位置离开节点的时候将节点从path中移除
        path.pop();

        //        // 前序位置
        //        traverse(root.left);
        //        // 中序位置
        //        traverse(root.right);
        //        // 后序位置

        //        System.out.println("val：" + root.val);
        //        traverseDfs(root.left);
        //        System.out.println("val：" + root.val);
        //        traverseDfs(root.right);
        //        System.out.println("val：" + root.val);
    }

    /**
     * DFS查找二叉树所有路径
     *
     * @param root 根
     * @return {@link List }<{@link ArrayDeque }>
     */
    public List<ArrayDeque> findAllPaths(TreeNode root) {
        // 所有路径
        List<ArrayDeque> paths = new ArrayList<>();
        // 当前路径
        ArrayDeque deque = new ArrayDeque();
        // 查找所有路径
        traverseDfs(root, deque, paths);
        return paths;
    }

    /**
     * 层序遍历（BFS）
     * 常用于寻找最短路径（例如查询二叉树的最小深度）
     *
     * @param root 根
     */
    void traverseBfs(TreeNode root) {
        if (root == null) {
            return;
        }

        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        int depth = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                // 取出并打印当前节点
                TreeNode node = deque.poll();
                System.out.println("depth = " + depth + ", val = " + node.val);
                // 将当前节点的左右节点入队
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            }
            depth++;
        }

        System.out.println("depth：" + depth);
    }

    /**
     * BFS查询二叉树的最小深度
     * 它比DFS在查询最短路径上的优势是，只要它找到了第一个叶子节点，遍历就结束了，就找到最短的了
     * 而DFS则必须遍历完整棵树才行
     *
     * @param root 根
     * @return int
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {

            int sz = q.size();
            // 遍历当前层的节点
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                // 判断是否到达叶子结点
                if (cur.left == null && cur.right == null) {
                    return depth;
                }

                // 将下一层节点加入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            // 这里增加步数
            depth++;
        }
        return depth;
    }

    /**
     * 层序遍历（BFS）带权重
     *
     * @param root 根
     */
    void traverseBfsWeight(TreeNodePro root) {
        if (root == null) {
            return;
        }

        ArrayDeque<TreeNodePro> deque = new ArrayDeque<>();
        deque.offer(root);
        int depth = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                // 取出并打印当前节点
                TreeNodePro node = deque.poll();
                // 访问 cur 节点，同时知道它的路径权重和
                System.out.println("depth = " + node.weight + ", val = " + node.val);
                // 将当前节点的左右节点入队
                if (node.left != null) {
                    TreeNodePro left = node.left;
                    left.weight = left.weight + 1;
                    deque.offer(left);
                }
                if (node.right != null) {
                    TreeNodePro right = node.right;
                    right.weight = right.weight + 1;
                    deque.offer(right);
                }
            }
        }

        System.out.println("depth：" + depth);
    }

    /**
     * 多叉树的递归遍历（DFS）
     *
     * @param root 根
     */
    void traverseMultiDfs(MultiTreeNode root) {
        if (root == null) {
            return;
        }

        // 前序
        System.out.println("val：" + root.val);
        for (MultiTreeNode child : root.children) {
            traverseMultiDfs(child);
        }
        // 后序
        System.out.println("val：" + root.val);
    }

    /**
     * 多叉树的层序遍历（BFS）
     *
     * @param root 根
     */
    void traverseMultiBfs(MultiTreeNode root) {
        if (root == null) {
            return;
        }

        ArrayDeque<MultiTreeNode> deque = new ArrayDeque();
        deque.offer(root);

        int depth = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                MultiTreeNode node = deque.poll();
                System.out.println("depth = " + depth + ", val = " + node.val);

                for (MultiTreeNode child : node.children) {
                    deque.offer(child);
                }
            }
            depth++;
        }
    }
}
