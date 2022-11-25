package ConsoleBot;
import java.util.*;
public class Bot {
    private String currentCommand;
    private final ArrayList<String> availableCommands;
    private Writer writer;
    private Reader reader;

    public Bot(String key) {
        if (Objects.equals(key, "Console")) {
            writer = new WriterConsole();
            reader = new ReaderConsole();
        }
        if (Objects.equals(key,"Test"))
        {
            writer = new WriterTest();
            reader = new ReaderFile();
        }
        currentCommand = "hello";
        availableCommands = new ArrayList<>();
        availableCommands.add("info");
        availableCommands.add("go");
    }

    public void bot() {
        writer.hello();
        do {
            currentCommand = reader.read();
            while (!availableCommands.contains(currentCommand)){
                writer.errorInput();
                currentCommand = reader.read();
            }

            availableCommands.clear();

            switch (currentCommand) {
                case ("hello") -> {
                    writer.hello();
                    availableCommands.add("info");
                    availableCommands.add("go");
                }
                case ("info") -> {
                    writer.info();
                    availableCommands.add("exit");
                    availableCommands.add("go");
                }
                case ("go") -> {
                    writer.go();
                    availableCommands.add("up");
                    availableCommands.add("down");
                }
                case ("up") -> {
                    writer.up();
                    availableCommands.add("done");
                }
                case ("down") -> {
                    writer.down();
                    availableCommands.add("done");
                }
                case ("done") -> {
                    writer.done();
                    availableCommands.add("info");
                    availableCommands.add("go");
                    availableCommands.add("exit");
                }
                case ("exit") -> writer.exit();
            }
        } while (!Objects.equals(currentCommand, "exit"));
    }
}