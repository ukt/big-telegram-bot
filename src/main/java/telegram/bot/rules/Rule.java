package telegram.bot.rules;

import com.pengrad.telegrambot.model.Update;

public interface Rule {
    void run(Update update);
}
