package sample;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Screen;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketThread extends Task<Void> {

    public SocketThread(){
        System.out.println("KKKKKK");

    }

    boolean socketThreadOpen= true;
   /*public void run() {
        try {
            while (true){
                System.out.println("5555");
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
            final String messageString = new String(message, "utf-8");
            System.out.println(new String(message, "utf-8"));
            System.out.println("Thread is about to start  3 ");
            final in_game_controller controller = (in_game_controller) Main.map.get("controller");
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        if (messageString.equals("TURN_OVER")) {
                            controller.refresh();
                        } else if (messageString.equals("PLAYER_JOINED")) {
                            System.out.println("player joinede girdi");
                        }
                    } catch (Exception e) {

                    }

                }
            });
        }
        }catch(Exception e){

        }

    }*/
    public void closeThread(){
        socketThreadOpen = false;
    }

    protected Void call() throws Exception {
        boolean temp = true;
        int temp1 = 0;
        final in_game_controller controller = (in_game_controller) Main.map.get("controller");
        int temp2 = 0;
        System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEE");
        Socket socket = new Socket("192.168.1.32", 7008);
            final int count = temp1;
            try {
                while (true){
                    System.out.println("5555");
                    //Socket socket = new Socket("192.168.1.32", 7008);
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
                     String messageStr = new String(message, "utf-8");
                     final String messageString = messageStr;
                    System.out.println(new String(message, "utf-8"));
                    System.out.println("Thread is about to start  3 ");
                    System.out.println("Thread is about to start  4 ");

                    if(socket.isClosed()){
                        socket = new Socket("192.168.1.32", 7008);

                        System.out.println("Thread is about to start 1");
                        socket.getOutputStream().write(userName.getBytes("utf-8").length);
                        socket.getOutputStream().write(userName.getBytes("utf-8"));

                        socket.getOutputStream().write(tableId.getBytes("utf-8").length);
                        socket.getOutputStream().write(tableId.getBytes("utf-8"));
                    }

                    Platform.runLater(new Runnable() {
                        public void run() {
                            try {
                                System.out.println("Thread is about to start  5 " + messageString);
                                if (messageString.equals("TURN_OVER")) {
                                    controller.refresh();
                                } else if (messageString.equals("PLAYER_JOINED")) {
                                    System.out.println("player joinede girdi");
                                }else if(messageString.equals("AGE_OVER")){
                                    System.out.println("ageovera girdiiiiiiiiiiiiiiiiiiiiiiiiiii");
                                    controller.ageOver(1);
                                }else if(messageString.equals("DICE_ROLL_OVER")){
                                    controller.diceGameEnds();
                                }else if(messageString.equals("TABLE_START")){
                                    wait_contoller abo = (wait_contoller) Main.map.get("wait_controller");
                                    abo.refreshPressed();

                                }else if(messageString.equals("DICE_ROLL_PLAYER_JOINED")){
                                    controller.playerEnteredDiceGame();
                                }
                            } catch (Exception e) {

                            }

                        }
                    });
                }
            } catch (Exception interrupted) {

            }


        return null;
    }
}
