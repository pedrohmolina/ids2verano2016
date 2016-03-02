package ar.com.caece.ids2.barapp.cocina;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.caece.ids2.barapp.cocina.dao.PedidoDao;
import ar.com.caece.ids2.barapp.cocina.model.DetalleCerveza;
import ar.com.caece.ids2.barapp.cocina.model.DetallePedido;
import ar.com.caece.ids2.barapp.cocina.model.Pedido;
import ar.com.caece.ids2.barapp.cocina.util.CocinaEstados;

public class PedidoDaoTest {

	private AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
	private PedidoDao pedidoDao;

	@Before
	public void setUp() throws Exception {
		pedidoDao = context.getBean(PedidoDao.class);
	}
	
	@After
	public void tearDown() throws Exception {
		context.close();
	}
	
	/**
	 * Test que verifica el almacenamiento de un pedido en la base.
	 */
	@Test
	public void testSavePedido() {
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		listaPlatos.add(new DetallePedido(7, 1, 433, CocinaEstados.PENDIENTE));
		listaPlatos.add(new DetallePedido(7, 2, 233, CocinaEstados.PENDIENTE));
		
		List<DetalleCerveza> listaCervezas = new ArrayList<DetalleCerveza>();
		listaCervezas.add(new DetalleCerveza(7, 332, 9));
		Pedido pedido = crearPedido(7, 1, CocinaEstados.PENDIENTE, true, listaPlatos, listaCervezas);
		
		try {
			pedidoDao.save(pedido);
		} catch(Exception e) {
			fail("Error: " + e);
		}
	}
	
	@Test
	public void testUpdatePedido() {
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		listaPlatos.add(new DetallePedido(7, 5, 123, CocinaEstados.PENDIENTE));
		listaPlatos.add(new DetallePedido(7, 6, 643, CocinaEstados.PENDIENTE));
		
		List<DetalleCerveza> listaCervezas = new ArrayList<DetalleCerveza>();
		listaCervezas.add(new DetalleCerveza(7, 667, 1));
		Pedido pedido = crearPedido(7, 1, CocinaEstados.PENDIENTE, true, listaPlatos, listaCervezas);
		
		try {
			pedidoDao.update(pedido);	
		} catch(Exception e) {
			fail("Error: " + e);
		}
	}
	
	private Pedido crearPedido(int id, int idMesa, int estado, boolean entregarTodoJunto, List<DetallePedido> listaPlatos, List<DetalleCerveza> listaCervezas) {
		Pedido pedido = new Pedido();
		
		pedido.setId(id);
		pedido.setIdMesa(idMesa);
		pedido.setEstadoPedido(estado);
		pedido.setEntregarTodoJunto(entregarTodoJunto);
		pedido.setListaPlatos(listaPlatos);
		pedido.setListaCervezas(listaCervezas);
		
		return pedido;
	}
	
}
