package TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.*;

public class TelegramBot extends TelegramLongPollingBot
{
    private final String name;
    private final String token;
    private  final Map<Long, User> users;

    public TelegramBot(String name, String token)
    {
        this.name = name;
        this.token = token;
        users = new HashMap<>();
    }

    @Override
    public String getBotUsername() { return name; }

    @Override
    public String getBotToken() { return token; }

    @Override
    public void onUpdateReceived(Update update)
    {
        try
        {
            if(update.hasMessage() && update.getMessage().hasText())
            {
                Message inMess = update.getMessage();
                Long chatId = inMess.getChatId();

                if (!users.containsKey(chatId))
                {
                    User user = new User(chatId);
                    users.put(chatId, user);
                }

                User user = users.get(chatId);
                user.setCommand(inMess.getText());

                ArrayList<SendMessage> outputObjList = new ArrayList<>(user.objMessageList(inMess.getText()));

                execute(outputObjList.get(0));

                if (outputObjList.size()>1)
                {
                    Thread.sleep(1000);
                    execute(outputObjList.get(1));
                }
            }
        }
        catch (TelegramApiException e) { e.printStackTrace(); }
        catch (InterruptedException | IOException e) { throw new RuntimeException(e); }
    }
}