package uz.javohir.telegram_bot.botapi.handler.orderHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.cache.UserDataCache;
import uz.javohir.telegram_bot.service.LangService;
import uz.javohir.telegram_bot.service.OrderService;
import uz.javohir.telegram_bot.utils.Emojis;

@Component
public class OrderHandler implements InputMessageHandler {

    @Autowired
    private OrderService orderService;

    @Override
    public SendMessage handle(Message message) {
        return orderService.getOrderMessage(message.getChatId(), "Choose order types");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ORDER;
    }
}
