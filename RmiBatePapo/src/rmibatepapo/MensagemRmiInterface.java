package rmibatepapo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



/**
 *
 * @author Marcelo Gomes
 */

public interface MensagemRmiInterface extends Remote {    
    
    public void put_mensagem(String txt) throws RemoteException;
    public String get_mensagem() throws RemoteException;
}
