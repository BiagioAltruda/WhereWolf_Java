package game.lupus.javafx;

import java.io.IOException;
import java.util.ArrayList;

import game.lupus.javafx.buttons.ButtonTest;
import game.lupus.manager.GameManager;
import game.lupus.manager.TurnManager;
import game.lupus.model.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	ButtonTest voteButton = new ButtonTest(105, 95, 40, 20, "Vote", () -> {
		System.out.println("Vote clicked!");
	});

	@Override
	public void start(Stage primaryStage) throws IOException {

		AnchorPane pane = new AnchorPane();
		Canvas canvas = new Canvas();
		Scene scene = new Scene(pane, windowWidth, windowHeight);

		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		pane.getChildren().add(canvas);

		// JavaFX offers mouse properties yayy huh? /
		canvas.setOnMouseMoved(e -> {
			mouseX = e.getX();
			mouseY = e.getY();
		});

		canvas.setOnMouseClicked(e -> {
			if (voteButton.isHovered(e.getX(), e.getY())) {
				voteButton.onClick();
			}
		});

		primaryStage.setScene(scene);
		primaryStage.setTitle("NEGRO");
		primaryStage.show();

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Player p1 = new Player();
		p1.setUsername("Bardo");
		p1.setId(1);
		p1.setPassed(false);

		Player p2 = new Player();
		p2.setUsername("Lupo");
		p2.setId(2);
		p2.setPassed(false);

		Player p3 = new Player();
		p3.setUsername("Luce");
		p3.setId(3);
		p3.setPassed(false);

		Player p4 = new Player();
		p4.setUsername("Biagi");
		p4.setId(4);
		p4.setPassed(false);

		GameManager.instance.setPlayers(p1, p2, p3, p4);

		// Animation loop
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				double w = canvas.getWidth();
				double h = canvas.getHeight();

				gc.setFill(Color.DARKSLATEBLUE);
				gc.fillRect(0, 0, w, h);

				gc.setFill(Color.WHITE);

				if (TurnManager.instance.isNight()) {
					gc.fillText("NIGHT", 20, 30);
				} else {
					gc.fillText("DAY", 20, 30);
				}

				gc.fillText(TurnManager.instance.timeController() + "", 20, 90);

				gc.fillText("Manche : " + TurnManager.instance.getCurrentTurn() + "", 20, 60);

				int x = 100;
				int y = 500;
				gc.setFont(new Font("Arial", 24));
				for (int i = 0; i < GameManager.instance.getPlayers().size(); i++) {
					gc.setFill(Color.BLACK);

					gc.fillText(GameManager.instance.getPlayers().get(i).getUsername(), x, y - 50);
					gc.setFill(Color.RED);

					gc.fillOval(x, y, 100, 100); // center object
					x = x + 150;
				}
			}
		}.start();

	}
}