package rmibatepapo;

import java.rmi.RemoteException;


/**
 *
 * @author Marcelo Gomes
 */

public class MensagemRmiImp implements MensagemRmiInterface {

    
    String mensagem;

    
    protected MensagemRmiImp(String mensagem) throws RemoteException{
        super();
    }

    @Override
    public void put_mensagem(String txt) 
    {
        mensagem = txt;
    }
    
    @Override
    public String get_mensagem()
    {
        return mensagem;
    }    
}
