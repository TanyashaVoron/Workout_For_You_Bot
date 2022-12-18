package TelegramBot.Game;

import java.util.ArrayList;
import java.util.Objects;

public class PlacesShips {

    //размещение корабля на поле
    protected String placementOfTheShipOnTheField(Integer shipSize,String[][] field, int firstCoor, int secondCoor, String turn )
    {
        if(Objects.equals(turn, "1"))
        {
            if(secondCoor + shipSize > 10) {
                field[firstCoor][secondCoor] = "_";
                return "goingOutOfBounds";
            }

            for(int i = 1; i < shipSize; i++)
                if(!Objects.equals(field[firstCoor][secondCoor + i], "_"))
                    return "shipError";

            for(int i = 0; i < shipSize; i++)
            {
                field[firstCoor][secondCoor + i] = "x";
                fillingFieldsAroundTheShip(firstCoor, secondCoor+i, field);
            }
        }

        if(Objects.equals(turn, "0"))
        {
            if(firstCoor + shipSize > 10)
            {
                field[firstCoor][secondCoor] = "_";
                return "goingOutOfBounds";
            }

            for(int i = 1; i < shipSize; i++)
                if(!Objects.equals(field[firstCoor+i][secondCoor], "_"))
                    return "shipError";

            for(int i = 0; i < shipSize; i++)
            {
                field[firstCoor+i][secondCoor] = "x";
                fillingFieldsAroundTheShip(firstCoor+i, secondCoor, field);
            }
        }
        return "*";
    }

    //заполнение полей (o) вокруг корабля
    protected void fillingFieldsAroundTheShip(int firstCoor, int secondCoor, String[][] field)
    {
        if(firstCoor + 1 < 10 && !Objects.equals(field[firstCoor + 1][secondCoor], "x"))
            field[firstCoor+1][secondCoor] = "o";

        if(firstCoor - 1 > -1 && !Objects.equals(field[firstCoor - 1][secondCoor], "x"))
            field[firstCoor-1][secondCoor] = "o";

        if(secondCoor + 1 < 10 && !Objects.equals(field[firstCoor][secondCoor + 1], "x"))
            field[firstCoor][secondCoor + 1] = "o";

        if(secondCoor - 1 > -1 && !Objects.equals(field[firstCoor][secondCoor - 1], "x"))
            field[firstCoor][secondCoor - 1] = "o";

        if(firstCoor - 1 > -1)
        {
            if (secondCoor - 1 > -1 && !Objects.equals(field[firstCoor - 1][secondCoor - 1], "x"))
                field[firstCoor - 1][secondCoor - 1] = "o";

            if (secondCoor + 1 < 10 && !Objects.equals(field[firstCoor - 1][secondCoor + 1], "x"))
                field[firstCoor - 1][secondCoor + 1] = "o";
        }

        if(firstCoor + 1 < 10)
        {
            if (secondCoor - 1 > -1 && !Objects.equals(field[firstCoor + 1][secondCoor - 1], "x"))
                field[firstCoor + 1][secondCoor - 1] = "o";

            if (secondCoor + 1 < 10 && !Objects.equals(field[firstCoor + 1][secondCoor + 1], "x"))
                field[firstCoor + 1][secondCoor + 1] = "o";
        }
    }

    protected String vector(String[][] field, ArrayList<String> availableCommands, Integer firstCoor, Integer secondCoor)
    {
        field[firstCoor][secondCoor] = "x";
        String text = convertFieldToString(field);
        availableCommands.clear();
        availableCommands.add("0");
        availableCommands.add("1");
        return text;
    }

    protected void updateavailableCommand(String[][] field, ArrayList<String> availableCommand)
    {
        availableCommand.clear();
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                if(Objects.equals(field[i][j], "_"))
                    availableCommand.add(i+""+j+"");
    }

    protected String convertFieldToString(String[][] fieldInput)
    {
        StringBuilder text = new StringBuilder(" * 0 1 2 3 4 5 6 7 8 9\n");
        for(int i=0;i<10;i++)
        {
            text.append(i);
            for(int j=0;j<10;j++)
                text.append(" ").append(fieldInput[i][j]);
            text.append("\n");
        }
        return text.toString();
    }
}
