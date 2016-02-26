package ar.com.caece.ids2.barapp.cocina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.caece.ids2.barapp.cocina.dao.PedidoDao;
import ar.com.caece.ids2.barapp.cocina.exceptions.EstadoInexistenteException;
import ar.com.caece.ids2.barapp.cocina.exceptions.FlujoIncorrectoException;
import ar.com.caece.ids2.barapp.cocina.exceptions.PedidoInexistenteException;
import ar.com.caece.ids2.barapp.cocina.exceptions.PedidoSinPlatosException;
import ar.com.caece.ids2.barapp.cocina.model.Pedido;
import ar.com.caece.ids2.barapp.cocina.util.CocinaEstados;

@Component
public class GestionPedidoDefault implements GestionPedido {
	
	@Autowired
	private PedidoDao pedidoDao;
	
	@Override
	public void recibirPedido(Pedido pedido) throws FlujoIncorrectoException, PedidoSinPlatosException {
		
		if (pedido.getListaPlatos().size() > 0) {
			if (pedido.getEstadoPedido() == CocinaEstados.PENDIENTE) {
				pedidoDao.save(pedido);
			} else throw new FlujoIncorrectoException();
		} else throw new PedidoSinPlatosException();
		
	}

	@Override
	public void modificarPedido(Pedido pedido) throws EstadoInexistenteException, PedidoInexistenteException, PedidoSinPlatosException {
		
		if (pedido.getListaPlatos().size() == 0) {
			throw new PedidoSinPlatosException();
		}
		Pedido pedidoAux = pedidoDao.get(pedido.getId());
		
		if (pedidoAux != null) {
			//Borramos el pedido actual en la base
			pedidoDao.delete(pedidoAux);
			
			//Validamos el pedido recibido
			if (pedido.getEstadoPedido() >= CocinaEstados.PENDIENTE 
					&& pedido.getEstadoPedido() <= CocinaEstados.TERMINADO) {
				pedidoDao.save(pedido);
			} else throw new EstadoInexistenteException();
		} else throw new PedidoInexistenteException();
		
	}

	@Override
	public void cancelarPedido(int idPedido) throws FlujoIncorrectoException, PedidoInexistenteException {
		Pedido pedido = pedidoDao.get(idPedido);
		
		if (pedido != null) {
			if (pedido.getEstadoPedido() != CocinaEstados.TERMINADO) {
				pedido.setEstadoPedido(CocinaEstados.CANCELADO);
				pedidoDao.update(pedido);
			} else throw new FlujoIncorrectoException(); 
		} else throw new PedidoInexistenteException();
	}
	
	@Override
	public void iniciarPedido(Pedido pedidoRecibido) throws FlujoIncorrectoException, PedidoInexistenteException {
		Pedido pedido = pedidoDao.get(pedidoRecibido.getId());
		
		if (pedido != null) {
			if (pedido.getEstadoPedido() == CocinaEstados.PENDIENTE) {
				pedido.setEstadoPedido(CocinaEstados.EN_PREPARACION);
				pedidoDao.update(pedido);
			} else throw new FlujoIncorrectoException();
		} else throw new PedidoInexistenteException();
	}
	
	public void terminarPedido(Pedido pedidoRecibido) throws PedidoInexistenteException, FlujoIncorrectoException {
		Pedido pedido = pedidoDao.get(pedidoRecibido.getId());
		
		if (pedido != null) {
			if (pedido.getEstadoPedido() == CocinaEstados.EN_PREPARACION) {
				pedido.setEstadoPedido(CocinaEstados.TERMINADO);
				pedidoDao.update(pedido);
			} else throw new FlujoIncorrectoException();
		} else throw new PedidoInexistenteException();
	}

	public PedidoDao getPedidoDao() {
		return pedidoDao;
	}

	public void setPedidoDao(PedidoDao pedidoDao) {
		this.pedidoDao = pedidoDao;
	}
	
}
