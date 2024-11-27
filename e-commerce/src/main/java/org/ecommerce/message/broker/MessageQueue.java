package org.ecommerce.message.broker;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue <T> {
    private final Queue<T> messsageQueue = new ConcurrentLinkedQueue<>();

    public synchronized void enqueue(T message) {
        messsageQueue.add(message);
        notify();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (messsageQueue.isEmpty()) {
            wait();
        }
        return messsageQueue.poll();
    }
}
