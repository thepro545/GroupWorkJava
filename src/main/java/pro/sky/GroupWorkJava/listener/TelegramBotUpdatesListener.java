package pro.sky.GroupWorkJava.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.KeyBoard.KeyBoardShelter;
import pro.sky.GroupWorkJava.model.Person;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.repository.PersonRepository;
import pro.sky.GroupWorkJava.repository.ReportRepository;
import pro.sky.GroupWorkJava.service.PhotoService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {


    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private static final String START_CMD = "/start";

    private static final String GREETING_TEXT = ", Приветствую! Чтобы найти то, что тебе нужно - нажми на нужную кнопку";

    private static final String infoAboutShelter = "Наш сайт с информацией \nhttps://google.com \n" +
            "Контактные данные \nhttps://yandex.ru\n" +
            "Общие рекомендации \nhttps://ru.wikipedia.org\n" +
            "";
    private static final String infoAboutDogs = "Правила знакомства с животным \nhttps://google.com \n" +
            "Список документов \nhttps://yandex.ru\n" +
            "Список рекомендаций \nhttps://ru.wikipedia.org\n" +
            "Советы кинолога \nhttps://ru.wikipedia.org\n" +
            "Прочая информация \nhttps://google.com\n" +
            "";

    private static final String infoContactsVolonter = "Контактные данные волонтера  \n@thepro545 \n" +
            "Телефон - +7 999 999 99 99 \n";
    private static final String infoAboutReport = "Для отчета нужна следующая информация:\n" +
            "- Фото животного.  \n" +
            "- Рацион животного\n" +
            "- Общее самочувствие и привыкание к новому месту\n" +
            "- Изменение в поведении: отказ от старых привычек, приобретение новых.\nСкопируйте следующий пример";

    private static final String reportExample = "Рацион: ваш текст;\n" +
            "Самочувствие: ваш текст;  \n" +
            "Поведение: ваш текст;";

    private static final String REGEX_MESSAGE = "(Рацион:)(\\s)([\\W]+)(;)\n" +
            "(Самочувствие:)(\\s)([\\W]+)(;)\n" +
            "(Поведение:)(\\s)([\\W]+)(;)";


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private KeyBoardShelter keyBoardShelter;
    @Autowired
    private PhotoService photoService;
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

//            if (update.message() != null && update.message().photo() != null && update.message().caption() != null) {
////                getReport(update);
////                savePhoto(update);
////                photoService.textCaption(update, update.message().caption());
////                Pattern pattern = Pattern.compile(REGEX_MESSAGE);
//                Pattern pattern = Pattern.compile("(Рацион:)(\\s)([\\W]+)(;)\n" +
//                        "(Самочувствие:)(\\s)([\\W]+)(;)\n" +
//                        "(Поведение:)(\\s)([\\W]+)(;)");
//                Matcher matcher = pattern.matcher(update.message().caption());
//                System.out.println("222");
//                if (matcher.matches()) {
//                    System.out.println("123");
//                    String ration = matcher.group(3);
//                    String health = matcher.group(7);
//                    String habits = matcher.group(11);
//                    System.out.println(ration);
//                    System.out.println(health);
//                    System.out.println(habits);
//                }
//            }

            if (update.message().photo() != null && update.message().caption() != null) {
                Pattern pattern = Pattern.compile("(Рацион:)(\\s)([\\W]+)(;)\n" +
                        "(Самочувствие:)(\\s)([\\W]+)(;)\n" +
                        "(Поведение:)(\\s)([\\W]+)(;)");
                Matcher matcher = pattern.matcher(update.message().caption());
                System.out.println(matcher);
                if (matcher.matches()) {
                    String rac = matcher.group(3);
                    String pov = matcher.group(7);
                    String hab = matcher.group(11);

                    System.out.println(rac);
                    System.out.println(pov);
                    System.out.println(hab);
                }
            }
            // Добавление имени и телефона в базу через кнопку оставить контакты
            if (update.message() != null && update.message().contact() != null) {
                shareContact(update);

            }


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
                    case "Информация о приюте":
                        sendMessage(chatId, infoAboutShelter);
                        break;
                    case "Советы и рекомендации":
                        sendMessage(chatId, infoAboutDogs);
                        break;
                    case "Прислать отчет о питомце":
                        sendMessage(chatId, infoAboutReport);
                        sendMessage(chatId, reportExample);
                        break;
                    case "Вернуться в меню":
                        keyBoardShelter.sendMenu(chatId);
                        break;
                    case "Привет":
                        if (messageId != null) {
                            sendReplyMessage(chatId, "И тебе привет", messageId);
                            keyBoardShelter.checkInline(chatId);
                            break;
                        }
                    case "Позвать волонтера":
                        sendMessage(chatId, infoContactsVolonter);
                        break;

                    case "null":
                        System.out.println("Нельзя");
                        sendMessage(chatId, "Я не знаю такой команды(NULL)");
                        break;
                    case "":
                        System.out.println("Нельзя");
                        sendMessage(chatId, "Пустое сообщение");
                        break;
                    default:
                        sendReplyMessage(chatId, "Я не знаю такой команды", messageId);
                        break;

                }
//                if (update.message().chat() != null) {
//                    sendReplyMessage(chatId, "Я не знаю что это. Попробуйте другую функцию",messageId);
//                }
            } catch (NullPointerException e) {
                sendReplyMessage(chatId, "Ошибка. Я не понимаю это сообщение", messageId);
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

    public void shareContact(Update update) {
        if (update.message().contact() != null) {
            String firstName = update.message().contact().firstName();
            String lastName = update.message().contact().lastName();
            String phone = update.message().contact().phoneNumber();
            long finalChatId = update.message().chat().id();
            var sortChatId = personRepository.findAll().stream().filter(i -> i.getChatId() == finalChatId)
                    .collect(Collectors.toList());
            if (!sortChatId.isEmpty()) {
                sendMessage(finalChatId, "Вы уже в базе");
                return;
            }
            if (lastName != null) {
                String name = firstName + " " + lastName;
                personRepository.save(new Person(name, phone, finalChatId));
                sendMessage(finalChatId, "Вас успешно добавили в базу. Скоро вам перезвонят.");
                return;
            }
            personRepository.save(new Person(firstName, phone, finalChatId));
            sendMessage(finalChatId, "Вас успешно добавили в базу. Скоро вам перезвонят.");
        }
    }

//    public void getReport(Update update) {
//        Pattern pattern = Pattern.compile("(Рацион:)(\\s)(\\W+)(;)\n" +
//                "(Самочувствие:)(\\s)(\\W+)(;)\n" +
//                "(Поведение:)(\\s)(\\W+)(;)");
//        Matcher matcher = pattern.matcher(update.message().caption());
//        if (matcher.matches()) {
//            ReportData photo = photoService.findPhoto(update.message().chat().id());
//            String ration = matcher.group(3);
//            String health = matcher.group(7);
//            String habits = matcher.group(11);
//            photo.setRation(ration);
//            photo.setHabits(habits);
//            photo.setRation(health);
//        }
//    }

    private void savePhoto(Update update) {
        GetFile getFileRequest = new GetFile(update.message().photo()[0].fileId());
        GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
        try {
            File file = getFileResponse.file();
            file.fileSize();
            byte[] fileContent = telegramBot.getFileContent(file);
            photoService.uploadPhoto(update.message().chat().id(), fileContent, file, update.message().caption());//,
            telegramBot.execute(new SendMessage(update.message().chat().id(), "Отчет успешно принят"));
        } catch (IOException e) {
            System.out.println("Ошибка загрузки фото");
        }
    }


}
