package org.paysdk.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.models.User;
import org.paysdk.pay.services.ProjectService;
import org.paysdk.pay.services.UserService;
import org.paysdk.pay.services.realizations.MessageService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class PayApplication implements ApplicationRunner {

	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper objectMapper = new ObjectMapper();

	private final MessageService messageService;
	private final UserService userService;
	private final ProjectService projectService;

	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {

		try {

			TelegramBot bot = new TelegramBot("1417055143:AAEH2kYow1YW_VSble3_xFqJVQivjrOqk_w");

			bot.setUpdatesListener(updates -> {
				updates.forEach(System.out::println);

				updates.forEach(update -> {

					if (update.message() == null) return;

					if (update.message().text().equals("/start")) {
						bot.execute(new SendMessage(update.message().chat().id(),
								"Добро пожаловать, " + update.message().from().firstName() + "!"));
					} else {
						// reg
						callbackRegisterCommand(bot, update);

						// create developer
						callbackAddDeveloperCommand(bot, update);

						// project
						callbackProjectCommand(bot, update);

						// add project
						processAddProjectCommand(bot, update);

						// add project
						callbackGetAllProjectsByTelegramIdCommand(bot, update);

						// history
						callbackHistoryCommand(bot, update);
					}
				});

				return UpdatesListener.CONFIRMED_UPDATES_ALL;
			});
		} catch (Exception e) {
			// ignore
		}

	}

	private void callbackGetAllProjectsByTelegramIdCommand(TelegramBot bot, Update update) {
		if (update.message().text().equals("/my")) {

			List<Project> projects = projectService.findByTelegramId(update.message().chat().id().toString());

			String projectsList = "";

			if (projects != null && projects.size() > 0) {
				for (Project project : projects) {
					projectsList = project + "\n";
				}

				bot.execute(new SendMessage(update.message().chat().id(),
						"<b>Ваши проекты:</b>\n" +
								projectsList).parseMode(ParseMode.HTML));
			} else {
				bot.execute(new SendMessage(update.message().chat().id(),
						"У вас нет проектов, пожалуйста добавьте с помощью /project.").parseMode(ParseMode.HTML));
			}
		}
	}

	private void processAddProjectCommand(TelegramBot bot, Update update) {

		try {
			if (update.message().text().startsWith("project")) {

				Project project = messageService.extractProject(update.message().text());
				User userFromDb = userService.findByTelegramId(update.message().chat().id().toString());

				if (userFromDb == null) {
					bot.execute(new SendMessage(update.message().chat().id(),
							"Вы не зарегистрированы как разработчик. Пожалуйста воспользуйтесь командой " +
									"<b>/reg</b> для регистрации. А чтобы посмотреть список проектов, наберите " +
									"/my.").parseMode(ParseMode.HTML));
					return;
				}

				project.setUser(userFromDb);
				Project storedProject = projectService.save(project);

				bot.execute(new SendMessage(update.message().chat().id(),
						"Проект " + storedProject.getName() + " готов.\n" +
								"Ваш токен:\n\n" +
								"<b>" + storedProject.getToken() + "</b>").parseMode(ParseMode.HTML));
			}
		} catch (Exception e) {
			// ignore
		}
	}

	private void callbackRegisterCommand(TelegramBot bot, com.pengrad.telegrambot.model.Update update) {
		if (update.message().text().equals("/reg")) {
			bot.execute(new SendMessage(update.message().chat().id(),
					"Для того, чтобы зарегистрировать нового разработчика, отправьте " +
							"следующее сообщение с тремя элементами:\n\n" +
							"<b>developer</b> - кодовое слово\n<b>MerchantId</b> - Идентификатор продавца" +
							"\n<b>SecretKey</b> - секретный ключ\n\n" +
							"<i>Обратите внимание, что каждый элемент должен начинаться " +
							"с новой строки. Пример:</i>\n\n" +
							"developer\n384823903\n49308232").parseMode(ParseMode.HTML));
		}
	}

	private void callbackProjectCommand(TelegramBot bot, com.pengrad.telegrambot.model.Update update) {
		if (update.message().text().equals("/project")) {
			bot.execute(new SendMessage(update.message().chat().id(),
					"Для того, чтобы создать новый проект, отправьте " +
							"следующее сообщение с двумя элементами:\n\n" +
							"<b>project</b> - кодовое слво\n<b>My First Project</b> - название проекта\n\n" +
							"<i>Обратите внимание, что каждый элемент должен начинаться " +
							"с новой строки. Пример:</i>\n\n" +
							"project\nMy First Project\n").parseMode(ParseMode.HTML));
		}
	}

	private void callbackHistoryCommand(TelegramBot bot, Update update) {
		if (update.message().text().equals("/history")) {
			bot.execute(new SendMessage(update.message().chat().id(),
					"<b>Платежи за последний месяц</b>\n" +
							"... ...\n" +
							"... ...\n" +
							"... ...\n" +
							"... ...").parseMode(ParseMode.HTML));
		}
	}

	private void callbackAddDeveloperCommand(TelegramBot bot, Update update) {
		// new message text
		String message = update.message().text();

		try {
			if (message.startsWith("developer\n")) {

				User user = messageService.extractUser(message);
				user.setTelegramId(update.message().chat().id().toString());

				userService.save(user);

				bot.execute(new SendMessage(update.message().chat().id(),
						"Вы были успешно зарегистрированы как разработчик.\n" +
								"Теперь вы можете создать свой проект для получения токена,\n" +
								"используя команду /project.").parseMode(ParseMode.HTML));

//            bot.execute(new SendMessage(update.message().chat().id(),
//                    "Ваш токен:\n\n" +
//                            "<b>KLjdi89REb3894Fdbb8KJEosfd3f3Ie4</b>").parseMode(ParseMode.HTML));
			}
		} catch (Exception e) {
			// ignore
		}
	}
}
