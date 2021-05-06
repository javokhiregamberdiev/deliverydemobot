package uz.javohir.telegram_bot.botapi.handler.settingsHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.service.SettingService;

@Component
public class SettingsHandler implements InputMessageHandler {

    @Autowired
    private SettingService settingService;

    @Override
    public SendMessage handle(Message message) {
        return settingService.getSettingsMessage(message.getChatId(), "Setting bot");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SETTINGS;
    }
}
