package ink.andromeda.leisure.scripts;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DiffOrderNos {

    public static void main(String[] args) throws IOException {
        List<String> all = Files.readAllLines(Paths.get("/Users/andromeda/Desktop/all.tsv"));
        all.removeAll(Files.readAllLines(Paths.get("/Users/andromeda/Desktop/sub.tsv")));
        Files.write(Path.of("/Users/andromeda/Desktop/diff.tsv"), all, StandardOpenOption.CREATE);
    }


}
