package TelegramBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AnswersFactory
{
    public boolean keyTest = false;
    protected TextOutput textOutput;
    //текущая команда, введенная данным пользователем
    protected String command;
    //массив команд доступных далее пользователю
    protected ArrayList<String> availableCommands;
    //0 - down, 1 - up
    private Boolean partOfBody;
    private Integer numberCommandEx;
    private final Parsing parsing;

    public AnswersFactory()
    {
        textOutput = new TextOutput();
        command = "";
        availableCommands = new ArrayList<>();
        availableCommands.add("start");
        numberCommandEx = -1;
        parsing = new Parsing();
    }

    public void setCommand(String com) { command = com; }

    public ArrayList<String> getAvailableCommands() { return availableCommands; }

    public Boolean getPartOfBody() { return partOfBody; }

    public Integer getNumberCommandEx() { return numberCommandEx; }

    public ArrayList<String> getResponse() throws IOException
    {
        ArrayList<String> outputStrList = new ArrayList<>();

        if (!availableCommands.contains(command))
        {
            outputStrList.add(textOutput.getText("error"));
            return outputStrList;
        }

        if (Objects.equals(command, "next"))
        {
            if (partOfBody) command = "up";
            else command = "down";
            outputStrList.add(textOutput.getText(command));
        }

        outputStrList.add(textOutput.getText(command));
        availableCommands.clear();

        switch (command)
        {
            case "start" ->
            {
                availableCommands.add("info");
                availableCommands.add("video");
                availableCommands.add("with you");
            }
            case "info" ->
            {
                availableCommands.add("exit");
                availableCommands.add("video");
                availableCommands.add("with you");
            }
            case "with you" ->
            {
                availableCommands.add("exit");
                availableCommands.add("down");
                availableCommands.add("up");
            }
            case "done" ->
            {
                availableCommands.add("exit");
                availableCommands.add("info");
                availableCommands.add("video");
                availableCommands.add("with you");
            }
            case "exit" ->
            {
                availableCommands.add("start");
                command = "";
                numberCommandEx = -1;
            }
            case "video" ->
            {
                availableCommands.add("exit");
                availableCommands.add("gym");
                availableCommands.add("home");
            }
        }

        if (Objects.equals(command, "up") || Objects.equals(command, "down"))
        {
            outputStrList.clear();

            if (numberCommandEx == -1)
            {
                numberCommandEx = 0;
                partOfBody = (Objects.equals(command, "up"));
            } else numberCommandEx += 1;

            if (numberCommandEx < 5)
            {
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
        else if(Objects.equals(command, "home") || Objects.equals(command, "gym"))
        {
            availableCommands.add("exit");
            availableCommands.add("leg "+command);
            availableCommands.add("butt "+command);
            availableCommands.add("press");
            availableCommands.add("arm "+command);
            availableCommands.add("full body "+command);
        }
        else if(textOutput.getWorkoutVideo(command))
        {
            outputStrList.clear();
            if (keyTest)
                outputStrList.add(textOutput.getText("workoutVideo"));
            else
                outputStrList.add(textOutput.getText("workoutVideo") + "\n" + parsing.getVideoLink(command));

            availableCommands.add("done");
        }
        return outputStrList;
    }
}