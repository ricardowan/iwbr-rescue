package cn.iwbr.rescue.grammar.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StringTest {

    public static void main(String[] args) {
        validCharacters();
    }

    public static void validCharacters(){
        Scanner scanner = new Scanner(System.in);

        String strS = scanner.next();
        String strL = scanner.next();

        int indexS = 0;
        int indexL = 0;

        while (indexS < strS.length() && indexL < strL.length()){
            if(strS.charAt(indexS) == strL.charAt(indexL)){
                indexS++;
            }
            indexL++;
        }

        int finalIndex = -1;
        if(indexS == strS.length()){
            finalIndex =  indexL-1;
        }
        System.out.println(finalIndex);
    }

    public void test(){
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
