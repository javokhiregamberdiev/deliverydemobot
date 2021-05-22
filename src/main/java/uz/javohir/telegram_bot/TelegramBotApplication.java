package uz.javohir.telegram_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TelegramBotApplication {

    public static void main(String[] args) {

//        ApiContextInitializer.init();
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
