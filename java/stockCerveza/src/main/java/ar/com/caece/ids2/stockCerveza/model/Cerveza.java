package ar.com.caece.ids2.stockCerveza.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Cerveza")
@NamedQueries({
    @NamedQuery(name="Cerveza.listarCervezas",
                query="SELECT c FROM Cerveza c")
})
public class Cerveza implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7003708295312881290L;
	
	
	@Id
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Cerveza",initialValue=1, allocationSize=1)
	private int idCerveza;
	
	@ManyToOne
	@JoinColumn(name="marcaCervezaId")
	private MarcaCerveza marcacerveza;
	
	
	@ManyToOne
	@JoinColumn(name="estiloCervezaId")
	private EstiloCerveza estiloCerveza;


	@ManyToMany
	  @JoinTable(
	      name="CervezaPresentacion",
	      joinColumns=@JoinColumn(name="idCerveza", referencedColumnName="idCerveza"),
	      inverseJoinColumns=@JoinColumn(name="idPresentacion", referencedColumnName="idPresentacion"))
	private List<PresentacionCerveza> presentacionCerveza;
	
	@Column(name="precio")
	private Double precio;
	
	private int cantidad;

	@Column(name="estado")
	private boolean estado;
	
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Cerveza(){
		
	}
	
	public MarcaCerveza getMarcacerveza() {
		return marcacerveza;
	}

	public void setMarcacerveza(MarcaCerveza marcacerveza) {
		this.marcacerveza = marcacerveza;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdCerveza() {
		return idCerveza;
	}

	public void setIdCerveza(int idCerveza) {
		this.idCerveza = idCerveza;
	}
	public List<PresentacionCerveza> getPresentacionCerveza() {
		return presentacionCerveza;
	}

	public void setPresentacionCerveza(List<PresentacionCerveza> presentacionCerveza) {
		this.presentacionCerveza = presentacionCerveza;
	}
	public EstiloCerveza getEstiloCerveza() {
		return estiloCerveza;
	}

	public void setEstiloCerveza(EstiloCerveza estiloCerveza) {
		this.estiloCerveza = estiloCerveza;
	}
	
	@Override
	public String toString(){
		return this.getMarcacerveza().getDescripcion()+ "-" +
				this.getEstiloCerveza().getDescripcion();
				
	}

}
