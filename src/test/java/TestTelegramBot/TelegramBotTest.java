package TestTelegramBot;

import TelegramBot.AnswersFactory;
import TelegramBot.TextOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TelegramBotTest {
    private final String[] args = {
            "start", "info", "exit",
            "start", "info", "go", "exit",
            "start", "go", "up", "exit",
            "start", "go", "up", "next", "exit",
            "start", "go", "up", "next", "next", "exit",
            "start", "go", "up", "next", "next", "next", "exit",
            "start", "go", "up", "next", "next", "next", "next", "exit",
            "start", "go", "up", "next", "next", "next", "next", "next", "done", "exit",
            "start", "go", "up", "next", "next", "next", "next", "next", "done", "exit", "start", "info", "exit",
            "start", "go", "up", "next", "next", "next", "next", "next", "done", "exit", "start", "go", "down", "exit",
            "start", "go", "down", "next", "exit",
            "start", "go", "down", "next", "next", "exit",
            "start", "go", "down", "next", "next", "next", "exit",
            "start", "go", "down", "next", "next", "next", "next", "exit",
            "start", "go", "down", "next", "next", "next", "next", "next", "done", "exit",
            "start", "go", "down", "next", "next", "next", "next", "next", "done", "info", "exit",
            "start", "go", "down", "next", "next", "next", "next", "next", "done", "go", "exit",

            "start", "asfg", "info", "asdfg", "exit", "sddy10",
            "start", "gh", "info", "13l", "go", "13l", "exit",
            "start", "asdfgh", "go", "sddy10", "up", "sddy10", "exit",
            "start", "asfg", "go", "asdfg", "up", "asdfg", "next", "asfg", "exit",
            "start", "gh", "go", "13l", "up", "13l", "next", "gh", "next", "asfg", "exit",
            "start", "asdfgh", "go", "sddy10", "up", "sddy10", "next", "asdfgh", "next", "gh", "next", "asdfg", "exit",
            "start", "asfg", "go", "asdfg", "up", "asdfg", "next", "asfg", "next", "asdfgh", "next", "13l", "next", "13l", "exit",
            "start", "gh", "go", "13l", "up", "13l", "next", "gh", "next", "asfg", "next", "sddy10", "next", "sddy10", "next", "gh", "done", "skdhl", "exit",
            "start", "asdfgh", "go", "sddy10", "up", "sddy10", "next", "asdfgh", "next", "gh", "next", "asdfg", "next", "asdfg", "next", "asdfgh", "done", "dsfgh", "exit", "skdhl", "start", "skdhl", "info", "skdhl", "exit",
            "start", "asfg", "go", "asdfg", "up", "asdfg", "next", "asfg", "next", "asdfgh", "next", "13l", "next", "13l", "next", "asfg", "done", "dfgh", "exit", "dsfgh", "start", "dsfgh", "go", "dsfgh", "down", "asdfghl", "exit",
            "start", "gh", "go", "13l", "down", "13l", "next", "gh", "exit",
            "start", "asdfgh", "go", "sddy10", "down", "sddy10", "next", "asdfgh", "next", "gh", "exit",
            "start", "asfg", "go", "asdfg", "down", "asdfg", "next", "asfg", "next", "asdfgh", "next", "asdfg", "exit",
            "start", "gh", "go", "13l", "down", "13l", "next", "gh", "next", "asfg", "next", "13l", "next", "gh", "exit",
            "start", "asdfgh", "go", "sddy10", "down", "sddy10", "next", "asdfgh", "next", "gh", "next", "sddy10", "next", "asdfgh", "next", "skdhl", "done", "skdhl", "exit",
            "start", "asfg", "go", "asdfg", "down", "asdfg", "next", "asfg", "next", "asdfgh", "next", "asdfg", "next", "asfg", "next", "dsfgh", "done", "dsfgh", "info", "skdhl", "exit",
            "start", "gh", "go", "13l", "down", "13l", "next", "gh", "next", "asfg", "next", "13l", "next", "gh", "next", "dfgh", "done", "dfgh", "go", "dsfgh", "exit",
    };
    TextOutput textOutput = new TextOutput();
    private final HashMap<String,ArrayList<String>> availableCommandsMap = new HashMap<>();
    private final HashMap<String,String> outputStrMap = new HashMap<>();

    TelegramBotTest(){
        availableCommandsMap.put("start",new ArrayList<>(Arrays.asList("info", "go")));
        availableCommandsMap.put("info",new ArrayList<>(Arrays.asList("exit", "go")));
        availableCommandsMap.put("go", new ArrayList<>(Arrays.asList("exit","down", "up")));
        availableCommandsMap.put("done",new ArrayList<>(Arrays.asList("exit","info", "go")));
        availableCommandsMap.put("up",new ArrayList<>(List.of("exit","next")));
        availableCommandsMap.put("down",new ArrayList<>(List.of("exit","next")));
        availableCommandsMap.put("lastEx",new ArrayList<>(List.of("done")));
        availableCommandsMap.put("ex", new ArrayList<>(List.of("exit", "next")));
        availableCommandsMap.put("exit",new ArrayList<>(List.of("start")));

        outputStrMap.put("up0","1. Бабочка лежа на животе.");
        outputStrMap.put("up1","2. Поднятие корпуса лежа с согнутыми в коленях ногами.");
        outputStrMap.put("up2","3. Скручивания лежа.");
        outputStrMap.put("up3","4. Отжимания с шагом в сторону.");
        outputStrMap.put("up4","5. Обратные отжимания.");
        outputStrMap.put("up5","6. Планка со сменой рук.");

        outputStrMap.put("down0","1. Велосипед.");
        outputStrMap.put("down1","2. Ягодичный мост в статике.");
        outputStrMap.put("down2","3. Присед, руки касаются пола.");
        outputStrMap.put("down3","4. Выпады назад.");
        outputStrMap.put("down4","5. Альпинист.");
        outputStrMap.put("down5","6. Приведение бедра для внутренней части бедра.");
    }

    private ArrayList<String> checkEqualsavailableCommands(String commandTest,Integer numberCommandEx) {
        if(commandTest.equals("next"))
            if (numberCommandEx == -1)
                return availableCommandsMap.get("lastEx");
            else return availableCommandsMap.get("ex");

        if(commandTest.equals("up") || commandTest.equals("down"))
            return availableCommandsMap.get("ex");

        if(availableCommandsMap.containsKey(commandTest))
            return availableCommandsMap.get(commandTest);

        return new ArrayList<>(List.of("*"));
    }

    private ArrayList<String> checkOutputStrList(String commandTest, Boolean partOfBody,Integer numberCommandEx){
        switch (commandTest){
            case "up" -> {
                return new ArrayList<>(Arrays.asList(outputStrMap.get("up0")+"\n"+textOutput.getText("time"), textOutput.getText("next")));
            }
            case "down" -> {
                return new ArrayList<>(Arrays.asList(outputStrMap.get("down0")+"\n"+textOutput.getText("time"), textOutput.getText("next")));
            }
            case "next" ->{
                if (partOfBody)
                    if (numberCommandEx == -1)
                        return new ArrayList<>(List.of(textOutput.getText("lastEx") + "\n" + outputStrMap.get("up5")));
                    else
                        return new ArrayList<>(Arrays.asList(outputStrMap.get("up" + numberCommandEx)+"\n"+textOutput.getText("time"), textOutput.getText("next")));
                else
                    if (numberCommandEx == -1)
                        return new ArrayList<>(List.of(textOutput.getText("lastEx") + "\n" + outputStrMap.get("down5")));
                    else return new ArrayList<>(Arrays.asList(outputStrMap.get("down"+ numberCommandEx)+"\n"+textOutput.getText("time"), textOutput.getText("next")));
            }
            default -> {
                if (availableCommandsMap.containsKey(commandTest))
                    return new ArrayList<>(Collections.singletonList(textOutput.getText(commandTest)));
                return new ArrayList<>(Collections.singletonList(textOutput.getText("error")));
            }
        }
    }

    @Test
    void Test(){
        AnswersFactory answersFactory = new AnswersFactory();
        for (String arg : args){
            answersFactory.setCommand(arg);
            ArrayList<String> outputStrList = new ArrayList<>(answersFactory.getResponse().stream().toList());

            //проверка на совпадение availableCommands - доступные далее команды
            if (!Objects.equals(checkEqualsavailableCommands(arg, answersFactory.getNumberCommandEx()), new ArrayList<>(List.of("*"))))
               Assertions.assertEquals(checkEqualsavailableCommands(arg,answersFactory.getNumberCommandEx()),answersFactory.getAvailableCommands());
            //проверка на совпадения outputStrList - строки для вывода
            Assertions.assertEquals(checkOutputStrList(arg,answersFactory.getPartOfBody(),answersFactory.getNumberCommandEx()),outputStrList);
        }
    }
}
