package sample;

import com.sun.security.ntlm.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class start_game_controller {
    static String TableID ="";
    @FXML
    TextField tableID;
    @FXML
    private Button create_game_button;

    @FXML
    private Button back_button;

    @FXML
    Parent root;

    // private Scene secondScene;
    ServerConnection con = new ServerConnection();
    public void onPressCreateGame(ActionEvent event) throws Exception{
        con.sendRequestCreate(player_id_controller.WonderID, tableID.getText());
        Scene scene1 = create_game_button.getScene();
        TableID = tableID.getText();
        root = FXMLLoader.load(getClass().getResource("/creator_wait_screen.fxml"));
        scene1.setRoot(root);
    }

    public void onPressBackButton(ActionEvent event) throws Exception{

        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/play_game_screen.fxml"));
        scene1.setRoot(root);
    }

}