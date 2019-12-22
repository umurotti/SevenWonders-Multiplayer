/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umur
 */
public class TableNotifier implements Runnable{
    private LinkedBlockingQueue<Event> eventQueue;
    private Map<String, Socket> eventChannel;
    
    public TableNotifier(LinkedBlockingQueue<Event> eventQueue, Map<String, Socket> sockets) {
        this.eventChannel = sockets;
        this.eventQueue = eventQueue;
        
        Thread thr = new Thread(this);
        thr.start();
    }
    private volatile boolean isActive = true;
    
    public void shutDown() {
        isActive = false;
    }
    
    @Override
    public void run() {
        while (isActive) {
            try {

                Event ev = eventQueue.take();
                for (Map.Entry<String, Socket> entry : eventChannel.entrySet()) {
                    try {
                        
                        entry.getValue().getOutputStream().write(ev.getLength());
                        entry.getValue().getOutputStream().write(ev.getContent().getBytes());
                    } catch (IOException ex) {
                        Logger.getLogger(TableNotifier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (Throwable ex) {
                Logger.getLogger(TableNotifier.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
}
