package cn.iwbr.rescue.grammar.base;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StringTest {

    static int total = 0;

    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        landDistribution();
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
        gameDfs(intArray, 0, 0, 0);
        System.out.println(res);
        scanner.close();
    }

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

    /**
     * 最长子串
     */
    public static void theLongestCharacter() {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();

        int maxLength = -1;
        boolean hasLetter = false;
        int l = 0, r = 0;

        Deque<Integer> letterIndex = new ArrayDeque<>();

        while (r < str.length()) {
            char c = str.charAt(r);

            if (Character.isLetter(c)) {
                hasLetter = true;
                letterIndex.addLast(r);

                if (letterIndex.size() > 1) {
                    l = letterIndex.removeFirst() + 1;
                }

                if (r == l) {
                    r++;
                    continue;
                }
            }

            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }

        if (!hasLetter) {
            System.out.println(-1);
        } else {
            System.out.println(maxLength);
        }
    }

    /**
     * 拆分均衡字符串
     */
    public static void splitEqualizationString() {
        Scanner scanner = new Scanner(System.in);

        String next = scanner.next();

        char[] charArray = next.toCharArray();
        int count = 0;
        int dd = 0;
        for (char c : charArray) {
            if (c == 'X') {
                dd++;
            } else if (c == 'Y') {
                dd--;
            }
            if (dd == 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * 机器人搬砖
     */
    public static void robotSlabs() {
        Scanner scanner = new Scanner(System.in);

        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (nums.length > 8) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(nums);
        int l = 1, r = nums[nums.length - 1];
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canFinish(nums, 8, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(l);
    }

    /**
     * 出租车司机的伎俩
     */
    public static void cabDriverTricks() {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        int real = 0;
        for (char c : str.toCharArray()) {
            int i = c - '0';
            if (i > 4) {
                i--;
            }
            real = real * 9 + i;
        }
        System.out.println(real);
    }

    /**
     * 最富有小家庭
     */
    public static void richestSmallFamily() {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Integer> map = new HashMap<>();
        while (n > 1 && scanner.hasNextLine()) {
            int[] single = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int p = single[0];
            int c = single[1];
            if (map.containsKey(p)) {
                map.put(p, map.getOrDefault(p, 0) + nums[c - 1]);
            } else {
                map.put(p, nums[p - 1] + nums[c - 1]);
            }
            n--;
        }
        List<Integer> sortList = map.values().stream().sorted(Integer::compareTo).collect(Collectors.toList());

        System.out.println(sortList.get(sortList.size() - 1));
    }

    /**
     * 开源项目热门列表
     */
    public static void hotListOfOpenSourceProjects() {
//        5
//        5 6 6 1 2
//        camila 13 88 46 26 169
//        grace 64 38 87 23 103
//        lucas 91 79 98 154 79
//        leo 29 27 36 43 178
//        ava 29 27 36 43 178
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] project = scanner.nextLine().split(" ");
            map.put(project[0], getSum(nums, project));
        }

        map.entrySet().stream().sorted((entry, entry1) -> {
            if (!entry.getValue().equals(entry1.getValue())) {
                return entry1.getValue() - entry.getValue();
            } else {
                return entry.getKey().compareToIgnoreCase(entry1.getKey());
            }
        }).collect(Collectors.toList()).forEach(f -> System.out.println(f.getKey()));
    }

    private static int getSum(int[] nums, String[] project) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i] * Integer.parseInt(project[i + 1]);
        }
        return sum;
    }

    /**
     * 身高差排序
     */
    public static void sortDyHeightDifference() {
        Scanner scanner = new Scanner(System.in);

        int h = scanner.nextInt();
        int n = scanner.nextInt();

        scanner.nextLine();

        int[] heightArray = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = Math.min(n, heightArray.length);
        Map<Integer, Integer> heightMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            heightMap.put(heightArray[i], Math.abs(heightArray[i] - h));
        }

        heightMap.entrySet().stream().sorted((entry, entry1) -> {
            if (!entry1.getValue().equals(entry.getValue())) {
                return entry.getValue() - entry1.getValue();
            } else {
                return entry.getKey() - entry1.getKey();
            }
        }).collect(Collectors.toList()).forEach(entry -> System.out.print(entry.getKey() + " "));
    }

    /**
     * 土地分配
     */
    public static void landDistribution() {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        scanner.nextLine();

        Map<Integer, List<int[]>> map = new HashMap();
        for (int i = 0; i < m; i++) {
            int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                int value = array[j];
                if (value > 0) {
                    int[] single = new int[]{i, j};
                    List<int[]> orDefault = map.getOrDefault(array[j], new ArrayList<>());
                    orDefault.add(single);
                    map.put(array[j], orDefault);
                }
            }
        }

        int maxArea = 0;
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            List<int[]> value = entry.getValue();
            List<Integer> xList = new ArrayList<>();
            List<Integer> yList = new ArrayList<>();
            for (int[] ints : value) {
                xList.add(ints[0]);
                yList.add(ints[1]);
            }
            Integer xMin = xList.stream().min(Integer::compareTo).get();
            Integer xMax = xList.stream().max(Integer::compareTo).get();
            Integer yMin = yList.stream().min(Integer::compareTo).get();
            Integer yMax = yList.stream().max(Integer::compareTo).get();

            int area = (xMax - xMin + 1) * (yMax - yMin + 1);
            maxArea = Math.max(maxArea, area);
        }

        System.out.println(maxArea);
    }

    /**
     * 银饰的重量
     */
    public static void weightOfSilverJewelry() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        scanner.nextLine();

        int[] values = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = Math.min(n, values.length);
        int l = 0;
        while (n >= 3) {

        }
    }
}
