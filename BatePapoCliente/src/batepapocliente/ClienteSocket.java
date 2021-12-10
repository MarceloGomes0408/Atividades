package batepapocliente;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


/**
 *
 * @author Marcelo Gomes
 */

public class ClienteSocket {
    Socket socket;
    String ip;
    int porta;
    
    public ClienteSocket(String ip, int porta){
        this.ip=ip;
        this.porta=porta;
    }
    
    public void conect() {
        try {
            socket = new Socket(ip, porta);
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
    
    public String getName(){
        String nomeHost = socket.getInetAddress().getHostName();
        return nomeHost;
    }
}
