package TelegramBot.Game;
import TelegramBot.TextOutput;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    public String turn;
    public String[][] field = new String[10][10];
    public ArrayList<String> availableCommands = new ArrayList<>();
    public Integer shipNumber;
    public Boolean vectorShip;

    private String error;

    public Boolean flagPlayerPlacesShips;

    PlacesShips placesShips;
    TextOutput textOutput;
    ConvertTurn convertTurn;

    Player() {
        shipNumber = 0;
        vectorShip = false;
        flagPlayerPlacesShips = true;
        placesShips = new PlacesShips();
        textOutput = new TextOutput();
        convertTurn = new ConvertTurn();

        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                field[i][j] = "_";
    }

    private String vectorShips()
    {
        vectorShip = false;
        shipNumber++;
        return placesShips.vector(field, availableCommands, convertTurn.getfirstCoor(),convertTurn.getSecondCoor())+textOutput.getText("vector");
    }

    private String ship1()
    {
        String text = textOutput.getText("ship"+shipNumber.toString());
        text += textOutput.getText("inputRule");
        field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "x";
        placesShips.updateavailableCommand(field,availableCommands);
        text += placesShips.convertFieldToString(field);
        shipNumber++;
        return text;
    }

    private String ship234()
    {
        String text = textOutput.getText("ship"+shipNumber.toString());
        text+= textOutput.getText("inputRule");

        int size;
        if(shipNumber == 1) size=4;
        else if(shipNumber < 4) size=3;
        else size=2;

        if (!Objects.equals(field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "_"))
            error = placesShips.placementOfTheShipOnTheField(size,field,convertTurn.getfirstCoor(),convertTurn.getSecondCoor(),turn);

        if (Objects.equals(error, "goingOutOfBounds") || Objects.equals(error, "shipError"))
        {
            shipNumber--;
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "_";
            return textOutput.getText(error) + "\n";
        }

        placesShips.updateavailableCommand(field,availableCommands);
        text += placesShips.convertFieldToString(field);
        if(shipNumber == 6) shipNumber++;
        else vectorShip = true;
        return text;
    }

    private String ship0()
    {
        placesShips.updateavailableCommand(field, availableCommands);
        vectorShip = true;
        return textOutput.getText("ship0")+textOutput.getText("inputRule")+placesShips.convertFieldToString(field);
    }
    protected String placesShips() {
        String text = "";

        if (turn.length() == 2)
            convertTurn.convert(turn);

        if (vectorShip) return vectorShips();
        else {
            if (shipNumber == 0) return ship0();

            if (shipNumber == 1) return ship234();

            if (shipNumber >= 2 && shipNumber <= 6) {
                text = ship234();
                if (!Objects.equals(error, "*")) {
                    error = "*";
                    return text + ship234();
                }
                return text;
            }
        }

        if (shipNumber >= 7 && shipNumber <= 10)
            return ship1();


        flagPlayerPlacesShips = false;
        availableCommands.add("погнали");
        return "Начинаем играть!";
    }
}
