package uz.javohir.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.javohir.telegram_bot.utils.Emojis;

import java.util.ArrayList;
import java.util.List;

@Service
public class LangService {
    @Autowired
    private ReplyMessageService replyMessageService;
    
    public SendMessage getLanguageMessage(final Long chatId, final String textMessage){
        final ReplyKeyboardMarkup replyKeyboardMarkup = getLanguageKeyboard();
        final SendMessage languageMessage = createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
        return languageMessage;
    }


    public ReplyKeyboardMarkup getLanguageKeyboard(){

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        row1.add(new KeyboardButton(replyMessageService.getReplyText("button.language.uzbek")));
        row2.add(new KeyboardButton(replyMessageService.getReplyText("button.language.english")));
        row3.add(new KeyboardButton(replyMessageService.getReplyText("button.language.russian")));
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;

    }
    
    private SendMessage createMessageWithKeyboard(final Long chatId, String textMessage, 
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup){
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textMessage);
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }
}
