package rmibatepapo;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;



/**
 *
 * @author Marcelo Gomes
 */

public class ClienteRMI {
     
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        
        RmiClient cliente=new RmiClient();
        cliente.conectar();
        
        cliente.getMensagemBuffer().incluir(0, "Marcelo");
        cliente.getMensagemBuffer().incluir(1, "Marcia");
        
        cliente.getMensagemBuffer().alterar(0,"Mariana");
        
        cliente.getMensagemBuffer().excluir(1);
        
        List<String> listagem=cliente.getMensagemBuffer().consultarPorNome("Mariana");
        
        for(String linha:listagem){
            System.out.println(linha);
        }
    }
}

