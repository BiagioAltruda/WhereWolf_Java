package game.lupus.utils;

import ch.qos.logback.core.joran.action.Action;
import game.lupus.enums.ActionType;
import game.lupus.javafx.buttons.Button;
import game.lupus.manager.GameManager;
import game.lupus.manager.TurnManager;

public class GlobalVar {

    public static int lobbySize = GameManager.instance.getPlayers().size();

    private static ActionType currentActionType = ActionType.VOTE;

    public static Button voteButton = new Button(200, 100, 70, 35, currentActionType.toString(), () -> {
			int i;
			for (i = 0; i < lobbySize; i++) {
				if (GameManager.instance.getPlayers().get(i).isChecked()) {
                    if (currentActionType == ActionType.VOTE) {
					System.out.println("You voted " + GameManager.instance.getPlayers().get(i).getUsername());
                    
                    //Da rivedere una volta impostato il client server
                    GameManager.instance.getPlayers().get(0).setPassed(true);
                    TurnManager.instance.turnController();
					break;
                    }
				}
			}

			if (i == lobbySize) {
				System.out.println("You must select a player before voting, if you do not wish to vote you can abstain");
			}
		});

}
