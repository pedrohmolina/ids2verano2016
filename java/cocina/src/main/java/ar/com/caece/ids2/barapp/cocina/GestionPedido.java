package ar.com.caece.ids2.barapp.cocina;

import ar.com.caece.ids2.barapp.cocina.exceptions.EstadoInexistenteException;
import ar.com.caece.ids2.barapp.cocina.exceptions.FlujoIncorrectoException;
import ar.com.caece.ids2.barapp.cocina.exceptions.PedidoInexistenteException;
import ar.com.caece.ids2.barapp.cocina.exceptions.PedidoSinPlatosException;
import ar.com.caece.ids2.barapp.cocina.model.Pedido;

/**
 * Interface presentada al modulo de toma de pedidos
 * con las acciones correspondientes al manejo de pedidos.
 *
 */
public interface GestionPedido {

	/**
	 * Metodo que recibe el pedido con los detalles de la mesa.
	 * El pedido ingresa con estado pendiente.
	 * 
	 * @param pedido: datos de la mesa
	 * @exception FlujoIncorrectoException: ocurre cuando se recibe el plato con estado distinto a pendiente
	 * @exception PedidoSinPlatosException: ocurre si el pedido recibido no tiene platos asignados
	 */
	public void recibirPedido(Pedido pedido) throws FlujoIncorrectoException, PedidoSinPlatosException;
	
	/**
	 * Metodo que recibe un pedido a modificar.
	 * 
	 * @param pedido: pedido que sera modificado
	 * @exception PedidoInexistenteException: ocurre cuando no se encuentra el pedido solicitado
	 * @exception EstadoInexistenteException: ocurre cuando el pedido a modificar tiene un estado invalido.
	 * @exception PedidoSinPlatosException: ocurre si el pedido recibido no tiene platos asignados
	 */
	public void modificarPedido(Pedido pedido) throws EstadoInexistenteException, PedidoInexistenteException, PedidoSinPlatosException;
	
	/**
	 * Metodo que cancela un pedido a partir del id.
	 * El pedido quedara en estado cancelado.
	 * 
	 * @param idPedido: id que identifica el pedido
	 * @exception PedidoInexistenteException: ocurre cuando no se encuentra el pedido solicitado
	 * @exception FlujoIncorrectoException: ocurre si el pedido a cancelar se encuentra en estado finalizado
	 */
	public void cancelarPedido(int idPedido) throws PedidoInexistenteException, FlujoIncorrectoException ;

	/**
	 * Metodo que inicia un pedido a partir del id.
	 * 
	 * @param idPedido
	 * @exception PedidoInexistenteException: ocurre cuando no se encuentra el pedido solicitado
	 * @exception FlujoIncorrectoException: ocurre si el pedido a iniciar no se encuetra en estado pendiente
	 */
	public void iniciarPedido(Pedido pedidoRecibido) throws PedidoInexistenteException, FlujoIncorrectoException;
	
	/**
	 * Metodo que pone un pedido en estado finalizado.
	 * 
	 * @param idPedido: id que indentifica el pedido
	 * @exception PedidoInexistenteException: ocurre cuando no se encuentra el pedido solicitado
	 * @exception FlujoIncorrectoException: ocurre si el pedido a iniciar no se encuetra en estado en preparacion
	 */
	public void terminarPedido(Pedido pedidoRecibido) throws PedidoInexistenteException, FlujoIncorrectoException;
	
}
