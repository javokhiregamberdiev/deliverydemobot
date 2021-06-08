package uz.javohir.telegram_bot.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.javohir.telegram_bot.utils.Emojis;

@Service
public class ReplyMessageService {
    private LocaleMessageService localeMessageService;

    public ReplyMessageService(LocaleMessageService messageService) {
        this.localeMessageService = messageService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage, Object... args) {
        return new SendMessage(String.valueOf(chatId), localeMessageService.getMessage(replyMessage, args));
    }

    public String getReplyText(String replyText) {
        return localeMessageService.getMessage(replyText);
    }

    public String getReplyText(String replyText, Object... args) {
        return localeMessageService.getMessage(replyText, args);
    }

    public void setLanguage(String localTag){
        localeMessageService.getLocaleResourceBundle(localTag);
    }

}
