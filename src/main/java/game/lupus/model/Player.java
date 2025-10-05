
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
import java.util.Random;

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
	
//	private double playerX;
//	private double playerY;
	
	private final int width = 75;
	private final int height = 75;
	
	
	// diameter ( start and end )

	/*
	 * centerX - radius, centerX + radius, centerY - radius, centerY + radius ( diameter range ) 
	 * we can draw squares using coordinates from diameter ( horizontal axis and vertical axis ) 
	 * also, we make a check for collision, if there is always a collision we can make the squares smaller i guess
	 */
	
	// temporary solution
	
	// window
	private final int windowWidth = 1280;
	private final int windowHeight = 720;
		
	// radius for the circle
    double r = windowWidth / 6;
    double radius = (windowHeight - r) / 2;
		
	private double startX = windowWidth * 0.3 - r;
	private double endX = windowWidth * 0.3 + r;

	private double startY = windowHeight * 0.5 - r;
	private double endY = windowHeight * 0.5 + r;
	
	Random rand = new Random();
	
	// Random angle from 0 to 2π
	double angle = rand.nextDouble() * 2 * Math.PI;

	// Convert polar to Cartesian
	double startingPlayerPositionX = windowWidth * 0.3 + radius * Math.cos(angle);
	double startingPlayerPositionY = windowHeight * 0.5 + radius * Math.sin(angle);
	

 	// name subject to change, checked is too see if the mouse is hovering or not
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

	public void render(GraphicsContext gc, double mouseX, double mouseY, double playerX, double playerY) {
		
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
	    return mouseX >= startingPlayerPositionX && mouseX <= startingPlayerPositionX + width &&
	           mouseY >= startingPlayerPositionY && mouseY <= startingPlayerPositionY + height;
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
