package cn.iwbr.rescue.grammar.algorithm;

import lombok.Data;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @description: 算法测试
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/02/20
 */
@SpringBootTest
public class AlgorithmTest {

    /**
     * 找零钱问题（贪心算法）
     */
    @Test
    public void GreedyCoinChange(){
        // 不同面额的硬币
        int[] coins = {1, 2, 5, 10, 20, 50, 100};
        // 需要找零的金额
        int n = 124;

        int minCoins = minCoins(coins, n);
        System.out.println("最少需要 " + minCoins + " 枚硬币");
    }

    /**
     * 最短路径算法（贪心算法）
     */
    public void DijkstraAlgorithm(){

    }



    private static int minCoins(int[] coins, int amount) {
        // 对硬币面额进行升序排序
        Arrays.sort(coins);
        int numCoins = 0;
        // 从最大面额的硬币开始找起
        int index = coins.length - 1;
        while (amount > 0 && index >= 0) {
            if (coins[index] <= amount) {
                // 整数相除会自动舍弃小数点后数据自动取整（不会四舍五入）
                int count = amount / coins[index];
                numCoins += count;
                amount -= count * coins[index];
            }
            index--;
        }
        return numCoins;
    }

    @Data
    class Edge{
        private int to;

        private int weight;
    }
}
