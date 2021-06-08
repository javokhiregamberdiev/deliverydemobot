package uz.javohir.telegram_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.javohir.telegram_bot.botapi.TelegramFacade;

@Component
public class TelegramBot extends TelegramWebhookBot {

    @Autowired
    private TelegramFacade telegramFacade;

    private String webHookPath = "https://116084ea4c03.ngrok.io";
    private String botUserName = "@javohir12345bot";
    private String botToken = "1756876870:AAFuwTUoqdATfEmPif0dtNR7PiOqa3VwaSc";

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasCallbackQuery()){
            System.out.println(update.getCallbackQuery().getData());
        }else {
            System.out.println(update.getMessage().getText());
        }
        BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);
        return replyMessageToUser;
    }
}
