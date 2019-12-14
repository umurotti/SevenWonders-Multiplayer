package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class main_menu_controller {
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
    public void onPressExitButton(ActionEvent event) throws Exception{


        Platform.exit();
    }

    public void onPressHowtoPlayButton(ActionEvent event) throws Exception{

        Scene scene1 = how_to_play_button.getScene();
        //root = FXMLLoader.load(getClass().getResource("/how_to_play_screen.fxml"));
        root = FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
        scene1.setRoot(root);
    }
    public void onPressCreditsButton(ActionEvent event) throws Exception{

        Scene scene1 = credits_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/credits_screen.fxml"));
        scene1.setRoot(root);
    }
    public void onPressPlayGameButton(ActionEvent event) throws Exception{

        Scene scene1 = play_game_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/player_id_screen.fxml"));
        scene1.setRoot(root);
    }
    public void onPressSettingButton(ActionEvent event) throws Exception{

        Scene scene1 = settings_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/settings_screen.fxml"));
        scene1.setRoot(root);
    }

}
