package TelegramBot.Game;

import java.util.HashMap;

public class ConvertTurn
{
    private Integer firstCoor;
    private Integer secondCoor;

    private final HashMap<String,Integer> first;
    private final HashMap<String,Integer> second;

    ConvertTurn()
    {
        first = new HashMap<>();
        first.put("A", 0);
        first.put("B", 1);
        first.put("C", 2);
        first.put("D", 3);
        first.put("E", 4);
        first.put("F", 5);
        first.put("G", 6);
        first.put("H", 7);
        first.put("I", 8);
        first.put("K", 9);

        second = new HashMap<>();
        second.put("1", 0);
        second.put("2", 1);
        second.put("3", 2);
        second.put("4", 3);
        second.put("5", 4);
        second.put("6", 5);
        second.put("7", 6);
        second.put("8", 7);
        second.put("9", 8);
        second.put("10", 9);
    }

    protected Integer getFirstCoor() { return firstCoor; }
    protected Integer getSecondCoor() { return secondCoor; }

    protected void convert(String turn)
    {
        if (first.containsKey(turn.charAt(0)))
            firstCoor = first.get(turn.charAt(0));

        if (second.containsKey(turn.charAt(1)))
            secondCoor = second.get(turn.charAt(1));
    }
}
