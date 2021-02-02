package ink.andromeda.leisure.scripts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("(\"Failed_Detail_Code\"\\s*:\\s*\"-?\\d+\")|(\"Error_Code\"\\s*:\\s*\"-?\\d+\")|(\"MG_MSG_PLAYER_VERSION\"\\s*:\\s*\".+?\")");
        List<String> collect = Files.readAllLines(Paths.get("/Users/windlively/Downloads/错误码.txt")).stream()
                .map(s -> {
                    List<String> strs = new ArrayList<>();
                    Matcher matcher = pattern.matcher(s);
                    int i = 0;
                    while (matcher.find()) {
                        strs.add(matcher.group());
                    }
                    return "{" + String.join(",", strs) + "}";
                }).collect(Collectors.toList());

        Files.write(Paths.get("/Users/windlively/Desktop/result.txt"), collect);
        System.out.println(collect);
    }
}
