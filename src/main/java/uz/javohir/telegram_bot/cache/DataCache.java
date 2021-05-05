package uz.javohir.telegram_bot.cache;

import uz.javohir.telegram_bot.botapi.BotState;
import uz.javohir.telegram_bot.botapi.handler.UserProfileData;

public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfileData getUserProfileData(int userId);

    void saveUserProfileData(int userId, UserProfileData userProfileData);
}
