package BotTraining.Telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private final Long id;

    private ReplyKeyboardMarkup keyboard;
    private final AnswersFactory answersFactory;

    protected User(Long idUser){
        id = idUser;
        ReplyKeyboardMarkup keyboard = null;
        answersFactory = new AnswersFactory();
    }

    protected void setCommand(String messageText){
        answersFactory.command = messageText;
    }
    ReplyKeyboardMarkup initKeyboard() {
        ArrayList<String> listCommands = new ArrayList<>(answersFactory.availableCommands.stream().toList());
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        //replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования
        //Создаем список с рядами кнопок
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        while(!listCommands.isEmpty()) {
            //Создаем один ряд кнопок и добавляем его в список
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRows.add(keyboardRow);
            //Добавляем одну кнопку с текстом в наш ряд
            keyboardRow.add(new KeyboardButton(listCommands.get(listCommands.size()-1)));
            //добавляем лист с одним рядом кнопок в главный объект
            replyKeyboardMarkup.setKeyboard(keyboardRows);
            listCommands.remove(listCommands.size()-1);
        }
        return replyKeyboardMarkup;
    }

    protected ArrayList<SendMessage> objMessageList(String text) throws InterruptedException {
        ArrayList<SendMessage> outputObjList = new ArrayList<>();
        ArrayList<String> outputStrList = new ArrayList<>(answersFactory.getResponse());

        if (outputStrList.size() > 1){
            SendMessage answer1 = new SendMessage();
            answer1.setChatId(id);
            answer1.setText(outputStrList.get(0));
            outputObjList.add(answer1);

            SendMessage answer2 = new SendMessage();
            answer2.setChatId(id);
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
        answer.setChatId(id);
        answer.setText(outputStrList.get(0));

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(initKeyboard().getKeyboard());
        keyboard.setResizeKeyboard(true); //подгоняем размер
        keyboard.setOneTimeKeyboard(Objects.equals(text, "go")); //скрываем после использования
        answer.setReplyMarkup(keyboard);

        outputObjList.add(answer);
        return outputObjList;
    }

}
