package game.lupus;

import game.lupus.model.Player;
import game.lupus.enums.ActionType;
import game.lupus.model.roles.Role;

public class ActionDTO {
    private int id;
    private ActionType actionType;
    private Role role;
    private Player sender;
    private Player target;
}
