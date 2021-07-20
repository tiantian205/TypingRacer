package TypingRacer;
import java.io.*;
import java.net.Socket;

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    private String clientName;
    private double clientProgress;
    private double clientTime;
    private boolean clientStatus;
    private int clientRanking;
    private String clientText;
    Client client;
    Server server;
    public ServerClientThread(Socket inSocket,int counter, Client client, String clientText, Server server){
        this.serverClient = inSocket;
        this.clientNo=counter;
        this.client = client;
        this.clientText = clientText;
        this.server = server;
    }

    enum Commands {
        SEND_NAME,
        SEND_PROGRESS,
        SEND_TIME,
        SEND_STATUS,
        REQUEST_RANKING,
        REQUEST_TEXT,
        REQUEST_START,
    }

    public void run(){
        try{

            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage= "", serverMessage = "";
            while(true){
                clientMessage=inStream.readUTF();
                //split client msg into command & content
                final String[] split = clientMessage.split("#");
                switch(Commands.valueOf(split[0])){
                    case SEND_NAME:
                        clientName = split[1];
                        client.setName(clientName); //set client name on the server end
                        break;

                    case SEND_PROGRESS:
                        clientProgress = Double.parseDouble(split[1]);
                        client.setProgress(clientProgress); //update client progress in typing
                        break;

                    case SEND_TIME:
                        clientTime = Double.parseDouble(split[1]);
                        client.setTime(clientTime);//receive client total typing time
                        break;
                    case SEND_STATUS:
                        clientStatus = Boolean.parseBoolean(split[1]);
                        client.setFinished(clientStatus);//client game status(if finished typing)
                        break;
                    case REQUEST_START:
                        serverMessage = Boolean.toString(server.getGameStatus());
                        outStream.writeUTF(serverMessage);
                        outStream.flush();//send signal to start all client's games
                        break;
                    case REQUEST_RANKING:
                        clientRanking=client.getRank();
                        serverMessage = String.valueOf(client.getRank());
                        outStream.writeUTF(serverMessage);
                        outStream.flush();//sends client final ranking after finishing
                        break;
                    case REQUEST_TEXT:
                        serverMessage = clientText;
                        outStream.writeUTF(serverMessage);
                        outStream.flush(); //sends the text downloaded from GDrive
                        break;
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}
