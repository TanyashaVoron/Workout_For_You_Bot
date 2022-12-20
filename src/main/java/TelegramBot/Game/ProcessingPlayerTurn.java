package TelegramBot.Game;

import TelegramBot.TextOutput;

import java.util.ArrayList;
import java.util.Objects;

public class ProcessingPlayerTurn {
    public String turn;
    public String text;
    public String[][] field;
    public String[][] fieldPattern;
    public ArrayList<String> availableCommands;
    TextOutput textOutput;
    ConvertTurn convertTurn;
    public Boolean flagStartGame;

    public Boolean flagRepeatTurn;
    PlacesShips placesShips;
    ProcessingPlayerTurn()
    {
        text = "";
        field = new String[10][10];
        fieldPattern = new String[10][10];
        availableCommands = new ArrayList<>();
        textOutput = new TextOutput();
        convertTurn = new ConvertTurn();
        flagStartGame = false;
        placesShips = new PlacesShips();
        flagRepeatTurn = false;

        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                field[i][j] = "_";
    }

    public void createPatternField(String[][] field)
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                if (Objects.equals(field[i][j], "x")) fieldPattern[i][j] = "x";
                else fieldPattern[i][j] = "_";
    }

    private Boolean winner()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                if (Objects.equals(fieldPattern[i][j], "x")) return false;
        return true;
    }

    public void makeMovePlayer()
    {
        if (turn.length() == 2)
            convertTurn.convert(turn);

        if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "_"))
        {
            text=textOutput.getText("_");
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "#";
            placesShips.updateavailableCommand(field,availableCommands);
            text+=placesShips.convertFieldToString(field);
            flagRepeatTurn = false;
        }
        else if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "x"))
        {
            if(winner())
            {
                text=textOutput.getText("win");
                flagStartGame = false;
                return;
            }
            text=textOutput.getText("x");
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "*";
            fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "*";
            placesShips.updateavailableCommand(field,availableCommands);
            text+=placesShips.convertFieldToString(field);
            text+=textOutput.getText("inputRule");
            flagRepeatTurn = true;
        }
    }

    public void firstMessage()
    {
        text = "";
        text+=textOutput.getText("firstMessage")+textOutput.getText("inputRule");
        placesShips.updateavailableCommand(field,availableCommands);
        text+=placesShips.convertFieldToString(field);
    }

    public void makeMovePlayerBot()
    {
        if (turn.length() == 2)
            convertTurn.convert(turn);

        if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "_"))
        {
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "#";
            placesShips.updateavailableCommand(field,availableCommands);
            text+=placesShips.convertFieldToString(field);
            text+=textOutput.getText("inputRule");
            flagRepeatTurn = false;
        }
        else if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "x"))
        {
            if(winner())
            {
                text=textOutput.getText("win");
                flagStartGame = false;
                return;
            }

            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "*";
            fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "*";
            placesShips.updateavailableCommand(field,availableCommands);
            flagRepeatTurn = true;
        }
    }
}
