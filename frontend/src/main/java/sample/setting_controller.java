package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class setting_controller   {
    @FXML
    private Button back_button;



    @FXML
    Parent root;



    @FXML
    private void onPressMusicOn(ActionEvent event)throws Exception{
        Main.mediaPlayer.play();
    }

    @FXML
    private void onPressMusicOff(ActionEvent event)throws Exception{
        Main.mediaPlayer.stop();
    }

    @FXML
    private void darkTheme(ActionEvent event) throws Exception{
        String workingDir = System.getProperty("user.dir");
        workingDir += "/src/main/resources/css/dark_theme.css";
        File darkcss = new File(workingDir);
        Main.scene.getStylesheets().clear();
        Main.scene.getStylesheets().add(darkcss.toURI().toURL().toExternalForm());

    }

    @FXML
    private void lightTheme(ActionEvent event) throws Exception{
        String workingDir = System.getProperty("user.dir");
        workingDir += "/src/main/resources/css/light_theme.css";
        File lightcss = new File(workingDir);
        Main.scene.getStylesheets().clear();
        Main.scene.getStylesheets().add(lightcss.toURI().toURL().toExternalForm());
    }

    public void onPressBackButton(ActionEvent event) throws Exception{

        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
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



}
