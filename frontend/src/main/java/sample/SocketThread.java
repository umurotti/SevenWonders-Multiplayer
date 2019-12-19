package sample;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Screen;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import java.util.concurrent.TimeUnit;

public class SocketThread extends Task<Void> {

    in_game_controller game_controller;
    public SocketThread(in_game_controller in_game_controller){
        game_controller = in_game_controller;
    }

    boolean socketThreadOpen= true;
    public void closeThread(){
        socketThreadOpen = false;
    }

    protected Void call() throws Exception {
        boolean temp = true;
        int temp1 = 0;
        int temp2 = 0;
        boolean gate = false;
        while(temp){
            final int count = temp1;
            try {
                Thread.sleep(500);
                temp2++;
                if(temp2 == 10){
                    gate= true;
                    temp2 = 0;
                }

                if(gate)
                Platform.runLater(new Runnable() {
                    public void run() {
                        try{
                            System.out.println("ddddd");
                            if(count == 0){
                                //game_controller.diceGamePopOver(new ActionEvent());
                                System.out.println("aaaaaaaaaaaaaa");
                                game_controller.refresh();

                            }
                            else if(count == 1){
                                //game_controller.playerEnteredDiceGame("TheColossusofRhodes");
                                game_controller.refresh();
                                System.out.println("abbbaa");
                           }
                            else if (count == 2){
                                game_controller.refresh();
                                System.out.println("abccccbaa");
                            }
                        }catch (Exception e){
                        }
                    }
                });


            } catch (InterruptedException interrupted) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
                    break;
                }
            }
            /*if(game_controller == null){
                temp = false;
                System.out.println("stopped");
            }*/
            System.out.println(temp1);
            if(temp1 == 1 &&gate)
                temp1 = 2;
            else if(temp1 == 0&&gate)
                temp1 = 1;
            else if(temp1 == 2&&gate)
                temp1 = 3;
            gate= false;
        }
        return null;
    }
}
