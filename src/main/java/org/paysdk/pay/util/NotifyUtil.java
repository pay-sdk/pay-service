package org.paysdk.pay.util;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.paysdk.pay.models.Payment;

public class NotifyUtil {

    public static void sendNotification(Payment payment, String tid) {

        BotUtil.bot.setUpdatesListener(updates -> {
            updates.forEach(System.out::println);

            updates.forEach(update -> {
                BotUtil.bot.execute(new SendMessage(tid,
                        "Поступил платеж!\n\n" +
                                payment.getProductName() + "\n" +
                                payment.getMoneyAmount() + "\n"));
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public static void sendNotification(String s, String tid) {

        BotUtil.bot.setUpdatesListener(updates -> {
            updates.forEach(System.out::println);

            updates.forEach(update -> {
                BotUtil.bot.execute(new SendMessage(tid,
                        "Notification Test"));
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
