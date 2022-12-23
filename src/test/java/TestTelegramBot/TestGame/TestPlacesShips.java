package TestTelegramBot.TestGame;

import TelegramBot.Game.PlacesShips;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
