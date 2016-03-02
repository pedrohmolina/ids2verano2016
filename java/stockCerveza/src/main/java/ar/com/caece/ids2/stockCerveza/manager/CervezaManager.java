package ar.com.caece.ids2.stockCerveza.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.com.caece.ids2.stockCerveza.exception.CervezaInexistenteException;
import ar.com.caece.ids2.stockCerveza.exception.NoEncontroEntidadException;
import ar.com.caece.ids2.stockCerveza.exception.SinStockCervezaException;
import ar.com.caece.ids2.stockCerveza.exception.ValidacionException;
import ar.com.caece.ids2.stockCerveza.exception.ValorFueraDeRangoException;
import ar.com.caece.ids2.stockCerveza.model.Cerveza;
import ar.com.caece.ids2.stockCerveza.model.EstiloCerveza;
import ar.com.caece.ids2.stockCerveza.model.MarcaCerveza;
import ar.com.caece.ids2.stockCerveza.model.PresentacionCerveza;

public class CervezaManager extends Manager{
	
	public CervezaManager(){
		super();
	}
	
	
	public CervezaManager(EntityManager em) {
		
	}


	/**
	 * Metodo que dado un id de cerveza devuelve la instancia de Cerveza con ese id
	 * @param idCerveza
	 * @throws NoEncontroEntidadException 
	 */
	public Cerveza buscarCerveza(int cervezaId) throws CervezaInexistenteException{
		
		Cerveza cerveza = null;
		cerveza = em.find(Cerveza.class, cervezaId);
		if(cerveza == null){
			throw new CervezaInexistenteException();
		}	
			
		return cerveza;
	}
	
	/**
	 * Metodo devuelve todas las instancias de Cerveza existentes
	 */
	@SuppressWarnings("unchecked")
	public List<Cerveza> listarCervezas() throws RuntimeException{
		Query q = em.createNamedQuery("Cerveza.listarCervezas");
		return (List<Cerveza>)q.getResultList();
	}
	
	
	/**
	 * Metodo que crea una cerveza nueva
	 * @param marcaCerveza
	 * @param presentacionCerveza
	 * @param estiloCerveza
	 * @param cantidad
	 * @param precio
	 */
	public Cerveza crearCerveza(MarcaCerveza marcaCerveza, List<PresentacionCerveza> presentacionesCerveza, EstiloCerveza estiloCerveza, int cantidad, Double precio) throws RuntimeException,Exception{
		
		Cerveza cerveza = new Cerveza();
		
		
		if(precio.compareTo(100000D) > 0  || precio.compareTo(0D) < 0){
			throw new ValorFueraDeRangoException("precio");
		}
		
		if(cantidad > 1000000 || cantidad < 0) {
			throw new ValorFueraDeRangoException("cantidad");
		}
		
		MarcaCervezaManager mcm = new MarcaCervezaManager();
		mcm.buscarMarcaCerveza(marcaCerveza.getId());
		
		
		PresentacionCervezaManager  pcm = new PresentacionCervezaManager();
		for(PresentacionCerveza presentacionCerveza: presentacionesCerveza){
			pcm.buscarPresentacionCerveza(presentacionCerveza.getIdPresentacion());
		}
		
		EstiloCervezaManager ecm = new EstiloCervezaManager();
		ecm.buscarEstiloCerveza(estiloCerveza.getId());
		
		
		
		cerveza.setMarcacerveza(marcaCerveza);
		cerveza.setPresentacionCerveza(presentacionesCerveza);
		cerveza.setEstiloCerveza(estiloCerveza);
		cerveza.setCantidad(cantidad);
		cerveza.setPrecio(precio);
		
		em.getTransaction().begin();
		em.persist(cerveza);
		em.flush();
		em.getTransaction().commit();
		return cerveza;
	}
	
	/**
	 * Metodo que cambia el estado a una cerveza
	 * @param cervezaId
	 * @param estado
	 * @throws CervezaInexistenteException 
	 */
	public Cerveza setearEstadoCerveza(int cervezaId, boolean estado) throws RuntimeException, CervezaInexistenteException{
		Cerveza cerveza = buscarCerveza(cervezaId);
		
		cerveza.setEstado(estado);
		em.getTransaction().begin();
		em.merge(cerveza);
		em.flush();
		em.getTransaction().commit();
		return cerveza;
		
	}
	
	/**
	 * Metodo que decrementa el stock de una cerveza dada
	 * @param cervezaId
	 * @param cantidadADecrementar
	 * @throws ValidacionException 
	 */
	public Cerveza decrementarCantidad(int cervezaId, int cantADecrementar) throws RuntimeException,SinStockCervezaException,CervezaInexistenteException, ValidacionException{
		Cerveza cerveza = buscarCerveza(cervezaId);
		
		if(cerveza.getCantidad() < cantADecrementar) {
			throw new SinStockCervezaException(cerveza);
		}
		
		if( cantADecrementar <= 0 ) {
			throw new ValidacionException("Cantidad de cerveza a decrementar invalida");
		}
		
		cerveza.setCantidad(cerveza.getCantidad() - cantADecrementar);
		
		em.getTransaction().begin();
		em.merge(cerveza);
		em.flush();
		em.getTransaction().commit();
		
		return cerveza;
		
	}
	
	/**
	 * Metodo que incrementa el stock de una cerveza dada
	 * @param cervezaId
	 * @param cantidadAIncrementar
	 */
	public Cerveza incrementarCantidad(int cervezaId, int cantAIncrementar) throws RuntimeException,CervezaInexistenteException{
		Cerveza cerveza = buscarCerveza(cervezaId);
		
		cerveza.setCantidad(cerveza.getCantidad() + cantAIncrementar);
		
		em.getTransaction().begin();
		em.merge(cerveza);
		em.flush();
		em.getTransaction().commit();
		
		return cerveza;
		
	}
	
	/**
	 * Metodo que cambia el precio a una cerveza
	 * @param cervezaId
	 * @param precio
	 * @throws CervezaInexistenteException 
	 */
	public Cerveza cambiarPrecio(int cervezaId, Double precio) throws RuntimeException, CervezaInexistenteException{
		Cerveza cerveza = buscarCerveza(cervezaId);
		
		cerveza.setPrecio(precio);;
		
		em.getTransaction().begin();
		em.merge(cerveza);
		em.flush();
		em.getTransaction().commit();
		
		return cerveza;
	}
}
