package ar.com.caece.ids2.barapp.cocina.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.caece.ids2.barapp.cocina.model.Pedido;

@Repository
public class PedidoDao {
	
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Pedido pedido) {
		Session s = sessionFactory.getCurrentSession();
		s.save(pedido);
	}
	
	@Transactional
	public void update(Pedido pedido) {
		Session s = sessionFactory.getCurrentSession();
		s.update(pedido);
	}
	
	@Transactional
	public void delete(Pedido pedido) {
		Session s = sessionFactory.getCurrentSession();
		s.delete(pedido);
	}
	
	@Transactional
	public Pedido get(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Pedido)s.get(Pedido.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
