package TelegramBot.Game;

import TelegramBot.TextOutput;

public class Game {
    public FieldGeneration player;
    private final FieldGeneration playerBot;
    private boolean flagStartGame;
    TextOutput textOutput;
    RandomGeneratorTurn randomGeneratorTurn;

    public Game()
    {
        player = new FieldGeneration();
        playerBot = new FieldGeneration();

        flagStartGame = false;

        textOutput = new TextOutput();
        randomGeneratorTurn = new RandomGeneratorTurn();
    }

    public void setFlagGame() { flagStartGame = true; }
    public boolean getFlagGame() { return flagStartGame; }
    public void setPlayerTurn(String turn) { player.turn = turn; }

    public String logics()
    {
        while (playerBot.flagPlayerPlacesShips && playerBot.shipNumber <= 10)
        {
            playerBot.turn = randomGeneratorTurn.randomGeneratorTurn(playerBot.availableCommands);
            System.out.println(playerBot.turn);
            playerBot.placesShips();
        }
        System.out.println(playerBot.getAvailableCommands());

        if (player.flagPlayerPlacesShips)
        {
            if (player.shipNumber < 10)
            {
                player.placesShips();
                return player.text;
            }
            player.placesShips();
            return "Поле бота\n" + playerBot.getAvailableCommands() + "\nПоле игрока\n" + player.getAvailableCommands();
        }
        return "*";
    }
}

/*
00 0
02 0
04 0
06 0
08 0
90 1
70
50
99
77
 */