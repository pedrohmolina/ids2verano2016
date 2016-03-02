package ar.com.caece.ids2.barapp.cocina.exceptions;

public class FlujoIncorrectoException extends Exception {

	private static final String FLUJO_INCORRECTO_MESSAGE = "El pasaje de estado es incorrecto.";
	
	@Override
	public String getMessage() {
		return FLUJO_INCORRECTO_MESSAGE;
	}
	
	@Override
	public String toString() {
		return FLUJO_INCORRECTO_MESSAGE;
	}
	
}
