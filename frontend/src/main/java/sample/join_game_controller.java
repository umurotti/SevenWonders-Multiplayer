package sample;

import com.sun.security.ntlm.Server;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class join_game_controller implements Initializable  {
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
    int noOfGames = 0;
    ServerConnection con = new ServerConnection();
    public void join_button_action(ActionEvent event) throws Exception{

        String selectedGame;
        ObservableList<String> observableList;
        observableList = game_list.getSelectionModel().getSelectedItems();
        selectedGame = observableList.get(0);

        //selectedGame = join_id.getText();
        con.sendRequestJoin(selectedGame,player_id_controller.WonderID);
        TableID = selectedGame;
        Main.tableID = TableID;
        Scene scene1 = join_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/wait_screen.fxml"));
        scene1.setRoot(root);
        SocketThread socket = new SocketThread();
        Thread thread = new Thread(socket);
        thread.start();
    }

    public void back_button_action(ActionEvent event) throws Exception{

        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/play_game_screen.fxml"));
        scene1.setRoot(root);
    }

    public void refresh_game_list(ActionEvent event)throws Exception{
        HashMap<String,Integer> waitTables = con.getTableList();
        game_list.getItems().clear();
        for(String tableID : waitTables.keySet()){
            System.out.println("elif" + tableID);
            game_list.getItems().add(tableID);
        }

    }
    public void initialize(URL location, ResourceBundle resources){
        //SOME SERVER CONNECTİON METHOD
        try{
            HashMap<String,Integer> waitTables = con.getTableList();
            for(String tableID : waitTables.keySet()){
                game_list.getItems().add(tableID);
            }

        }catch (Exception e){
        }

    }
}
