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

public class how_to_play_controller implements Initializable {
    @FXML
    private Button back_button;

    @FXML
    Parent root;

    private Scene secondScene;
    public void onPressButton1(ActionEvent event) throws Exception{

        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/resources/sample.fxml"));
        scene1.setRoot(root);
    }
/*
    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }

    public void openSecondScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
