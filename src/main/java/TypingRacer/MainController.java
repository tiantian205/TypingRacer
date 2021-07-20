package TypingRacer;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    public StackPane vistaHolder;
    public MainController (){ }

    //method to set the holder pane to other panes

    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }
}



