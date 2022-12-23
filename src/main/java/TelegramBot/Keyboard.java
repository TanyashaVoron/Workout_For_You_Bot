package TelegramBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard
{
    protected ReplyKeyboardMarkup keyboard;

    Keyboard() { keyboard = new ReplyKeyboardMarkup(); }

    void initKeyboardWorkout(Boolean oneTime, ArrayList<String> availableCommands)
    {
        ArrayList<String> listCommands = new ArrayList<>(availableCommands.stream().toList());
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        /*if (listCommands.get(0).length() == 2){
            listCommands.clear();
            listCommands.add("exit");
        }*/

        while(!listCommands.isEmpty())
        {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRows.add(keyboardRow);
            keyboardRow.add(new KeyboardButton(listCommands.get(listCommands.size()-1)));
            keyboard.setKeyboard(keyboardRows);
            listCommands.remove(listCommands.size()-1);
        }

        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(oneTime);
    }
}
