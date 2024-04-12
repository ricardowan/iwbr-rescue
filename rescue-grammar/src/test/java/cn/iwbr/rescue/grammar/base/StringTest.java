package cn.iwbr.rescue.grammar.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StringTest {

    public static void main(String[] args) {
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
