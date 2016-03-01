package ar.com.caece.ids2.stockCerveza.facade;

import java.util.List;

import ar.com.caece.ids2.stockCerveza.exception.NoEncontroEntidadException;
import ar.com.caece.ids2.stockCerveza.manager.CervezaManager;
import ar.com.caece.ids2.stockCerveza.model.Cerveza;
import ar.com.caece.ids2.stockCerveza.model.DetalleCerveza;
import ar.com.caece.ids2.stockCerveza.model.Pedido;

public class PedidoFacade {

	public PedidoFacade(){
		
	}
	
	public void realizarPedido(Pedido pedido) throws RuntimeException, Exception{
		CervezaManager cm = new CervezaManager();
		
		List<DetalleCerveza> detalleCervezas = pedido.getCervezas();
		
		for(DetalleCerveza detalleCerveza : detalleCervezas) {
			cm.decrementarCantidad(detalleCerveza.getCerveza().getIdCerveza(), detalleCerveza.getCantidad());
			
		}
		
		
		
	}
	
}
