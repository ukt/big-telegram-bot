package telegram.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import helper.file.SharedObject;
import javafx.util.Pair;
import telegram.bot.dto.ActionItemDto;

import java.util.*;

import static telegram.bot.data.Common.ACTION_ITEMS2;
import static telegram.bot.data.Common.RESOLVED_ACTION_ITEMS;

public class ClearActionItem implements Command {

    @Override
    public Pair<ParseMode, List<String>> run(Update update, String values) {
        HashMap<Integer, ActionItemDto> actionItems = SharedObject.loadMap(ACTION_ITEMS2, new HashMap<Integer, ActionItemDto>());
        HashMap<Integer, ActionItemDto> resolvedItems = SharedObject.loadMap(RESOLVED_ACTION_ITEMS, new HashMap<Integer, ActionItemDto>());
        Integer key = values.isEmpty()?0:Integer.parseInt(values);
        if (actionItems.containsKey(key)) {
            ActionItemDto remove = actionItems.remove(key);
            resolvedItems.put(key, remove);
            SharedObject.save(ACTION_ITEMS2, actionItems);
            SharedObject.save(RESOLVED_ACTION_ITEMS, resolvedItems);
            String message = String.format("Action item successfully resolved. <pre>%s</pre>\nTap /showActionItems to update list", remove.getValue());
            return new Pair<>(ParseMode.HTML, Collections.singletonList(message));
        }
        return new Pair<>(ParseMode.HTML, Collections.singletonList("Action item does not exist. \nTap /showActionItems to update list"));
    }
}