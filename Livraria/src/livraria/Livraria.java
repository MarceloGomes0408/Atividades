
package livraria;

/**
 *
 * @author Marcelo Gomes
 */
import java.util.Scanner;

public class Livraria {
    public static void main(String[] args) throws Exception {
        /*
        Criar um livro.
        Criar um autor, e vincular ao livro
        Criar um publicador, e vincular ao livro
        Criar um usuário(Editorial/Consumidor), e vincular a ordem de compra
        Criar um Entregador.
        Criar InformacoesDeEntrega e vincular o entregador.
        Criar uma conta
        Criar um classe de Informacoes de compra e vincular a Conta
        Vincular tudo acima.
        temos um objeto de venda.
        Criar uma ordem de compra
        */
        Book ArteDaGuerra = new Book();
        
        Author Marcelo = new Author();
        ArteDaGuerra.setAuthor(Marcelo);
        
        Publisher ZasTraz = new Publisher();
        ArteDaGuerra.setPublisher(ZasTraz);
        
        User Compra = new User();
        
        Shipper entrega = new Shipper();
        ShippingInfo infoEntrega = new ShippingInfo();
        infoEntrega.setShipper(entrega);
        
        Account conta = new Account();
        Compra.setAccount(conta);
        conta.setId(1);
        conta.setPassword("123");
        conta.setEmailAddress("luismarcelogomesig@email.com");

        BillingInfo infoCompra = new BillingInfo();
        infoCompra.setAccount(conta);
        
        Order compra = new Order();
        compra.setUser(Compra);
        compra.setBook(ArteDaGuerra);
        compra.setShippingInfo(infoEntrega);
        compra.setBillingInfo(infoCompra);
        if(compra.isFullfilled())
        {
            Scanner scannerId;
            int id = 0;
            String psw = "";
            try {
                scannerId = new Scanner(System.in);
                System.out.println("id");
                id = scannerId.nextInt();
                System.out.println("senha");
                psw = scannerId.next();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(compra.getBillingInfo().getAccount().validateLogin(id, psw))
            {
                System.out.println("Você adquiriu uma ótima leitura:)");
            }
        }
    }
}
