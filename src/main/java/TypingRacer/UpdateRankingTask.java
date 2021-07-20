package TypingRacer;

 import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

 public class UpdateRankingTask extends Task {
    @Override
    protected Object call() throws Exception {
        //constantly requests for this client's ranking
        while(true) {
            TimeUnit.MILLISECONDS.sleep(50); //slows down the constant request so game doesn't freeze/crash

            this.updateMessage(
                    ClientMainScreenController.client.requestRanking(
                    ClientMainScreenController.client.inStream,
                    ClientMainScreenController.client.outStream
                    )
            );

        }
    }
 }

