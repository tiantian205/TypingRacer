package TypingRacer;

import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

 public class UpdateProgressTask extends Task {
    Client client;

    @Override
    protected Object call() throws Exception {
        while(client.getProgress() != 1) {
            TimeUnit.MILLISECONDS.sleep(50);
            //applies to the progress bar
            this.updateProgress(client.getProgress(), (double) 1);
            if(client.isFinished()) {
                //applies to the textbox
                this.updateMessage("finished");
            }
        } //task completes when progress = 1
        return null;
    }
    //constructor parsing in the current client
    public UpdateProgressTask(Client client){
        this.client = client;
    }

 }

