package TypingRacer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    //starting the main stage
    public void start(Stage stage) throws Exception{
        stage.setTitle("Typing Racer");
        stage.setScene(createScene(loadMainPane())); //loads in the holder pane
        stage.show();
    }
    //loads the holder pane of the stack pane
    public Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane main =loader.load(getClass().getResourceAsStream("/fxml/main.fxml"));
        MainController c = loader.getController();
        ViewNavigator.setMainController(c);
        ViewNavigator.loadVista(ViewNavigator.MAINSCREEN);
        return main;
    }
    //creates a scene --> background to all visual elements
    public Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}