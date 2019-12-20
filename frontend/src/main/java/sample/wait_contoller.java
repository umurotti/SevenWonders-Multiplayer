package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class wait_contoller {
    @FXML
    Parent root;
    @FXML
    Button wait_refresh;
    public void refreshPressed()throws Exception{
        Scene scene1 = wait_refresh.getScene();
        root = (Parent)Main.map.get("home");
        scene1.setRoot(root);
    }
}
