package uz.javohir.telegram_bot.utils;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emojis {
    FLAG_UZB(EmojiParser.parseToUnicode("::")),
    FLAG_ENG(EmojiParser.parseToUnicode(":flag_united_kingdom:")),
    FLAG_RUS(EmojiParser.parseToUnicode(":flag_russia:")),
    SUCCESS_MARK(EmojiParser.parseToUnicode(":check_mark_button:")),
    NOTIFICATION_MARK_FAILED(EmojiParser.parseToUnicode(":exclamation:"));

    private String emojiName;
    @Override
    public String toString() {
        return emojiName;
    }
}
