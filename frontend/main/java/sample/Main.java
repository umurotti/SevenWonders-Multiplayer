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
        File lightcss = new File("/Users/elifkasapoglu/Desktop/sample/src/main/resources/css/light_theme.css");
        Main.scene.getStylesheets().add(lightcss.toURI().toURL().toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        main_menu_controller controller = new main_menu_controller();


        Media musicFile = new Media("file:///Users/elifkasapoglu/Desktop/sample/src/main/resources/background_music.wav");
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setVolume(0.6);
        mediaPlayer.play();

    }


    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
