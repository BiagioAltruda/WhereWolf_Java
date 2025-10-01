package game.lupus.manager;

import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class TurnManager {

    private int currentTurn;
    private boolean isNight;
    private final int turnMinutes = 7; // Minuti totali di un turno
    private long turnStartTime; // Timestamp di inizio turno
    private AtomicBoolean allPassed;

    // riscrivere il thread, aggiungiere una variabile AtomicBolean per AllPassed nella classe; nel thread fare controllo solo sulla variabile e sul timer
    
    public final static TurnManager instance = new TurnManager();

    public TurnManager() {
        this.currentTurn = 1;
        this.isNight = false;
        this.turnStartTime = System.currentTimeMillis();
        this.allPassed = new AtomicBoolean(false);
        currentTurn();
        update();
        
    }
    

    public int getCurrentTurn() {
        return currentTurn;
    }

    public boolean isNight() {
        return isNight;
    }

    private void currentTurn(){

        if(isNight)
        {
            // implementare controller API con metodi per la notte
        } 
        else 
        {
            // implementare controller API con metodi per il giorno
        }
    }

    public void nextTurn() {
        currentTurn++;
        isNight = !isNight;
        turnStartTime = System.currentTimeMillis(); // reset timer
        currentTurn();
    }


    public void update() {
        Thread updateThread = new Thread(() -> {
            while (true) {
                allPlayersPassed();
                if (timeController().equals("00:00"))
                    nextTurn();
                if (passedController()){
                    nextTurn();
                    GameManager.instance.getPlayers().forEach(player -> player.setPassed(false));
                }
                    
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

    private boolean passedController() {
       if (this.allPassed.get() == true)
            return true;
        return false;
    }

    private boolean allPlayersPassed() {

        //non entra qua dentro
        GameManager.instance.getPlayers().forEach(player -> { 
            System.out.println(player.getUsername() + " passed: " + player.hasPassed());
            if (!player.hasPassed()) { 
                allPassed.set(false);

                System.out.println("Metto a falso : "+allPassed.getAcquire());
            }
        });

        return allPassed.getAcquire();
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


    public String timeController() {
        return getTurnCountdown();
    }

}
