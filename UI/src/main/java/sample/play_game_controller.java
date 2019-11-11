package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class play_game_controller implements Initializable {

    @FXML
    private Button create_new_game_button;
    @FXML
    private Button join_game_button;
    @FXML
    private Button back_button;

    @FXML
    Parent root;

    //private Scene secondScene;
    public void onPressCreateButton(ActionEvent event) throws Exception{

        Scene scene1 = create_new_game_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/resources/start_game_screen.fxml"));
        scene1.setRoot(root);
    }
    public void onPressJoinButton(ActionEvent event) throws Exception{

        Scene scene1 = join_game_button.getScene();
        root = FXMLLoader.load(getClass().getResource("../../resources/join_game_screen.fxml"));
        scene1.setRoot(root);
    }
    public void onPressBackButton(ActionEvent event) throws Exception{

        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("../../resources/sample.fxml"));
        scene1.setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo
    }
}
