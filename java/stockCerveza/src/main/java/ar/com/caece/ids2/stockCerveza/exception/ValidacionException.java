package ar.com.caece.ids2.stockCerveza.exception;

public class ValidacionException extends Exception {
	
	private String message;
	public ValidacionException (String message){
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return message;
		
	}

}
