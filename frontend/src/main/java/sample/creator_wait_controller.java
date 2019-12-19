package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class creator_wait_controller {

    @FXML
    private Button start_game_button;

    @FXML
    private Button refresh_button;

    @FXML
    Parent root;
    ServerConnection con = new ServerConnection();
    // private Scene secondScene;

    public void onPressStartGame(ActionEvent event) throws Exception{
        con.sendRequestStartTable(start_game_controller.TableID);
        /*Scene scene1 = start_game_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
        scene1.setRoot(root);*/

        Scene scene1 = start_game_button.getScene();
        root =(Parent) Main.map.get("home");
        scene1.setRoot(root);
    }
}
