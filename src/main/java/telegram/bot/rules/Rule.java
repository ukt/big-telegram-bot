package telegram.bot.rules;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;

public interface Rule {
    void run(Update update);
    default void callback(CallbackQuery callbackQuery){
        //nothing
    }
}