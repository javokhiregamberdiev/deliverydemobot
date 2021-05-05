package uz.javohir.telegram_bot.botapi;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.javohir.telegram_bot.cache.UserDataCache;
import uz.javohir.telegram_bot.service.MainMenuService;

@Component
public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private MainMenuService mainMenuService;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache, MainMenuService mainMenuService) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.mainMenuService = mainMenuService;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

//
//        if (update.hasCallbackQuery()){
//            CallbackQuery callbackQuery = update.getCallbackQuery();
//            return processCallbackQuery(callbackQuery);
//        }

        Message message = update.getMessage();
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
                botState = BotState.ASK_FILLING_PROFILE;
                break;
            case "English":
                botState = BotState.ASK_FILLING_PROFILE;
                break;
            case "Русский язык":
                botState = BotState.ASK_FILLING_PROFILE;
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
            case "Events":
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
                botState = BotState.SETTINGS;
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

//    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery){
//
//        final long chatId = buttonQuery.getMessage().getChatId();
//        final int userId = buttonQuery.getFrom().getId();
//        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId, "Use the main menu");
//
//        if (buttonQuery.getData().equals("buttonYes")){
//            callBackAnswer = new SendMessage(String.valueOf(chatId), "Enter the username");
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_PASSWORD);
//        }else if (buttonQuery.getData().equals("buttonNo")){
//            callBackAnswer = sendAnswerCallbackQuery(buttonQuery.getFrom().getFirstName() + "Come back when you're ready", true, buttonQuery);
//        }
//        return callBackAnswer;
//    }
////    Come back when you're ready
//    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackQuery){
//        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
//        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
//        answerCallbackQuery.setShowAlert(alert);
//        answerCallbackQuery.setText(text);
//        return answerCallbackQuery;
//    }

}
