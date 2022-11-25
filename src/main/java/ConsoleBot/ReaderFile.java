package ConsoleBot;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class ReaderFile implements Reader {
    private Integer flag;
    private String currentLine;

    public ReaderFile() {
        flag = 0;
        currentLine = "";
    }

    private String readFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        return String.join(System.lineSeparator(), lines);
    }
    public String read() {
        String filePath = "./src/input_for_tests.txt";
        String[] arr;

        if (flag == 0) {
            String content = "";
            try {
                content = readFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            arr = content.split("\n", 2);
            currentLine = arr[0];
            flag = 1;
        }

        if (!currentLine.equals("")){
            arr = currentLine.split(" ", 2);
            currentLine = arr[1];
            return arr[0];
        } else{
            System.out.println("Достигнут конец строки, невозможно произвести выборку.");
            return "";
        }
    }
}