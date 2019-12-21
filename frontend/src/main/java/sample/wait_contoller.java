package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.HashMap;

public class wait_contoller {
    @FXML
    Parent root;
    @FXML
    Button wait_refresh;
    ServerConnection con = new ServerConnection();
    public void refreshPressed()throws Exception{

        in_game_controller in_game_controller= (in_game_controller) Main.map.get("controller");
        HashMap<String,WonderBoard> wonderboard=  (HashMap<String, WonderBoard>) con.ConvertJson(Main.tableID);
        Scene scene1 = wait_refresh.getScene();
        root = (Parent)Main.map.get("home");
        scene1.setRoot(root);
        in_game_controller.beginRefresh(wonderboard);
    }
}
