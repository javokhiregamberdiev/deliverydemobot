package uz.javohir.telegram_bot.botapi.handler.languageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.cache.UserDataCache;
import uz.javohir.telegram_bot.service.LangService;
import uz.javohir.telegram_bot.service.MainMenuService;
import uz.javohir.telegram_bot.service.ReplyMessageService;
import uz.javohir.telegram_bot.utils.Emojis;

import java.util.ArrayList;
import java.util.List;

@Component
public class LangHandler implements InputMessageHandler {

    @Autowired
    private ReplyMessageService replyMessageService;

    @Autowired
    private LangService langService;

    @Autowired
    private UserDataCache userDataCache;

    @Override
    public SendMessage handle(Message message) {

        String flagEng = "\ud83c\uddec\ud83c\udde7";
        String flagRu = "\ud83c\uddf7\ud83c\uddfa";
        String flagUz = "\ud83c\uddfa\ud83c\uddff";

        SendMessage replyToUser = replyMessageService.getReplyMessage(message.getChatId(), "reply.language.select", flagEng, flagRu, flagUz);
        replyToUser.setReplyMarkup(getInlineMessageButtons());

        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_PHONE);

        return replyToUser;
    }


    public InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        String flagEng = "\ud83c\uddec\ud83c\udde7";
        String flagRu = "\ud83c\uddf7\ud83c\uddfa";
        String flagUz = "\ud83c\uddfa\ud83c\uddff";
        InlineKeyboardButton buttonEnglish = new InlineKeyboardButton();
        buttonEnglish.setText(replyMessageService.getReplyText("button.language.english", flagEng));
        InlineKeyboardButton buttonRussian = new InlineKeyboardButton();
        buttonRussian.setText(replyMessageService.getReplyText("button.language.russian", flagRu));
        InlineKeyboardButton buttonUzbek = new InlineKeyboardButton();
        buttonUzbek.setText(replyMessageService.getReplyText("button.language.uzbek", flagUz));

        buttonEnglish.setCallbackData("english");
        buttonRussian.setCallbackData("russian");
        buttonUzbek.setCallbackData("uzbek");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonEnglish);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonRussian);

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonUzbek);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_LANG;
    }

}
