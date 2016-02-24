package ar.com.caece.ids2.barapp.facturacion.controllers;

import ar.com.caece.ids2.barapp.facturacion.Facturador;
import ar.com.caece.ids2.barapp.facturacion.FacturadorImpl;
import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sebastian Schepens on 22/2/2016.
 */
@RestController
public class FacturadorController implements Facturador {
    private Facturador facturador = new FacturadorImpl();

    @Override
    @RequestMapping(value = "/procesarPedido", method = RequestMethod.PUT)
    public void procesarPedido(@RequestBody Pedido pedido) throws TableNotFoundException, TableNotOccupiedException, IllegalArgumentException {
        System.out.println(pedido);
        facturador.procesarPedido(pedido);
    }

    @Override
    @RequestMapping(value = "/mesas/{codigoMesa}/cuenta", method = RequestMethod.GET)
    public Cuenta cuentaMesa(@PathVariable Integer codigoMesa) throws TableNotFoundException, TableNotOccupiedException {
        return facturador.cuentaMesa(codigoMesa);
    }

    @Override
    @RequestMapping(value = "/mesas/{codigoMesa}/facturar", method = RequestMethod.PUT)
    public List<Factura> facturarMesa(@PathVariable Integer codigoMesa, Long limit, Long tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException {
        return facturador.facturarMesa(codigoMesa, limit, tip, medio);
    }

    @RequestMapping(value = "/mesas/{codigoMesa}/ocupar", method = RequestMethod.PUT)
    public void ocuparMesa(@PathVariable Integer codigoMesa) throws TableNotFoundException, TableAlreadyOccupiedException {
        facturador.ocuparMesa(codigoMesa);
    }

    @RequestMapping(value = "/mesas/{codigoMesa}/liberar", method = RequestMethod.PUT)
    public void liberarMesa(Integer codigoMesa) throws TableNotFoundException, TableNotOccupiedException {
        facturador.liberarMesa(codigoMesa);
    }

    @Override
    @RequestMapping(value = "/mesas/{codigoMesa}/estado", method = RequestMethod.GET)
    public Integer obtenerEstadoMesa(@PathVariable Integer codigoMesa) throws TableNotFoundException {
        return facturador.obtenerEstadoMesa(codigoMesa);
    }

    @RequestMapping(value = "/mesas/{codigoMesa}/saldo", method = RequestMethod.GET)
    public Long obtenerSaldo(Integer codigoMesa) throws TableNotFoundException, TableNotOccupiedException {
        return facturador.obtenerSaldo(codigoMesa);
    }

    @RequestMapping(value = "/mesas/{codigoMesa}/pedidos", method = RequestMethod.GET)
    public List<Pedido> obtenerPedidos(@PathVariable Integer codigoMesa) throws TableNotFoundException {
        return facturador.obtenerPedidos(codigoMesa);
    }

    @Override
    @RequestMapping(value = "/mesas/{codigoMesa}", method = RequestMethod.GET)
    public Mesa obtenerMesa(@PathVariable Integer codigoMesa) throws TableNotFoundException {
        return facturador.obtenerMesa(codigoMesa);
    }

    @RequestMapping(value = "/mesas", method = RequestMethod.GET)
    public List<Mesa> obtenerMesas() throws TableNotFoundException {
        return facturador.obtenerMesas();
    }

    @RequestMapping(value = "/mesas/{name}", method = RequestMethod.POST)
    public Mesa crearMesa(@PathVariable String name) throws DuplicateTableException, IllegalArgumentException {
        return facturador.crearMesa(name);
    }

    @Override
    @RequestMapping(value = "/mesas/{codigoMesa}", method = RequestMethod.DELETE)
    public void quitarMesa(Integer codigoMesa) throws TableNotFoundException {
        facturador.quitarMesa(codigoMesa);
    }
}
