package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class player_id_controller {
    static String WonderID;
    @FXML
    private TextField wonderID;
    @FXML
    private Button play_game_button;
    @FXML
    private Button back_button;
    @FXML
    Parent root;

    ServerConnection con = new ServerConnection();
    public void onPressBackButton(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        scene1.setRoot(root);
    }

    public void onPressPlayButton(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Scene scene1 = play_game_button.getScene();
        WonderID = wonderID.getText();
        Main.wonderID = WonderID;
        root = FXMLLoader.load(getClass().getResource("/play_game_screen.fxml"));
        scene1.setRoot(root);
    }

}
