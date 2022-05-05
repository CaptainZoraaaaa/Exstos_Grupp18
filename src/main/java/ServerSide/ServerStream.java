package ServerSide;

import java.io.*;
import java.net.Socket;

/**
 * @author Emma Mörk
 * this class handles server output and server input for a client
 */
public class ServerStream {
    private Socket socket;
    private ClientHandler clientHandler;
    private ServerBuffer serverBuffer;

    public ServerStream(Socket socket, ClientHandler clientHandler, ServerBuffer serverBuffer){
        this.socket = socket;
        this.clientHandler = clientHandler;
        this.serverBuffer = serverBuffer;

        StartThreads();
    }

    /**
     * creates and run two threads, one for output and one for input (from server)
     */
    private void StartThreads() {
        new ServerReceiver().start();
        new ServerSender().start();
    }

    /**
     * ServerReceiver holds a inputstream receiving information from server
     */
    public class ServerReceiver extends Thread{
        private Package packageObject;

        /**
         * thread for receiving data server -> client.
         * reads object with readObject() and save the data to a variable which then is
         * passed along to a clientHandlet-method
         */
        @Override
        public void run() {
            while (!Thread.interrupted()){
                try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
                    packageObject = (Package) ois.readObject();
                    clientHandler.packageRecieved(packageObject);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ServerSender hold outputstream which send information to server
     * this thread is notified by the ServerBuffet when there is anything new to send, by using method get() we send last added
     * object
     */
    public class ServerSender extends Thread{

        /**
         * run() runs the thread, while the thread isn't interrupted it will try to create the output-stream to the socket, it will
         * write the message/package and flush() to make sure no data is lost when leaving the try. Since the stream is created within
         * the try, there is no need to close it since it happen automatically when leaving the try-block
         */
        @Override
        public void run() {
            while (!Thread.interrupted()){
                try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {
                    oos.writeObject(serverBuffer.get());//write latest object added in ServerBuffer
                    oos.flush();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
