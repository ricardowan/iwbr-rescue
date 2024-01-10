package cn.iwbr.rescue.grammar;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: Java8新特性测试类
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2024/01/10
 */
@Slf4j
@SpringBootTest
public class Java8Tests {

    private int number = 1;

    /**
     * Lambda表达式测试
     */
    @Test
    public void lambdaTest() {
        // 1. 不需要参数,返回值为 5
        //() -> 5

        // 2. 接收一个参数(数字类型),返回其2倍的值
        //x -> 2 * x

        // 3. 接受2个参数(数字),并返回他们的差值
        //(x, y) -> x – y

        // 4. 接收2个int型整数,返回他们的和
        //(int x, int y) -> x + y

        // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
        //(String s) -> System.out.print(s)

        // 函数式编程
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.forEach(number -> System.out.println(number * 2));

        // 使用方法引用
        List<String> names1 = Arrays.asList("Alice", "Bob", "Charlie");
        names1.forEach(System.out::println);

        // 并行处理
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        numbers1.parallelStream().forEach(number -> System.out.println(number * 2));

        // 使用Lambda实现Addition接口
        Addition addition = (a, b) -> a + b;
        System.out.println(addition.addTwoNumbers(1, 2));
        Addition addition1 = (int a, int b) -> {
            System.out.println("哈哈哈哈");
            return a + b;
        };
        System.out.println(addition1.addTwoNumbers(2, 2));

        // 变量作用域，表达式只能引用final修饰的外层变量，这就是说不能在 lambda 内部修改定义在域外的变量
        Addition addition2 = (a, b) -> a + b + number;
        System.out.println(addition2.addTwoNumbers(3, 2));
    }

    /**
     * 函数式接口测试
     */
    @Test
    public void functionalInterfaceTest(){
        Addition addition = (a, b) -> a + b;

        // 调用抽象方法
        addition.addTwoNumbers(1, 2);

        // 调用默认方法
        addition.addTwoNumbers2(2, 2);

        // 调用静态方法
        Addition.addTwoNumbers3(3, 2);
    }

    @Test
    public void optionalTest(){

        // 创建一个非空值的Optional对象，如果传入null则会抛出异常：NullPointerException
        Optional<String> optional = Optional.of("Hello, World!");
        
        // 创建一个允许传入null值的Optional对象
        String str = "Hello, World!";
        Optional<String> optionalNullable = Optional.ofNullable(null);
        str = Optional.ofNullable(str).orElse("空值");

        // 创建一个空的 Optional 对象
        Optional<String> emptyOptional = Optional.empty();

        // 获取 Optional 对象中的值，如果值不存在，会抛出 NoSuchElementException
        String value = optional.get();

        // 如果 Optional 对象包含值，则返回 true，否则返回 false
        optional.ifPresent(s -> System.out.println("Value: " + s));

        // 如果 Optional 对象包含值，则返回该值，否则返回传入的默认值
        String result = optional.orElse("Default Value");

        // 如果 Optional 对象包含值，则返回该值，否则调用传入的 Supplier 函数获取默认值
        String result1 = optional.orElseGet(() -> "Default Value");

        // 如果 Optional 对象包含值，则返回该值，否则抛出通过 Supplier 提供的异常
        String result2 = optional.orElseThrow(() -> new IllegalArgumentException("Value not present"));
    }

    @Test
    public void streamTest(){

        // **************** Stream 的创建 **************** //

        // 根据集合创建流
        List<String> fruitList = Arrays.asList("apple", "banana", "orange");
        Stream<String> streamFromList = fruitList.stream();

        // 根据数组创建流
        String[] myArray = {"apple", "banana", "orange"};
        Stream<String> streamFromArray = Arrays.stream(myArray);

        // 使用静态方法创建流
        Stream<String> streamOfValues = Stream.of("apple", "banana", "orange");

        // **************** Stream 的中间操作 **************** //

        // 过滤流中的元素
        List<String> filterList = streamOfValues.filter(s -> s.startsWith("a")).collect(Collectors.toList());

        // 对流中的元素进行转换或处理的操作，该操作对流中所有元素生效，会修改流中的元素
        // 使用map将字符串转换为它们的长度
        List<Integer> lengthList = fruitList.stream().map(String::length).collect(Collectors.toList());

        // 不会改变流中元素的值，它主要用于调试、查看流中元素的中间状态
        // 使用peek查看每个元素的长度
        List<Integer> length = fruitList.stream()
                .peek(s -> System.out.println("Length of " + s + ": " + s.length())).map(String::length).collect(Collectors.toList());

        // 去重
        List<String> distinctList = fruitList.stream().distinct().collect(Collectors.toList());

        // 排序
        List<String> orderList = fruitList.stream().sorted((a, b) -> (a.length() - b.length())).collect(Collectors.toList());

        // 截取前 N 个元素
        List<String> limitList = fruitList.stream().limit(2).collect(Collectors.toList());

        // 跳过前 N 个元素
        List<String> skipList = fruitList.stream().skip(1).collect(Collectors.toList());

        // **************** Stream 的终端操作 **************** //

        // 遍历流中的元素
        fruitList.stream().forEach(System.out::println);

        // 将元素收集为集合或其他数据结构
        List<String> collectedList = fruitList.stream().collect(Collectors.toList());

        // 将元素逐个结合起来，得到一个结果
        String names = fruitList.stream().reduce((a,b) -> a.concat(",").concat(b)).get();
        System.out.println(names);

        // 检查是否至少一个元素匹配给定的条件
        boolean anyMatchResult = fruitList.stream().anyMatch(s -> s.contains("a"));

        // 检查是否所有元素都匹配给定的条件
        boolean allMatchResult = fruitList.stream().allMatch(s -> s.length() > 2);

        // 检查是否没有元素匹配给定的条件
        boolean noneMatchResult = fruitList.stream().noneMatch(s -> s.endsWith("z"));

        // 返回 Stream 中的元素数量
        long count = fruitList.stream().count();

        // 找到最小和最大的元素。
        Optional<String> minValue = fruitList.stream().min(String::compareTo);
        Optional<String> maxValue = fruitList.stream().max(String::compareTo);

        // **************** 并行处理 ****************//

        // 在 Stream 上调用 parallel() 方法可以使得操作在并行执行
        Stream<String> parallelStream = fruitList.stream().parallel();

        // 使用 parallelStream() 方法创建并行流。
        Stream<String> parallelStream1 = fruitList.parallelStream();

    }

    @FunctionalInterface
    interface Addition {
        int addTwoNumbers(int a, int b);

        default void addTwoNumbers2(int a, int b) {
            System.out.println(a * b);
        }

        static void addTwoNumbers3(int a, int b) {
            System.out.println(a + b);
        }
    }

}
