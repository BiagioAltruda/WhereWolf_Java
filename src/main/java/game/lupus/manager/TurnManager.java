package game.lupus.manager;

import java.util.concurrent.atomic.AtomicBoolean;

public class TurnManager {

    private int currentTurn;
    private boolean isNight;
    private final int turnMinutes = 7; // Minuti totali di un turno
    private long turnStartTime; // Timestamp di inizio turno
    


    public TurnManager() {
        this.currentTurn = 0;
        this.isNight = false;
        this.turnStartTime = System.currentTimeMillis();
        update();
    }
    

    public int getCurrentTurn() {
        return currentTurn;
    }

    public boolean isNight() {
        return isNight;
    }

    public void nextTurn() {
        currentTurn++;
        isNight = !isNight;
        turnStartTime = System.currentTimeMillis(); // reset timer
    }


    public void update() {
        Thread updateThread = new Thread(() -> {
            while (true) {
                turnController(); 
                //Implementare una stampa sull'interfaccia del countdown
                try {
                    Thread.sleep(16); 
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        updateThread.setDaemon(true); 
        updateThread.start();
    }

    private void turnController() {

        timeController();

        if (allPlayersPassed()) {
            GameManager.instance.getPlayers().forEach(player -> player.setPassed(false));
            nextTurn();
        }
    }

    private boolean allPlayersPassed() {
        AtomicBoolean allPassed = new AtomicBoolean(true);
        
        GameManager.instance.getPlayers().forEach(player -> {
            if (!player.isPassed()) {
                allPassed.set(false);
            }
        });
        return allPassed.get();
    }

    // Mostra il countdown in tempo reale dei 7 minuti
    private String getTurnCountdown() {
        long now = System.currentTimeMillis();
        long elapsedMillis = now - turnStartTime;
        long totalMillis = turnMinutes * 60 * 1000;
        long remainingMillis = Math.max(totalMillis - elapsedMillis, 0);
        long minutes = remainingMillis / 60000;
        long seconds = (remainingMillis % 60000) / 1000;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void timeController() {
        
    }

}
