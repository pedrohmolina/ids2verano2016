package ar.com.caece.ids2.stockCerveza.exception;

import ar.com.caece.ids2.stockCerveza.model.Cerveza;

public class SinStockCervezaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cerveza cerveza;
	public SinStockCervezaException(Cerveza cerveza){
		super();
		this.cerveza = cerveza;
	}
	
	@Override
	public String getMessage(){
		return "La cerveza: "+ this.cerveza.toString() + " se encuentra momentaneamente sin stock";
		
	}

}
