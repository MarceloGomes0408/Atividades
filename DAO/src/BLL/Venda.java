
package BLL;

import dao.PedidoDAO;
import MODEL.Pedido;

/**
 *
 * @author Marcelo Gomes
 */

public class Venda {
   
    public void vender(Pedido pedido) {
        if(pedido.getCliente() != null && pedido.getItens() != null) 
        {
            if(pedido.getItens().size() > 0)
            {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.inserir(pedidoDAO);
            }
        }
    }
}
