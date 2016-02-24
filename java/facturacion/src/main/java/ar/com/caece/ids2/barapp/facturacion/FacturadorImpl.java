package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;
import ar.com.caece.ids2.barapp.facturacion.services.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class FacturadorImpl implements Facturador {
    private MesaService mesaService;
    private PedidoService pedidoService;
    private MenuService menuService;

    public FacturadorImpl() {
        this.mesaService = new MesaServiceImpl();
        this.pedidoService = new PedidoServiceImpl();
        this.menuService = new MenuServiceImpl();
    }

    public FacturadorImpl(MesaService mesaService, PedidoService pedidoService, MenuService menuService) {
        this.mesaService = mesaService;
        this.pedidoService = pedidoService;
        this.menuService = menuService;
    }

    @Override
    public void procesarPedido(Pedido pedido) throws TableNotFoundException, TableNotOccupiedException, IllegalArgumentException {
        Mesa m = mesaService.getMesa(pedido.getCodigoMesa());
        if (m.getEstado() == Mesa.State.LIBRE) {
            throw new TableNotOccupiedException("Table is closed");
        }
        Optional<Pedido> op = m.getPedidos().stream().filter(p -> p.getCodigo() == pedido.getCodigo()).findFirst();
        if (op.isPresent()) {
            m.removePedido(pedido.getCodigo());
        }
        if (pedido.getEstado() != Pedido.State.CANCELADO) {
            m.addPedido(pedido);
        }
    }

    @Override
    public Cuenta cuentaMesa(Integer mesa) throws TableNotFoundException, TableNotOccupiedException {
        Mesa m = mesaService.getMesa(mesa);
        if (m.isClosed()) {
            throw new TableNotOccupiedException("Table is not occupied");
        }
        List<DetallePlato> detallePlatos = new ArrayList<>();
        List<DetalleCerveza> detalleCervezas = new ArrayList<>();
        Long total = 0L;
        for (Pedido p : m.getPedidos()) {
            for (DetallePlato detallePlato : p.getPlatos()) {
                detallePlatos.add(detallePlato);
                total += (menuService.obtenerValorPlato(detallePlato.getCodigoPlato()) * detallePlato.getCantidad());
            }
            for (DetalleCerveza detalleCerveza : p.getCervezas()) {
                detalleCervezas.add(detalleCerveza);
                total += (menuService.obtenerValorBebida(detalleCerveza.getCodigoCerveza()) * detalleCerveza.getCantidad());
            }
        }
        return new Cuenta(detallePlatos, detalleCervezas, total);
    }

    @Override
    public List<Factura> facturarMesa(Integer code, Long limit, Long tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException {
        Mesa m = mesaService.getMesa(code);
        List<Factura> facturas = new ArrayList<>();
        Factura f = new Factura(medio);
        for (Pedido p : m.getPedidos()) {
            for (DetallePlato detallePlato : p.getPlatos()) {
                Long valor = menuService.obtenerValorPlato(detallePlato.getCodigoPlato());
                for (int i = 0; i < detallePlato.getCantidad(); i++) {
                    if (f.getTotal() + valor > limit) {
                        facturas.add(f);
                        f = new Factura(medio);
                    }
                    f.addPlato(new DetallePlato(detallePlato.getCodigoPlato(), 1));
                    f.setTotal(f.getTotal() + valor);
                }
            }
            for (DetalleCerveza detalleCerveza : p.getCervezas()) {
                Long valor = menuService.obtenerValorBebida(detalleCerveza.getCodigoCerveza());
                for (int i = 0; i < detalleCerveza.getCantidad(); i++) {
                    if (f.getTotal() + valor > limit) {
                        facturas.add(f);
                        f = new Factura(medio);
                    }
                    f.addBebida(new DetalleCerveza(detalleCerveza.getCodigoCerveza(), 1));
                    f.setTotal(f.getTotal() + valor);
                }
            }
        }
        if (f.getTotal() + tip > limit) {
            facturas.add(f);
            f = new Factura(medio);
        }
        f.setTip(tip);
        return facturas;
    }

    @Override
    public void ocuparMesa(Integer code) throws TableNotFoundException, TableAlreadyOccupiedException {
        mesaService.getMesa(code).open();
    }

    @Override
    public void liberarMesa(Integer code) throws TableNotFoundException, TableNotOccupiedException {
        mesaService.getMesa(code).close();
    }

    @Override
    public Integer obtenerEstadoMesa(Integer code) throws TableNotFoundException {
        return Arrays.asList(Mesa.State.values()).indexOf(mesaService.getMesa(code).getEstado());
    }

    @Override
    public Long obtenerSaldo(Integer code) throws TableNotFoundException, TableNotOccupiedException {
        return cuentaMesa(code).getTotal();
    }

    @Override
    public List<Pedido> obtenerPedidos(Integer code) throws TableNotFoundException {
        return mesaService.getMesa(code).getPedidos();
    }

    @Override
    public Mesa obtenerMesa(Integer codigoMesa) throws TableNotFoundException {
        return mesaService.getMesa(codigoMesa);
    }

    @Override
    public List<Mesa> obtenerMesas() throws TableNotFoundException {
        return mesaService.getMesas();
    }

    @Override
    public Mesa crearMesa(String name) throws DuplicateTableException, IllegalArgumentException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException();
        }
        return mesaService.createMesa(name);
    }

    @Override
    public void quitarMesa(Integer code) throws TableNotFoundException {
        mesaService.destroyMesa(code);
    }
}
