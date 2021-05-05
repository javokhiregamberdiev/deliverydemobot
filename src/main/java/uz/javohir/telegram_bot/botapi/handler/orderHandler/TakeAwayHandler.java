package uz.javohir.telegram_bot.botapi.handler.orderHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.service.OrderService;

@Component
public class TakeAwayHandler implements InputMessageHandler {
    @Autowired
    private OrderService orderService;

    @Override
    public SendMessage handle(Message message) {
        return orderService.getOrderMessage(message.getChatId(), "Take Away service is not ready yet.");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.TAKE_AWAY;
    }
}
