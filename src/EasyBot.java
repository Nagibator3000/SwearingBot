import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class EasyBot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new EasyBot());
            System.out.println("Bot is working!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("Лёха") || message.getText().equals("Юра") || message.getText().equals("Настя")) {
                switch (message.getText()) {
                    case "Юра":
                        sendMsg(message, "Юра - XXX");
                        break;
                    case "Настя":
                        sendMsg(message, "Настя - XXXX");
                        break;
                    case "Лёха":
                        sendMsg(message, "Лёха - XXX");
                        break;
                }
            } else {
                sendMsg(message, "Я не знаю что ответить на это");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "swearing_maya_bot";
    }

    @Override
    public String getBotToken() {
        return "TOKEN";
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplayToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
