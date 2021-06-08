package uz.javohir.telegram_bot.botapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.javohir.telegram_bot.botapi.handler.UserProfileData;
import uz.javohir.telegram_bot.cache.UserDataCache;
import uz.javohir.telegram_bot.service.MainMenuService;
import uz.javohir.telegram_bot.service.ReplyMessageService;
import uz.javohir.telegram_bot.service.ShareContactService;

@Component
public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private MainMenuService mainMenuService;
    private ReplyMessageService replyMessageService;
    @Autowired
    private ShareContactService shareContactService;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache, MainMenuService mainMenuService, ReplyMessageService replyMessageService) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.mainMenuService = mainMenuService;
        this.replyMessageService = replyMessageService;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();

        if (update.hasCallbackQuery()){
            return processCallbackQuery(update.getCallbackQuery());
        }

        if (message.hasContact() && message != null ){
            BotState botState = BotState.ASK_FINISH;
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), botState);
            message.getLocation();
            replyMessage = botStateContext.processInputMessage(botState, message);
        }

        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }



    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.ASK_LANG;
                break;
            case "Uzbek tili":
                botState = BotState.ASK_PHONE;
                break;
            case "English":
                botState = BotState.ASK_PHONE;
                break;
            case "Русский язык":
                botState = BotState.ASK_PHONE;
                break;
            case "Order":
                botState = BotState.ORDER;
                break;
            case "Delivery":
                botState = BotState.DELIVERY;
                break;
            case "Take away":
                botState = BotState.TAKE_AWAY;
                break;
            case "Go home":
                botState = BotState.MAIN_MENU;
                break;
            case "Cashback":
                botState = BotState.CASHBACK;
                break;
            case "Tracking":
                botState = BotState.EVENTS;
                break;
            case "Leave feedback":
                botState = BotState.LEAVE_FEEDBACK;
                break;
            case "Information":
                botState = BotState.INFORMATION;
                break;
            case "Settings":
                botState = BotState.SETTINGS;
                break;
            case "Edit name":
                botState = BotState.SETTINGS;
                break;
            case "Edit number":
                botState = BotState.SETTINGS;
                break;
            case "Select language":
                botState = BotState.SELECT_LANG;
                break;
            case "Select city":
                botState = BotState.SETTINGS;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery){

        UserProfileData userProfileData = userDataCache.getUserProfileData(buttonQuery.getFrom().getId());

        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();

        if (buttonQuery.getData().equals("english")){
            userProfileData.setLocaleTag("en-EN");
            userDataCache.saveUserProfileData(userId,userProfileData);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_CODE);
        }else if (buttonQuery.getData().equals("russian")){
            userProfileData.setLocaleTag("ru-RU");
            userDataCache.saveUserProfileData(userId,userProfileData);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_CODE);
        }else if (buttonQuery.getData().equals("uzbek")){
            userProfileData.setLocaleTag("uz-UZ");
            userDataCache.saveUserProfileData(userId,userProfileData);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_CODE);
        }

        replyMessageService.setLanguage(userProfileData.getLocaleTag());

        return shareContactService.getShareContactMsg(chatId, replyMessageService.getReplyText("reply.askPhone"));
    }
//    Come back when you're ready
    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackQuery){
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }

}
