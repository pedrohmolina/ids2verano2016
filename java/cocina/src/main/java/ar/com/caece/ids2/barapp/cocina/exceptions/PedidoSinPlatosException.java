package ar.com.caece.ids2.barapp.cocina.exceptions;

public class PedidoSinPlatosException extends Exception {

	private static final String PEDIDO_SIN_PLATOS_MESSAGE = "El pedido no tiene platos asociados.";
	
	@Override
	public String getMessage() {
		return PEDIDO_SIN_PLATOS_MESSAGE;
	}
	
	@Override
	public String toString() {
		return PEDIDO_SIN_PLATOS_MESSAGE;
	}

}
