package TypingRacer;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Pattern;

public class ClientMainScreenController{
    public TextField textFieldIP;
    public TextField textFieldName;
    public static TCPClient client;
    private String IP_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"; //https://mkyong.com/regular-expressions/how-to-validate-ip-address-with-regular-expression/


    public void joinGame(ActionEvent actionEvent) throws Exception {
        String ip = textFieldIP.getText();
        String name = textFieldName.getText();
        try{
        if(Pattern.matches(IP_PATTERN, ip)) { //validate input
            client = new TCPClient(ip);
            client.sendName(name, client.outStream);
            ViewNavigator.loadVista("/fxml/clientWaitingScreen.fxml"); //changes visual
        }else{
            textFieldIP.setText("enter valid ip");
        }}catch(Exception ex){
            ex.printStackTrace();
        }

    }


    public void clientMainReturnToMenu(ActionEvent actionEvent) {
        ViewNavigator.loadVista("/fxml/mainScreen.fxml");
    }


}


