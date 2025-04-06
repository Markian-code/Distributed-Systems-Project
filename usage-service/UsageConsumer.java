package usageservice;

import messagequeue.Message;
import messagequeue.MessageQueueService;

public class UsageConsumer {
    private final MessageQueueService queue;

    public UsageConsumer(MessageQueueService queue) {
        this.queue = queue;
    }

    public void start() throws InterruptedException {
        while (true) {
            Message msg = queue.receiveMessage();
            System.out.println("Verarbeite Nachricht: " + msg);

            if (msg.getType() == Message.MessageType.CONSUME) {
                // Verbrauch in Datenbank schreiben
            } else if (msg.getType() == Message.MessageType.PRODUCE) {
                // Produktion in Datenbank schreiben
            }
        }
    }
}
