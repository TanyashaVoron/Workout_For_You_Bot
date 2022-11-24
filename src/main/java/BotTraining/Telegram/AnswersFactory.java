package BotTraining.Telegram;

import java.util.ArrayList;
import java.util.Objects;

public class AnswersFactory {

    protected TextOutput textOutput;

    //текущая команда, введенная данным пользователем
    protected String command;
    //0 - down, 1 - up
    private Boolean partOfBody;
    //массив комманд поступных далее пользователю
    protected ArrayList<String> availableCommands;
    //номер упражнения
    private Integer numberCommandEx;

    protected AnswersFactory(){
        textOutput = new TextOutput();
        command = "";
        availableCommands = new ArrayList<>();
        availableCommands.add("start");
        numberCommandEx = -1;
    }

    protected ArrayList<String> getResponse() {
        ArrayList<String> outputStrList = new ArrayList<>();

        if (!availableCommands.contains(command)){ // || outputStrList.get(0) == null){
            //outputStrList.clear();
            outputStrList.add(textOutput.getText("error"));
            return outputStrList;
        }

        if (Objects.equals(command, "next")) {
            if (partOfBody)
                command = "up";
            else
                command = "down";
            outputStrList.add(textOutput.getText(command));
        }

        outputStrList.add(textOutput.getText(command));

        availableCommands.clear();

        switch (command) {
            case "start" -> {
                availableCommands.add("info");
                availableCommands.add("go");
            }
            case "info" -> {
                availableCommands.add("exit");
                availableCommands.add("go");
            }
            case "go" -> {
                availableCommands.add("exit");
                availableCommands.add("down");
                availableCommands.add("up");
            }
            case "done" -> {
                availableCommands.add("exit");
                availableCommands.add("info");
                availableCommands.add("go");
            }
            case "exit" -> {
                availableCommands.add("start");
                command = "";
                numberCommandEx = -1;
            }
        }

        if (Objects.equals(command, "up") || Objects.equals(command, "down")) {

            outputStrList.clear();
            if (numberCommandEx == -1) {
                numberCommandEx = 0;
                partOfBody = (Objects.equals(command, "up"));
            } else
                numberCommandEx += 1;

            if (numberCommandEx < 5) {
                availableCommands.add("exit");
                availableCommands.add("next");
                outputStrList.add(textOutput.getText(command).split("\n")[numberCommandEx] + "\n" + textOutput.getText("time"));
                outputStrList.add(textOutput.getText("next"));
                return outputStrList;
            }

            availableCommands.add("done");
            outputStrList.add(textOutput.getText("lastEx") + "\n" + textOutput.getText(command).split("\n")[numberCommandEx]);
            numberCommandEx = -1;
        }
        return outputStrList;
    }
}