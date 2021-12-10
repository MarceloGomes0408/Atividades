
package cadastropessoas;

/**
 *
 * @author Marcelo Gomes
 */

import java.util.ArrayList;
import java.util.List;

public class CRUDPessoas {
   
    List<Pessoa> listaDePessoas = new ArrayList<>();
   
    //Create
    public void AdicionarPessoa(Pessoa Pessoa)
   {
    listaDePessoas.add(Pessoa);
   } 

   //Read
   public Pessoa buscarPessoa(String nomePessoa)
   {
       for(Pessoa b:listaDePessoas)
       {
           if(b.getNome().equals(nomePessoa))
           {
               return b;
           }
        }
        return null;
   }

   public Pessoa buscarPessoa(Pessoa pessoa)
   {
       for(Pessoa b:listaDePessoas)
       {
           if(b == pessoa)
           {
               return b;
           }
        }
        return null;
   }

   public String GerarRelatorio()
   {
       String relatorio = "";
       for(Pessoa b:listaDePessoas){
           relatorio += "Nome: " + b.getNome() + "\n";
           relatorio += "Endereco: " + b.getEndereco() + "\n";
           relatorio += "Idade: " + b.getIdade() + "\n";
       }
       return relatorio;
   }

   //Delete
   public boolean RemoverPessoa(String nomePessoa){
       Pessoa b = buscarPessoa(nomePessoa);
       if(b != null)
       {
           listaDePessoas.remove(b);
           return true;
       }
       return false;
   }

   public boolean RemoverPessoa(Pessoa pessoa){
    Pessoa b = buscarPessoa(pessoa);
    if(b != null)
    {
        listaDePessoas.remove(b);
        return true;
    }
    return false;
}

   //Update
   public boolean AtualizarPessoa(Pessoa pessoa)
   {
       if(listaDePessoas.contains(pessoa))
       {
           Pessoa p = buscarPessoa(pessoa);
            p.setNome(pessoa.getNome());
            p.setEndereco(pessoa.getEndereco());
            p.setIdade(pessoa.getIdade());
           return true;
       }
       return false;
   }

}