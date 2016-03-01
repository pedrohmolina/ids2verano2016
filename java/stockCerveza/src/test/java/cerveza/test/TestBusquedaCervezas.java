package cerveza.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ar.com.caece.ids2.stockCerveza.manager.CervezaManager;
import ar.com.caece.ids2.stockCerveza.model.Cerveza;
import static org.mockito.Mockito.*;

public class TestBusquedaCervezas extends TestCase {
	
	

	@Test
	public void test() {
		
		
	
		Cerveza cerveza = mock(Cerveza.class);
		when(cerveza.getCantidad()).thenReturn(10);
		when(cerveza.isEstado()).thenReturn(true);
		
		
		// you can mock concrete classes, not only interfaces
		List<Cerveza> listaCervezas = mock(List.class);

		when(listaCervezas.size()).thenReturn(1);
		when(listaCervezas.get(0)).thenReturn(cerveza);

		CervezaManager cm = mock(CervezaManager.class);
		when(cm.listarCervezas()).thenReturn(listaCervezas);

		List<Cerveza> cervezasBuscadas = cm.listarCervezas();
			
		for(int i = 0 ; i < cervezasBuscadas.size(); i++){
			
			Cerveza cervezaBuscada = cervezasBuscadas.get(i);
			if( cervezaBuscada.getCantidad()>0 && cerveza.isEstado() == false) {
				fail("No puede existir una cerveza deshabilitada con stock");
			}
		}

		
	}

}
