package org.paysdk.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import org.paysdk.pay.dto.UserRequest;
import org.paysdk.pay.models.User;
import org.paysdk.pay.services.realizations.MessageService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@SpringBootApplication
public class PayApplication implements ApplicationRunner {

	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper objectMapper = new ObjectMapper();

	private final MessageService messageService;


	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {

		TelegramBot bot = new TelegramBot("1417055143:AAEH2kYow1YW_VSble3_xFqJVQivjrOqk_w");

		bot.setUpdatesListener(updates -> {
			updates.forEach(System.out::println);

			updates.forEach(update -> {

				if (update.message().text().equals("/start")) {
					bot.execute(new SendMessage(update.message().chat().id(),
							"Добро пожаловать, " + update.message().from().firstName() + "!"));
				} else {
					processAddCommand(bot, update);
					processHistoryCommand(bot, update);
					processDeveloperCommand(bot, update);
				}
			});

			// TODO: implement it

			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});

	}

	private void processAddCommand(TelegramBot bot, com.pengrad.telegrambot.model.Update update) {
		if (update.message().text().equals("/add")) {
			bot.execute(new SendMessage(update.message().chat().id(),
					"Для того, чтобы зарегистрировать нового разработчика, отправьте " +
							"следующее сообщение с тремя элементами:\n\n" +
							"<b>1.Слово developer</b>\n<b>2.MerchantId</b>\n<b>3.SecretKey</b>\n\n" +
							"<i>Обратите внимание, что каждый элемент должен начинаться " +
							"с новой строки. Пример:</i>\n\n" +
							"developer\n384823903\n49308232").parseMode(ParseMode.HTML));
		}
	}

	private void processProjectCommand(TelegramBot bot, com.pengrad.telegrambot.model.Update update) {
		if (update.message().text().equals("/add")) {
			bot.execute(new SendMessage(update.message().chat().id(),
					"Для того, чтобы зарегистрировать нового разработчика, отправьте " +
							"следующее сообщение с тремя элементами:\n\n" +
							"<b>1.Команда new</b>\n<b>2.MerchantId</b>\n<b>3.SecretKey</b>\n\n" +
							"<i>Обратите внимание, что каждый элемент должен начинаться " +
							"с новой строки. Пример:</i>\n\n" +
							"new\n384823903\n49308232").parseMode(ParseMode.HTML));
		}
	}

	private void processHistoryCommand(TelegramBot bot, Update update) {
		if (update.message().text().equals("/history")) {
			bot.execute(new SendMessage(update.message().chat().id(),
					"<b>Платежи за последний месяц</b>\n" +
							"... ...\n" +
							"... ...\n" +
							"... ...\n" +
							"... ...").parseMode(ParseMode.HTML));
		}
	}

	private void processDeveloperCommand(TelegramBot bot, Update update) {
		// new message text
		String message = update.message().text();

		if (message.startsWith("developer\n")) {

			User user = messageService.extractUser(message.substring(9));
			user.setTelegramId(update.message().chat().id().toString());

			bot.execute(new SendMessage(update.message().chat().id(),
					"Вы были успешно зарегистрированы.").parseMode(ParseMode.HTML));

//            bot.execute(new SendMessage(update.message().chat().id(),
//                    "Ваш токен:\n\n" +
//                            "<b>KLjdi89REb3894Fdbb8KJEosfd3f3Ie4</b>").parseMode(ParseMode.HTML));
		}
	}
}
