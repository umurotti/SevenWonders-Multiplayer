package sample;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class main_menu_controller {
    @FXML
    GridPane sample_grid;
    @FXML
    private Button exit_button;
    @FXML
    private Button how_to_play_button;
    @FXML
    private Button credits_button;
    @FXML
    private Button play_game_button;
    @FXML
    private Button settings_button;
    @FXML
    Parent root;

   /* @FXML
    javafx.scene.image.ImageView image_logo;
    public void welcome() throws Exception{
        javafx.util.Duration sec2 = javafx.util.Duration.millis(1500);
        FadeTransition ft = new FadeTransition(sec2);
        ft.setFromValue(1.0f);
        ft.setToValue(0.3f);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        ft.setNode(image_logo);
        ft.play();

        if(ft.getCycleCount() == 3){
            Scene scene1 = image_logo.getScene();
            root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
            scene1.setRoot(root);
        }
    }*/






    public void onPressExitButton(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Platform.exit();
    }

    public void onPressHowtoPlayButton(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
/*
        Scene scene1 = how_to_play_button.getScene();
        //root = FXMLLoader.load(getClass().getResource("/how_to_play_screen.fxml"));
        root = FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
        scene1.setRoot(root);*/
        Scene scene1 = how_to_play_button.getScene();
        root =(Parent) Main.map.get("home");
        scene1.setRoot(root);
        SocketThread socketThread = new SocketThread();
        Thread newT = new Thread(socketThread);
        newT.start();





/*
        Map<String,Image> images2 = new HashMap<String, Image>();
        int i = 0;
        String workingDir = System.getProperty("user.dir");
        workingDir += "/src/main/resources/Cards";
        File dir = new File(workingDir);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                InputStream temp = new FileInputStream(child);
                String name =child.getName();
                if(name.indexOf(" ") >0){
                    name = name.substring(0,name.indexOf(" "))+  name.substring(name.indexOf(" " ) +1);
                }
                name = name.toUpperCase();
                images2.put(child.getName().substring(0,child.getName().length()-4), new Image(temp));
                i++;
            }
        }*/

        SocketThread socket = new SocketThread();
        Thread thread = new Thread(socket);
        System.out.println("Thread is about to start");
        thread.start();


    }
    public void onPressCreditsButton(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Scene scene1 = credits_button.getScene();
        //root = FXMLLoader.load(getClass().getResource("/credits_screen.fxml"));
        root = FXMLLoader.load(getClass().getResource("/join_game_screen.fxml"));
        scene1.setRoot(root);
    }
    public void onPressPlayGameButton(ActionEvent event) throws Exception{


        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Scene scene1 = play_game_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/player_id_screen.fxml"));
        scene1.setRoot(root);

    }
    public void onPressSettingButton(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Scene scene1 = settings_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/settings_screen.fxml"));
        scene1.setRoot(root);
    }

}
