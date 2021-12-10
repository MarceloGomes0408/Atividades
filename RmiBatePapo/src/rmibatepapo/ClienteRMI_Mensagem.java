package rmibatepapo;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;




/**
 *
 * @author Marcelo Gomes
 */


public class ClienteRMI_Mensagem {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException, IOException {
        
        RmiClienteMensagem client = new RmiClienteMensagem();
        client.conectar();
                        
        List<String> buffer = new ArrayList<String>();
        String msg_servidor = "";
        String username = "";
        System.out.println("Digite seu nome");
        Scanner input_username = new Scanner(new InputStreamReader(System.in, "UTF-8"));
        username = input_username.nextLine();
        System.out.println("Digite uma mensagem");
        
        while (true) 
        {
            buffer.add(client.getMensagemBuffer().get_mensagem());
            Thread.sleep(1000);
            buffer.add(client.getMensagemBuffer().get_mensagem());
            if(buffer.get(0) == buffer.get(1))
            {
                buffer.clear();
            }
            else
            {
                msg_servidor = buffer.get(1);
                buffer.clear();
            }
            Scanner input = new Scanner(new InputStreamReader(System.in, "UTF-8"));
            String texto = input.nextLine();
            client.getMensagemBuffer().put_mensagem(username + ": " + texto);
        }
    }
}