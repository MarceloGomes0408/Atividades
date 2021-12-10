
package BLL;

import DAL.DAOOrder;
import Models.Order;
/**
 *
 * @author Marcelo Gomes
 */

public class Sale {
   
    public void vender(Order order) {
        if(order.getCliente() != null && order.getOrderItens() != null) 
        {
            if(order.getItem().size() > 0)
            {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.inserir(pedidoDAO);
            }
        }
    }
}