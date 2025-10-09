package game.lupus.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import game.lupus.javafx.buttons.Button;
import game.lupus.manager.GameManager;
import game.lupus.manager.TurnManager;
import game.lupus.model.Player;
import game.lupus.model.roles.Contadino;
import game.lupus.model.roles.Lupo;
import game.lupus.utils.GlobalVar;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXTestGame extends Application {


	// window
	private final int windowWidth = 1280;
	private final int windowHeight = 720;

	// mouse
	private double mouseX = 0;
	private double mouseY = 0;

	// ignore this one for now, was meant to be the radius, but maybe some numbers
	// are wrong, have to fix it
	double r = windowWidth / 6;

	Random rand = new Random();

	@Override
	public void start(Stage primaryStage) throws IOException {

		AnchorPane pane = new AnchorPane();
		Canvas canvas = new Canvas();
		Scene scene = new Scene(pane, windowWidth, windowHeight);

		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		pane.getChildren().add(canvas);

		/*
		 * TESTING BLOCK WITH HARD CODED players
		 */

		Player p1 = new Player();
		p1.setUsername("Bardo");
		p1.setId(1);
		p1.setPassed(false);
		p1.setRole(new Contadino());
		//p1.setAngle((rand.nextDouble() * 2 * Math.PI));

		Player p2 = new Player();
		p2.setUsername("Lupo");
		p2.setId(2);
		p2.setRole(new Lupo());
		p2.setPassed(true);
		//p2.setAngle((rand.nextDouble() * 2 * Math.PI));

		Player p3 = new Player();
		p3.setUsername("Luce");
		p3.setId(3);
		p3.setPassed(true);
		//p3.setAngle((rand.nextDouble() * 2 * Math.PI));

		Player p4 = new Player();
		p4.setUsername("Biagi");
		p4.setId(4);
		p4.setPassed(true);
		//p4.setAngle((rand.nextDouble() * 2 * Math.PI));

		GameManager.instance.setPlayers(p1, p2, p3, p4);

		// credo che assegnare lobbySize fin da subito sia meglio cosi non si deve
		// chiamare ogni volta con GAMEMANAGER.INSTANCE...
		

		// VOTE BUTTON
		

		
		/* 
		 * MOUSE METHODS 
		 */
		// JavaFX offers mouse properties yayy huh? /
		canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				
				
				if (GlobalVar.voteButton.isMouseOver(e.getX(), e.getY())) {
					GlobalVar.voteButton.onClick();
				}

				for (int i = 0; i < GlobalVar.lobbySize; i++) {
					 Player player = GameManager.instance.getPlayers().get(i);
					 
					if (player.isMouseOver
							(e.getX(), e.getY(),
							player.getStartingPlayerPositionX(),
							player.getStartingPlayerPositionY()
							)
						) {
						GameManager.instance.getPlayers().get(i).onClick();

						/*
						 * non mi viene in mente altro, siccome lobbySize non toccherà nemmeno i 2 zeri,
						 * un O n al quadrato non dovrebbe rallentare
						 */
						for (int j = 0; j < GlobalVar.lobbySize; j++) {
							// if i and j are the same, therefore the same player, nothing happens
							// otherwise it sets it's isChecked to false;
							if (j != i)
								GameManager.instance.getPlayers().get(j).setChecked(false);
						}
					}

				}
			}
		});

		/*
		 * setOnMouseMoved / setOnMouseClicked notes ABOVE TRADITIONAL VERSION BELOW (IN
		 * COMMENT ) LAMBDA VERSION the traditional version is more verbose, but it's
		 * easier to understand and debug imo
		 */
//		canvas.setOnMouseMoved(e -> {
//		mouseX = e.getX();
//		mouseY = e.getY();
//		});
//		
//		canvas.setOnMouseClicked(e -> {
//			if (voteButton.isHovered(e.getX(), e.getY())){
//				voteButton.onClick();
//				} 
//			});
		
		
		// ---------------------------------------------------------------------------------- END MOUSE METHODS

		primaryStage.setScene(scene);
		primaryStage.setTitle("NEGRO");
		primaryStage.show();

		GraphicsContext gc = canvas.getGraphicsContext2D();

		
		
		

		
		
		//------------------------------ START ANIMATION TIMER
		new AnimationTimer() {

			@Override
			public void handle(long now) {

				// w and h goes inside AnimationTimer because when the canvas resizes it gets updated
				// otherwise it would be blank
				double w = canvas.getWidth();
				double h = canvas.getHeight();

				// CHANGE BACKGROUND DAY/NIGHT
				if (TurnManager.instance.isNight()) {
					gc.setFill(Color.DARKSLATEBLUE);
					gc.fillRect(0, 0, w, h);
					gc.fillText("NIGHT", 20, 30);
				} else {
					gc.fillText("DAY", 20, 30);
					gc.setFill(Color.DARKORANGE);
					gc.fillRect(0, 0, w, h);
				}

				// Calculate circle parameters based on current canvas size
				double r = w / 6;
				double circleRadius = (h - r) / 2;
				double centerX = w * 0.3;
				double centerY = h * 0.5;

				// Draw the circle, it just a guide for debugging/testing
				gc.strokeOval(centerX - circleRadius, centerY - circleRadius, circleRadius * 2, circleRadius * 2);

				gc.setFill(Color.WHITE);

				// time, manche 
				gc.fillText(TurnManager.instance.timeController() + "", w * 0.01, h * 0.05);
				gc.fillText("Manche : " + TurnManager.instance.getCurrentTurn() + "", w * 0.01, h * 0.1);

				
				// ** delete later
				if(GameManager.instance.getPlayers().get(0).getRole() instanceof Contadino && TurnManager.instance.isNight()) {

                } else {
                    GlobalVar.voteButton.render(gc, mouseX, mouseY);
                }

				gc.setFont(new Font("Arial", 24));
				
				// angles are the same for each player
				double fullCircle = 2 * Math.PI;
				double anglePerPlayer = fullCircle/GlobalVar.lobbySize;
				
				for (int i = 0; i < GlobalVar.lobbySize; i++) {
					
					// so we don't have to type the latter part of this line over and over again
				    Player player = GameManager.instance.getPlayers().get(i);
				   
				   
				    // Calculate position on circle
				    double playerX = centerX + circleRadius * Math.cos(anglePerPlayer + (anglePerPlayer * i));
				    double playerY = centerY + circleRadius * Math.sin(anglePerPlayer + (anglePerPlayer * i));					    
				    
				    // drawing from the center rather than the top left
				    double squareX = playerX - player.getWidth() / 2;
				    double squareY = playerY - player.getHeight() / 2;

                    player.setStartingPlayerPositionX(squareX);
				    player.setStartingPlayerPositionY(squareY);
				    
				    // Draw username above the square
				    gc.setFill(Color.BLACK);
				    gc.fillText(player.getUsername(), playerX - (w * 0.03), playerY - (h * 0.07));
				    // Render 
				    player.getAction();
				    player.render(gc, mouseX, mouseY, squareX, squareY);
				}
			}
		}.start();

	}
}