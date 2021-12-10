
package herancaemfiguras;

/**
 *
 * @author Marcelo Gomes
 */

public class HerancaEmFiguras {
    public static void main(String[] args) throws Exception {
        Quadrado quadrado = new Quadrado();
        quadrado.Draw();

        Circulo circulo = new Circulo();
        circulo.Draw();

        Triangulo triangulo = new Triangulo();
        triangulo.Draw();
        Heranca(quadrado);

    }

    public static void Heranca(Figura f) 
    {
        f.DrawShape();
    }
}