package org.ecommerce.message.broker.producers;

import org.ecommerce.logs.Log;
import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.models.Identity;

public class Producer <T extends Identity> extends Log implements Runnable {
    private final MessageQueue<T> queue;
    private final T t;

    public Producer(MessageQueue<T> queue, T t) {
        this.queue = queue;
        this.t = t;
    }

    @Override
    public void run() {
        queue.enqueue(t);
        Log.info("Producer " + t.getId() + " sent to queue");
    }
}
