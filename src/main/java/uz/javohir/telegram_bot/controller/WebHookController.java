package uz.javohir.telegram_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.javohir.telegram_bot.TelegramBot;
import uz.javohir.telegram_bot.botapi.TelegramFacade;

@RestController
public class WebHookController {

    @Autowired
    private TelegramFacade telegramFacade;

    @Autowired
    private TelegramBot telegramBot;

//    @RequestMapping(value = "/api/v1/telegram/{token}", method = RequestMethod.POST)
//    public BotApiMethod<?> onUpdateReceived(@PathVariable String token, @RequestBody Update update) {
//
//        System.out.println(token);
//
//        return telegramFacade.handleUpdate(update);
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return telegramBot.onWebhookUpdateReceived(update);
    }
}
