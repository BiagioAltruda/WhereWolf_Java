package game.lupus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import game.lupus.javafx.JavaFXTest;

@SpringBootApplication
public class LupusApplication {

	public static void main(String[] args) {
		//	SpringApplication.run(LupusApplication.class, args);
		
		javafx.application.Application.launch(JavaFXTest.class, args);
	}

}
