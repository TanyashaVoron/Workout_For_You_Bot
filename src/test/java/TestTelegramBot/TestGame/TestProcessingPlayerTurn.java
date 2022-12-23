package TestTelegramBot.TestGame;

import TelegramBot.Game.ProcessingPlayerTurn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class TestProcessingPlayerTurn {

    private ProcessingPlayerTurn player;

    protected String convertToString(String[][] field) {
        String str = "";
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                str = str + ("" + field[i][j] + " ");
        return str;
    }

    @Test
    void testCreatePatternField() {

        player = new ProcessingPlayerTurn();

        String[][] field = new String[10][10];


        for (int bias_width = 0; bias_width < 10; bias_width++) {
            for (int bias_length = 0; bias_length < 10; bias_length++) {
                //String pair = "" + bias_width + bias_length;

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (i == bias_length && j == bias_width)
                            field[i][j] = "🚢";
                        else
                            field[i][j] = "➖";
                    }
                }

                player.createPatternField(field);

                Assertions.assertEquals(convertToString(field), convertToString(player.getFieldPattern()));
            }
        }
    }

    @Test
    void testWinner() {
        player = new ProcessingPlayerTurn();

        String[][] field = new String[10][10];

        for (int bias_width = 0; bias_width < 10; bias_width++) {
            for (int bias_length = 0; bias_length < 10; bias_length++) {

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (i == bias_length && j == bias_width)
                            field[i][j] = "🚢";
                        else //Character.getNumericValue(
                            field[i][j] = "➖";
                    }
                }


                player.createPatternField(field);

                Assertions.assertEquals(false, player.winner());

            }
        }
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                field[i][j] = "➖";
        player.createPatternField(field);

        Assertions.assertEquals(true, player.winner());
    }

    @Test
    void testMakeMovePlayerAndBot() {
        String[][] field;

        for (int bias_width = 0; bias_width < 10; bias_width++) {
            for (int bias_length = 0; bias_length < 10; bias_length++) {
                player = new ProcessingPlayerTurn();

                field = new String[10][10];

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        field[i][j] = "➖";
                    }
                }
                player.setTurn("" + bias_length + bias_width);

                player.createPatternField(field);
                field[bias_length][bias_width] = "🗯";
                player.makeMovePlayer();
                Assertions.assertEquals(convertToString(field), convertToString(player.getField()));
                field[bias_length][bias_width] = "➖";


                field[bias_length][bias_width] = "🚢";
                player.createPatternField(field);
                field[bias_length][bias_width] = "🥴";
                player.makeMovePlayer();
                Assertions.assertEquals(convertToString(field), convertToString(player.getField()));
                Assertions.assertEquals(convertToString(field), convertToString(player.getFieldPattern()));

                player.setTurn("" + bias_length + bias_width);

                player.createPatternField(field);
                field[bias_length][bias_width] = "🗯";
                player.makeMovePlayerBot();
                Assertions.assertEquals(convertToString(field), convertToString(player.getField()));
                field[bias_length][bias_width] = "➖";


                field[bias_length][bias_width] = "🚢";
                player.createPatternField(field);
                field[bias_length][bias_width] = "🥴";
                player.makeMovePlayerBot();
                Assertions.assertEquals(convertToString(field), convertToString(player.getField()));
                Assertions.assertEquals(convertToString(field), convertToString(player.getFieldPattern()));

            }
        }
    }

}