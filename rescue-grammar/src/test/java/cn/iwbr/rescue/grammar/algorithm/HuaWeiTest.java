package cn.iwbr.rescue.grammar.algorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 华威测试
 * @author: <a href="mailto:ricardomrwang@gmail.com">wangbaorui</a>
 * @date: 2024-04-23 19:12:31
 */
public class HuaWeiTest {

    public static void main(String[] args) {
        longExpression();
    }

    public static void longExpression(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        char[] charArray = str.toCharArray();
        int index = -1;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (Character.isDigit(c)) {
                if (index == -1) {
                    index = i;
                }
            } else if (index!= -1 && "+-*".contains(String.valueOf(c))) {
                if (!"+-*".contains(String.valueOf(charArray[i - 1]))) {
                    i++;
                } else {
                    list.add(str.substring(index, i));
                    index = -1;
                }
            } else {
                if(index != -1){
                    list.add(str.substring(index, i));
                    index = -1;
                }
            }
        }
        if(index != -1){
            list.add(str.substring(index));
        }

        list = list.stream().sorted((a,b) ->{
            return b.length() - a.length();
        }).collect(Collectors.toList());

        if (!list.isEmpty()) {
            String s = list.get(0);
            System.out.println(getResult(s));
        } else {
            System.out.println(0);
        }
    }

    /**
     * 获取结果
     *
     * @return int
     */
    private static int getResult(String str){
        List<String> list = new ArrayList<>();

        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if(Character.isDigit(c)){
                sb.append(c);
            } else {
                list.add(sb.toString());
                list.add(String.valueOf(c));
                sb.setLength(0);
            }
        }
        list.add(sb.toString());

        for (int i = 0; i < list.size(); i++) {
            if("*".equals(list.get(i))){
                int result = Integer.parseInt(list.get(i -1)) * Integer.parseInt(list.get(i+1));
                list.set(i - 1, String.valueOf(result));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }

        Integer result = Integer.parseInt(list.get(0));

        for (int i = 1; i < list.size(); i+=2) {
            if ("+".equals(list.get(i))) {
                result = result + Integer.parseInt(list.get(i + 1));
            } else {
                result = result - Integer.parseInt(list.get(i + 1));
            }
        }

        return result;
    }

    private static boolean isChar(String str){
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static void arrayTest(){
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        if (str.isEmpty()) {
            return;
        }
        int[] array = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
        if (array.length <= 0) {
            return;
        }

        Map<Integer,Integer> map = new HashMap<>();
        for (int i : array) {
            Integer orDefault = map.getOrDefault(i, 0);
            orDefault++;
            map.put(i,orDefault);
        }
        List<Map.Entry<Integer, Integer>> entryList = map.entrySet().stream().sorted((entry1, entry2) -> {
            if (!entry1.getValue().equals(entry2.getValue())) {
                return entry2.getValue() - entry1.getValue();
            } else {
                return str.indexOf(entry1.getKey()) - str.indexOf(entry2.getKey());
            }
        }).collect(Collectors.toList());

        for (int i = 0; i < entryList.size(); i++) {
            Integer key = entryList.get(i).getKey();
            if (i != entryList.size() - 1) {
                System.out.print(key + ",");
            } else {
                System.out.print(key);
            }
        }
    }
}
