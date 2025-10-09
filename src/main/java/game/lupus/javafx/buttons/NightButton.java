package game.lupus.javafx.buttons;

import ch.qos.logback.core.joran.action.Action;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NightButton {

    private double x, y, width, height;

    private String text;

    private Runnable action;

    private boolean isPressed = false;

    private Action onClickAction;



    public void showButton(){

    }


    public void hideButton(){

    }
}
