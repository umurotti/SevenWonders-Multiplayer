package sample;

import com.sun.security.ntlm.Server;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.Socket;

public class start_game_controller {
    static String TableID = "";
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

    public void onPressCreateGame(ActionEvent event) throws Exception {
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        con.sendRequestCreate(player_id_controller.WonderID, tableID.getText());
        Scene scene1 = create_game_button.getScene();
        TableID = tableID.getText();
        Main.tableID = TableID;
        con.sendRequestCreate(player_id_controller.WonderID, TableID);
        in_game_controller te = (in_game_controller)Main.map.get("controller");
        SocketThread socket = new SocketThread();
        Thread thread = new Thread(socket);
        System.out.println("Thread is about to start");
        thread.start();


        root = FXMLLoader.load(getClass().getResource("/creator_wait_screen.fxml"));
        scene1.setRoot(root);



/*
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Socket socket = new Socket("192.168.1.32", 7008);
                        String userName = Main.wonderID;
                        String tableId = Main.tableID;
                        System.out.println("Thread is about to start 1");
                        socket.getOutputStream().write(userName.getBytes("utf-8").length);
                        socket.getOutputStream().write(userName.getBytes("utf-8"));

                        socket.getOutputStream().write(tableId.getBytes("utf-8").length);
                        socket.getOutputStream().write(tableId.getBytes("utf-8"));

                        System.out.println("Thread is about to start  2");
                        byte length = (byte) socket.getInputStream().read();
                        byte[] message = new byte[length];
                        socket.getInputStream().read(message);
                        System.out.println(new String(message, "utf-8"));
                        System.out.println("Thread is about to start  3 ");
                        Platform.runLater(new Runnable() {
                            public void run() {
                                try {

                                } catch (Exception e) {

                                }

                            }
                        });


                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            break;
                        }
                    }


                }
                return null;
            }


        };
        Thread t = new Thread(task);
        t.start();
*/

    }
    public void onPressBackButton(ActionEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.BUTTON_CLICK);
        Scene scene1 = back_button.getScene();
        root = FXMLLoader.load(getClass().getResource("/play_game_screen.fxml"));
        scene1.setRoot(root);
    }
}