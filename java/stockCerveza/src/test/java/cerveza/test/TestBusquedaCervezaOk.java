package cerveza.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.com.caece.ids2.stockCerveza.manager.CervezaManager;
import ar.com.caece.ids2.stockCerveza.model.Cerveza;

public class TestBusquedaCervezaOk {

	@Test
	public void test() {
		try{
			
			CervezaManager cm = new CervezaManager();
			Cerveza cerveza = cm.buscarCerveza(5);
			
			System.out.println("Precio:"+cerveza.getPrecio());
			
		}catch(Exception e){
			fail("Fallo la busqueda de cerveza: "+e.getMessage());
		}
	}

}
