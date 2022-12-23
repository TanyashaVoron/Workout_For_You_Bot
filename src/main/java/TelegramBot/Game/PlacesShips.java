package TelegramBot.Game;

import java.util.ArrayList;
import java.util.Objects;

public class PlacesShips {

    //размещение корабля на поле
    public String placementOfTheShipOnTheField(Integer shipSize,String[][] field, int firstCoor, int secondCoor, String turn)
    {
        if(Objects.equals(turn, "1"))
        {
            if(secondCoor + shipSize > 10) {
                field[firstCoor][secondCoor] = "➖";
                return "goingOutOfBounds";
            }

            for(int i = 1; i < shipSize; i++)
                if(!Objects.equals(field[firstCoor][secondCoor + i], "➖"))
                    return "shipError";


            for(int i = 0; i < shipSize; i++)
            {
                field[firstCoor][secondCoor + i] = "🚢";
                fillingFieldsAroundTheShip(firstCoor, secondCoor+i, field);
            }
        }

        if(Objects.equals(turn, "0"))
        {
            if(firstCoor + shipSize > 10)
            {
                field[firstCoor][secondCoor] = "➖";
                return "goingOutOfBounds";
            }

            for(int i = 1; i < shipSize; i++)
                if(!Objects.equals(field[firstCoor+i][secondCoor], "➖"))
                    return "shipError";


            for(int i = 0; i < shipSize; i++)
            {
                field[firstCoor+i][secondCoor] = "🚢";
                fillingFieldsAroundTheShip(firstCoor+i, secondCoor, field);
            }
        }
        return "🥴";
    }

    //заполнение полей (⚓) вокруг корабля
    public void fillingFieldsAroundTheShip(int firstCoor, int secondCoor, String[][] field)
    {
        if(firstCoor + 1 < 10 && !Objects.equals(field[firstCoor + 1][secondCoor], "🚢"))
            field[firstCoor+1][secondCoor] = "⚓";

        if(firstCoor - 1 > -1 && !Objects.equals(field[firstCoor - 1][secondCoor], "🚢"))
            field[firstCoor-1][secondCoor] = "⚓";

        if(secondCoor + 1 < 10 && !Objects.equals(field[firstCoor][secondCoor + 1], "🚢"))
            field[firstCoor][secondCoor + 1] = "⚓";

        if(secondCoor - 1 > -1 && !Objects.equals(field[firstCoor][secondCoor - 1], "🚢"))
            field[firstCoor][secondCoor - 1] = "⚓";

        if(firstCoor - 1 > -1)
        {
            if (secondCoor - 1 > -1 && !Objects.equals(field[firstCoor - 1][secondCoor - 1], "🚢"))
                field[firstCoor - 1][secondCoor - 1] = "⚓";

            if (secondCoor + 1 < 10 && !Objects.equals(field[firstCoor - 1][secondCoor + 1], "🚢"))
                field[firstCoor - 1][secondCoor + 1] = "⚓";
        }

        if(firstCoor + 1 < 10)
        {
            if (secondCoor - 1 > -1 && !Objects.equals(field[firstCoor + 1][secondCoor - 1], "🚢"))
                field[firstCoor + 1][secondCoor - 1] = "⚓";

            if (secondCoor + 1 < 10 && !Objects.equals(field[firstCoor + 1][secondCoor + 1], "🚢"))
                field[firstCoor + 1][secondCoor + 1] = "⚓";
        }
    }

    protected String vector(String[][] field, ArrayList<String> availableCommands, Integer firstCoor, Integer secondCoor)
    {
        field[firstCoor][secondCoor] = "🚢";
        String text = convertFieldToString(field);
        availableCommands.clear();
        availableCommands.add("0");
        availableCommands.add("1");
        return text;
    }

    public void updateAvailableCommand(String[][] field, ArrayList<String> availableCommand)
    {
        //System.out.println(field);
        availableCommand.clear();
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
                if (Objects.equals(field[i][j], "➖"))
                    availableCommand.add(i + "" + j + "");
            }
    }

    protected String convertFieldToString(String[][] fieldInput)
    {
        StringBuilder text = new StringBuilder("        " + "0️⃣ 1️⃣ 2️⃣ 3️⃣ 4️⃣ 5️⃣ 6️⃣ 7️⃣ 8️⃣ 9️⃣\n");

        for(int i=0;i<10;i++)
        {
            text.append(i + "️⃣ ");
            for(int j=0;j<10;j++)
                text.append(" ").append(fieldInput[i][j]);
            text.append("\n");
        }
        return text.toString();
    }
}
