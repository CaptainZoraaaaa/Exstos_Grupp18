package ServerSide;

import java.util.LinkedList;

/**
 * @param <T> typecast, object
 *           anytime a package is created/received to be sent to the server, we use put( ) method to put it in the
 *           buffer, this also trigger the notifyAll() which notify threads on wait() to start. The servers use get()-method
 *           to send the latest object, and then removes it
 */
public class ServerBuffer<T> {
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
