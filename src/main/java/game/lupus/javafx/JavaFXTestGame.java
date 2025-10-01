package game.lupus.javafx;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.stage.Stage;

public class JavaFXTestGame extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException{



        AnchorPane pane = new AnchorPane();
        Canvas canvas = new Canvas();
        Scene scene = new Scene(pane, 1280, 720);

        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        pane.getChildren().add(canvas);

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

                // Clear and redraw everything every frame
                gc.setFill(Color.DARKSLATEBLUE);
                gc.fillRect(0, 0, w, h);

                gc.setFill(Color.WHITE);

                if(TurnManager.instance.isNight()) {
                gc.fillText("NIGHT", 20, 30);
                } else {
                    gc.fillText("DAY", 20, 30);
                }

                gc.fillText(TurnManager.instance.timeController() + "", 20 , 90);

                gc.fillText("Manche : " + TurnManager.instance.getCurrentTurn() + "", 20, 60);

                gc.setFill(Color.RED);
                gc.fillOval(w / 2 - 50, h / 2 - 50, 100, 100); // center object

                
               
            }
        }.start();


                Player p1 = new Player();
                p1.setUsername("Bardo");
                p1.setId(1);
                p1.setPassed(false);

                Player p2 = new Player();
                p2.setUsername("Lupo");
                p2.setId(2);
                p2.setPassed(false);


                GameManager.instance.setPlayers(p1, p2);
        
    }


}