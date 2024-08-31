package org.ecommerce.message.broker.consumers;

import org.ecommerce.logs.Log;
import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.models.Product;

public abstract class Consumer<T> extends Log implements Runnable {
    private final MessageQueue<T> queue;
    private volatile boolean running = true;

    public Consumer(MessageQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (running) {
                T message = queue.dequeue();
                processOrder(message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        running = false;
    }

    abstract void processOrder(T t);
    abstract int checkForInventory(Product product);
    abstract void updateInventory(Product product);
    abstract void chargeCustomer(T t);
    abstract void sendNotification(T t);
}

