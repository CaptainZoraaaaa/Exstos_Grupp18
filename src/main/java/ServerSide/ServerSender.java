package ServerSide;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ServerSender hold outputstream which send information to server
 * this thread is notified by the ServerBuffet when there is anything new to send, by using method get() we send last added
 * object
 */
public class ServerSender extends Thread{
    private ServerBuffer serverBuffer;
    private Socket socket;
    private ObjectOutputStream oos;

    public ServerSender(ServerBuffer serverBuffer, Socket socket) {
        this.serverBuffer = serverBuffer;
        this.socket = socket;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * run() runs the thread, while the thread isn't interrupted it will try to create the output-stream to the socket, it will
     * write the message/package and flush() to make sure no data is lost when leaving the try. Since the stream is created within
     * the try, there is no need to close it since it happen automatically when leaving the try-block
     */
    @Override
    public void run() {
        while (!socket.isClosed()){
            try {
                oos.writeObject(serverBuffer.get());//write latest object added in ServerBuffer
                oos.flush();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
