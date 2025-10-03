package game.lupus.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Loads menu.fxml file 
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));

			Scene scene = new Scene(root);

			// Set up the stage
			/*
			 * if someone is reading this code, JavaFX is basically a theater
			 * we have the stage, scenes etc...
			 * if you are wondering what the .fxml files are... well it's something WE don't like
			 * it's sort of a html + css file
			 */
			Image icon = new Image("lupusIcon.PNG");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Il giochino del lupo mannaro");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.err.println("Error loading FXML file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}