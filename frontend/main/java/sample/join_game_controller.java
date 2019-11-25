package sample;

import com.sun.security.ntlm.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class join_game_controller {
    static String TableID = "";

    @FXML
    ListView game_list;
    @FXML
    Button refresh_button;
    @FXML
    Button join_button;
    @FXML
    TextField join_id;
    @FXML
    private Button back_button;

    @FXML
    Parent root;

    ServerConnection con = new ServerConnection();
    public void join_button_action(ActionEvent event) throws Exception{
        con.sendRequestJoin(join_id.getText(),player_id_controller.WonderID);
        TableID =  join_id.getText() ;
        Scene scene1 = join_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/wait_screen.fxml"));
        scene1.setRoot(root);
    }

    public void back_button_action(ActionEvent event) throws Exception{

        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/play_game_screen.fxml"));
        scene1.setRoot(root);
    }

    public void refresh_game_list(MouseEvent event)throws Exception{
        String gameid = "game";
        for(int i = 0 ; i < 50 ; i++){

            game_list.getItems().add(i,gameid + i);
        }


    }


}
