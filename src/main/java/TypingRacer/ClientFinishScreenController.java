package TypingRacer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFinishScreenController implements Initializable {
    public TextField txtTime;
    public TextField txtRanking;


    public void returnToMenu(ActionEvent actionEvent) {
        ViewNavigator.loadVista("/fxml/mainScreen.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UpdateRankingTask task = new UpdateRankingTask();
        txtRanking.textProperty().unbind();
        txtRanking.textProperty().bind(task.messageProperty());
        new Thread(task).start();

        txtTime.setText(String.valueOf(ClientGameScreenController.getTime()));
    }


}
