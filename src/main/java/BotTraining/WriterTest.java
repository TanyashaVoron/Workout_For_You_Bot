package BotTraining;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
public class WriterTest implements Writer  {

    private String result;

    public WriterTest() {
        result = "";
    }

    public void hello(){}

    public void info() {
        result += "info ";
    }

    public void go() {
        result += "go ";
    }

    public void up() {
        result += "up ";
    }

    public void down() {
        result += "down ";
    }

    public void done() {
        result += "done ";
    }

    public void exit() {
        result += "exit /";
        File file = new File("./src/output_for_tests.txt");
        try {
            file.delete();
            file.createNewFile();
        }
        catch (Exception e) {
            System.err.println(e);
        }

        try (PrintWriter out = new PrintWriter(file, StandardCharsets.UTF_8))
        {
            out.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void errorInput() {
        result += "error ";
    }
}