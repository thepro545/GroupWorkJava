package pro.sky.GroupWorkJava.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private static final String START_CMD = "/start";

    private static final String GREETING_TEXT = ", Приветствую! Чтобы найти то, что тебе нужно - нажми на нужную кнопку";

    private static final String INVALID_ID_NOTIFY_OR_CMD = "Такой команды не существует";

    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            String nameUser = update.message().chat().firstName();
            String textUpdate = update.message().text();
            long chatId = update.message().chat().id();

            if (textUpdate.equals(START_CMD)) {
                sendMessage(chatId, nameUser + GREETING_TEXT);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendMessage(Long chatId, String messageText) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMessage);
    }

//    public void sendMessage(NotificationTask task) {
//        sendMessage(task.getChatId(), task.getNotificationMessage());
//    }


    //отправка сообщений в ТГ Бот
    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse sendResponse = telegramBot.execute(message);
    }

}
