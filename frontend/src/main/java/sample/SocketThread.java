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
   public void run() {
        /*try{
            while(socketThreadOpen){
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println("aaa");
                game_controller.refresh();
                socketThreadOpen = true;

            }
        }catch(Exception e){

        }*/

    }
    public void closeThread(){
        socketThreadOpen = false;
    }

    protected Void call() throws Exception {
        boolean temp = true;
        int temp1 = 0;
        while(temp){
            final int count = temp1;
            try {
                Thread.sleep(5000);

                Platform.runLater(new Runnable() {
                    public void run() {
                       /* try{
                            if(count == 0)
                                game_controller.diceGamePopOver(new ActionEvent());
                            else if(count == 1)
                                game_controller.playerEnteredDiceGame("TheColossusofRhodes");
                            else if (count == 2)
                                game_controller.refresh();

                        }catch (Exception e){

                        }*/

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
            System.out.println("aaa");
            if(temp1 == 2)
                temp1 = 3;
            else if(temp1 == 1)
                temp1 = 2;
            else if(temp1 == 0)
                temp1 = 1;

        }
        return null;
    }
}
