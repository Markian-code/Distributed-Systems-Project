package messagequeue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueueService {
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public void sendMessage(Message msg) {
        queue.offer(msg);
    }

    public Message receiveMessage() throws InterruptedException {
        return queue.take(); // blockiert, bis eine Nachricht ankommt
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
