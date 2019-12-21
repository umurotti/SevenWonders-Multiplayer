package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.HashMap;

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
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        con.sendRequestCreate(player_id_controller.WonderID, player_id_controller.WonderID);
        Scene scene1 = start_game_button.getScene();
        con.sendRequestStartTable(start_game_controller.TableID);
        /*Scene scene1 = start_game_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
        scene1.setRoot(root);*/
        in_game_controller in_game_controller= (in_game_controller) Main.map.get("controller");
        HashMap<String,WonderBoard> wonderboard=  (HashMap<String, WonderBoard>) con.ConvertJson(Main.tableID);
        root =(Parent) Main.map.get("home");
        scene1.setRoot(root);
        in_game_controller.beginRefresh(wonderboard);
    }
}
