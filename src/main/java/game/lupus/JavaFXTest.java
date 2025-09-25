package game.lupus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

	@Override
	public void start(Stage primaryStage) {

		Label label = new Label("Sium!");
		Parent root;
		try {

			root = FXMLLoader.load(getClass().getResource("test.fxml"));

			Scene scene = new Scene(root);

//			primaryStage.setWidth(400);
//			primaryStage.setHeight(400);

			Image icon = new Image("lupusIcon.PNG");

			// X and Y coordinates on where the window will appear
			/*
			 * primaryStage.setX(100); primaryStage.setY(100);
			 */

			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("JavaFX Test ");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
