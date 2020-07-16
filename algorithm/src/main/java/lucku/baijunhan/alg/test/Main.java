package lucku.baijunhan.alg.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

static     char ch = '\0';
    public static void main(String[] args) {
        Map<String, Integer> map = Stream.of(new String[]{"a","a","b,","c", "c", "c"})
                .collect(Collectors.toMap(a -> a, a -> 1, (o, n) -> o + 1));
        System.out.println(map);

        Map<String, List<String>> map2 = Stream.of(new String[]{"a","a","b,","c", "c", "c"})
                .collect(Collectors.toMap(a -> a, a -> {
                    List<String> l = new ArrayList<>();
                    l.add(a);
                    return l;
                }, (o, n) -> {
                    o.addAll(n);
                    return o;
                }));
        
    }

}
