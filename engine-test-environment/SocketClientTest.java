import java.net.*;

public class SocketClientTest {
    public static void main(String[] args) throws Exception{
        Socket s = new Socket();
        SocketAddress addr = new InetSocketAddress("192.168.1.27", 8008);
        s.connect(addr);
        s.getOutputStream().write("ömer".getBytes());
        while(true) {
            byte [] bytes = new byte[100];
            int readValue = s.getInputStream().read(bytes);
            System.out.println(new String(bytes, 0, readValue));
        }
        //s.close();
    }
}