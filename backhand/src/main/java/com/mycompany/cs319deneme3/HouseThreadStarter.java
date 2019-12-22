/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs319deneme3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.House;

/**
 *
 * @author umur
 */
public class HouseThreadStarter extends HttpServlet {
    
    private volatile boolean isActive = true;
    private Thread thr;
    private ServerSocket ss = null;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        thr = new Thread(new Runnable() {
            @Override
            public void run() {
                
                try {
                    ss = new ServerSocket(7008, 10, InetAddress.getByName("0.0.0.0"));
                } catch (UnknownHostException ex) {
                    Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.SEVERE, null, ex);
                }
                try 
                    {
                    Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.INFO, "Thread starting");
                    while (isActive) {
                        Socket s = ss.accept();
                        
                        Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.INFO, "New Connection remote {0} local {1}", new Object[]{s.getRemoteSocketAddress().toString(), s.getLocalSocketAddress().toString()});
                        
                        byte[] bytes = new byte[1];
                        int readValue = s.getInputStream().read(bytes);
                        byte[] userId = new byte[bytes[0]];
                        s.getInputStream().read(userId);
                        
                        readValue = s.getInputStream().read(bytes);
                        byte[] tableId = new byte[bytes[0]];
                        s.getInputStream().read(tableId);
                        String user = new String(userId, "utf-8");
                        String table = new String(tableId, "utf-8");
                        
                        if (House.getInstance().getTables().containsKey(table)) {
                            Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.INFO, "User {1} associated with table {0}", new Object[]{table, user});
                            House.getInstance().getTables().get(table).addChannel(user, s);
                        } else {
                            Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.INFO, "Table {0} not found", new Object[]{table});
                            s.close();
                        }
                        
                    }
                } catch (Throwable ex) {
                    Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.INFO, "Closing server socket");
                    ss.close();
                } catch (IOException ex) {
                    Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thr.start();
    }

    @Override
    public void destroy() {
        super.destroy(); 
        isActive = false;
        try {
            if(ss != null)
                ss.close();
        } catch (IOException ex) {
            Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        thr.interrupt();
        Logger.getLogger(HouseThreadStarter.class.getName()).log(Level.INFO, "Thread terminating");
    }
    
    
    
    


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
