package uz.javohir.telegram_bot.entities;

import lombok.Data;
import uz.javohir.telegram_bot.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "telegram_users")
public class TelegramUser extends BaseEntity {

    private Long telegramUserId;
    private Long chatId;
    private String phone;
    private String fullName;

}
