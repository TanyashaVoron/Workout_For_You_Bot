package TelegramBot.Game;

import java.util.HashMap;

public class ConvertTurn
{
    private Integer firstCoor;
    private Integer secondCoor;
    private final HashMap<String,Integer> map;

    ConvertTurn()
    {
        map = new HashMap<>();
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
    }

    protected Integer getfirstCoor() { return firstCoor; }
    protected Integer getSecondCoor() { return secondCoor; }
    
    protected void convert(String playerTurn)
    {
        firstCoor = map.get(playerTurn.charAt(0)+"");
        secondCoor = map.get(playerTurn.charAt(1)+"");
    }
}
