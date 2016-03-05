package ar.com.caece.ids2.barapp.cocina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ar.com.caece.ids2.barapp.cocina.dao.PedidoDao;
import ar.com.caece.ids2.barapp.cocina.exceptions.EstadoInexistenteException;
import ar.com.caece.ids2.barapp.cocina.exceptions.FlujoIncorrectoException;
import ar.com.caece.ids2.barapp.cocina.exceptions.PedidoInexistenteException;
import ar.com.caece.ids2.barapp.cocina.exceptions.PedidoSinPlatosException;
import ar.com.caece.ids2.barapp.cocina.exceptions.StockException;
import ar.com.caece.ids2.barapp.cocina.model.DetallePedido;
import ar.com.caece.ids2.barapp.cocina.model.Pedido;
import ar.com.caece.ids2.barapp.cocina.util.CocinaEstados;
import ar.com.caece.ids2.barapp.cocina.util.ConexionStock;

@Component
public class GestionPedidoDefault implements GestionPedido {
	
	@Autowired
	private PedidoDao pedidoDao;
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public void recibirPedido(Pedido pedido) throws FlujoIncorrectoException, PedidoSinPlatosException, StockException {
		if (pedido.getListaPlatos().size() > 0) {
			if (pedido.getEstadoPedido() == CocinaEstados.PENDIENTE) {
				callRestStock(pedido);
				pedidoDao.save(pedido);
			} else throw new FlujoIncorrectoException();
		} else throw new PedidoSinPlatosException();
	}

	/*
	 * Llamada al servicio rest del modulo stock
	 */
	private void callRestStock(Pedido pedido) throws StockException {
		for (DetallePedido detallePedido : pedido.getListaPlatos()) {
			String requestJson = "{Codigo: " + pedido.getId() + ", CodigoMesa: " + pedido.getIdMesa() + ", Estado: " +
					pedido.getEstadoPedido() + ", Cervezas:[{CodigoDetalleCerveza: " + detallePedido.getDetalleCerveza().getCodCerveza() +
					", cerveza:{idCerveza: " + detallePedido.getDetalleCerveza().getId() + "}, cantidad: " + detallePedido.getDetalleCerveza().getCantidad() + "}]}";

			String response = restTemplate.getForObject(ConexionStock.URL_STOCK + "?pedido={pedido}", String.class, requestJson);
			validateStatus(response);
		}
	}
	
	/*
	 * Validacion del status code recibido desde el servicio rest
	 * En caso de codigo distinto de ok se crea una excepcion
	 * con el mensaje y error correspondiente.
	 */
	private void validateStatus(String response) throws StockException {
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject)jsonParser.parse(response);

		if (jsonObject.get("statusCode").getAsInt() != ConexionStock.STATUS_CODE_STOCK_OK) {
			String message = "";
			if (!jsonObject.get("message").isJsonNull()) {
				message = jsonObject.get("message").getAsString();
			}
			throw new StockException(jsonObject.get("statusCode").getAsInt(), message);
		}
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
