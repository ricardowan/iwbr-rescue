package cn.iwbr.rescue.grammar.base;

import java.util.*;
import java.util.stream.Collectors;

public class StringTest {

    static int total = 0;

    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        gameTest();
    }

    /**
     * 有效字符
     */
    public static void validCharacters() {
        Scanner scanner = new Scanner(System.in);

        String strS = scanner.next();
        String strL = scanner.next();

        int indexS = 0;
        int indexL = 0;

        while (indexS < strS.length() && indexL < strL.length()) {
            if (strS.charAt(indexS) == strL.charAt(indexL)) {
                indexS++;
            }
            indexL++;
        }

        int finalIndex = -1;
        if (indexS == strS.length()) {
            finalIndex = indexL - 1;
        }
        System.out.println(finalIndex);
    }

    /**
     * 找好朋友
     */
    public static void findFriend() {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String[] nextStr = scanner.nextLine().split(" ");
        int[] height = new int[n];
        for (int i = 0; i < nextStr.length; i++) {
            height[i] = Integer.parseInt(nextStr[i]);
        }

        int[] result = new int[n];
        ArrayDeque<Integer> arrayDeque = new ArrayDeque();
        arrayDeque.push(0);
        for (int i = 1; i < n; i++) {
            while (!arrayDeque.isEmpty() && height[i] > height[arrayDeque.peek()]) {
                result[arrayDeque.pop()] = i;
            }
            arrayDeque.push(i);
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * 孙悟空吃桃子
     */
    public static void eatPeaches() {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split(" ");
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            intArray[i] = Integer.parseInt(array[i]);
        }

        int h = scanner.nextInt();
        int n = intArray.length;
        if (n == 0 || h <= 0 || n >= 10000 || h >= 10000 || n > h) {
            System.out.println(0);
            return;
        }

        Arrays.sort(intArray);

        int min = 1, max = intArray[n - 1];
        while (min < max) {
            int mid = min + (max - min) / 2;
            if (canFinish(intArray, h, mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min);
    }

    /**
     * 王者荣耀游戏分组
     */
    public static void gameTest() {
        Scanner scanner = new Scanner(System.in);
        String[] strArray = scanner.nextLine().split(" ");
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            int num = Integer.parseInt(strArray[i]);
            total += num;
            intArray[i] = num;
        }
        gameDfs(intArray, 0,0,0);
        System.out.println(res);
        scanner.close();
    }

    /**
     * 游戏dfs
     *
     * @param nums         nums
     * @param index        索引
     * @param count        计数
     * @param currentTotal 当前总计
     */
    private static void gameDfs(int[] nums, int index, int count, int currentTotal) {

        if (count == 5) {
            int otherTotal = total - currentTotal;
            res = Math.min(res, Math.abs(otherTotal - currentTotal));
            return;
        }

        if (index == 10) {
            return;
        }

        gameDfs(nums, index + 1, count + 1, currentTotal + nums[index]);
        gameDfs(nums, index + 1, count, currentTotal);
    }

    private static boolean canFinish(int[] p, int h, int k) {
        int total = 0;
        for (int i : p) {
            total += Math.ceil(i * 1.0 / k);
        }
        return total <= h;
    }

    public void test() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        List<String> list = new ArrayList();
        while (in.hasNext()) {
            if (list.size() >= t) {
                break;
            }
            list.add(in.next());
        }
        Collections.sort(list);
        System.out.println(String.join(" ", list));
    }
}
