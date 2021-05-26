package uz.javohir.telegram_bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShareLocationService {
    public SendMessage getShareLocationMsg(final Long chatId, final String textMessage){
        final ReplyKeyboardMarkup replyKeyboardMarkup = getContactKeyboard();
        final SendMessage languageMessage = createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
        return languageMessage;
    }

    public ReplyKeyboardMarkup getContactKeyboard(){

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("Share contact");
        button.setRequestContact(true);
        row1.add(button);
        keyboard.add(row1);

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
