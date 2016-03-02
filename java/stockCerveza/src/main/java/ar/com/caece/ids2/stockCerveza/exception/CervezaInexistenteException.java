package ar.com.caece.ids2.stockCerveza.exception;

public class CervezaInexistenteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CervezaInexistenteException(){
		super();
	} 
	
	@Override
	public String getMessage(){
		return "Cerveza Inexistente";
	}
}
