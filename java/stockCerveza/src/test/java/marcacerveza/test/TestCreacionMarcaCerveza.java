package marcacerveza.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ar.com.caece.ids2.stockCerveza.manager.MarcaCervezaManager;
import ar.com.caece.ids2.stockCerveza.manager.ProveedorManager;
import ar.com.caece.ids2.stockCerveza.model.MarcaCerveza;
import ar.com.caece.ids2.stockCerveza.model.Proveedor;

public class TestCreacionMarcaCerveza {

	@Test
	public void test() {
		try{
			
			String marcaNueva = "Boris";
			
			MarcaCervezaManager mcm = new MarcaCervezaManager();
			ProveedorManager pm = new ProveedorManager();
			
			Proveedor proveedor = pm.buscarProveedor(1);
			
			mcm.crearMarCerveza(marcaNueva, proveedor);
			
			List<MarcaCerveza> marcasCerveza = mcm.listarMarcasCerveza();
			
			boolean encontre = false;
			for(MarcaCerveza marcaCerveza : marcasCerveza){
				
				if(marcaCerveza.getDescripcion().equals(marcaNueva) && marcaCerveza.getProveedor().getRazonSocial().equals(proveedor.getRazonSocial())){
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
