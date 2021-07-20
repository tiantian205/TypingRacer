package TypingRacer;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class ServerWaitingScreenController implements Initializable {
    public ListView playerList;
    public TextField txtIPAddress;
    public TextField txtTotalPlayer;

    public void returnToMenu(ActionEvent actionEvent) {
        ViewNavigator.loadVista("/fxml/mainScreen.fxml");
    }

    public void startServerGame(ActionEvent actionEvent) {
        MainScreenController.task.serverClass.setGameStatus(true);
        ViewNavigator.loadVista("/fxml/serverGameScreen.fxml");
    }

    public void refreshListView(ActionEvent actionEvent) {
        playerList.getItems().clear();
        txtTotalPlayer.setText(String.valueOf(MultithreadedSocketServer.joinedClients.size()));
        //refreshes the list view box containing all joined clients' names
        //loops through all joined clients and prints their names in the listview
        for (int i =0; i < MultithreadedSocketServer.joinedClients.size(); i++){
            if(MultithreadedSocketServer.joinedClients.get(i) != null) {
                playerList.getItems().add(MultithreadedSocketServer.joinedClients.get(i).getName());
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            txtIPAddress.setText(Inet4Address.getLocalHost().getHostAddress()); //displays the server computer's IP address
            txtIPAddress.setEditable(false);
            txtTotalPlayer.setEditable(false);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

