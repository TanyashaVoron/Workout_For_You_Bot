package ConsoleBot;
import java.util.Scanner;
public class ReaderConsole implements Reader{
    public String read(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}