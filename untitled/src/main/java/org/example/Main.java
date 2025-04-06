package org.example;

import messagequeue.Message;
import messagequeue.MessageQueueService;
import usageservice.UsageConsumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Nachrichtenschlange wird erstellt
        MessageQueueService queue = new MessageQueueService();

        // Consumer wird gestartet (in eigenem Thread)
        UsageConsumer consumer = new UsageConsumer(queue);
        Thread consumerThread = new Thread(() -> {
            try {
                consumer.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumerThread.start();

        // Simuliere den EnergyProducer, der Nachrichten in die Queue schickt
        for (int i = 0; i < 3; i++) {
            Message msg = new Message(Message.MessageType.PRODUCE, "Produktionseintrag " + i);
            queue.sendMessage(msg);
            Thread.sleep(500); // kleine Pause
        }

        for (int i = 0; i < 2; i++) {
            Message msg = new Message(Message.MessageType.CONSUME, "Verbrauchseintrag " + i);
            queue.sendMessage(msg);
            Thread.sleep(500); // kleine Pause
        }
    }
}
