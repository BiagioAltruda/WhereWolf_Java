
package game.lupus.manager;

import java.util.List;

import game.lupus.model.Player;
import lombok.Getter;
import lombok.Setter;


public class GameManager {

    public final static GameManager instance = new GameManager();

    private List<Player> players; // Placeholder for player objects

    public void setPlayers(Player...players) {
        this.players =List.of(players);
    }

    public GameManager(Player... players) {
        this.players = List.of(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setRoles(){
        // Implement role assignment logic here
    }

}
