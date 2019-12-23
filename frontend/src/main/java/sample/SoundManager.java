package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundManager {
    public enum MUSIC {BUTTON_CLICK,CARD_DEAL,ENEMIES,MY_WONDER,DICE,AGE_OVER,GAME_OVER}
    static MediaPlayer mediaPlayer;

    public static void play(MUSIC music) throws Exception{
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        String workingDir = System.getProperty("user.dir");
        //MediaPlayer mediaPlayer;
        Media musicFile;
        workingDir += "/src/main/resources/musics/";
        switch (music){
            case BUTTON_CLICK:
                workingDir += "card_flip_001.wav";
                break;
            case CARD_DEAL:
                workingDir += "card__multi_flip_003.wav";
                break;
            case ENEMIES:
                workingDir += "enemies_pane.wav";
                break;
            case MY_WONDER:
                workingDir += "my_wonder_pane.wav";
                break;
            default:
                workingDir += "card_flip_001.wav";
        }
        musicFile = new Media(new File(workingDir).toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setVolume(0.6);
        mediaPlayer.play();
    }

}