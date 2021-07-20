package TypingRacer;
import javafx.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainScreenController {
    public static MultithreadedSocketServer task;

    public void openClientMainPane(ActionEvent actionEvent) {
        ViewNavigator.loadVista("/fxml/clientMainScreen.fxml");
    }

    public void quitApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void serverCreateGame(ActionEvent actionEvent){
        //creates a server on a separate thread so the game is still responsive
        task = new MultithreadedSocketServer();
        task.setOnRunning((succeesesEvent)->{
            ViewNavigator.loadVista("/fxml/serverWaitingScreen.fxml");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();

    }

}