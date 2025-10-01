package game.lupus.javafx.buttons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Button {
	
	private double x, y, width, height; 
	
	private String text;
	
	private Runnable action;
	
	private boolean isPressed = false;

	public Button(double x, double y, double width, double height, String text, Runnable action) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.action = action;
	}
	
	public void render(GraphicsContext gc, double mouseX, double mouseY) {
		
	    boolean isHovered = isMouseOver(mouseX, mouseY);
	    
	    if(isHovered) {
	    	gc.setFill(Color.LIGHTGREEN);
	    } else {
	    	gc.setFill(Color.LIGHTGRAY);
	    }
	    
	    gc.fillRect(x, y, width, height);
	    gc.setStroke(Color.BLACK);
	    gc.strokeRect(x, y, width, height);

	    gc.setFill(Color.BLACK);
	    gc.fillText(text, x + 10, y + height / 2 + 5);
	}
	
	/*
	   JavaFX mouse event listeners.
    JavaFX has built-in methods that detect mouse movement and clicks on Canvas
    such a blessing huh? / 
	 * 
	 */
	public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width &&
               mouseY >= y && mouseY <= y + height;
    }

    public void onClick() {
		if (action != null)
			action.run();
    }

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Runnable getAction() {
		return action;
	}

	public void setAction(Runnable action) {
		this.action = action;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	} 
	

	
}
