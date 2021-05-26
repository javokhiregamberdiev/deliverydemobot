package uz.javohir.telegram_bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettingService {

    public SendMessage getSettingsMessage(final long chatId, final String textMessage) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getSettingsKeyboard();
//        final SendMessage mainMenuMessage =
//                createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), "asd");


        return sendMessage;
    }

    private ReplyKeyboardMarkup getSettingsKeyboard() {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

//        KeyboardButton keyboardButton1 = new KeyboardButton();
//        keyboardButton1.setText("Edit name");
//        KeyboardButton keyboardButton2 = new KeyboardButton();
//        keyboardButton2.setText("Edit number");
        KeyboardButton keyboardButton3 = new KeyboardButton();
        keyboardButton3.setText("Select language");
        KeyboardButton keyboardButton4 = new KeyboardButton();
        keyboardButton4.setText("Send Location");
        keyboardButton4.setRequestLocation(true);
        KeyboardButton keyboardButton5 = new KeyboardButton();
        keyboardButton5.setText("Go home");

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
//
//        row1.add(keyboardButton1);
//        row1.add(keyboardButton2);
        row2.add(keyboardButton3);
        row2.add(keyboardButton4);
        row3.add(keyboardButton5);
//        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboard);
//        replyKeyboardMarkup.setKeyboard((List<KeyboardRow>) forceReplyKeyboard);
        return replyKeyboardMarkup;
    }

    private ReplyKeyboardMarkup forceReply(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(final long chatId,
                                                  String textMessage,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup) {
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
