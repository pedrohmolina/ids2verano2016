package ar.com.caece.ids2.stockCerveza.exception;

public class NoEncontroEntidadException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2274778620785668037L;
	private Class<?> entidadClase;
	public NoEncontroEntidadException(Class<?> entidadClase){
		super();
		this.entidadClase = entidadClase; 
	} 
	
	@Override
	public String getMessage(){
		return "No se encontro la instancia de la clase:"+ entidadClase.getName();
	}
}
