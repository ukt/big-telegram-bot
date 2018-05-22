package telegram.bot.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.response.BaseResponse;
import helper.string.StringHelper;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ShowAlertFromResource implements Command {
    private Function<Update, String> filePathSupplier;
    private TelegramBot bot;
    public ShowAlertFromResource(String filePathSupplier, TelegramBot bot) {
        this(update -> filePathSupplier, bot);
    }

    public ShowAlertFromResource(Function<Update, String> filePathSupplier, TelegramBot bot) {
        this.filePathSupplier = filePathSupplier;
        this.bot = bot;
    }

    @Override
    public Pair<ParseMode, List<String>> run(Update update, String values) {
        CallbackQuery callbackQuery = update.callbackQuery();
        String text;
        try {
            text = StringHelper.getFileAsString(filePathSupplier.apply(update));
        } catch (IOException e) {
            e.printStackTrace();
            return new Pair<>(ParseMode.HTML, Collections.singletonList("Fail: " + e.getMessage()));
        }
        BaseResponse response = bot.execute(new AnswerCallbackQuery(callbackQuery.id())
            .text(text)
            .showAlert(true)
        );
        return new Pair<>(ParseMode.HTML, Collections.singletonList("Ok: " + response.description()));
    }
}
