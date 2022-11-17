package BotTraining.Telegram;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        availableCommands.add("/start");
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
        Map<String, String> map = new HashMap<String, String>();
        Storage()
        {
            map.put("/start", """
                Привет! Давай тренироваться!
                Введи:
                (go) чтобы начать,
                (info) если хочешь узнать побольше обо мне.""");
            map.put("info", """
                Давай знакомиться!
                Я твой личный фитнес-тренер и подберу тренировку, подходящую именно тебе.
                Введи:
                (go) чтобы начать,
                (exit) если не хочешь тренироваться""");
            map.put("go", """
                Что ты хочешь потренировать сегодня?
                Введи:
                (up) если ты хочешь тренировать руки, спину и пресс,
                (down) - ноги и ягодицы.""");
            map.put("up", """
                1. Жим штанги лёжа - 3-4 подхода по 6-8 повторений.
                2. Тяга штанги к поясу - 3-4 подхода по 6-8 повторений.
                3. Жим штанги с груди стоя - 3-4 подхода по 8-12 повторений.
                4. Отжимания от брусьев - 3-4 подхода по 8-15 повторений.
                5. Подтягивания обратным хватом - 3-4 подхода по 8-15 повторений.
                6. Махи гантелями в стороны - 3-4 подхода по 10-15 повторений.
                У тебя всё получится!
                Пиши (done), когда сделаешь.""");
            map.put("down", """
                1. Приседания со штангой: 4 подхода по 6-8, 6-8, 8-10, 8-10 повторений
                2. Жим ногами: 3 подхода по 10-12 повторений без отдыха.
                3. Шагающие выпады с гантелями: 3 подхода по 10, 12, 14 шагов на каждую ногу.
                4. Подъемы на носки стоя: 4 подхода по 12, 12, 20, 20 повторений.
                5. Приседания с гирей (гоблет): 4 подхода по 10-12 повторений, отдых 90 секунд.
                6. Обратные выпады в тренажере Смита: 3 подхода по 10-12 повторений на каждую ногу без отдыха.
                У тебя всё получится!
                Напиши (done), когда сделаешь.""");
            map.put("done", """
                Ты молодец!
                Продолжим тренировку?
                (go) - хочу ещё,
                (exit) - заканчиваю
                (info) - хочу узнать о тебе больше""");
            map.put("exit", "Пока! Возвращайся:)");
            map.put("error", "Не понимаю( Введи команду правильно (без пробелов, с маленькой буквы, без скобок или кавычек)");

        }

        String getResponse(String command)
        {
            String resp = map.get(command);
            if (!availableCommands.contains(command) || resp == null){
                return map.get("error");
            }

            availableCommands.clear();
            switch (command) {
                case ("/start") -> {
                    availableCommands.add("info");
                    availableCommands.add("go");
                }
                case ("info") -> {
                    availableCommands.add("exit");
                    availableCommands.add("go");
                }
                case ("go") -> {
                    availableCommands.add("up");
                    availableCommands.add("down");
                }
                case ("done") -> {
                    availableCommands.add("info");
                    availableCommands.add("go");
                    availableCommands.add("exit");
                }
                case ("exit") -> availableCommands.add("/start");
                default -> availableCommands.add("done");
            }
            return resp;

        }
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
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage answer = new SendMessage();

                //Добавляем в наше сообщение id чата, а также наш ответ
                answer.setChatId(chatId);
                answer.setText(response);

                //Отправка в чат
                execute(answer);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(String textMsg) {

        return storage.getResponse(textMsg);
    }
}
