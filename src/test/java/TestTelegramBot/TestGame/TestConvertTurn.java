package TestTelegramBot.TestGame;

import TelegramBot.Game.ConvertTurn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestConvertTurn {

    ConvertTurn conv;
    @Test
    void testConvert() {
        conv = new ConvertTurn();
        String a, b;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                conv.convert("" + i + j);

                Assertions.assertEquals("" + i + j, "" + conv.getfirstCoor() + conv.getSecondCoor());
            }
        }
    }
}
