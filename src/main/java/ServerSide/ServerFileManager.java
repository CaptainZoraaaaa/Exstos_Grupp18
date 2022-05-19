package ServerSide;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ServerFileManager {
    private static DataOutputStream dos;
    private static DataInputStream dis;
    private static BufferedWriter bw;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static Server server;

    public ServerFileManager(Server server) {
        this.server = server;
    }
    /**
     * @author Anna Håkansson
     *
     * @param type the class that needs an ID
     * @return the ID fetched from file and -1 if the reading failed
     *
     * This method uses the type-string to get access
     * to the right dat-file and returns the ID written
     * there.
     */
    public static synchronized int getIDFromFile(String type) {
        String logtext;
        int ID = -1; //if it doesnt work it will return the "fail"-value
        String filename = String.format("files/%sID.dat", type); //format the string to get the right filename

        try {
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
            ID = dis.readInt(); //read ID
            logtext = String.format("A %sID was fetched from the .dat-file", type);
        } catch (IOException e) {
            logtext = String.format("Failure in ServerFileManager.readIDFile due to %s", e);
            e.printStackTrace();
            System.err.println(logtext);
        }
        writeLog(logtext);
        return ID;
    }
    /**
     * @author Anna Håkansson
     *
     * @param currentID the ID that was used last
     * @param type that needs it's ID-value incremented
     *
     * This method takes the previously used ID, increments
     * it and writes the new file to the right dat file according
     * to the "type"-string.
     */
    public static synchronized void writeNewID(int currentID, String type) {
        String logtext;
        String filename = String.format("files/%sID.dat", type); //format string to get right file
        int newID = currentID + 1; //increment ID
        try{ //create stream
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            dos.writeInt(newID); //write the new ID
            dos.flush();
            logtext = String.format("A new %sID was created.", type);
        } catch (Exception e) {
            logtext = String.format("Error in Server.writeNewID due to %s", e);
            System.err.println(logtext);
        }
       writeLog(logtext);
    }
    /**@author Anna Håkansson
     * @param logText to be added to the log
     * Saves the LocalDateTime at the executing moment and appends
     * it together with the logtext to the logtext file.
     */
    public static synchronized void writeLog(String logText) {
        System.out.println(logText);
        try {
            bw = new BufferedWriter(new FileWriter("files/log.txt")); //create writer
            bw.write(String.format("%s: %s%n", LocalDateTime.now(), logText)); //append the time and the logtext (e.g. add an extra line instead of overwriting)
            bw.flush();
        } catch (IOException e) {
            System.err.println("Failure in Server.writeLog due to" + e);
        }
    }
    /**
     * @author Anna Håkansson
     *
     * @param map to be written to file
     * @param type the type of map (user/client/project)
     *
     * Method for writing a hashmap to a .dat-file with an
     * object output stream.
     */
    public static synchronized void writeMapToFile(HashMap map, String type) {
        String logtext;
        String filename = String.format("files/%s.dat", type); //format string to get right filename
        try { //create stream
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            oos.writeObject(map); //write
            oos.flush();
            logtext = String.format("The %sMap was written to the .dat-file", type);
        } catch (IOException e) {
            logtext = String.format("Failure in Server.writeMapToFile while reading %sMap due to %s", type, e);
            System.err.println(logtext);
        }
        writeLog(logtext);
    }
    /**
     * @author Anna Håkansson
     *
     * @param type of map to be read
     * @return the map read from the .dat-file
     *
     * Method for reading a hashmap from a .dat-file by
     * giving the wanted type of map.
     */
    public static synchronized HashMap readMapFromFile(String type) {
        String logtext;
        String filename = String.format("files/%s.dat", type); //format the string to get the right filename
        HashMap map = null; //initialize map
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
            map = (HashMap) ois.readObject(); //read map
            logtext = String.format("The %sMap was read from the .dat-file", type);
        } catch (IOException | ClassNotFoundException e) {
            logtext = String.format("Failure in Server.readMapFromFile while reading %sMap due to %s", type, e);
            System.err.println(logtext);
        }
        writeLog(logtext);
        return map;
    }

    public static synchronized void close() {
        try {
            ois.close();
            oos.close();
            dos.close();
            dis.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
