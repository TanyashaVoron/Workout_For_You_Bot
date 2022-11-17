package BotTraining;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BotTest {
    private void writeInFile(String text, String path) {
        try {
            File file = new File(path);
            file.createNewFile();
        } catch (Exception e) {
            System.err.println(e);
        }

        try (PrintWriter out = new PrintWriter(path, StandardCharsets.UTF_8)) {
            out.print(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String path) {
        String content = "";
        try {
            List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            content = String.join(System.lineSeparator(), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Test
    void TestBot() {
        String[] args = {"info go up done go down done exit /", "info go down done go down done exit /",
                "info exit /", "info go up done exit /", "info go up done go down done exit /",
                "go up done go down done exit /", "go down done go up done exit /",
                "info error error error go up done go down done exit /",
                "info go down error error done go down done exit /", "info error exit /",
                "info go up done exit /", "error info go up done go down done exit /",
                "go up done go error down done exit /", "go down done go error up done exit /"};

        for (String arg : args) {
            String path = "./src/input_for_tests.txt";
            writeInFile(arg, path);

            Bot bot = new Bot("Test");
            bot.bot();
            File file_in = new File(path);
            file_in.delete();

            path = "./src/output_for_tests.txt";
            String res = readFile(path);
            Assertions.assertEquals(arg, res);
            File file_out = new File(path);
            file_out.delete();
        }
    }
}