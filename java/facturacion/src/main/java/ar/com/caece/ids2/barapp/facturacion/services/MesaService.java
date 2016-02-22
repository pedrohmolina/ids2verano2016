package ar.com.caece.ids2.barapp.facturacion.services;

import java.util.List;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;

public interface MesaService {

	public Mesa createMesa(String name) throws DuplicateTableException;

	public Mesa getMesa(Integer code) throws TableNotFoundException;

	public List<Mesa> getMesas();

	public void destroyMesa(Integer code) throws TableNotFoundException;
}
