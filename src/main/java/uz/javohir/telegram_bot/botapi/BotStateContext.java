package uz.javohir.telegram_bot.botapi;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler ->
                this.messageHandlers.put(handler.getHandlerName(), handler
                ));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentStateHandler = findMessageHandler(currentState);
        return currentStateHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {

        if (isFillingProfileState(currentState)) {
            return messageHandlers.get(BotState.ASK_FILLING_PROFILE);
        }

        if (isAskLangState(currentState)) {
            return messageHandlers.get(BotState.ASK_LANG);
        }

        return messageHandlers.get(currentState);
    }

    private boolean isAskLangState(BotState currentState) {
        switch (currentState) {
            case ASK_LANG:
                return true;
            default:
                return false;
        }
    }

    private boolean isFillingProfileState(BotState currentState) {
        switch (currentState) {
            case ASK_FILLING_PROFILE:
            case ASK_PHONE:
            case ASK_CODE:
            case ASK_NAME:
            case ASK_FINISH:
                return true;
            default:
                return false;
        }
    }

}
