package uz.javohir.telegram_bot.botapi.handler.cashBackHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.service.MainMenuService;
import uz.javohir.telegram_bot.service.OrderService;

@Component
public class CashBackHandler implements InputMessageHandler {
    @Autowired
    private MainMenuService mainMenuService;

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(), message.getFrom().getFirstName() + "  you hav not cashback");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CASHBACK;
    }
}
