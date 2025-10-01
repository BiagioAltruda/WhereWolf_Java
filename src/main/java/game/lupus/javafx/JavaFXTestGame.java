package game.lupus.javafx;

import java.io.IOException;
import java.util.ArrayList;
import game.lupus.javafx.buttons.Button;
import game.lupus.manager.GameManager;
import game.lupus.manager.TurnManager;
import game.lupus.model.Player;
import game.lupus.model.roles.Lupo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXTestGame extends Application {

	// window
	private final int windowWidth = 1280;
	private final int windowHeight = 720;

	// mouse
	private double mouseX = 0;
	private double mouseY = 0;

	// button
	Button voteButton = new Button(200, 100, 70, 35, "Vote", () -> {
		System.out.println("Vote clicked!");

		// method that blasts the selected player, for testing purpouses the three other
		// player will not vote
	});

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

		Player p2 = new Player();
		p2.setUsername("Lupo");
		p2.setId(2);
		p2.setRole(new Lupo());
		p2.setPassed(true);

		Player p3 = new Player();
		p3.setUsername("Luce");
		p3.setId(3);
		p3.setPassed(true);

		Player p4 = new Player();
		p4.setUsername("Biagi");
		p4.setId(4);
		p4.setPassed(true);

		GameManager.instance.setPlayers(p1, p2, p3, p4);

		// credo che assegnare lobbySize fin da subito sia meglio cosi non si deve
		// chiamare ogni volta con GAMEMANAGER.INSTANCE.NEGRO...
		int lobbySize = GameManager.instance.getPlayers().size();

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

				if (voteButton.isMouseOver(e.getX(), e.getY())) {
					voteButton.onClick();
				}

				for (int i = 0; i < lobbySize; i++) {
					
					if (GameManager.instance.getPlayers().get(i).isMouseOver(e.getX(), e.getY())) {
						GameManager.instance.getPlayers().get(i).onClick();
						
						/*
						 * non mi viene in mente altro, siccome lobbySize non toccherà nemmeno i 2 zeri, un
						 * O n al quadrato non dovrebbe rallentare
						 */
						for (int j = 0; j < lobbySize; j++) {
							// if i and j are the same, therefore the same player nothing happens
							// otherwise it sets it's isChecked to false;
							if (j != i) 
							GameManager.instance.getPlayers().get(j).setChecked(false);				
						}
					}

				}
			}
		});

		/*
		 * setOnMouseMoved / setOnMouseClicked notes ABOVE TRADITIONAL VERSION BELOW (
		 * IN COMMENT ) LAMBDA VERSION the traditional version is more verbose, but it's
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

		primaryStage.setScene(scene);
		primaryStage.setTitle("NEGRO");
		primaryStage.show();

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Animation loop
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				double w = canvas.getWidth();
				double h = canvas.getHeight();

				if (TurnManager.instance.isNight()) {
					gc.setFill(Color.DARKSLATEBLUE);
					gc.fillRect(0, 0, w, h);
					gc.fillText("NIGHT", 20, 30);
				} else {
					gc.fillText("DAY", 20, 30);
					gc.setFill(Color.DARKORANGE);
					gc.fillRect(0, 0, w, h);
				}

				gc.setFill(Color.WHITE);

				gc.fillText(TurnManager.instance.timeController() + "", 20, 90);

				gc.fillText("Manche : " + TurnManager.instance.getCurrentTurn() + "", 20, 60);

				voteButton.render(gc, mouseX, mouseY);

				int x = 100;
				int y = 500;
				gc.setFont(new Font("Arial", 24));

				for (int i = 0; i < GameManager.instance.getPlayers().size(); i++) {
					gc.setFill(Color.BLACK);
					gc.fillText(GameManager.instance.getPlayers().get(i).getUsername(), x, y - 50);

					GameManager.instance.getPlayers().get(i).setPlayerX(x);
					GameManager.instance.getPlayers().get(i).setPlayerY(y);

					GameManager.instance.getPlayers().get(i).getAction();

					GameManager.instance.getPlayers().get(i).render(gc, mouseX, mouseY);
					x = x + 150;
				}
			}
		}.start();

	}
}