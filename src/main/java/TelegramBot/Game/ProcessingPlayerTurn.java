package TelegramBot.Game;

import TelegramBot.TextOutput;

import java.util.ArrayList;
import java.util.Objects;

public class ProcessingPlayerTurn {
    private String turn;
    private String text;
    private final String[][] field;
    private final String[][] fieldPattern;
    private final ArrayList<String> availableCommands;
    private final TextOutput textOutput;
    private final ConvertTurn convertTurn;
    private Boolean flagStartGame;
    private Boolean flagRepeatTurn;
    private final PlacesShips placesShips;
    public ProcessingPlayerTurn()
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
                field[i][j] = "➖";
    }
    public void setTurn(String t){ turn = t; }
    public String getTurn(){ return turn; }
    public void setText(String t){ text = t; }
    public String getText(){ return text; }
    public String[][] getFieldPattern(){ return fieldPattern; }
    public String[][] getField(){ return field; }
    public ArrayList<String> getAvailableCommands(){ return availableCommands; }
    public void clearAvailableCommands(){
        availableCommands.clear();
    }

    public void setFlagStartGame(Boolean flag){ flagStartGame = flag; }
    public Boolean getFlagStartGame(){ return flagStartGame; }
    public Boolean getFlagRepeatTurn(){ return flagRepeatTurn; }

    public void createPatternField(String[][] field)
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                if (Objects.equals(field[i][j], "🚢")) fieldPattern[i][j] = "🚢";
                else fieldPattern[i][j] = "➖";
    }

    public Boolean winner()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                if (Objects.equals(fieldPattern[i][j], "🚢")) return false;
        return true;
    }

    public void makeMovePlayer()
    {
        if (turn.length() == 2)
            convertTurn.convert(turn);

        if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "➖"))
        {
            text=textOutput.getText("➖");
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🗯";
            placesShips.updateAvailableCommand(field,availableCommands);
            text+=placesShips.convertFieldToString(field);
            flagRepeatTurn = false;
        }
        else if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "🚢"))
        {
            if(winner())
            {
                text=textOutput.getText("win");
                flagStartGame = false;
                return;
            }
            text=textOutput.getText("🚢");
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🥴";
            fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🥴";
            placesShips.updateAvailableCommand(field,availableCommands);
            text+=placesShips.convertFieldToString(field);
            text+=textOutput.getText("inputRule");
            flagRepeatTurn = true;
        }
    }

    public void firstMessage()
    {
        text = "";
        text+=textOutput.getText("firstMessage")+textOutput.getText("inputRule");
        placesShips.updateAvailableCommand(field,availableCommands);
        text+=placesShips.convertFieldToString(field);
    }

    public void makeMovePlayerBot()
    {
        if (turn.length() == 2)
            convertTurn.convert(turn);

        if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "➖"))
        {
            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🗯";
            placesShips.updateAvailableCommand(field,availableCommands);
            text+=placesShips.convertFieldToString(field);
            text+=textOutput.getText("inputRule");
            flagRepeatTurn = false;
        }
        else if(Objects.equals(fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()], "🚢"))
        {
            if(winner())
            {
                text=textOutput.getText("win");
                flagStartGame = false;
                return;
            }

            field[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🥴";
            fieldPattern[convertTurn.getfirstCoor()][convertTurn.getSecondCoor()] = "🥴";
            placesShips.updateAvailableCommand(field,availableCommands);
            flagRepeatTurn = true;
        }
    }
}
