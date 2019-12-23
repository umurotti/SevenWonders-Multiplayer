package sample;

import com.sun.javafx.scene.traversal.ParentTraversalEngine;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class Main extends Application {

    public static MediaPlayer mediaPlayer;
    public static Scene scene;
    public static String tableID;
    public static String wonderID;
     static Map<String, Object> map = new HashMap<String,Object>();
     static Parent te;

    @Override
    public void start(Stage primaryStage) throws Exception{



        //Parent root = FXMLLoader.load(getClass().getResource("/welcome_screen.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));

        primaryStage.setTitle("Hello World");
        scene = new Scene(root,1920,1080);
        String workingDir = System.getProperty("user.dir");
        String workingDir2 = workingDir + "/src/main/resources/css/light_theme.css";
        File lightcss = new File(workingDir2);
        Main.scene.getStylesheets().add(lightcss.toURI().toURL().toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();


        Object loaded;
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/dice_popover.fxml"));
        map.put("dicePopOver",loaded);



        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
        map.put("select_card_popover",loaded);



        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/trade_popover.fxml"));
        map.put("trade_popover",loaded);



//        FXMLLoader b = new FXMLLoader(getClass().getResource("/age_over.fxml"));
        //final in_game_controller controllerAge= b.getController();
        //loaded = (Parent)b.load();
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/age_over.fxml"));
        map.put("age_over",loaded);
        //map.put("controllerAge",controllerAge);


        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/card_zoom.fxml"));
        map.put("zoom",loaded);


        //loaded = (Parent)FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
        FXMLLoader a = new FXMLLoader(getClass().getResource("/in_game_screen.fxml"));
        loaded = (Parent)a.load();
        final in_game_controller temp = a.getController();
        map.put("home", loaded);
        map.put("loader", a);

        Task<HashMap> task = new Task<HashMap>() {
            @Override
            protected HashMap call() throws Exception {
                HashMap<String, Image> images2 = new HashMap<String, Image>();
                    try {
                        int i = 0;
                        String workingDir = System.getProperty("user.dir");
                        workingDir += "/src/main/resources/Cards";
                        File dir = new File(workingDir);
                        File[] directoryListing = dir.listFiles();
                        if (directoryListing != null) {
                            for (File child : directoryListing) {
                                InputStream temp = new FileInputStream(child);
                                String name =child.getName();
                                if(name.indexOf(" ") >0){
                                    name = name.substring(0,name.indexOf(" "))+  name.substring(name.indexOf(" " ) +1);
                                }
                                name = name.toUpperCase();
                                images2.put(child.getName().substring(0,child.getName().length()-4), new Image(temp));
                                i++;
                            }
                        }
                        temp.images2 = images2;
                    } catch (Exception interrupted) {

                    }

                return images2;
            }


        };
        Thread t = new Thread(task);
        t.start();

        map.put("controller",temp);

/*
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                boolean asd = true;
                while(asd){
                    try {


                                try{

                                    Object loaded ;
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/dice_popover.fxml"));
                                    map.put("dicePopOver",loaded);
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
                                    map.put("select_card_popover",loaded);
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/trade_popover.fxml"));
                                    map.put("trade_popover",loaded);
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/age_over.fxml"));
                                    map.put("age_over",loaded);

                                    //loaded = (Parent)FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
                                    FXMLLoader a = new FXMLLoader(getClass().getResource("/in_game_screen.fxml"));
                                    loaded = (Parent)a.load();
                                    in_game_controller temp = a.getController();
                                    map.put("home", loaded);
                                    map.put("controller",temp);
                                }catch (Exception e){
                                }

                    } catch (Exception e) {
                    }
                    asd= false;
                }
                return null;
            }
        };
        Thread t = new Thread(task);
        t.start();

*/






        /*workingDir += "/src/main/resources/background_music.wav";
        Media musicFile = new Media(workingDir);
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setVolume(0.6);
        mediaPlayer.play();*/
/*
        Object loaded ;
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/dice_popover.fxml"));
        map.put("dicePopOver",loaded);
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
        map.put("select_card_popover",loaded);
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/trade_popover.fxml"));
        map.put("trade_popover",loaded);
        loaded = (GridPane)FXMLLoader.load(getClass().getResource("/age_over.fxml"));
        map.put("age_over",loaded);

            //loaded = (Parent)FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
             FXMLLoader a = new FXMLLoader(getClass().getResource("/in_game_screen.fxml"));
             loaded = (Parent)a.load();
            in_game_controller temp = a.getController();
            map.put("home", loaded);
            map.put("controller",temp);*/
    }


    public static void main(String[] args) throws Exception{

        launch(args);
/*
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                boolean asd = true;
                while(asd){
                    try {
                                try{
                                   Object loaded ;
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/dice_popover.fxml"));
                                    map.put("dicePopOver",loaded);
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
                                    map.put("select_card_popover",loaded);
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/trade_popover.fxml"));
                                    map.put("trade_popover",loaded);
                                    loaded = (GridPane)FXMLLoader.load(getClass().getResource("/age_over.fxml"));
                                    map.put("age_over",loaded);

                                    loaded = (Parent)FXMLLoader.load(getClass().getResource("/in_game_screen.fxml"));
                                    FXMLLoader a = new FXMLLoader(getClass().getResource("/in_game_screen.fxml"));
                                    loaded = (Parent)a.load();
                                    te = a.load();
                                    Parent b = a.load();
                                    in_game_controller temp = a.getController();
                                    map.put("home", b);
                                    map.put("controller",temp);
                                }catch (Exception e){
                                }

                    } catch (Exception e) {
                    }
                    asd= false;
                }
                return null;
            }
        };
        Thread t = new Thread(task);
        t.start();
*/
    }
}
