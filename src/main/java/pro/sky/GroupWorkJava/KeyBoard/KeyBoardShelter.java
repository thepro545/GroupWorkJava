package pro.sky.GroupWorkJava.KeyBoard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.GroupWorkJava.listener.TelegramBotUpdatesListener;
import com.vdurmont.emoji.EmojiParser;


@Service
public class KeyBoardShelter {

    @Autowired
    private TelegramBot telegramBot;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);


    /**
     * send menu
     * @param chatId
     */
    public void sendMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "Вызвано основное меню ");
        String emoji_kissing = EmojiParser.parseToUnicode(":pig:");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(emoji_kissing+ "Информация о возможностях бота"),
                new KeyboardButton("Узнать информацию о приюте"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Как взять питомца из приюта"),
                new KeyboardButton("Прислать отчет о питомце"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"));

        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
        SendMessage request = new SendMessage(chatId, "Главное меню")
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);
        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }

    /**
     * info about animal shelter
     * @param chatId
     */
    public void sendMenuInfoShelter(long chatId) {
        logger.info("Method sendMenuInfoShelter has been run: {}, {}", chatId, "Вызвали Информация о приюте");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton("Кидает на статью"),
                new KeyboardButton("Оставить контактные данные"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
        SendMessage request = new SendMessage(chatId, "Информацию о приюте")
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);
        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }

    /**
     * rules for adopting a pet
     * @param chatId
     */
    public void sendMenuTakeAnimal(long chatId) {
        logger.info("Method sendMenuTakeAnimal has been run: {}, {}", chatId, "вызвали Как взять питомца из приюта");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton("Кидает на статью"),
                new KeyboardButton("Оставить контактные данные"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
        SendMessage request = new SendMessage(chatId, "Информацию о приюте")
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);
        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }
}
