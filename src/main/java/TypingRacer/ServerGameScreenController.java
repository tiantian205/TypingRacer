package TypingRacer;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerGameScreenController implements Initializable {
    public ListView listViewNames;
    public ListView listViewProgressBars;
    public ListView listViewStatus;
    public static ArrayList<Client> finishedClients = new ArrayList<Client>();
    private int rank = 1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProgressBar tmp;
        listViewNames.setFixedCellSize(30);
        listViewProgressBars.setFixedCellSize(30);
        listViewStatus.setFixedCellSize(30);
        for(int i = 0; i <MultithreadedSocketServer.joinedClients.size(); i++ ){
            //creating task

            Client client = MultithreadedSocketServer.joinedClients.get(i);

            UpdateProgressTask progressTask = new UpdateProgressTask(client);

            tmp = new ProgressBar();
            //rebinding a progress bar's update property to the task
            tmp.progressProperty().unbind();
            tmp.progressProperty().bind(progressTask.progressProperty());

            Label tmpLabel = new Label("");
            //rebinding a label's text update to the task
            tmpLabel.textProperty().unbind();
            tmpLabel.textProperty().bind(progressTask.messageProperty());

            listViewProgressBars.getItems().add(i, tmp);
            listViewNames.getItems().add(i, client.getName());
            listViewStatus.getItems().add(i, tmpLabel);

            progressTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                    (EventHandler<WorkerStateEvent>) event->{

                //add the client to an arraylist to be displayed at the end
                insertInOrder(client, finishedClients);
                client.setRank(rank); //gives the client a rank when finished
                rank++;
            });


            new Thread(progressTask).start();
        }
    }

    public void goToEndGameScreen(ActionEvent actionEvent) {
        ViewNavigator.loadVista("/fxml/serverFinishScreen.fxml");
    }

    public void insertInOrder(Client client, ArrayList<Client> clientList ){
        if(clientList.size() > 0){
            if(client.getTime() >= clientList.get(clientList.size()-1).getTime()){
                clientList.add(client);
            }else {
                for (int i = 0; i < clientList.size(); i++) {
                    if (client.getTime() < clientList.get(i).getTime()) {
                        clientList.add(i, client);
                        break;
                    }
                }
            }
        }else{
            clientList.add(client);
        }
    }


}
