package estilocerveza.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ar.com.caece.ids2.stockCerveza.manager.EstiloCervezaManager;
import ar.com.caece.ids2.stockCerveza.model.EstiloCerveza;

public class TestCreacionEstiloCerveza {

	@Test
	public void test() {
		try{
			String estiloNuevo = "Pilsner";
			
			EstiloCervezaManager ecm = new EstiloCervezaManager();
			
			
			ecm.crearEstiloCerveza(estiloNuevo);
		
			List<EstiloCerveza> estilosCerveza = ecm.listarEstiloCerveza();
			
			boolean encontre = false;
			for(EstiloCerveza estiloCerveza : estilosCerveza){
				
				if(estiloCerveza.getDescripcion().equals(estiloNuevo)){
					encontre = true;
				}
			}
			
			if(!encontre){
				fail("No se creo correctamente el estilo de cerveza");
			}
		

		}catch(Exception e){
			fail("Hubo un error al correr el caso de test: "+e.getMessage());
			
		}
	}

}
