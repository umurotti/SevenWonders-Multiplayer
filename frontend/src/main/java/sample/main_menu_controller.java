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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

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
        SocketThread socketThread = new SocketThread((in_game_controller) Main.map.get("controller"));
        Thread newT = new Thread(socketThread);
        newT.start();
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
