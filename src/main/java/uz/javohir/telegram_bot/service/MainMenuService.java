package uz.javohir.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainMenuService {
    @Autowired
    private ReplyMessageService replyMessageService;

    public SendMessage getMainMenuMessage(final long chatId, final String textMessage) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard();
        final SendMessage mainMenuMessage =
                createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);

        return mainMenuMessage;
    }

    private ReplyKeyboardMarkup getMainMenuKeyboard() {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton1.setText(replyMessageService.getReplyText("button.order"));
        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText(replyMessageService.getReplyText("button.setting"));
        KeyboardButton keyboardButton3 = new KeyboardButton();
        keyboardButton3.setText(replyMessageService.getReplyText("button.tracking"));
        KeyboardButton keyboardButton4 = new KeyboardButton();
//        keyboardButton4.setText("Leave feedback");
//        KeyboardButton keyboardButton5 = new KeyboardButton();
//        keyboardButton5.setText("Information");
//        KeyboardButton keyboardButton6 = new KeyboardButton();
//        keyboardButton6.setText("Settings");


        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
//        KeyboardRow row3 = new KeyboardRow();
//        KeyboardRow row4 = new KeyboardRow();

        row1.add(keyboardButton1);
        row2.add(keyboardButton2);
        row2.add(keyboardButton3);
//        row3.add(keyboardButton4);
//        row3.add(keyboardButton5);
//        row4.add(keyboardButton6);
        keyboard.add(row1);
        keyboard.add(row2);
//        keyboard.add(row3);
//        keyboard.add(row4);
        replyKeyboardMarkup.setKeyboard(keyboard);
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
