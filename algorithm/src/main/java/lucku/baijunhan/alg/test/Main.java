package lucku.baijunhan.alg.test;

import java.util.*;

public class Main{

    public static void main(String[] args) {

        System.out.println(System.getProperties());

//        Scanner scanner = new Scanner(System.in);
//        int count = scanner.nextInt();
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            String next = scanner.next();
//            list.add(next);
//        }
//        List<String> repair = repair(list);
//        for (String s : repair) {
//            System.out.println(s);
//        }

    }

    private static List<String> repair(List<String> list){
        List<String> result = new ArrayList<>();
        for (String str : list) {
            char[] chars = str.toCharArray();
            int countA = 1;
            int countB = 0;
            for (int i = 1; i < chars.length; i++) {
                if(countA == 2 && countB == 1 && chars[i] == chars[i-1]){
                    chars[i-1] = '\0';
                    countA = 2;
                    countB = 1;
                }
                else if(countA == 2 && chars[i] == chars[i-1]){
                    chars[i-1] = '\0';
                    countA = 2;
                    countB = 0;
                }
                else{
                    if(chars[i] == chars[i-1]){
                        if(countA < 2){
                            countA++;
                        }
                        else if(countA == 2 && countB < 2){
                            countB++;
                        }
                    }
                    else{
                        if(countA == 2){
                            countB++;
                        }
                        else{
                            countA = 1;
                            countB = 0;
                        }
                    }
                }
            }
            result.add(new String(chars));
        }
        return result;
    }
}