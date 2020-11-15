package org.paysdk.pay.util;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;

public class NotifyUtil {

    public static void sendNotification() {

        BotUtil.bot.setUpdatesListener(updates -> {
            updates.forEach(System.out::println);

            updates.forEach(update -> {
                BotUtil.bot.execute(new SendMessage(update.message().chat().id(),
                        "Добро пожаловать, " + update.message().from().firstName() + "!"));
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
