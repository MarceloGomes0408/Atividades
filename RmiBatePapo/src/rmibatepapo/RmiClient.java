package rmibatepapo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import rmi_helloworld.DAORMIClientInterface;



/**
 *
 * @author Marcelo Gomes
 */

public class RmiClient {
    static DAORMIClientInterface mensagem;
    
    public static void conectar() throws NotBoundException, MalformedURLException, RemoteException{
        
       // System.setProperty("java.security.policy","file:./politicas.policy");
        
        
      //  Registry registry =LocateRegistry.getRegistry("127.0.0.1", 5003);
      //  mensagem=(DAORMIClientInterface) registry.lookup("MensagemService");
        
      
    }   
    
    public DAORMIClientInterface getMensagemBuffer(){
        return mensagem;
    }
}