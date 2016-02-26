package ar.com.caece.ids2.barapp.cocina.exceptions;

public class EstadoInexistenteException extends Exception {
	
	private static final String ESTADO_INEXISTENTE_MESSAGE = "No existe el estado de pedido.";
	
	@Override
	public String getMessage() {
		return ESTADO_INEXISTENTE_MESSAGE;
	}
	
	@Override
	public String toString() {
		return ESTADO_INEXISTENTE_MESSAGE;
	}

}
