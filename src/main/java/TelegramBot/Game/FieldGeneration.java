package TelegramBot.Game;
import TelegramBot.TextOutput;

import java.util.ArrayList;
import java.util.Objects;

public class FieldGeneration
{
    private String turn;
    private String text;
    private final String[][] field;
    private final ArrayList<String> availableCommands;
    private final TextOutput textOutput;
    private final ConvertTurn convertTurn;

    private Integer shipNumber;
    private Boolean vectorShip;
    private String error;
    private Boolean flagPlayerPlacesShips;
    private final PlacesShips placesShips;

    FieldGeneration()
    {
        text = "";
        field = new String[10][10];
        availableCommands = new ArrayList<>();
        shipNumber = 0;
        vectorShip = false;
        flagPlayerPlacesShips = true;
        placesShips = new PlacesShips();
        textOutput = new TextOutput();
        convertTurn = new ConvertTurn();

        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                field[i][j] = "➖";
    }

    public void setTurn(String str){
        turn = str;
    }
    public String getTurn(){ return turn; }

    public void setText(String str){
        text = str;
    }
    public String getText(){ return text; }

    public String[][] getField(){ return field; }

    public void clearAvailableCommands(){
        availableCommands.clear();
    }
    public ArrayList<String>  getAvailableCommands(){ return availableCommands; }

    public Integer getShipNumber(){ return shipNumber; }

    public Boolean getFlagPlayerPlacesShips(){ return flagPlayerPlacesShips; }

    private void vectorShips()
    {
        vectorShip = false;
        shipNumber++;
        text = textOutput.getText("vector1") + placesShips.vector(field, availableCommands, convertTurn.getfirstCoor(),convertTurn.getSecondCoor())+textOutput.getText("vector2");
    }

    private void shipEnd()
    {
        field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🚢";
        placesShips.fillingFieldsAroundTheShip(convertTurn.getfirstCoor(),convertTurn.getSecondCoor(),field);
        placesShips.updateAvailableCommand(field,availableCommands);
        shipNumber++;
    }

    private void ship1()
    {
        text += textOutput.getText("ship"+shipNumber.toString());
        text += textOutput.getText("inputRule");
        field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🚢";
        placesShips.fillingFieldsAroundTheShip(convertTurn.getfirstCoor(),convertTurn.getSecondCoor(),field);
        placesShips.updateAvailableCommand(field,availableCommands);
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

        if (!Objects.equals(field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "➖"))
            error = placesShips.placementOfTheShipOnTheField(size,field,convertTurn.getfirstCoor(),convertTurn.getSecondCoor(),turn);

        if (Objects.equals(error, "goingOutOfBounds") || Objects.equals(error, "shipError"))
        {
            shipNumber--;
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "➖";
            text = textOutput.getText(error) + "\n";
            return;
        }

        placesShips.updateAvailableCommand(field,availableCommands);
        text += placesShips.convertFieldToString(field);
        if(shipNumber == 6) shipNumber++;
        else vectorShip = true;
    }

    private void ship0()
    {
        placesShips.updateAvailableCommand(field, availableCommands);
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
                if (!Objects.equals(error, "🥴"))
                {
                    error = "🥴";
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
