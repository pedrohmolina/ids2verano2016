package ar.com.caece.ids2.barapp.cocina.exceptions;

/**
 * Excepcion que se arroja si el servicio rest del modulo stock
 * arroja un resultado inesperado. 
 * Guarda el mensaje y el codigo devueltos por el servicio. 
 */
public class StockException extends Exception {
	
	private static final String STOCK_MESSAGE = "Error en servicion de stock";
	private int status;
	private String message;

	public StockException(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return STOCK_MESSAGE + ", statusCode: " + status + ", message: " + message;
	}
	
	@Override
	public String toString() {
		return STOCK_MESSAGE + ", statusCode: " + status + ", message: " + message;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

}
