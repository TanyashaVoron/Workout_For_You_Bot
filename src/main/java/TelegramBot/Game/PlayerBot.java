package TelegramBot.Game;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerBot {
    public String turn;
    public String[][] field = new String[10][10];
    public ArrayList<String> availableCommands = new ArrayList<>();
    public Integer shipNumber;
    public Boolean vectorShip;

    private String error;

    private Integer firstCoor;
    private Integer secondCoor;

    PlacesShips placesShips;

    private Boolean flagError=false;

    public PlayerBot() {
        shipNumber = 0;
        vectorShip = false;
        placesShips = new PlacesShips();
        turn="*";

        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                field[i][j] = "_";

        placesShips.updateavailableCommand(field,availableCommands);
    }

    private Integer randomGenerator(Integer bound){ return ((int) ( Math.random() * 101 ))%bound; }

    private void vectorShips()
    {
        vectorShip = false;
        shipNumber++;
        field[firstCoor][secondCoor] = "x";
        turn=randomGenerator(2)+"";
    }

    private void ship1()
    {
        field[firstCoor][secondCoor] = "x";
        placesShips.updateavailableCommand(field,availableCommands);
        shipNumber++;
    }

    private void ship234()
    {
        int size;
        if(shipNumber == 1) size=4;
        else if(shipNumber < 4) size=3;
        else size=2;

        if (!Objects.equals(field[firstCoor][secondCoor], "_"))
            error = placesShips.placementOfTheShipOnTheField(size,field,firstCoor,secondCoor,turn);

        if (Objects.equals(error, "goingOutOfBounds") || Objects.equals(error, "shipError"))
        {
            shipNumber--;
            field[firstCoor][secondCoor] = "_";
            return;
        }

        placesShips.updateavailableCommand(field,availableCommands);
        if(shipNumber == 6) shipNumber++;
        else vectorShip = true;
    }

    private void ship0()
    {
        placesShips.updateavailableCommand(field, availableCommands);
        vectorShip = true;
    }

    private void generationTurn()
    {
        do{
            firstCoor=randomGenerator(10);
            secondCoor=randomGenerator(10);
        } while(!availableCommands.contains(firstCoor.toString()+secondCoor.toString()));
    }

    public void placesShips()
    {
        //System.out.println(placesShips.convertFieldToString(field));
        //System.out.println(availableCommands);
        generationTurn();

        while(shipNumber<10)
        {
            if(vectorShip) vectorShips();
            else
            {
                if (shipNumber == 0) ship0();

                else if (shipNumber == 1) ship234();

                else if (shipNumber >= 2 && shipNumber <= 6)
                {
                    ship234();
                    if (!Objects.equals(error, "*"))
                    {
                        generationTurn();
                        flagError=true;
                        error = "*";
                        ship234();
                    }
                }
            }

            if (shipNumber >= 7 && shipNumber <= 10)
                ship1();

            if (vectorShip) System.out.println("новый ход!________________");
            System.out.println(turn);
            System.out.println(shipNumber);
            System.out.println(placesShips.convertFieldToString(field));
            System.out.println(availableCommands);
            generationTurn();

        }

        System.out.println(placesShips.convertFieldToString(field));
    }
}

