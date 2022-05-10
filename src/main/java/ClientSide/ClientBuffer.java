package ClientSide;

import java.util.LinkedList;

/**
 * @param <T> typecast, object
 *           notify thread that new object can be sent to avoid busy-wait
 */
public class ClientBuffer<T> {
    private LinkedList<T> buffer = new LinkedList<T>();

    public synchronized void put(T obj) {
        buffer.addLast(obj);
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while(buffer.isEmpty()) {
            wait();
        }
        return buffer.removeFirst();
    }

    public int size() {
        return buffer.size();
    }
}
