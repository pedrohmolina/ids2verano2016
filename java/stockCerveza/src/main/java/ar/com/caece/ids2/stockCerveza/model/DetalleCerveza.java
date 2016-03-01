package ar.com.caece.ids2.stockCerveza.model;

public class DetalleCerveza {
	private int CodigoDetalleCerveza;
	private Cerveza cerveza;
	private int cantidad;

	public DetalleCerveza(){
		
	}
	
	public int getCodigoDetalleCerveza() {
		return CodigoDetalleCerveza;
	}
	public void setCodigoDetalleCerveza(int codigoDetalleCerveza) {
		CodigoDetalleCerveza = codigoDetalleCerveza;
	}
	public Cerveza getCerveza() {
		return cerveza;
	}
	public void setCerveza(Cerveza cerveza) {
		this.cerveza = cerveza;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
