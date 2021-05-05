package uz.javohir.telegram_bot.botapi.handler.eventsHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.service.MainMenuService;

@Component
public class EventsHandler implements InputMessageHandler {
    @Autowired
    private MainMenuService mainMenuService;

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(), message.getFrom().getFirstName() + "  Unfortunately we have not new events");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.EVENTS;
    }
}
