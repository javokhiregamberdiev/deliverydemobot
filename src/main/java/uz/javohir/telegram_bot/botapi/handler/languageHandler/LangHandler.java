package uz.javohir.telegram_bot.botapi.handler.languageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.cache.UserDataCache;
import uz.javohir.telegram_bot.service.LangService;
import uz.javohir.telegram_bot.service.MainMenuService;
import uz.javohir.telegram_bot.service.ReplyMessageService;
import uz.javohir.telegram_bot.utils.Emojis;

@Component
public class LangHandler implements InputMessageHandler {

    @Autowired
    private ReplyMessageService replyMessageService;

    @Autowired
    private LangService langService;

    @Autowired
    private UserDataCache userDataCache;

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyToUser = null;
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.ASK_LANG)) {
            replyToUser = langService.getLanguageMessage(message.getChatId(), "Choose the language" + Emojis.NOTIFICATION_MARK_FAILED);
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_FILLING_PROFILE);
        }
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_LANG;
    }

}
