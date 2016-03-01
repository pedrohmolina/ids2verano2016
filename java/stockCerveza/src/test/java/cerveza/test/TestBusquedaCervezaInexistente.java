package cerveza.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.com.caece.ids2.stockCerveza.exception.NoEncontroEntidadException;
import ar.com.caece.ids2.stockCerveza.manager.CervezaManager;
import ar.com.caece.ids2.stockCerveza.model.Cerveza;

public class TestBusquedaCervezaInexistente {

	@Test(expected=NoEncontroEntidadException.class)	
	public void test() throws NoEncontroEntidadException {
			
			CervezaManager cm = new CervezaManager();
			Cerveza cerveza = cm.buscarCerveza(1);
			cerveza.getPrecio();
		
	
		
		}
}
