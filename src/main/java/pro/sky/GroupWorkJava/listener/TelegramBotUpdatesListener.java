package pro.sky.GroupWorkJava.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.KeyBoard.KeyBoardShelter;
import pro.sky.GroupWorkJava.model.Person;
import pro.sky.GroupWorkJava.repository.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {


    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private static final String START_CMD = "/start";

    private static final String GREETING_TEXT = ", Приветствую! Чтобы найти то, что тебе нужно - нажми на нужную кнопку";

    private static final String INVALID_ID_NOTIFY_OR_CMD = "Такой команды не существует";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private KeyBoardShelter keyBoardShelter;

    @Autowired
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
            Integer messageId = update.message().messageId();
            Long VolonterChat = 440504531L;


            if (update.message().contact().phoneNumber() != null) {
                String firstName = update.message().contact().firstName();
                String lastName = update.message().contact().lastName();
                String phone = update.message().contact().phoneNumber();
                long chatId = update.message().chat().id();
                var sortChatId = personRepository.findAll().stream().filter(i -> i.getChatId() == chatId)
                        .collect(Collectors.toList());
                if (!sortChatId.isEmpty()) {
                    sendMessage(chatId, "Вы уже в базе");
                    return;
                }
                if (lastName != null) {
                    String name = firstName + " " + lastName;
                    personRepository.save(new Person(name, phone, chatId));
                    return;
                }
                personRepository.save(new Person(firstName, phone, chatId));
                return;
            }
 //            phone = update.message().contact().phoneNumber();
//            if (phone != null) {
//
//                sendMessage(VolonterChat, "Тут перезвонить надо " + phone + " " + nameUser);
//                System.out.println(phone);
//            }

            Integer message123 = update.message().forwardFromMessageId();
            long chatId = update.message().chat().id();


            try {
                switch (textUpdate) {
                    case START_CMD:
                        sendMessage(chatId, nameUser + GREETING_TEXT);
                        keyBoardShelter.sendMenu(chatId);
                        break;
                    case "Как взять питомца из приюта":
                        keyBoardShelter.sendMenuTakeAnimal(chatId);
                        break;
                    case "Узнать информацию о приюте":
                        keyBoardShelter.sendMenuInfoShelter(chatId);
                        break;
                    case "Вернуться в меню":
                        keyBoardShelter.sendMenu(chatId);
                        break;
                    case "Привет":
                        if (messageId != null) {
                            sendReplyMessage(chatId, "И тебе привет", messageId);
                            break;
                        }
//                    case "Позвать волонтера":
//                            sendReplyMessage(VolonterChat, "Тут перезвонить надо " + phone, messageId);
//                            break;
                    case "Contact":
                        if (messageId != null) {
                            sendReplyMessage(chatId, "123", messageId);
                            break;
                        }
                    case "null":
                        System.out.println("Нельзя");
                        sendMessage(chatId, "Я не знаю такой команды(NULL)");
                        break;
                    case "":
                        System.out.println("Нельзя");
                        sendMessage(chatId, "Пустое сообщение");
                        break;

                    default:
//                        if (messageId != null) {
//                        message.replyToMessage().text();
//                        sendMsg(message, "Скоро вам ответят");
                        sendReplyMessage(chatId, "Я не знаю такой команды", messageId);
//                        }
                        break;

                }
            } catch (NullPointerException e) {
                sendReplyMessage(chatId, "Ошибка", messageId);
                System.out.println("Ошибка");
            }

        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendReplyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
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

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage(message, text);
        sendMessage.replyToMessageId(message.messageId());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
    }

}
