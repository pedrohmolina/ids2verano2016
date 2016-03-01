package cerveza.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ar.com.caece.ids2.stockCerveza.manager.CervezaManager;
import ar.com.caece.ids2.stockCerveza.manager.EstiloCervezaManager;
import ar.com.caece.ids2.stockCerveza.manager.MarcaCervezaManager;
import ar.com.caece.ids2.stockCerveza.manager.PresentacionCervezaManager;
import ar.com.caece.ids2.stockCerveza.model.Cerveza;
import ar.com.caece.ids2.stockCerveza.model.EstiloCerveza;
import ar.com.caece.ids2.stockCerveza.model.MarcaCerveza;
import ar.com.caece.ids2.stockCerveza.model.PresentacionCerveza;

public class TestCreacionCerveza {

	@Test
	public void test() {
		
		try{
			
			
			int cantidadCerveza = 100;
			Double precioCerveza = new Double(100);
			
			EstiloCervezaManager ecm = new EstiloCervezaManager();
			MarcaCervezaManager mcm = new MarcaCervezaManager();
			PresentacionCervezaManager pcm = new PresentacionCervezaManager();
			CervezaManager cm = new CervezaManager();
			
			
			List<PresentacionCerveza> presentacionCerveza = pcm.listarPresentacionCerveza();
			MarcaCerveza marcaCerveza = mcm.buscarMarcaCerveza(1);
			EstiloCerveza estiloCerveza = ecm.buscarEstiloCerveza(1); 
			
			cm.crearCerveza(marcaCerveza, presentacionCerveza,estiloCerveza, cantidadCerveza, precioCerveza);
	
			List<Cerveza> cervezas = cm.listarCervezas();
			
			boolean encontre = false;
			for(Cerveza cerveza : cervezas){
				if(cerveza.getCantidad() == cantidadCerveza && cerveza.getPrecio().compareTo(precioCerveza) == 0 && cerveza.getEstiloCerveza().getId() == 1){
					encontre = true;
					
				}
			}
			
			
			if(!encontre){
				fail("No se creo correctamente la cerveza");
			}
		
			
		}catch(Exception e ){
			fail("Hubo un error al correr el caso de test: "+e.getMessage());
		}
	
	}

}
