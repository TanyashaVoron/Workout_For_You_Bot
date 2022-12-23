package TestTelegramBot.TestGame;

import TelegramBot.Game.PlacesShips;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Objects;

public class TestPlacesShips {

    PlacesShips ships;
    protected String convertToString(String[][] field) {
        String str = "";
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                str = str + ("" + field[i][j] + " ");
        return str;
    }

    @Test
    void testPlacementOfTheShipOnTheField() {
        ships = new PlacesShips();
        String[][] field = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = "➖";
                field[i][j+5] = "🚢";
            }
        }
        for (int shipSize = 1; shipSize < 5; shipSize++) {
            for (int turn = 0; turn < 2; turn++)
                Assertions.assertEquals("goingOutOfBounds", ships.placementOfTheShipOnTheField(shipSize+1, field, 10 - shipSize, 10 - shipSize, Integer.toString(turn)));


            for (int firstCoor = 0; firstCoor < 5; firstCoor++)
                for (int secondCoor = 0; secondCoor < 5; secondCoor++) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 5; j++) {
                            field[i][j] = "➖";
                            field[i][j + 5] = "🚢";
                        }
                    }
                    Assertions.assertEquals("🥴", ships.placementOfTheShipOnTheField(shipSize, field, firstCoor, secondCoor, "0"));
                    for (int i = 0; i < 10; i++)
                        for (int j = 0; j < 10; j++)
                            field[i][j] = "🚢";

                    for (int turn = 0; turn < 2; turn++)
                        Assertions.assertEquals("shipError", ships.placementOfTheShipOnTheField(shipSize+1, field, firstCoor, secondCoor, Integer.toString(turn)));

                    for (int i = 0; i < 10; i++)
                        for (int j = 0; j < 5; j++)
                            field[j][i] = "➖";

                    Assertions.assertEquals("🥴", ships.placementOfTheShipOnTheField(shipSize, field, firstCoor, secondCoor, "1"));

                }
        }
    }


    /*@Test
    void testFillingFieldsAroundTheShip() {
        PlacesShips places = new PlacesShips();
        String[][] clearField = new String[10][10];
        String[][] resultField = new String[10][10];

        for (int ship = 1; ship < 5; ship++) {
            for (int bias = 1; bias < 7; bias++) {


                for (int first = 0; first < 10; first++) {
                    for (int second = 0; second < 10; second++) {
                        clearField[first][second] = "➖";
                        resultField[first][second] = "➖";
                    }
                }
                while(ship > 0) {
                    clearField[bias+ship][bias] = "🚢";
                    resultField[bias+ship][bias] = "🚢";
                    ship -= 1;
                }
                for (int first = 1; first < 9; first++)
                    for (int second = 1; second < 9; second++) {
                        if (!Objects.equals(resultField[first][second], "🚢")) {
                            Boolean a = Objects.equals(resultField[first + 1][second], "🚢") || (Objects.equals(resultField[first][second + 1], "🚢"));
                            Boolean b = Objects.equals(resultField[first - 1][second], "🚢") || (Objects.equals(resultField[first][second - 1], "🚢"));
                            Boolean c = Objects.equals(resultField[first + 1][second + 1], "🚢") || (Objects.equals(resultField[first - 1][second - 1], "🚢"));
                            Boolean d = Objects.equals(resultField[first + 1][second - 1], "🚢") || (Objects.equals(resultField[first - 1][second + 1], "🚢"));
                            if ((a || b) || (c || d))
                                resultField[first][second] = "⚓";
                        }
                    }

                places.fillingFieldsAroundTheShip(bias, bias, clearField);
                Assertions.assertEquals(convertToString(resultField), convertToString(clearField));

            }
        }
    }*/

    @Test
    void testUpdateAvailableCommand() {
        String[][] field = new String[10][10];
        ArrayList<String> availableCommand = new ArrayList<>();
        ArrayList<String> availableCommandResult = new ArrayList<>();
        ships = new PlacesShips();

        for (int bias = 1; bias < 10; bias++) {
            availableCommand.clear();
            availableCommandResult.clear();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {
                    field[i][j] = "➖";
                    availableCommandResult.add("" + i + j);
                    field[i][9 - j % bias] = "🚢";
                }
            }
            ships.updateAvailableCommand(field, availableCommand);

            Assertions.assertEquals(availableCommandResult, availableCommand);
        }
    }

}
