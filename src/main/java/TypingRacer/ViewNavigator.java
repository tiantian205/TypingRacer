package TypingRacer;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class ViewNavigator {
    //fxml file of the holder pane
    public static final String MAIN  = "/fxml/main.fxml";

    //fxml file of the main screen of the application
    public static final String MAINSCREEN = "/fxml/mainScreen.fxml";
    public static MainController mainController;

    //used to set the holder panes controller
    public static void setMainController(MainController mainController) {
        ViewNavigator.mainController = mainController;
    }
    //set the current pane to the string a which is the format of "_.fxml"
    public static void loadVista(String a) {
        try {
            mainController.setVista(FXMLLoader.load(ViewNavigator.class.getResource(a)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


