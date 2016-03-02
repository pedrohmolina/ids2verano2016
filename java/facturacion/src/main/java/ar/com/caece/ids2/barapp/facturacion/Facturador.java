package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;

import java.util.List;

/**
 * Created by Sebastian Schepens on 16/2/2016.
 */
public interface Facturador {
    /**
     * Agrega, modifica o quita un pedido de una mesa de acuerdo al estado del pedido
     *
     * @param pedido el pedido que debe ser procesado
     * @throws TableNotFoundException
     * @throws IllegalArgumentException
     */
    public void procesarPedido(Pedido pedido) throws TableNotFoundException, TableNotOccupiedException, IllegalArgumentException;  //listo

    /**
     * Devuelve una {@link Cuenta} para la mesa data
     *
     * @param codigoMesa codigo identificador de la mesa
     * @return {@link Cuenta} para la mesa dada, con items y total
     * @throws TableNotFoundException
     * @throws TableNotOccupiedException
     */
    public Cuenta cuentaMesa(Integer codigoMesa) throws TableNotFoundException, TableNotOccupiedException;//tested

    /**
     * Procesa el {@link MedioDePago} agrega la propina a la cuenta y divide en facturas de no mas del limite.
     *
     * @param codigoMesa codigo identificador de la mesa a facturar.
     * @param limit      limite de valor para una factura.
     * @param tip        valor de la propina a agregar a la factura.
     * @param medio      medio de pago.
     * @return {@link List} de {@link Factura}
     * @throws TableNotFoundException
     * @throws IllegalArgumentException
     */
    public List<Factura> facturarMesa(Integer codigoMesa, Long limit, Long tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException; //tested

    /**
     * Marca una {@link Mesa} como ocupada.
     *
     * @param codigoMesa codigo identificador de la {@link Mesa}
     * @throws TableNotFoundException
     * @throws TableAlreadyOccupiedException
     */
    public void ocuparMesa(Integer codigoMesa) throws TableNotFoundException, TableAlreadyOccupiedException;//tested

    /**
     * Marca una {@link Mesa} como libre.
     *
     * @param codigoMesa codigo identificador de la {@link Mesa}
     * @throws TableNotFoundException
     * @throws TableNotOccupiedException
     */
    public void liberarMesa(Integer codigoMesa) throws TableNotFoundException, TableNotOccupiedException;//tested

    /**
     * Devuelve el estado de una mesa en su valor de entero.
     *
     * @param codigoMesa codigo identificador de la {@link Mesa}
     * @return {@link Integer} representante del estado {@link ar.com.caece.ids2.barapp.facturacion.models.Mesa.State} de la mesa.
     * @throws TableNotFoundException
     */
    public Integer obtenerEstadoMesa(Integer codigoMesa) throws TableNotFoundException;//tested

    /**
     * Devuelve el total de la cuenta hasta el momento para una mesa.
     *
     * @param codigoMesa codigo identificador de la {@link Mesa}
     * @return {@link Long} saldo total de la cuenta en centavos.
     * @throws TableNotFoundException
     */
    public Long obtenerSaldo(Integer codigoMesa) throws TableNotFoundException, TableNotOccupiedException;//tested

    /**
     * Devuelve la lista de pedidos correspondientes a una mesa.
     *
     * @param codigoMesa codigo identificador de la {@link Mesa}
     * @return {@link List} de los {@link Pedido} asociados a una mesa.
     * @throws TableNotFoundException
     */
    public List<Pedido> obtenerPedidos(Integer codigoMesa) throws TableNotFoundException;//tested

    /**
     * Devuelve el listado de mesas existentes en el sistema.
     *
     * @return {@link List} de las {@link Mesa}
     */
    public Mesa obtenerMesa(Integer codigoMesa) throws TableNotFoundException;

    /**
     * Devuelve el listado de mesas existentes en el sistema.
     *
     * @return {@link List} de las {@link Mesa}
     */
    public List<Mesa> obtenerMesas() throws TableNotFoundException;

    /**
     * Da de alta una mesa.
     *
     * @param mesaName nombre para la nueva mesa
     * @return {@link Mesa} creada
     * @throws DuplicateTableException
     * @throws IllegalArgumentException
     */
    public Mesa crearMesa(String mesaName) throws DuplicateTableException, IllegalArgumentException; //tested

    /**
     * Elimina una mesa del sistema.
     *
     * @param codigoMesa codigo identificador de la mesa
     * @throws TableNotFoundException
     */
    public void quitarMesa(Integer codigoMesa) throws TableNotFoundException;
}
