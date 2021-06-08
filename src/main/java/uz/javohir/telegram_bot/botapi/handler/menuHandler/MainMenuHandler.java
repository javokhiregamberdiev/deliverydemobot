package uz.javohir.telegram_bot.botapi.handler.menuHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.service.MainMenuService;
import uz.javohir.telegram_bot.service.ReplyMessageService;

@Component
public class MainMenuHandler implements InputMessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Autowired
    private ReplyMessageService replyMessageService;

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(), replyMessageService.getReplyText("reply.mainMenu.welcomeMessage"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;
    }
}
