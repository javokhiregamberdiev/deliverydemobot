package uz.javohir.telegram_bot.service;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Data
public class Ping {

    private String url = "https://www.google.com";

    @Scheduled(fixedRateString = "120000")
    public void pingMe() {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            connection.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
