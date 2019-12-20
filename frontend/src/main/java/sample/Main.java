package sample;

import com.sun.security.ntlm.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    public static MediaPlayer mediaPlayer;
    public static Scene scene;
    public static String tableID;
    public static String wonderID;
     static Map<String, Object> map = new HashMap<String,Object>();

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

        Object loaded ;
            //loaded = (Parent)FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
             FXMLLoader a = new FXMLLoader(getClass().getResource("/in_game_screen.fxml"));
             loaded = (Parent)a.load();
            in_game_controller temp = a.getController();
            map.put("home", loaded);
            map.put("controller",temp);
            loaded = (GridPane)FXMLLoader.load(getClass().getResource("/dice_popover.fxml"));
            map.put("dicePopOver",loaded);
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
        map.put("select_card_popover",loaded);
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/trade_popover.fxml"));
        map.put("trade_popover",loaded);
    }


    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
