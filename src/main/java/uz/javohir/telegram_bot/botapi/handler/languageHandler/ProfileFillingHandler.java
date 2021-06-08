package uz.javohir.telegram_bot.botapi.handler.languageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.InputMessageHandler;
import uz.javohir.telegram_bot.botapi.handler.UserProfileData;
import uz.javohir.telegram_bot.cache.DataCache;
import uz.javohir.telegram_bot.cache.UserDataCache;
import uz.javohir.telegram_bot.service.MainMenuService;
import uz.javohir.telegram_bot.service.ReplyMessageService;
import uz.javohir.telegram_bot.service.ShareContactService;
import uz.javohir.telegram_bot.utils.Emojis;

@Component
public class ProfileFillingHandler implements InputMessageHandler {

    @Autowired
    private UserDataCache userDataCache;
    @Autowired
    private ReplyMessageService replyMessageService;
    @Autowired
    private MainMenuService mainMenuService;
    @Autowired
    private ShareContactService shareContactService;

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.ASK_FILLING_PROFILE)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_NAME);
        }
        return processInputLang(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_FILLING_PROFILE;
    }

    private SendMessage processInputLang(Message message) {
        String userAnswer = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        UserProfileData userProfileData = userDataCache.getUserProfileData(userId);
        replyMessageService.setLanguage(userProfileData.getLocaleTag());
        SendMessage replyMessageToUser = null;
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        if (botState.equals(BotState.ASK_NAME)) {
            replyMessageToUser = replyMessageService.getReplyMessage(chatId, "Enter full name");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_PHONE);
        }

        if (botState.equals(BotState.ASK_PHONE)) {
//            replyMessageToUser = replyMessageService.getReplyMessage1(chatId, "Enter phone number");
            replyMessageToUser = shareContactService.getShareContactMsg(chatId, replyMessageService.getReplyText("reply.askPhone"));
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_CODE);
        }
        if (botState.equals(BotState.ASK_CODE)) {
//            replyMessageToUser = replyMessageService.getReplyMessage1(chatId, "Enter confirm code");
            if (userAnswer != null && !userAnswer.startsWith("+9989")){
                replyMessageToUser = new SendMessage(String.valueOf(chatId), replyMessageService.getReplyText("reply.askPhoneAgain"));
                userDataCache.setUsersCurrentBotState(userId, BotState.ASK_CODE);
            }else{
                userDataCache.setUsersCurrentBotState(userId, BotState.ASK_FINISH);
            }
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_FINISH);
        }
        if (botState.equals(BotState.ASK_FINISH)) {
            replyMessageToUser = mainMenuService.getMainMenuMessage(chatId, replyMessageService.getReplyText("reply.mainMenu.welcomeMessage"));
            userDataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);
        }
        return replyMessageToUser;

    }
}
