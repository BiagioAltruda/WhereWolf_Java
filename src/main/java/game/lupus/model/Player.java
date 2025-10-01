
package game.lupus.model;

import game.lupus.enums.Status;
import game.lupus.javafx.buttons.Button;
import game.lupus.model.roles.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "players")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Player {

	@Id
	@GeneratedValue
	private Integer id;
	private String username;

	@Nonnull
	private boolean passed;

	@ManyToOne
	@JoinColumn(name = "id")
	private Role role;
	@Column(name = "is_alive")
	private boolean isAlive;
	@Column(name = "current_aura")
	private int aura;
	@Column(name = "current_magic")
	private int magic;
	@Column(name = "status")
	private ArrayList<Status> statuses = new ArrayList<>();

	/*
	 * -----------------------------------------------------
	 * START TESTING AREA
	 * 
	 */
	Button btn;
	private double playerX;
	private double playerY;
	private final int width = 100;
	private final int height = 100;
	private boolean checked = false;
	
	private Runnable action = new Runnable(){
		 @Override
	        public void run() {
			 	
			 if(!isChecked())
			 {
				 setChecked(true);
				 System.out.println("selected player " + username);
			 } else {
				 setChecked(false);
				 System.out.println("player " + username + " unselected");
			 }
	                
	        }
	};

	public void render(GraphicsContext gc, double mouseX, double mouseY) {
		
		 boolean isHovered = isMouseOver(mouseX, mouseY);
		 
		 if(isHovered || isChecked()) {
		    	gc.setFill(Color.DARKGREEN);
		    } else {
		    	gc.setFill(Color.GREEN);
		    }
		 
		 gc.fillRect(playerX, playerY, width, height);
		 gc.setStroke(Color.BLACK);
		 gc.strokeRect(playerX, playerY, width, height);
		    
	}
	
	// SQUARE HITBOX 
	public boolean isMouseOver(double mouseX, double mouseY) {
	    return mouseX >= playerX && mouseX <= playerX + width &&
	           mouseY >= playerY && mouseY <= playerY + height;
	}
	  
	public void onClick() {
		if (action != null)
			action.run();
	}
	
	// non capisco come fare il getter e setter custom con lombok 
	public void lockedIn(double mouseX, double mouseY) {
		
		if(isMouseOver(mouseX, mouseY))
			setChecked(true);
	}
	/*
	 * END TESTING AREA
	 * 
	 * -----------------------------------------------------
	 */

	public boolean hasPassed() {
		return passed;
	}

}
