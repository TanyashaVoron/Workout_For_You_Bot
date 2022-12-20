package TelegramBot.Game;

import TelegramBot.TextOutput;

public class Game {
    public FieldGeneration fieldGenerationPlayer;
    private final FieldGeneration fieldGenerationPlayerBot;
    public ProcessingPlayerTurn processingTurnPlayer;
    public ProcessingPlayerTurn processingTurnPlayerBot;
    private boolean flagStartGame;
    TextOutput textOutput;
    RandomGeneratorTurn randomGeneratorTurn;

    public Game()
    {
        fieldGenerationPlayer = new FieldGeneration();
        fieldGenerationPlayerBot = new FieldGeneration();
        processingTurnPlayer = new ProcessingPlayerTurn();
        processingTurnPlayerBot = new ProcessingPlayerTurn();

        flagStartGame = false;

        textOutput = new TextOutput();
        randomGeneratorTurn = new RandomGeneratorTurn();
    }

    public void setFlagGame() { flagStartGame = true; }
    public boolean getFlagGame() { return flagStartGame; }

    public String logics(String turn)
    {
        while (fieldGenerationPlayerBot.flagPlayerPlacesShips && fieldGenerationPlayerBot.shipNumber <= 10)
        {
            fieldGenerationPlayerBot.turn = randomGeneratorTurn.randomGeneratorTurn(fieldGenerationPlayerBot.availableCommands);
            System.out.println(fieldGenerationPlayerBot.turn);
            fieldGenerationPlayerBot.placesShips();
        }

        if (fieldGenerationPlayer.flagPlayerPlacesShips)
        {
            fieldGenerationPlayer.turn = turn;
            if (fieldGenerationPlayer.shipNumber < 10)
            {
                fieldGenerationPlayer.placesShips();
                return fieldGenerationPlayer.text;
            }
            fieldGenerationPlayer.placesShips();

            processingTurnPlayer.flagStartGame = true;
            processingTurnPlayerBot.flagStartGame = true;

            processingTurnPlayer.createPatternField(fieldGenerationPlayerBot.field);
            processingTurnPlayerBot.createPatternField(fieldGenerationPlayer.field);

            processingTurnPlayer.firstMessage();
            processingTurnPlayerBot.firstMessage();
            processingTurnPlayerBot.text = "";
            return processingTurnPlayer.text;
            //return "Поле бота\n" + fieldGenerationPlayerBot.getAvailableCommands() + "\nПоле игрока\n" + fieldGenerationPlayer.getAvailableCommands();
        }

        if(processingTurnPlayer.flagStartGame && processingTurnPlayerBot.flagStartGame)
        {
            processingTurnPlayer.turn = turn;
            processingTurnPlayer.makeMovePlayer();
            processingTurnPlayer.text="\nВаш ход (" + processingTurnPlayer.turn + ")\n"+processingTurnPlayer.text;

            processingTurnPlayerBot.text = "";

            if(processingTurnPlayer.flagRepeatTurn)
                return processingTurnPlayer.text;

            do {
                processingTurnPlayerBot.turn = randomGeneratorTurn.randomGeneratorTurnGame(processingTurnPlayerBot.availableCommands);
                processingTurnPlayerBot.makeMovePlayerBot();
                processingTurnPlayerBot.text="\nХод бота (" + processingTurnPlayerBot.turn + ")\n"+processingTurnPlayerBot.text;

            } while (processingTurnPlayerBot.flagRepeatTurn);


            return processingTurnPlayer.text+"\n"+processingTurnPlayerBot.text;
        }

        flagStartGame = false;
        return "игра завершена!";
    }

    public void clear()
    {
        fieldGenerationPlayer.availableCommands.clear();
        fieldGenerationPlayer.text="";

        fieldGenerationPlayerBot.availableCommands.clear();
        fieldGenerationPlayerBot.text="";

        processingTurnPlayer.availableCommands.clear();
        processingTurnPlayer.text="";

        processingTurnPlayerBot.availableCommands.clear();
        processingTurnPlayerBot.text="";
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