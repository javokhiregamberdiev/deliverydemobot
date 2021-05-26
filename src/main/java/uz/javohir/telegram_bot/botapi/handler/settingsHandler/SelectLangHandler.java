package uz.javohir.telegram_bot.botapi.handler.settingsHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.service.SelectLangService;

@Component
public class SelectLangHandler implements InputMessageHandler {

    @Autowired
    private SelectLangService selectLangService;

    @Override
    public SendMessage handle(Message message) {
        return selectLangService.getSelectLanguageMessage(message.getChatId(), "Change language");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SELECT_LANG;
    }
}
