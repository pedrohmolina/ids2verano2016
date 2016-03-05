package ar.com.caece.ids2.barapp.cocina.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@Column(name="codigo")
	private int id;
	@Column(name="codigomesa")
	private int idMesa;
	@Column(name="estadopedido")
	private int estadoPedido;
	@Column(name="entregartodojunto")
	private boolean entregarTodoJunto;
	@OneToMany(mappedBy="id", cascade=CascadeType.ALL)
	private List<DetallePedido> listaPlatos;
	@OneToMany(mappedBy="id", cascade=CascadeType.ALL)
	private List<DetalleCerveza> listaCervezas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public int getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(int estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	public boolean isEntregarTodoJunto() {
		return entregarTodoJunto;
	}
	public void setEntregarTodoJunto(boolean entregarTodoJunto) {
		this.entregarTodoJunto = entregarTodoJunto;
	}
	public List<DetallePedido> getListaPlatos() {
		return listaPlatos;
	}
	public void setListaPlatos(List<DetallePedido> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}
	public List<DetalleCerveza> getListaCervezas() {
		return listaCervezas;
	}
	public void setListaCervezas(List<DetalleCerveza> listaCervezas) {
		this.listaCervezas = listaCervezas;
	}
	
}
