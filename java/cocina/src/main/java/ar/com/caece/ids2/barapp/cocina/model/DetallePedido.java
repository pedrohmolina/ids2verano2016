package ar.com.caece.ids2.barapp.cocina.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detallepedido")
public class DetallePedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="codigo")
	private int id;
	@Id
	@Column(name="codigodetalleplato")
	private int codigoDetallePlato;
	@Column(name="codigoplato")
	private int codPlato;
	@Column(name="estadodetallepedido")
	private int estado;

	public DetallePedido() {
	}
	
	public DetallePedido(int id, int codigoDetallePlato, int codigoPlato, int estado) {
		this.id = id;
		this.codigoDetallePlato = codigoDetallePlato;
		this.codPlato = codigoPlato;
		this.estado = estado;
	}
	
	public int getCodPlato() {
		return codPlato;
	}
	public void setCodPlato(int codPlato) {
		this.codPlato = codPlato;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPlato() {
		return codPlato;
	}
	public void setIdPlato(int idPlato) {
		this.codPlato = idPlato;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCodigoDetallePlato() {
		return codigoDetallePlato;
	}
	public void setCodigoDetallePlato(int codigoDetallePlato) {
		this.codigoDetallePlato = codigoDetallePlato;
	}
	
}
