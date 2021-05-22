package uz.javohir.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.javohir.telegram_bot.entities.TelegramUser;

import java.util.List;

@Repository
public interface TelegramUserRepo extends JpaRepository<Long, TelegramUser> {

}
