package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class start_game_controller {

    @FXML
    private Button start_game_button;

    @FXML
    Parent root;

    // private Scene secondScene;
    public void onPressStartGame(ActionEvent event) throws Exception{

        Scene scene1 = start_game_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/resources/in_game_screen.fxml"));
        scene1.setRoot(root);
    }
}

