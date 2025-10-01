package game.lupus.model.roles;

import game.lupus.enums.ActionType;
import game.lupus.model.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_name",  discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Role {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    public abstract void vote();
    public abstract void nightAction(ActionType actionType, Player... player);
}
