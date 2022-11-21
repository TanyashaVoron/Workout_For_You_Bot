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
        Map<String, String> mapOutput = new HashMap<>();
        private final Map<String,Integer> currentWorkoutExercise = new HashMap<>();
        private boolean keyComExeBool = false;
        ArrayList<String> expection = new ArrayList<>();
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
                1. Жим штанги лёжа - 3-4 подхода по 6-8 повторений.
                2. Тяга штанги к поясу - 3-4 подхода по 6-8 повторений.
                3. Жим штанги с груди стоя - 3-4 подхода по 8-12 повторений.
                4. Отжимания от брусьев - 3-4 подхода по 8-15 повторений.
                5. Подтягивания обратным хватом - 3-4 подхода по 8-15 повторений.
                6. Махи гантелями в стороны - 3-4 подхода по 10-15 повторений.""");
            mapOutput.put("down", """
                1. Приседания со штангой: 4 подхода по 6-8, 6-8, 8-10, 8-10 повторений
                2. Жим ногами: 3 подхода по 10-12 повторений без отдыха.
                3. Шагающие выпады с гантелями: 3 подхода по 10, 12, 14 шагов на каждую ногу.
                4. Подъемы на носки стоя: 4 подхода по 12, 12, 20, 20 повторений.
                5. Приседания с гирей (гоблет): 4 подхода по 10-12 повторений, отдых 90 секунд.
                6. Обратные выпады в тренажере Смита: 3 подхода по 10-12 повторений на каждую ногу без отдыха.""");
            mapOutput.put("done", """
                Ты молодец!
                Продолжим тренировку?
                (go) - хочу ещё,
                (exit) - заканчиваю
                (info) - хочу узнать о тебе больше""");
            mapOutput.put("lastExe", """
                Молодец! Осталось последнее упражнение!
                Пиши (done) когда закончишь!""");
            mapOutput.put("continue","""
                (continue) следующее упражнение!
                (exit) если хочешь прервать тренеровку и выйти""");
            mapOutput.put("time","У тебя 60 секунд!");
            mapOutput.put("timeIsOver", """
                Ой, что-то ты долго..
                Пиши
                (addTime) тогда я дам тебе еще немного времени
                (continue) если хотешь приступить к следующему упражнению
                (exit) если хочешь прервать тренеровку и выйти""");
            mapOutput.put("exit", "Пока! Возвращайся:)");
            mapOutput.put("error", "Не понимаю( Введи команду правильно (без пробелов, с маленькой буквы, без скобок или кавычек)");
            mapOutput.put("work","Время еще не закончилось! продолжай делать упражнение)");
            expection.add("Время пошло!");
        }
        HashMap<String,ArrayList<String>> getResponse(String command) {
            String resp = mapOutput.get(command);
            HashMap<String, ArrayList<String>> ans = new HashMap<>();

            if (!availableCommands.contains(command) || resp == null){
                //if(Objects.equals(command, "Время пошло!"))
                //ans.put(mapOutput.get("work"),availableCommands);
                //else
                ans.put(mapOutput.get("error"),availableCommands);
                return ans;
            }

            if (Objects.equals(command, "continue") || Objects.equals(command, "addTime")) {
                //если addTime надо currentWorkoutExercise.put(command,currentWorkoutExercise.get("up")-1);
                if (keyComExeBool)
                    command = "up";
                else
                    command = "down";
                resp = mapOutput.get(command);

            }

            availableCommands.clear();
            switch (command) {
                case ("start") -> {
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
                    availableCommands.add("exit");
                }
                case ("done") -> {
                    availableCommands.add("info");
                    availableCommands.add("go");
                    availableCommands.add("exit");
                }
                case ("up") -> {
                    if (currentWorkoutExercise.isEmpty())
                        currentWorkoutExercise.put("up",0);

                    if (currentWorkoutExercise.get("up")<5){
                        availableCommands.add("continue");
                        availableCommands.add("exit");
                        keyComExeBool = true;
                        currentWorkoutExercise.put("up",currentWorkoutExercise.get("up")+1);
                        ans.put(resp.split("\n")[currentWorkoutExercise.get("up")-1]+"\n"+mapOutput.get("time"),expection);
                        ans.put(mapOutput.get("continue"),availableCommands);
                        return ans;
                    }
                    availableCommands.clear();
                    availableCommands.add("done");
                    resp = mapOutput.get("lastExe")+"\n"+resp.split("\n")[currentWorkoutExercise.get("up")];
                    currentWorkoutExercise.clear();
                    keyComExeBool = false;
                }
                case ("down") -> {
                    if (currentWorkoutExercise.isEmpty())
                        currentWorkoutExercise.put("down",0);

                    if (currentWorkoutExercise.get("down")<5){
                        availableCommands.add("continue");
                        availableCommands.add("exit");
                        ans.put(resp.split("\n")[currentWorkoutExercise.get("down")-1]+"\n"+mapOutput.get("time"),expection);
                        ans.put(mapOutput.get("continue"),availableCommands);
                        return ans;
                    }

                    availableCommands.clear();
                    availableCommands.add("done");
                    resp = mapOutput.get("lastExe")+"\n"+resp.split("\n")[currentWorkoutExercise.get("down")];
                    currentWorkoutExercise.clear();
                }
                case ("exit") -> {
                    availableCommands.add("start");
                    currentWorkoutExercise.clear();
                    keyComExeBool = false;

                }
            }
            ans.put(resp,availableCommands);
            return ans;
        }
    }

    ReplyKeyboardMarkup initKeyboard(ArrayList<String> listCommand) {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования
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

    public Map<String,ReplyKeyboardMarkup> parseMessage(String textMsg) throws InterruptedException {
        Map<String, ReplyKeyboardMarkup> ans = new HashMap<>();
        //получили (текст для вывода: [возможные команды для ввода])
        Map<String,ArrayList<String>> map = new HashMap<>(storage.getResponse(textMsg));
        //извлекли текст для вывода
        ArrayList<String> listCommand = new ArrayList<>(map.keySet());
        while (!listCommand.isEmpty()) {
            String key = listCommand.get(0);
            //извлекли [возможные команды]
            ArrayList<String> listCommandByKey = new ArrayList<>(map.get(key));
            //if(listCommandByKey.get(0)==null)
            //создали клавиатуру по [] возможныхкоманд
            ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(initKeyboard(listCommandByKey).getKeyboard());
            //записали это а виде (текст для вывода, клавиатура)
            ans.put(key, keyboard);
            listCommand.remove(0);
        }
        return ans;
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
                Map<String,ReplyKeyboardMarkup> ansMap = new HashMap<>(parseMessage(inMess.getText()));
                //извлекаем текст для вывода
                ArrayList<String> listkeyb = new ArrayList<>(ansMap.keySet());
                int count = 0;
                while(listkeyb.size()>0){
                    if (count==1)
                        Thread.sleep(5000);
                    String response = listkeyb.get(0);
                    //извлекаем клавиатуру
                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(ansMap.get(response).getKeyboard());
                    //Создаем объект класса SendMessage - наш будущий ответ пользователю
                    SendMessage answer = new SendMessage();

                    //Добавляем в наше сообщение id чата, а также наш ответ
                    answer.setChatId(chatId);
                    answer.setText(response);
                    answer.setReplyMarkup(replyKeyboardMarkup);

                    //Отправка в чат
                    execute(answer);
                    //удаляем последний элемент
                    listkeyb.remove(0);
                    count++;
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
