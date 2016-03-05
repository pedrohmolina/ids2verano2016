package ar.com.caece.ids2.barapp.cocina.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detallecerveza")
public class DetalleCerveza {
	
	@Id
	@Column(name="codigo")
	private int id;
	@Column(name="codigocerveza")
	private int codCerveza;
	private int cantidad;
	
	public DetalleCerveza() {
	}
	
	public DetalleCerveza(int id, int codCerveza, int cantidad) {
		this.id = id;
		this.codCerveza = codCerveza;
		this.cantidad = cantidad;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodCerveza() {
		return codCerveza;
	}
	public void setCodCerveza(int codCerveza) {
		this.codCerveza = codCerveza;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
