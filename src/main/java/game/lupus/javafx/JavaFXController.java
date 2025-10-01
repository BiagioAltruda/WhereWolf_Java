package game.lupus.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JavaFXController implements Initializable{
	
	private Stage stage; 
	private Scene scene;
	private Parent root;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Sound.getInstance().playMusic();
	}
	
	// FXML annotated buttons 
	@FXML
	private Button createLobbyButton;
	
	@FXML
	private Button enterLobbyButton;
	
	// switches scene from Main Menu into Create Lobby
	@FXML
	public void menuToCreateLobby(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/createLobby.fxml"));
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//  switches scene from Main Menu to Join Lobby
	//  has yet to be implemented, test.fxml is just a random test file 
	@FXML
	public void menuToJoinLobby(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/test.fxml"));
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// switches scene from Create Lobby to Main Menu
	@FXML
	public void createLobbyToMenu(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}