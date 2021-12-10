
package classequadrados;

/**
 *
 * @author Marcelo Gomes
 */
public class ClasseQuadrados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Retangulo rx = new Retangulo(2, 2, 20, 10);
        Retangulo ry = new Retangulo(2, 1, 10, 6);

        if(rx.interceptaRetangulo(ry))
        {
            System.out.println("O retângulo x encontrou com o retângulo y");
        }
        else
        {
            System.out.println("Nada aconteceu");
        }
        // TODO code application logic here
    }
    
}

