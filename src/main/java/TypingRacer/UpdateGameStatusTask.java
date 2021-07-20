package TypingRacer;
import javafx.concurrent.Task;
import java.util.concurrent.TimeUnit;

 public class UpdateGameStatusTask extends Task {
    protected Object call() throws Exception {
        try{
            //constantly request for game status until the status is true for starting game
            while(!(ClientMainScreenController.client.requestStart(
                    ClientMainScreenController.client.inStream, ClientMainScreenController.client.outStream))){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
 }