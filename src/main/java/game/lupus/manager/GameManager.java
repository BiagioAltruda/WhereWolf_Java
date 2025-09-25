package game.lupus.manager;

import java.util.List;

import game.lupus.model.Player;

public class GameManager {

    public static GameManager instance;

    private List<Player> players; // Placeholder for player objects

    public List<Player> getPlayers() {
        return players;
    }


}
