package TypingRacer;
import java.net.*;
import java.io.*;

public class TCPClient {
    private static String text;
    private static String ranking;
    private static boolean ifStart;
    DataInputStream inStream;
    DataOutputStream outStream;
    public TCPClient(String ip) throws IOException {
        Socket socket = new Socket(ip,8888); //connection to server thread
        this.inStream = new DataInputStream(socket.getInputStream());
        this.outStream = new DataOutputStream(socket.getOutputStream());
    }

    //below are methods that can be called for sending/receiving specific data
    static void sendName(String name, DataOutputStream outStream) throws IOException{
        outStream.writeUTF("SEND_NAME#"+name);
        outStream.flush();
    }
    static void sendProgress(Double progress, DataOutputStream outStream) throws IOException{
        outStream.writeUTF("SEND_PROGRESS#"+progress);
        outStream.flush();
    }
    static void sendTime(Double time, DataOutputStream outStream) throws IOException{
        outStream.writeUTF("SEND_TIME#"+time);
        outStream.flush();
    }

    static void sendStatus(boolean b, DataOutputStream outStream) throws IOException{
        outStream.writeUTF("SEND_STATUS#"+b);
        outStream.flush();
    }
    static String requestRanking(DataInputStream inStream, DataOutputStream outStream) throws IOException {
        outStream.writeUTF("REQUEST_RANKING#");
        outStream.flush();
        String serverMessage = inStream.readUTF();
        ranking = serverMessage;
        return ranking;
    }

    static boolean requestStart(DataInputStream inStream, DataOutputStream outStream) throws IOException {
        outStream.writeUTF("REQUEST_START#");
        outStream.flush();
        String serverMessage = inStream.readUTF();
        ifStart = Boolean.valueOf(serverMessage);
        return ifStart;
    }

    static String requestText(DataInputStream inStream, DataOutputStream outStream) throws IOException {
        outStream.writeUTF("REQUEST_TEXT#");
        outStream.flush();
        String serverMessage = inStream.readUTF();
        text = serverMessage;
        return text;
    }
}

