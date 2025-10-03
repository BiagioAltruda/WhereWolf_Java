package game.lupus;

import game.lupus.model.Player;
import game.lupus.enums.ActionType;
import game.lupus.model.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActionDTO {
    private int id;
    private ActionType actionType;
    private Role role;
    private Player sender;
    private Player target;
}
