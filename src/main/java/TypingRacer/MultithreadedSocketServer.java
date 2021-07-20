package TypingRacer;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import javafx.concurrent.Task;
import java.net.*;

 import java.util.ArrayList;


  class MultithreadedSocketServer extends Task{ //extends task for JavaFX concurrency to ensure GUI is still responsive
    final int PORT_NUMBER = 8888;
    private String text = "";
    public static ArrayList<Client> joinedClients = new ArrayList<Client>();

    public Server serverClass = new Server();
    @Override
    protected Void call() throws Exception {
        ServerSocket server=new ServerSocket(PORT_NUMBER);//creates the server
        int counter=0;//used to number clients

        // establishes HTTP connection to Google Drive and verifies credentials (multiple calls to DriveQuickStart)
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(
                HTTP_TRANSPORT, DriveQuickstart.JSON_FACTORY, DriveQuickstart.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(DriveQuickstart.APPLICATION_NAME)
                .build();
        text = DriveQuickstart.fileExport(DriveQuickstart.FILE_ID,"text/plain", service.files());
        while(true){//infinite loop so the server is constantly listening for new connections and starting new threads
            counter++;//increments the client number(so for the first client its number is 1)
            Socket serverClient=server.accept();  //server accept the client connection request
            Client c = new Client();//new client object is created to be passed to the ServerClientThread
            ServerClientThread sct = new ServerClientThread(serverClient,counter,c, text, serverClass); //creates new thread for the new connection

            sct.start();//start the new thread

            joinedClients.add(c); //adds the client object to an ArrayList which is used in ServerGameScreen
        }
    }
  }







