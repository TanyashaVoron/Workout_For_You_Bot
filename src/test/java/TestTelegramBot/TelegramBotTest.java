package TestTelegramBot;

import TelegramBot.AnswersFactory;
import TelegramBot.TextOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class TelegramBotTest
{
    private final String[] args = {
            "start", "info", "exit",
            "start", "info", "with you", "exit",
            "start", "with you", "up", "exit",
            "start", "with you", "up", "next", "exit",
            "start", "with you", "up", "next", "next", "exit",
            "start", "with you", "up", "next", "next", "next", "exit",
            "start", "with you", "up", "next", "next", "next", "next", "exit",
            "start", "with you", "up", "next", "next", "next", "next", "next", "done", "exit",
            "start", "with you", "up", "next", "next", "next", "next", "next", "done", "exit", "start", "info", "exit",
            "start", "with you", "up", "next", "next", "next", "next", "next", "done", "exit", "start", "with you", "down", "exit",
            "start", "with you", "down", "next", "exit",
            "start", "with you", "down", "next", "next", "exit",
            "start", "with you", "down", "next", "next", "next", "exit",
            "start", "with you", "down", "next", "next", "next", "next", "exit",
            "start", "with you", "down", "next", "next", "next", "next", "next", "done", "exit",
            "start", "with you", "down", "next", "next", "next", "next", "next", "done", "info", "exit",
            "start", "with you", "down", "next", "next", "next", "next", "next", "done", "with you", "exit",

            "start", "asfg", "info", "asdfg", "exit", "sddy10",
            "start", "gh", "info", "13l", "with you", "13l", "exit",
            "start", "asdfgh", "with you", "sddy10", "up", "sddy10", "exit",
            "start", "asfg", "with you", "asdfg", "up", "asdfg", "next", "asfg", "exit",
            "start", "gh", "with you", "13l", "up", "13l", "next", "gh", "next", "asfg", "exit",
            "start", "asdfgh", "with you", "sddy10", "up", "sddy10", "next", "asdfgh", "next", "gh", "next", "asdfg", "exit",
            "start", "asfg", "with you", "asdfg", "up", "asdfg", "next", "asfg", "next", "asdfgh", "next", "13l", "next", "13l", "exit",
            "start", "gh", "with you", "13l", "up", "13l", "next", "gh", "next", "asfg", "next", "sddy10", "next", "sddy10", "next", "gh", "done", "skdhl", "exit",
            "start", "asdfgh", "with you", "sddy10", "up", "sddy10", "next", "asdfgh", "next", "gh", "next", "asdfg", "next", "asdfg", "next", "asdfgh", "done", "dsfgh", "exit", "skdhl", "start", "skdhl", "info", "skdhl", "exit",
            "start", "asfg", "with you", "asdfg", "up", "asdfg", "next", "asfg", "next", "asdfgh", "next", "13l", "next", "13l", "next", "asfg", "done", "dfgh", "exit", "dsfgh", "start", "dsfgh", "with you", "dsfgh", "down", "asdfghl", "exit",
            "start", "gh", "with you", "13l", "down", "13l", "next", "gh", "exit",
            "start", "asdfgh", "with you", "sddy10", "down", "sddy10", "next", "asdfgh", "next", "gh", "exit",
            "start", "asfg", "with you", "asdfg", "down", "asdfg", "next", "asfg", "next", "asdfgh", "next", "asdfg", "exit",
            "start", "gh", "with you", "13l", "down", "13l", "next", "gh", "next", "asfg", "next", "13l", "next", "gh", "exit",
            "start", "asdfgh", "with you", "sddy10", "down", "sddy10", "next", "asdfgh", "next", "gh", "next", "sddy10", "next", "asdfgh", "next", "skdhl", "done", "skdhl", "exit",
            "start", "asfg", "with you", "asdfg", "down", "asdfg", "next", "asfg", "next", "asdfgh", "next", "asdfg", "next", "asfg", "next", "dsfgh", "done", "dsfgh", "info", "skdhl", "exit",
            "start", "gh", "with you", "13l", "down", "13l", "next", "gh", "next", "asfg", "next", "13l", "next", "gh", "next", "dfgh", "done", "dfgh", "with you", "dsfgh", "exit",

            "start",	"video",	"exit",
            "start",	"video",	"home",	"exit",
            "start",	"video",	"home",	"leg home",	"done",	"exit",
            "start",	"video",	"home",	"butt home",	"done",	"exit",
            "start",	"video",	"home",	"press",	"done",	"exit",
            "start",	"video",	"home",	"arm home",	"done",	"exit",
            "start",	"video",	"home",	"full body home",	"done",
            "video",	"gym",	"exit",
            "start",	"video",	"gym",	"leg gym",	"done",	"exit",
            "start",	"video",	"gym",	"butt gym",	"done",	"exit",
            "start",	"video",	"gym",	"press",	"done",	"exit",
            "start",	"video",	"gym",	"arm gym",	"done",	"exit",
            "start",	"video",	"gym",	"full body gym",	"done",	"exit",
            "start",	"info",	"video",	"exit",

            "start",	"asdfghj",	"video",	"12345",	"exit",
            "start",	"werty",	"video",	"345",	"home",	"werty",	"exit",
            "start",	"xcvbn",	"video",	"qw4567u",	"home",	"xcvbn",	"leg home",	"done",	"exit",
            "start",	"aserty",	"video",	"12345",	"home",	"aserty",	"butt home",	"done",	"exit",
            "start",	"asdfghj",	"video",	"345",	"home",	"asdfghj",	"press",	"done",	"exit",
            "start",	"werty",	"video",	"qw4567u",	"home",	"werty",	"arm home",	"done",	"exit",
            "start",	"xcvbn",	"video",	"12345",	"home",	"xcvbn",	"full body home",	"done",
            "aserty",	"video",	"345",	"gym",	"aserty",	"exit",
            "start",	"asdfghj",	"video",	"qw4567u",	"gym",	"asdfghj",	"leg gym",	"done",	"exit",
            "start",	"werty",	"video",	"12345",	"gym",	"werty",	"butt gym",	"done",	"exit",
            "start",	"xcvbn",	"video",	"345",	"gym",	"xcvbn",	"press",	"done",	"exit",
            "start",	"aserty",	"video",	"qw4567u",	"gym",	"aserty",	"arm gym",	"done",	"exit",
            "start",	"asdfghj",	"video",	"12345",	"gym",	"asdfghj",	"full body gym",	"done",	"exit",
            "start",	"werty",	"info",	"bdf",	"video",	"werty",	"exit"
    };
    TextOutput textOutput = new TextOutput();
    private final HashMap<String,ArrayList<String>> availableCommandsMap = new HashMap<>();
    private final HashMap<String,String> outputStrMap = new HashMap<>();

    TelegramBotTest()
    {
        availableCommandsMap.put("start",new ArrayList<>(Arrays.asList("info", "video", "with you")));
        availableCommandsMap.put("info",new ArrayList<>(Arrays.asList("exit", "video", "with you")));
        availableCommandsMap.put("with you", new ArrayList<>(Arrays.asList("exit","down", "up")));
        availableCommandsMap.put("done",new ArrayList<>(Arrays.asList("exit","info", "video", "with you")));
        availableCommandsMap.put("up",new ArrayList<>(List.of("exit","next")));
        availableCommandsMap.put("down",new ArrayList<>(List.of("exit","next")));
        availableCommandsMap.put("lastEx",new ArrayList<>(List.of("done")));
        availableCommandsMap.put("ex", new ArrayList<>(List.of("exit", "next")));
        availableCommandsMap.put("exit",new ArrayList<>(List.of("start")));
        availableCommandsMap.put("video", new ArrayList<>(List.of("exit", "gym", "home")));
        availableCommandsMap.put("home", new ArrayList<>(List.of("exit", "leg home", "butt home", "press", "arm home", "full body home")));
        availableCommandsMap.put("gym", new ArrayList<>(List.of("exit", "leg gym", "butt gym", "press", "arm gym", "full body gym")));
        availableCommandsMap.put("workoutVideo", new ArrayList<>(List.of("done")));


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

    private ArrayList<String> checkEqualsavailableCommands(String commandTest,Integer numberCommandEx)
    {
        if (textOutput.getWorkoutVideo(commandTest)) return availableCommandsMap.get("workoutVideo");

        if(commandTest.equals("next"))
            if (numberCommandEx == -1) return availableCommandsMap.get("lastEx");
            else return availableCommandsMap.get("ex");

        if(commandTest.equals("up") || commandTest.equals("down")) return availableCommandsMap.get("ex");

        if(availableCommandsMap.containsKey(commandTest)) return availableCommandsMap.get(commandTest);

        return new ArrayList<>(List.of("*"));
    }

    private ArrayList<String> checkOutputStrList(String commandTest, Boolean partOfBody,Integer numberCommandEx) {
        switch (commandTest)
        {
            case "up" : return new ArrayList<>(Arrays.asList(outputStrMap.get("up0")+"\n"+textOutput.getText("time"), textOutput.getText("next")));

            case "down" : return new ArrayList<>(Arrays.asList(outputStrMap.get("down0")+"\n"+textOutput.getText("time"), textOutput.getText("next")));

            case "next" :
                if (partOfBody)
                    if (numberCommandEx == -1) return new ArrayList<>(List.of(textOutput.getText("lastEx") + "\n" + outputStrMap.get("up5")));
                    else return new ArrayList<>(Arrays.asList(outputStrMap.get("up" + numberCommandEx)+"\n"+textOutput.getText("time"), textOutput.getText("next")));
                else
                    if (numberCommandEx == -1) return new ArrayList<>(List.of(textOutput.getText("lastEx") + "\n" + outputStrMap.get("down5")));
                    else return new ArrayList<>(Arrays.asList(outputStrMap.get("down"+ numberCommandEx)+"\n"+textOutput.getText("time"), textOutput.getText("next")));

            default :
            {
                if (textOutput.getWorkoutVideo(commandTest)) return new ArrayList<>(Collections.singletonList(textOutput.getText("workoutVideo")));
                else if (availableCommandsMap.containsKey(commandTest)) return new ArrayList<>(Collections.singletonList(textOutput.getText(commandTest)));
                else return new ArrayList<>(Collections.singletonList(textOutput.getText("error")));
            }
        }
    }

    @Test
    void test() throws IOException
    {
        AnswersFactory answersFactory = new AnswersFactory();
        answersFactory.keyTest = true;

        for (String arg : args)
        {
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
