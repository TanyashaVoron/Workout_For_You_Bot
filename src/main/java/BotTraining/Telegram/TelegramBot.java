package BotTraining.Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class TelegramBot extends TelegramLongPollingBot {
    private final String name;
    private final String token;
    Storage storage;
    private final ArrayList<String> availableCommands;
    public TelegramBot(String name, String token){
        this.name = name;
        this.token = token;
        storage = new Storage();
        availableCommands = new ArrayList<>();
        availableCommands.add("start");
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public class Storage {
        //база данных вывода ключ-команда
        Map<String, String> mapOutput = new HashMap<>();
        //пара ключ-команда(up, down), содержимое-номер упражнения
        private final Map<String,Integer> currentWorkoutExercise = new HashMap<>();
        //флаг 1-up. 0-down
        private boolean keyComExeBool = false;
        Storage()
        {
            mapOutput.put("start", """
                Привет! Давай тренироваться!
                Введи:
                (go) чтобы начать,
                (info) если хочешь узнать побольше обо мне.""");
            mapOutput.put("info", """
                Давай знакомиться!
                Я твой личный фитнес-тренер и подберу тренировку, подходящую именно тебе.
                Введи:
                (go) чтобы начать,
                (exit) если не хочешь тренироваться""");
            mapOutput.put("go", """
                Что ты хочешь потренировать сегодня?
                Введи:
                (up) если ты хочешь тренировать руки, спину и пресс,
                (down) - ноги и ягодицы.
                (exit) если хочешь выйти""");
            mapOutput.put("up","""
                1. Бабочка лежа на животе.
                2. Поднятие корпуса лежа с согнутыми в коленях ногами.
                3. Скручивания лежа.
                4. Отжимания с шагом в сторону.
                5. Обратные отжимания.
                6. Планка со сменой рук.""");
            mapOutput.put("down", """
                1. Велосипед.
                2. Ягодичный мост в статике.
                3. Присед, руки касаются пола.
                4. Выпады назад.
                5. Альпинист.
                6. Приведение бедра для внутренней части бедра.""");
            mapOutput.put("done", """
                Время вышло!
                Ты молодец!
                Продолжим тренировку?
                (go) - хочу ещё,
                (exit) - заканчиваю
                (info) - хочу узнать о тебе больше""");
            mapOutput.put("lastExe", """
                Так держать! Осталось последнее упражнение!
                Пиши (done) когда закончишь!""");
            mapOutput.put("next","""
                Время вышло!
                (next) следующее упражнение!
                (exit) если хочешь прервать тренеровку и выйти""");
            mapOutput.put("time","У тебя 60 секунд!");
            mapOutput.put("exit", "Пока! Возвращайся:)");
            mapOutput.put("error", "Не понимаю( Введи команду правильно (без пробелов, с маленькой буквы, без скобок или кавычек)");
        }
        ArrayList<String> getResponse(String command) {
            ArrayList<String> outputStrList = new ArrayList<>();
            outputStrList.add(mapOutput.get(command));

            if (!availableCommands.contains(command) || outputStrList.get(0) == null){
                outputStrList.clear();
                outputStrList.add(mapOutput.get("error"));
                return outputStrList;
            }

            if (Objects.equals(command, "next")) {
                if (keyComExeBool)
                    command = "up";
                else
                    command = "down";
                outputStrList.add(mapOutput.get(command));
            }

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
                    currentWorkoutExercise.clear();
                    keyComExeBool = false;
                }
            }

            if(Objects.equals(command, "up") || Objects.equals(command, "down")) {
                outputStrList.clear();
                if (currentWorkoutExercise.isEmpty())
                    currentWorkoutExercise.put(command, 0);

                if (currentWorkoutExercise.get(command) < 5) {
                    availableCommands.add("exit");
                    availableCommands.add("next");
                    keyComExeBool = command.equals("up");
                    currentWorkoutExercise.put(command, currentWorkoutExercise.get(command) + 1);
                    outputStrList.add(mapOutput.get(command).split("\n")[currentWorkoutExercise.get(command) - 1] + "\n" + mapOutput.get("time"));
                    outputStrList.add(mapOutput.get("next"));
                    return outputStrList;
                }
                availableCommands.clear();
                availableCommands.add("done");
                outputStrList.add(mapOutput.get("lastExe") + "\n" + mapOutput.get(command).split("\n")[currentWorkoutExercise.get(command)]);
                currentWorkoutExercise.clear();
                keyComExeBool = false;
            }
            return outputStrList;
        }
    }

    ReplyKeyboardMarkup initKeyboard() {
        ArrayList<String> listCommand = new ArrayList<>(availableCommands.stream().toList());
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        //replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования
        //Создаем список с рядами кнопок
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        while(!listCommand.isEmpty()) {
            //Создаем один ряд кнопок и добавляем его в список
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRows.add(keyboardRow);
            //Добавляем одну кнопку с текстом наш ряд
            keyboardRow.add(new KeyboardButton(listCommand.get(listCommand.size()-1)));
            //добавляем лист с одним рядом кнопок в главный объект
            replyKeyboardMarkup.setKeyboard(keyboardRows);
            listCommand.remove(listCommand.size()-1);
        }
        return replyKeyboardMarkup;
    }

    public ArrayList<SendMessage> objMessageList(String text, String chatId) throws InterruptedException {
        ArrayList<SendMessage> outputObjList = new ArrayList<>();
        ArrayList<String> outputStrList = new ArrayList<>(storage.getResponse(text));

        if (Objects.equals(text, "up") || Objects.equals(text, "down") || Objects.equals(text, "next")){
            SendMessage answer1 = new SendMessage();
            answer1.setChatId(chatId);
            answer1.setText(outputStrList.get(0));
            outputObjList.add(answer1);

            SendMessage answer2 = new SendMessage();
            answer2.setChatId(chatId);
            answer2.setText(outputStrList.get(1));
            //answer2.setReplyMarkup(new ReplyKeyboardMarkup(initKeyboard(availableCommands).getKeyboard()));
            ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(initKeyboard().getKeyboard());
            keyboard.setResizeKeyboard(true); //подгоняем размер
            keyboard.setOneTimeKeyboard(true); //скрываем после использования
            answer2.setReplyMarkup(keyboard);

            outputObjList.add(answer2);

            return outputObjList;
        }

        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText(outputStrList.get(0));
        //answer.setReplyMarkup(new ReplyKeyboardMarkup(initKeyboard(availableCommands).getKeyboard()));
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(initKeyboard().getKeyboard());
        keyboard.setResizeKeyboard(true); //подгоняем размер
        keyboard.setOneTimeKeyboard(Objects.equals(text, "go")); //скрываем после использования
        answer.setReplyMarkup(keyboard);

        outputObjList.add(answer);
        return outputObjList;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if(update.hasMessage() && update.getMessage().hasText())
            {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                ArrayList<SendMessage> outputObjList = new ArrayList<>(objMessageList(inMess.getText(),chatId));

                execute(outputObjList.get(0));
                if (outputObjList.size()>1){
                    Thread.sleep(5000);
                    execute(outputObjList.get(1));
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}