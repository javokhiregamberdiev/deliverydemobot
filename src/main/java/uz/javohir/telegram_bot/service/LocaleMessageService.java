package uz.javohir.telegram_bot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class LocaleMessageService {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
    private final MessageSource messageSource;

    public LocaleMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void getLocaleResourceBundle(String localeTag) {
        if (! localeTag.equals(""))
            resourceBundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag(localeTag));
    }

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, resourceBundle.getLocale());
    }

    public String getMessage(String message, Object... args) {
        return messageSource.getMessage(message, args, resourceBundle.getLocale());
    }

//    public void setLanguage(String localTag){
//        getLocaleResourceBundle(localTag);
//    }
}
