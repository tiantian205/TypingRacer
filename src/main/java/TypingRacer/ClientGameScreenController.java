package TypingRacer;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public  class ClientGameScreenController implements Initializable {
    public TextField inputText;
    public TextArea ogText;
    public TextField txtIsCorrect;
    public TextField txtPointer;
    LinkedList words = new LinkedList();
    private String p;
    double startTime;
    double endTime;
    private static double totalTime;

    double percentage; //% per word
    double progress = 0; //current progress

    public LinkedList separateText(String t){
        String[] myString;
        myString = t.split("\\s+");
        for(int i = 0; i < myString.length; i++){
            String temp = myString[i];
            words.addAtTail(temp);
        }
        return words;
    }

    public void keyPressed(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.SPACE) {
            p = words.pointer.word;
            if (words.pointer != words.tail) { //word typed is correct
                if (inputText.getText().replaceAll("\\s+", "").equals(
                                p.replaceAll("\\s+", ""))
                ) {
                    words.pointer = words.pointer.next; //progress pointer moves up
                    txtIsCorrect.setText("that's correct!");
                    inputText.setText("");
                    inputText.positionCaret(0); //positions cursor at the front
                    progress = progress + 1 / percentage; //calculate progress made
                    ClientMainScreenController.client.sendProgress(progress,
                            ClientMainScreenController.client.outStream); //send to server
                    txtPointer.setText(words.pointer.word);
                }
                else { //word typed is wrong
                    txtIsCorrect.setText("typo!");
                }
            }
        }
    }

    public void checkFinish(ActionEvent actionEvent) throws IOException {
        p = words.pointer.word;
        if(words.pointer == words.tail){
            if (inputText.getText().replaceAll("\\s+","").equals(p.replaceAll("\\s+",""))) {
                txtIsCorrect.setText("finished!");
                inputText.setText("finished!");
                inputText.positionCaret(0);
                endTime = System.currentTimeMillis();
                System.out.println(endTime-startTime);
                totalTime = (endTime-startTime)/1000;

                progress = progress + 1/percentage;

                ClientMainScreenController.client.sendProgress((double)1,
                        ClientMainScreenController.client.outStream);


                ClientMainScreenController.client.sendStatus(true , ClientMainScreenController.client.outStream);
                ClientMainScreenController.client.sendTime(totalTime, ClientMainScreenController.client.outStream);
                ViewNavigator.loadVista("/fxml/clientFinishScreen.fxml");
            }

            else {
                txtIsCorrect.setText("You didn't finish typing!");
            }

        }
    }

    public static double getTime(){
        return totalTime;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            ogText.setText(ClientMainScreenController.client.requestText(ClientMainScreenController.client.inStream,ClientMainScreenController.client.outStream));
            separateText(ogText.getText());
            words.print();
            ogText.setWrapText(true);
            percentage = words.getCount();
            inputText.setEditable(true);
            startTime = System.currentTimeMillis();
            txtPointer.setText(words.head.word);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

