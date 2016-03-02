package ar.com.caece.ids2.barapp.cocina.exceptions;

public class PedidoInexistenteException extends Exception {

	private static final String PEDIDO_INEXISTENTE_MESSAGE = "No existe el pedido buscado.";
	
	@Override
	public String getMessage() {
		return PEDIDO_INEXISTENTE_MESSAGE;
	}
	
	@Override
	public String toString() {
		return PEDIDO_INEXISTENTE_MESSAGE;
	}
	
}
