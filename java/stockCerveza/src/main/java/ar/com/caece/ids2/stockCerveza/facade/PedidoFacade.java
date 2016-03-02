package ar.com.caece.ids2.stockCerveza.facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.caece.ids2.stockCerveza.manager.CervezaManager;
import ar.com.caece.ids2.stockCerveza.model.DetalleCerveza;
import ar.com.caece.ids2.stockCerveza.model.Pedido;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PedidoFacade {

	@Mock 
	private EntityManager em;
	
	@Mock
	private EntityTransaction et;
	
	private CervezaManager cm;
	
	public PedidoFacade(){
		cm = new CervezaManager(em);
	}
	
	public void realizarPedido(Pedido pedido) throws RuntimeException, Exception{
		
	     MockitoAnnotations.initMocks(this);
         
         when(em.getTransaction()).thenReturn(et);
         doNothing().when(em).persist(any(Pedido.class));

         doNothing().when(et).begin();
         doNothing().when(et).commit();
         
         
		em.getTransaction().begin();
	
		List<DetalleCerveza> detalleCervezas = pedido.getCervezas();
		
		for(DetalleCerveza detalleCerveza : detalleCervezas) {
			cm.decrementarCantidad(detalleCerveza.getCerveza().getIdCerveza(), detalleCerveza.getCantidad());
		}

		em.persist(pedido);
		em.getTransaction().commit();
	}
	
	     
    
}
