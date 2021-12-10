
package batepaposervidor;

/**
 *
 * @author Marcelo Gomes
 */

import java.util.Scanner;

/**
 *
 * @author Marcelo Gomes
 */

public class PrincipalServidor {
    public static void main(String[] args) {
        
        ServidorProdutor servidorProdutor = null;
        ServidorConsumidor servidorConsumidor = null;
        
        MensagemBuffer mensagensServidorEnvio=new MensagemBuffer(10);
        MensagemBuffer mensagensServidorRecepcao=new MensagemBuffer(10);
        ServidorSocket servidorSocket=new ServidorSocket(8889);
        servidorSocket.conect();
        
        if (servidorSocket.getSocket() != null) {
            servidorProdutor = new ServidorProdutor(mensagensServidorEnvio, servidorSocket.getSocket());
            servidorConsumidor = new ServidorConsumidor(mensagensServidorRecepcao, servidorSocket.getSocket());
        }

        Scanner leitor = new Scanner(System.in);
        
        while (true) {
            String texto = leitor.next();
            servidorProdutor.enviarMensagem("Servidor: @ " + texto);
            
            System.out.println(mensagensServidorRecepcao.get());
            // System.out.println(mensagensServidorRecepcao.get());
        }
          
    }

    private static class ServidorConsumidor {

        public ServidorConsumidor() {
        }
    }
}

