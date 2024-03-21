package cn.iwbr.rescue.grammar.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 排序算法测试
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/03/21
 */
@SpringBootTest
@Slf4j
public class SortAlgorithmTest {

    //这⾥的AtomicInteger只是为了当个计数器⽤
    private static final AtomicInteger atomic = new AtomicInteger(0);

    /**
     * 冒泡排序测试
     * 思路：遍历数组，与元素后面的元素比较，如果大于后面的元素则交换位置，继续比较
     */
    @Test
    public void bubbleSortTest() {
        int[] array = {10, 5, 7, 3, 4, 2, 6, 8, 9};
        bubbleSort(array);
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length - 1;
        while (true) {
            System.out.print("每轮比较的元素索引：");
            int last = 0;
            for (int i = 0; i < n; i++) {
                System.out.print(i + " ");
                int temp = arr[i];
                int next = arr[i + 1];
                if (temp > next) {
                    arr[i] = next;
                    arr[i + 1] = temp;
                    last = i;
                }
            }
            System.out.println(" 第" + atomic.incrementAndGet() + "轮冒泡结果:" + Arrays.toString(arr));
            n = last;
            if (n == 0) {
                break;
            }
        }
    }

    /**
     * 选择排序测试
     * 思路：遍历数组，找到某个元素后面的元素中的最小值，然后与当前元素替换位置
     */
    @Test
    public void selectSortTest() {
        int[] array = {10, 5, 7, 2, 3, 4, 2, 6, 8, 9};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int s = i;

            // 从数组的第i+1开始，找到后面数组中最小值并记录其索引位置
            for (int j = s + 1; j < arr.length; j++) {
                if (arr[j] < arr[s]) {
                    s = j;
                }
            }

            // 如果找到的最小值不是当前值，则与当前值交互位置，把最小的换到最前面来
            if (s != i) {
                int temp = arr[s];
                arr[s] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 插入排序测试
     * 思路：
     */
    @Test
    public void intersectSortTest(){
        int[] array = {10, 5, 7, 2, 3, 4, 2, 6, 8, 9};
        intersectSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void intersectSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0) {
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j];
                }else{
                    break;
                }
                j--;
            }
            arr[j+1] = temp;
        }
    }

    /**
     * 快速排序测试
     */
    @Test
    public void quickSortTest(){
        int[] array = {10, 5, 7, 2, 3, 4, 2, 6, 8, 9};
        quickSort(array, 0, array.length -1);
        System.out.println(Arrays.toString(array));
    }

    private void quickSort(int[] arr, int l, int h) {
        if (l >= h) {
            return;
        }
        //返回值代表了基准点元素所在的正确索引，⽤它确定下⼀轮分区的边界
        int p = partition(arr, l, h);
        //左边分区的范围确定
        quickSort(arr, l, p - 1);
        //右边分区的范围确定
        quickSort(arr, p + 1, h);
    }

    private int partition(int[] arr, int l, int h) {
        int pv = arr[l]; // 基准点元素
        int i = l;
        int j = h;
        while (i < j) {
            //j从右往左找⼩的
            while (i < j && arr[j] > pv) {
                j--;
            }
            //i从左往右找⼤的
            while (i < j && arr[i] <= pv) {
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        int temp = arr[l];
        arr[l] = arr[j];
        arr[j] = temp;
        return j;
    }

    /**
     * 二分查找法测试
     */
    @Test
    public void binarySearchTest(){
        int[] array = {10, 5, 7, 2, 3, 4, 2, 6, 8, 9};
        intersectSort(array);
        int i = binarySearch(array, 100);
        System.out.println("i = " + i);
    }

    private int binarySearch(int[] arr, int target){
        // 注意将已经比较过值的索引移除，不要进入下次查询，避免在查询不到数据的时候导致无限循环
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = (right + left) >>> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
