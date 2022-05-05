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

    public ServerStream(Socket socket, ClientHandler clientHandler){
        this.socket = socket;
        this.clientHandler = clientHandler;
    }

    /**
     * ServerReceiver holds a inputstream receiving information from server
     */
    public class ServerReceiver implements Runnable{
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
     */
    public class ServerSender implements Runnable{

        /**
         * run() runs the thread, while the thread isn't interrupted it will try to create the output-stream to the socket, it will
         * write the message/package and flush() to make sure no data is lost when leaving the try. Since the stream is created within
         * the try, there is no need to close it since it happen automatically when leaving the try-block
         */
        @Override
        public void run() {
            while (!Thread.interrupted()){
                try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {
                    oos.writeObject(new Object()); //change object type here
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
