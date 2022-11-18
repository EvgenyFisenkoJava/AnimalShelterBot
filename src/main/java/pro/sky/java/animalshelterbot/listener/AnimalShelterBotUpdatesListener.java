package pro.sky.java.animalshelterbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class AnimalShelterBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(AnimalShelterBotUpdatesListener.class);


    private final TelegramBot telegramBot;


    public AnimalShelterBotUpdatesListener(TelegramBot telegramBot) {


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
            Message message = update.message();
            Long chatId = update.message().chat().id();

            if (chatId != null && message.text().equals("/start")) {
                SendMessage msg = new SendMessage(chatId,
                        "Hi there!");
                telegramBot.execute(msg);
            }


        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
