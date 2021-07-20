package TypingRacer;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientWaitingScreenController implements Initializable {
    public void initialize(URL location, ResourceBundle resources){
        UpdateGameStatusTask gameStatusTask = new UpdateGameStatusTask(); //creates new task to refresh status
        //runs in the background

        gameStatusTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (EventHandler<WorkerStateEvent>) event->{
            ViewNavigator.loadVista("/fxml/clientGameScreen.fxml");//only changes screen when task given right status
        });

        new Thread(gameStatusTask).start();

    }
}
