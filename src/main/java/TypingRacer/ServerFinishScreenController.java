package TypingRacer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerFinishScreenController implements Initializable {
    public ListView<Integer> listRanking;
    public ListView<Double> listTime;
    public ListView<String> listName;
    int ranking = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sortClients(MultithreadedSocketServer.joinedClients);
        for (int i = 0; i < ServerGameScreenController.finishedClients.size(); i++) {
            listRanking.getItems().add(i, ranking);
            listTime.getItems().add(i, ServerGameScreenController.finishedClients.get(i).getTime());
            listName.getItems().add(i, ServerGameScreenController.finishedClients.get(i).getName());
            ranking = ranking + 1;
        }
    }

    public void btnRtrnClicked(ActionEvent actionEvent) {
        ViewNavigator.loadVista("/fxml/mainScreen.fxml");
    }
}