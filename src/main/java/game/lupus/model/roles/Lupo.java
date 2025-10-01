package game.lupus.model.roles;

import game.lupus.enums.ActionType;
import game.lupus.enums.Faction;
import game.lupus.model.Player;
import jakarta.persistence.Column;

public class Lupo extends Role {

    private final Faction faction = Faction.LUPI;
    @Column(name = "base_aura")
    private final boolean aura = true;
    @Column(name = "base_magic")
    private final boolean magic = false;
    private final String description = "Lupo Mannaro, ogni notte a partire dalla seconda, decide un giocatore da eliminare";

    @Override
    public void vote() {

    }

    @Override
    public void nightAction(ActionType actionType, Player... player) {

    }


}
