package TypingRacer;

//server object --> mainly used to update game status from the client waiting screen
 public class Server {
    private boolean gameStatus = false;

    public boolean getGameStatus() {
        return gameStatus;
    }
    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }
 }

