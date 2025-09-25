package game.lupus.model.roles;

import game.lupus.enums.ActionType;
import game.lupus.enums.Faction;
import game.lupus.model.Player;
import jakarta.persistence.Column;

public class Veggente extends Role{

    private final Faction faction = Faction.VILLAGGIO;
    @Column(name = "base_aura")
    private final boolean aura = false;
    @Column(name = "base_magic")
    private final boolean magic = true;
    private final String description = "Veggente del villaggio, ogni notte scopre l'aura di un giocatore in vita";

    @Override
    public void vote() {

    }

    @Override
    public void nightAction(ActionType actionType, Player... player) {

    }


}
