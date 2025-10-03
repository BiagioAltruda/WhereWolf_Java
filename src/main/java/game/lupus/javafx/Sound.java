package game.lupus.javafx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
    private static Sound instance;
    private MediaPlayer mediaPlayer;

    private Sound() {
        String path = getClass().getResource("/fxml/assets/DarkIsTheNight.mp3").toExternalForm();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        // loop Music
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    // for testing, each time a scene switches playMusic gets called again
    public static Sound getInstance() {
        if (instance == null) {
            instance = new Sound();
        }
        return instance;
    }

    public void playMusic() {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            mediaPlayer.play();
        }
    }

    public void stopMusic() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
    }
}