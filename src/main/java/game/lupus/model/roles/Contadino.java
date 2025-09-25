package game.lupus.model.roles;

import jakarta.persistence.Column;

public class Contadino extends Role {

    private final String faction = "Villaggio";
    @Column(name = "base_aura")
    private final boolean aura = false;
    @Column(name = "base_magic")
    private final boolean magic = false;
    private final String description = "Contadino del villaggio, non ha poteri";

    @Override
    public void vote() {

    }

    @Override
    public void nightAction() {

    }
}
