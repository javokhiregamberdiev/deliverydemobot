package uz.javohir.telegram_bot.botapi.handler.informationHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.service.MainMenuService;

@Component
public class InformationHandler implements InputMessageHandler {
    @Autowired
    private MainMenuService mainMenuService;

    @Override
    public SendMessage handle(Message message) {
        String text = "Information texts can be written on a variety of different topics, " +
                "from natural features and locations to historical figures and events. " +
                "We encounter them in our every-day lives whenever " +
                "we read something that provides with information.";
        return mainMenuService.getMainMenuMessage(message.getChatId(), text);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.INFORMATION;
    }
}
