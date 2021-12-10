import java.net.Socket;


/**
 *
 * @author Marcelo Gomes
 */


public class MensagemBuffer{

    String[] mensagens;
    private int dim, in, out, elementos;

    public MensagemBuffer(int n) {
        dim = n;
        mensagens = new String[dim];
        in = 0;
        out = 0;
        elementos = 0;
    }
    
    private boolean cheio() {
        return (elementos == dim);
    }

    private boolean vazio() {
        return (elementos == 0);
    }
    //===================
    public synchronized String get() {
        while (vazio()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
        String mensagemAtual = mensagens[out];
        out = (out + 1) % dim;
        elementos--;
        notifyAll();
        return (mensagemAtual);
    }
    
    public synchronized void put(String mensagem) {
        while (cheio()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
        mensagens[in] = mensagem;
        in = (in + 1) % dim;
        elementos++;
        notifyAll();
    }
    
}