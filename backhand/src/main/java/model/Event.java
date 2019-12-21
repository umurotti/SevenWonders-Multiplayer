/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umur
 */
public class Event {
    public static final Event TABLE_CHANGE = new Event("TABLE_CHANGE");
    public static final Event TURN_OVER = new Event("TURN_OVER");
    public static final Event DICE_ROLL = new Event("DICE_ROLL");
    public static final Event AGE_OVER = new Event("AGE_OVER");
    public static final Event PLAYER_JOINED = new Event("PLAYER_JOINED");
    private byte length;
    private String content;
    //private Event alo;
    
    public Event(String content) {
        this.content = content;
        try {
            this.length = (byte) content.getBytes("utf-8").length;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            this.length = (byte) content.getBytes().length;
        }
    }

    public byte getLength() {
        return length;
    }

    public String getContent() {
        return content;
    }
    
    
}
