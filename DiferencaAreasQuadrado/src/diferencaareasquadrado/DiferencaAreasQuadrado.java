
package diferencaAreasquadrado;

/**
 *
 * @author Marcelo Gomes
 */
public class diferencaAreasquadrado{

    
    public static void main(String[] args) throws Exception  {

        Retangulo r1 = new Retangulo(1, 1, 20, 10);
        Retangulo r2 = new Retangulo(2, 1, 10, 6);

        if(r1.interceptaRetangulo(r2))
        {
            System.out.println("O retângulo 1 enconta com o retângulo 2");
        }
        else
        {
            System.out.println("Não encontrou");
        }

        if(r1.diferencaRetangulos(r2) != -1)
        {
            System.out.println("A diferença é " + r1.diferencaRetangulos(r2));
        }

    }
}