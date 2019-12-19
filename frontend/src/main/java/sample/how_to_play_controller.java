package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class how_to_play_controller  {
    @FXML
    private Button back_button;

    @FXML
    TextField text1;
    @FXML
    private Button get;
    @FXML
    Parent root;

    ServerConnection con = new ServerConnection();
    private Scene secondScene;
    public void onPressButton1(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        scene1.setRoot(root);
    }

    public void onPressget(ActionEvent event) throws Exception{
        //con.getRequest();
        //String cards[] = con.getRequest();
        //System.out.print("AAAAAAAAAA");
        //text1.setText((cards[1] + cards[0] + cards[2]));

    }


/*
    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }

    public void openSecondScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }*/


}
