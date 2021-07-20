package TypingRacer;

 public class Client {
    private String name;
    private double time;
    private double progress;
    private int rank;
    private boolean finished=false;

    public void setFinished(boolean b){
        this.finished = b;
    }

    public String getName() {
        return name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinished() {
        return finished;
    }
 }
