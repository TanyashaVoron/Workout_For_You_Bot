package TelegramBot.Game;

import TelegramBot.TextOutput;

import java.util.ArrayList;

public class Game {
    private final FieldGeneration fieldGenerationPlayer;
    private final FieldGeneration fieldGenerationPlayerBot;
    private final ProcessingPlayerTurn processingTurnPlayer;
    public ProcessingPlayerTurn processingTurnPlayerBot;
    private boolean flagStartGame;
    private TextOutput textOutput;
    private final RandomGeneratorTurn randomGeneratorTurn;

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

    public Boolean getFieldFlagPlayerPlacesShips() { return fieldGenerationPlayer.getFlagPlayerPlacesShips(); }
    public ArrayList<String> getFieldAvailableCommands() { return fieldGenerationPlayer.getAvailableCommands(); }

    public Boolean getTurnPlayerFlagStartGame(){ return processingTurnPlayer.getFlagStartGame(); }
    public ArrayList<String> getProcessingTurnPlayerAvailable(){ return processingTurnPlayer.getAvailableCommands(); }

    public void setFlagGame() { flagStartGame = true; }
    public Boolean getFlagGame() { return flagStartGame; }


    public String logics(String turn)
    {
        while (fieldGenerationPlayerBot.getFlagPlayerPlacesShips() && fieldGenerationPlayerBot.getShipNumber() <= 10)
        {
            fieldGenerationPlayerBot.setTurn(randomGeneratorTurn.randomGeneratorTurn(fieldGenerationPlayerBot.getAvailableCommands()));
            System.out.println(fieldGenerationPlayerBot.getTurn());
            fieldGenerationPlayerBot.placesShips();
        }

        if (fieldGenerationPlayer.getFlagPlayerPlacesShips())
        {
            fieldGenerationPlayer.setTurn(turn);
            if (fieldGenerationPlayer.getShipNumber() < 10)
            {
                fieldGenerationPlayer.placesShips();
                return fieldGenerationPlayer.getText();
            }
            fieldGenerationPlayer.placesShips();

            processingTurnPlayer.setFlagStartGame(true);
            processingTurnPlayerBot.setFlagStartGame(true);

            processingTurnPlayer.createPatternField(fieldGenerationPlayerBot.getField());
            processingTurnPlayerBot.createPatternField(fieldGenerationPlayer.getField());

            processingTurnPlayer.firstMessage();
            processingTurnPlayerBot.firstMessage();
            processingTurnPlayerBot.setText("");
            return processingTurnPlayer.getText();
            //return "Поле бота\n" + fieldGenerationPlayerBot.getAvailableCommands() + "\nПоле игрока\n" + fieldGenerationPlayer.getAvailableCommands();
        }

        if(processingTurnPlayer.getFlagStartGame() && processingTurnPlayerBot.getFlagStartGame())
        {
            processingTurnPlayer.setTurn(turn);
            processingTurnPlayer.makeMovePlayer();
            processingTurnPlayer.setText("\nВаш ход (" + processingTurnPlayer.getTurn() + ")\n"+processingTurnPlayer.getText());

            processingTurnPlayerBot.setText("");

            if(processingTurnPlayer.getFlagRepeatTurn())
                return processingTurnPlayer.getText();

            do {
                processingTurnPlayerBot.setTurn(randomGeneratorTurn.randomGeneratorTurnGame(processingTurnPlayerBot.getAvailableCommands()));
                processingTurnPlayerBot.makeMovePlayerBot();
                processingTurnPlayerBot.setText("\nХод бота (" + processingTurnPlayerBot.getTurn() + ")\n"+processingTurnPlayerBot.getText());

            } while (processingTurnPlayerBot.getFlagRepeatTurn());


            return processingTurnPlayer.getText()+"\n"+processingTurnPlayerBot.getText();
        }

        flagStartGame = false;
        return "игра завершена!";
    }

    public void clear()
    {
        fieldGenerationPlayer.clearAvailableCommands();
        fieldGenerationPlayer.setText("");

        fieldGenerationPlayerBot.clearAvailableCommands();
        fieldGenerationPlayerBot.setText("");

        processingTurnPlayer.clearAvailableCommands();
        processingTurnPlayer.setText("");

        processingTurnPlayerBot.clearAvailableCommands();
        processingTurnPlayerBot.setText("");
    }
}