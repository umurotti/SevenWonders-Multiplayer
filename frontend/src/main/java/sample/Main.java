package sample;

import com.sun.security.ntlm.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static MediaPlayer mediaPlayer;
    public static Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        scene = new Scene(root,1920,1080);
        String workingDir = System.getProperty("user.dir");
        String workingDir2 = workingDir + "/src/main/resources/css/light_theme.css";
        File lightcss = new File(workingDir2);
        Main.scene.getStylesheets().add(lightcss.toURI().toURL().toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();
        main_menu_controller controller = new main_menu_controller();

        /*workingDir += "/src/main/resources/background_music.wav";
        Media musicFile = new Media(workingDir);
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setVolume(0.6);
        mediaPlayer.play();*/

    }


    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
