package ar.com.caece.ids2.barapp.cocina;


import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ar.com.caece.ids2.barapp.cocina.dao.PedidoDao;
import ar.com.caece.ids2.barapp.cocina.exceptions.EstadoInexistenteException;
import ar.com.caece.ids2.barapp.cocina.exceptions.FlujoIncorrectoException;
import ar.com.caece.ids2.barapp.cocina.exceptions.StockException;
import ar.com.caece.ids2.barapp.cocina.model.DetalleCerveza;
import ar.com.caece.ids2.barapp.cocina.model.DetallePedido;
import ar.com.caece.ids2.barapp.cocina.model.Pedido;
import ar.com.caece.ids2.barapp.cocina.util.CocinaEstados;

public class GestionPedidoDefaultTest {

	private GestionPedidoDefault comunicacionPedido = new GestionPedidoDefault();
	private static final int ESTADO_INEXISTENTE = 10;
	
	/**
	 * Test que verifica que se arroje una excepcion {@link FlujoIncorrectoException}
	 * Esto debido a que no se puede pasar un pedido de estado TERMINADO a CANCELADO
	 */
	@Test
	public void testCancelarPedidoFinalizado() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		Pedido pedido = crearPedido(1, 4, CocinaEstados.TERMINADO, true, null, null);
		
		when(pedidoDao.get(1)).thenReturn(pedido);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.cancelarPedido(1);
		} catch(Exception e) {
			Assert.assertEquals("El pasaje de estado es incorrecto.", e.getMessage());
		}
	}
	
	/**
	 * Test que verifica que un pedido se cancela correctamente.
	 * La cancelacion se puede realizar para cualquier estado
	 * menos TERMINADO, en particular el test cancela un pedido
	 * con estado EN_PREPARACION.
	 */
	@Test
	public void testCancelarPedido() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		
		Pedido pedido = crearPedido(1, 4, CocinaEstados.EN_PREPARACION, true, null, null);
		
		when(pedidoDao.get(1)).thenReturn(pedido);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.cancelarPedido(1);
			Assert.assertEquals(CocinaEstados.CANCELADO, pedido.getEstadoPedido());
		} catch(Exception e) {
			fail("error en test: " + e.getMessage());
		}
	}
	
	/**
	 * Test que verifica que no se puede modificar un pedido
	 * con un estado inexistente. Se arroja la exception 
	 * {@link EstadoInexistenteException}
	 */
	@Test
	public void testModificarPedidoConEstadoInexistente() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		
		listaPlatos.add(new DetallePedido());
		Pedido pedido = crearPedido(1, 6, ESTADO_INEXISTENTE, true, listaPlatos, null);
		
		when(pedidoDao.get(1)).thenReturn(pedido);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.modificarPedido(pedido);
		} catch(Exception e) {
			Assert.assertEquals("No existe el estado de pedido.", e.getMessage());
		}
	}
	
	/**
	 * Test que prueba el metodo iniciarPedido, o sea, que el pedido
	 * pase de PENDIENTE a EN_PREPARACION.
	 */
	@Test
	public void testModificarPedidoAEstadoEnPreparacion() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		
		listaPlatos.add(new DetallePedido());
		
		Pedido pedido = crearPedido(1, 21, CocinaEstados.PENDIENTE, true, listaPlatos, null);
		
		when(pedidoDao.get(1)).thenReturn(pedido);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.iniciarPedido(pedido);
			Assert.assertEquals(CocinaEstados.EN_PREPARACION, pedido.getEstadoPedido());
		} catch(Exception e) {
			fail("error en test: " + e.getMessage());
		}
	}
	
	/**
	 * Test que prueba que no se pueda modificar un pedido
	 * con estado PENDIENTE a TERMINADO.
	 * 
	 */
	@Test
	public void testFinalizarPedidoPendiente() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		Pedido pedido = crearPedido(1, 11, CocinaEstados.PENDIENTE, true, null, null);
		
		when(pedidoDao.get(1)).thenReturn(pedido);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.terminarPedido(pedido);
		} catch(Exception e) {
			Assert.assertEquals("El pasaje de estado es incorrecto.", e.getMessage());
		}
	}
	
	/**
	 * Test que verifica que si el pedido llega con estado distinto de PENDIENTE
	 * se arroje la excepcion {@link FlujoIncorrectoException}.
	 */
	@Test
	public void testRecibirPedidoNoPendiente() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		
		listaPlatos.add(new DetallePedido());
		
		Pedido pedido = crearPedido(1, 12, CocinaEstados.EN_PREPARACION, true, listaPlatos, null);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.recibirPedido(pedido);
		} catch(Exception e) {
			Assert.assertEquals("El pasaje de estado es incorrecto.", e.getMessage());
		}
	}
	
	@Test
	public void testPedidoInexistente() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		Pedido pedido = crearPedido(1, 5, CocinaEstados.EN_PREPARACION, true, null, null);
		
		when(pedidoDao.get(1)).thenReturn(pedido);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.cancelarPedido(1);
		} catch(Exception e) {
			Assert.assertEquals("No existe el pedido buscado.", e.getMessage());
		}
	}
	
	/**
	 * Test que recibe un pedido que incluye un plato que tiene como ingrediente
	 * una cerveza.
	 * 
	 * Probar en conjunto con la conexion del modulo stock.
	 * Configurar la clase ConexionStock con el host y port correspondientes
	 * para realizar el test.
	 */
	@Test
	@Ignore
	public void testRestRecibirPedido() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		DetalleCerveza detalleCerveza = new DetalleCerveza(6, 1, 1);
		DetallePedido detallePedido = new DetallePedido(1, 2, 34, 1, detalleCerveza);
		listaPlatos.add(detallePedido);
		Pedido pedido = crearPedido(1, 12, CocinaEstados.PENDIENTE, true, listaPlatos, null);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.recibirPedido(pedido);
		} catch(Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	/**
	 * Test de cantidad insuficiente de cerveza.
	 * 
	 * Probar en conjunto con la conexion del modulo stock.
	 * Configurar la clase ConexionStock con el host y port correspondientes
	 * para realizar el test.
	 */
	@Test
	@Ignore
	public void testRestRecibirPedidoCantidad() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		DetalleCerveza detalleCerveza = new DetalleCerveza(6, 1, 96);
		DetallePedido detallePedido = new DetallePedido(1, 2, 34, 1, detalleCerveza);
		listaPlatos.add(detallePedido);
		Pedido pedido = crearPedido(1, 12, CocinaEstados.PENDIENTE, true, listaPlatos, null);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.recibirPedido(pedido);
		} catch(StockException e) {
			//Se valida que el mensaje recibido conincida con el de cantidad insuficiente
			Assert.assertEquals("Error en servicion de stock, statusCode: 2, message: La cerveza: Dubel-Barley wine se encuentra momentaneamente sin stock", e.getMessage());
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	/**
	 * Test que recibe un pedido que incluye un plato que tiene como ingrediente
	 * una cerveza inexistente.
	 * 
	 * Probar en conjunto con la conexion del modulo stock.
	 * Configurar la clase ConexionStock con el host y port correspondientes
	 * para realizar el test.
	 */
	@Test
	@Ignore
	public void testRestRecibirPedidoCervezaInexistente() {
		PedidoDao pedidoDao = mock(PedidoDao.class);
		List<DetallePedido> listaPlatos = new ArrayList<DetallePedido>();
		DetalleCerveza detalleCerveza = new DetalleCerveza(9999, 1, 1);
		DetallePedido detallePedido = new DetallePedido(1, 2, 34, 1, detalleCerveza);
		listaPlatos.add(detallePedido);
		Pedido pedido = crearPedido(1, 12, CocinaEstados.PENDIENTE, true, listaPlatos, null);
		comunicacionPedido.setPedidoDao(pedidoDao);
		try {
			comunicacionPedido.recibirPedido(pedido);
		} catch(StockException e) {
			//Se valida que el mensaje recibido coincida con el de cerveza inexistente
			Assert.assertEquals("Error en servicion de stock, statusCode: 2, message: Cerveza Inexistente", e.getMessage());
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
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
