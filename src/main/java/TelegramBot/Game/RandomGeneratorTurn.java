package TelegramBot.Game;

import java.util.ArrayList;
import java.util.Objects;

public class RandomGeneratorTurn
{
    public String randomGeneratorTurn(ArrayList<String> availableCommands)
    {
        int size = availableCommands.size();

        if(size == 0) return (((int) ( Math.random() * 101 ))%10)+""+(((int) ( Math.random() * 101 ))%10);
        if(size == 2 && Objects.equals(availableCommands.get(0), "0"))
            return (((int) ( Math.random() * 101 ))%2)+"";

        return availableCommands.get(((int) ( Math.random() * size )));
    }

    public String randomGeneratorTurnGame(ArrayList<String> availableCommands)
    { return availableCommands.get(((int) ( Math.random() * availableCommands.size() ))); }
}