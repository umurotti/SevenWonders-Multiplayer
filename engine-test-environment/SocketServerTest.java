import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.*;

public class SocketServerTest {
    
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 8008;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        
        ArrayList<Socket> sockets = new ArrayList<Socket>();

        server = new ServerSocket(port);
        while (true) {
            Socket s = server.accept();
            sockets.add(s);
            byte [] bytes = new byte[1000000];
            int readValue = s.getInputStream().read(bytes);
            String clientMsg = new String(bytes, 0, readValue);
            System.out.println(clientMsg);

            String serverMsg = "Hello " + clientMsg;
            s.getOutputStream().write(serverMsg.getBytes());

            // sockets.forEach(e -> e.getOutputStream().write(serverMsg.getBytes()));
            String notify = "refresh";
            for (Socket e : sockets) {
                if (e != s)
                    e.getOutputStream().write(notify.getBytes());
            }
        }
    }
}