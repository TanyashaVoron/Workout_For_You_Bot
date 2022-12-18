package TelegramBot.Game;

import TelegramBot.TextOutput;

public class Game {
    public Player player;
    private PlayerBot playerBot;

    private PlacesShips placesShips;
    private boolean flagStartGame;

    TextOutput textOutput;
    ConvertTurn convertTurn;

    public Game()
    {
        player = new Player();
        playerBot = new PlayerBot();

        placesShips = new PlacesShips();
        flagStartGame = false;

        textOutput = new TextOutput();
        convertTurn = new ConvertTurn();
    }

    public void setFlagGame() { flagStartGame = true; }
    public boolean getFlagGame() { return flagStartGame; }
    public void setPlayerTurn(String turn) { player.turn = turn; }

    public String logics()
    {
        if(player.flagPlayerPlacesShips)
            return player.placesShips();
        return "----";
    }
}