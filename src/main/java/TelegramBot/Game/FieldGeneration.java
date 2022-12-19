package TelegramBot.Game;
import TelegramBot.TextOutput;

import java.util.ArrayList;
import java.util.Objects;

public class FieldGeneration {
    public String turn;
    public String text="";
    public String[][] field = new String[10][10];
    public ArrayList<String> availableCommands = new ArrayList<>();
    public Integer shipNumber;
    public Boolean vectorShip;

    private String error;

    public Boolean flagPlayerPlacesShips;

    PlacesShips placesShips;
    TextOutput textOutput;
    ConvertTurn convertTurn;

    FieldGeneration() {
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

    public String getAvailableCommands() { return placesShips.convertFieldToString(field); }

    private void vectorShips()
    {
        vectorShip = false;
        shipNumber++;
        text = placesShips.vector(field, availableCommands, convertTurn.getfirstCoor(),convertTurn.getSecondCoor())+textOutput.getText("vector");
    }

    private void shipEnd()
    {
        field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "x";
        placesShips.fillingFieldsAroundTheShip(convertTurn.getfirstCoor(),convertTurn.getSecondCoor(),field);
        placesShips.updateavailableCommand(field,availableCommands);
        shipNumber++;
    }

    private void ship1()
    {
        text += textOutput.getText("ship"+shipNumber.toString());
        text += textOutput.getText("inputRule");
        field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "x";
        placesShips.fillingFieldsAroundTheShip(convertTurn.getfirstCoor(),convertTurn.getSecondCoor(),field);
        placesShips.updateavailableCommand(field,availableCommands);
        text += placesShips.convertFieldToString(field);
        shipNumber++;
    }

    private void ship234()
    {
        text += textOutput.getText("ship"+shipNumber.toString());
        text += textOutput.getText("inputRule");

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
            text = textOutput.getText(error) + "\n";
            return;
        }

        placesShips.updateavailableCommand(field,availableCommands);
        text += placesShips.convertFieldToString(field);
        if(shipNumber == 6) shipNumber++;
        else vectorShip = true;
    }

    private void ship0()
    {
        placesShips.updateavailableCommand(field, availableCommands);
        vectorShip = true;
        text = textOutput.getText("ship0")+textOutput.getText("inputRule")+placesShips.convertFieldToString(field);
    }
    protected void placesShips()
    {
        text = "";

        if (turn.length() == 2)
            convertTurn.convert(turn);

        if (vectorShip)
        {
            vectorShips();
            return;
        }
        else
        {
            if (shipNumber == 0)
            {
               ship0();
               return;
            }

            if (shipNumber == 1)
            {
                ship234();
                return;
            }

            if (shipNumber >= 2 && shipNumber <= 6)
            {
                ship234();
                if (!Objects.equals(error, "*"))
                {
                    error = "*";
                    ship234();
                    return;
                }
                return;
            }
        }

        if (shipNumber >= 7 && shipNumber <= 9)
        {
            ship1();
            return;
        }

        if (shipNumber == 10)
        {
            shipEnd();
            flagPlayerPlacesShips = false;
        }
    }
}
