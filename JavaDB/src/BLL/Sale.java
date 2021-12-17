
package BLL;

import DAL.DAOPedido;
import Models.Pedido;
import Models.Cliente;
/**
 *
 * @author Marcelo Gomes
 */

public class Sale {
   
    public void vender(Pedido pedido) {
        if(pedido.getCliente() != null && pedido.getOrder() != null) 
        {
            if(pedido.getOrder().size() > 0)
            {
                DAOPedido DAOpedido = new DAOPedido();
                DAOpedido.inserir(DAOpedido);
            }
        }
    }
}