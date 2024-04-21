package cn.iwbr.rescue.grammar.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class HaWeiOdTest {

    static int total = 0;

    static int res = Integer.MAX_VALUE;

    static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        binaryConversion();
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

    /**
     * 最小字符串
     */
    public static void minimumString() {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();
        char[] values = str.toCharArray();

        char[] sortStr = str.toCharArray();
        Arrays.sort(sortStr);

        for (int i = 0; i < values.length; i++) {
            if (sortStr[i] != values[i]) {
                int temIndex = -1;
                for (int j = 0; j < sortStr.length; j++) {
                    if (sortStr[j] != values[i]) {
                        temIndex = j;
                    }
                }
                values[temIndex] = values[i];
                values[i] = sortStr[i];
                break;
            }
        }

        System.out.println(new String(values));
    }

    /**
     * 学生成绩排名
     */
    public static void rankingOfGrades() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        scanner.nextLine();

        String[] xkArray = scanner.nextLine().split(" ");

        Map<String, int[]> ranks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] stu = scanner.nextLine().split(" ");
            int[] values = new int[m + 1];
            int total = 0;
            for (int j = 0; j < m; j++) {
                int single = Integer.parseInt(stu[j + 1]);
                values[j] = single;
                total += single;
            }
            values[m] = total;
            ranks.put(stu[0], values);
        }

        String next = scanner.next();
        int index = -1;
        for (int i = 0; i < xkArray.length; i++) {
            if (next.equals(xkArray[i])) {
                index = i;
                break;
            }
        }

        final int finalIndex = index;
        ranks.entrySet().stream().sorted((entry, entry1) -> {
            if (finalIndex == -1) {
                return entry.getValue()[m] - entry1.getValue()[m];
            } else {
                return entry.getValue()[finalIndex] - entry1.getValue()[finalIndex];
            }
        }).collect(Collectors.toList()).forEach(f -> System.out.print(f.getKey() + " "));

    }

    /**
     * RSA加密算法、素数之积
     */
    public static void RSA() {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();

        if (isPrime(num)) {
            System.out.println("-1 -1");
            return;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                int other = num / i;
                if (isPrime(other) && isPrime(i)) {
                    System.out.println(i > other ? other + " " + i : i + " " + other);
                    return;
                }
            }
        }

        System.out.println("-1 -1");
    }

    /**
     * 判断一个数是否是素数
     * 素数：一个大于1的自然数，除了1和它自身外，不能被其他自然数整除的数叫做质数（任何一个数字n，都可以写成 n = a×b的形式，但是素数不行）
     * 50以内的素数：2、3、5、7、11、13、17、19、23、29、31、37、41 、43、47
     *
     * @param number 编号
     * @return boolean
     */
    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 购买宝石
     */
    public static void buyGems() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int money = scanner.nextInt();

        int l = 0, r = 0, max = 0, sum = 0;
        while (r < nums.length) {
            sum = sum + nums[r];
            if (sum > money) {
                sum -= nums[l];
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        System.out.println(max);
    }

    /**
     * 寻找聚餐地点
     */
    public static void findingMeetPlace() {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int n = scanner.nextInt();

        scanner.nextLine();

        // 两个人的位置
        List<int[]> positions = new ArrayList<>();
        // 聚餐地点
        List<int[]> targetList = new ArrayList<>();
        // 完整图
        int[][] map = new int[m][n];
        //  构建图
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int single = scanner.nextInt();
                if (single == 3) {
                    targetList.add(new int[]{i, j});
                }
                if (single == 2) {
                    positions.add(new int[]{i, j});
                }
                map[i][j] = single;
            }
        }

        int[] p1 = positions.get(0);
        int[] p2 = positions.get(1);
        boolean[][][] arrived = new boolean[m][n][2];

        int total = 0;
        for (int[] tar : targetList) {
            arrived = new boolean[m][n][2];
            if (arriveDfs(p1, tar, map, arrived, 0)) {
                arrived = new boolean[m][n][2];
                if (arriveDfs(p2, tar, map, arrived, 1)) {
                    total++;
                }
            }
        }
        System.out.println(total);
    }

    private static boolean arriveDfs(int[] currentPoint, int[] targetPoint, int[][] map, boolean[][][] arrived, int p) {
        System.out.println(Arrays.toString(currentPoint) + " " + map[currentPoint[0]][currentPoint[1]] + " " + Arrays.toString(targetPoint));
        // 如果当前点就是目标点则返回
        if (currentPoint[0] == targetPoint[0] && currentPoint[1] == targetPoint[1]) {
            System.out.println("进来了！");
            return true;
        }

        for (int[] dir : dirs) {
            int x = currentPoint[0] + dir[0];
            int y = currentPoint[1] + dir[1];
            // 如果已经访问过、超出边界、或者是障碍物则跳过
            if (x < 0 || x >= map.length || y < 0 || y >= map[0].length || map[x][y] == 1 || arrived[x][y][p]) {
                continue;
            }
            arrived[x][y][p] = true;
            if (arriveDfs(new int[]{x, y}, targetPoint, map, arrived, p)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 服务器组网
     */
    public static void serverNetworking(){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

            }
        }



    }

    /**
     * 二进制转换
     */
    public static void binaryConversion(){
        Scanner scanner = new Scanner(System.in);

        int i = scanner.nextInt();
        String bin = Integer.toBinaryString(i);
        int m = getOneCount(bin);

        int j = i + 1;
        while (j > i) {
            String tempBin = Integer.toBinaryString(j);
            if (m == getOneCount(tempBin)) {
                break;
            }
            j++;
        }
        System.out.println(j);
    }

    private static int getOneCount(String bin){
        int count = 0;
        for (int i = 0; i < bin.length(); i++){
            if(bin.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }
}
