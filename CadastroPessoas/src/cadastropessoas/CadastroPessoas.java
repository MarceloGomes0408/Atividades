
package cadastropessoas;

/**
 *
 * @author Marcelo Gomes
 */
public class CadastroPessoas {

public static void main(String[] args) throws Exception {
        Pessoa Marcelo = new Pessoa();
        Pessoa Marcia = new Pessoa();

        Marcelo.setNome("Marcelo");
        Marcelo.setEndereco("Rua Fulano de Tal");
        Marcelo.setIdade("40");

        Marcia.setNome("Marcia");
        Marcia.setEndereco("Rua Sem Nome");
        Marcia.setIdade("30");

        CRUDPessoas crud = new CRUDPessoas();

        crud.AdicionarPessoa(Marcelo);
        crud.AdicionarPessoa(Marcia);

        System.out.println(crud.GerarRelatorio()); 

        crud.RemoverPessoa(Marcelo);

        System.out.println(crud.GerarRelatorio()); 

        Marcia.setNome("Marcio");
        crud.AtualizarPessoa(Marcia);

        System.out.println(crud.GerarRelatorio()); 
    }
}
