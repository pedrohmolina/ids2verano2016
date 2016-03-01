package ar.com.caece.ids2.stockCerveza.model;

import java.util.List;

public class Pedido {
	 
	private int Codigo; 
	private int CodigoMesa;
	private int Estado;
	private List<DetalleCerveza> Cervezas;
	
	
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public int getCodigoMesa() {
		return CodigoMesa;
	}
	public void setCodigoMesa(int codigoMesa) {
		CodigoMesa = codigoMesa;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	public List<DetalleCerveza> getCervezas() {
		return Cervezas;
	}
	public void setCervezas(List<DetalleCerveza> cervezas) {
		Cervezas = cervezas;
	} 

}
