//package uz.javohir.telegram_bot;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.bots.TelegramWebhookBot;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import uz.javohir.telegram_bot.botapi.TelegramFacade;
//
//@Component
//public class TelegramBotNew extends TelegramWebhookBot {
//
//    @Autowired
//    private TelegramFacade telegramFacade;
//
//    private String webHookPath = "https://752a09141c85.ngrok.io";
//    private String botUserName = "@javohir12bot";
//    private String botToken = "1600367258:AAE1sZy56UD18jTP59iyAdFk7z3gXPNMxic";
//
//    @Override
//    public String getBotUsername() {
//        return botUserName;
//    }
//
//    @Override
//    public String getBotToken() {
//        return botToken;
//    }
//
//    @Override
//    public String getBotPath() {
//        return webHookPath;
//    }
//
//    @Override
//    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
//
//        BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);
//        return replyMessageToUser;
//    }
//}
