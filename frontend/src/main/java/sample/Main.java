package sample;

import com.sun.security.ntlm.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root,1920,1080));
        primaryStage.setFullScreen(true);
        primaryStage.show();
        main_menu_controller controller = new main_menu_controller();
    }


    public static void main(String[] args) throws Exception{
        launch(args);

    }
}
