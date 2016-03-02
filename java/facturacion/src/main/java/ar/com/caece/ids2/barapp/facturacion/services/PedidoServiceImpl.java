package ar.com.caece.ids2.barapp.facturacion.services;

import ar.com.caece.ids2.barapp.facturacion.models.DetalleCerveza;
import ar.com.caece.ids2.barapp.facturacion.models.DetallePlato;
import ar.com.caece.ids2.barapp.facturacion.models.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoServiceImpl implements PedidoService {

    private MenuService menuService = new MenuServiceImpl();

    private List<DetallePlato> detallePlatos = new ArrayList<>();
    private List<DetalleCerveza> detalleCervezas = new ArrayList<>();
    private Long total = 0L;

    @Override
    public Long getCuenta(List<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            for (DetallePlato detallePlato : p.getPlatos()) {
                this.detallePlatos.add(detallePlato);
                this.total += (menuService.obtenerValorPlato(detallePlato.getCodigoPlato()) * detallePlato.getCantidad());
            }
            for (DetalleCerveza detalleCerveza : p.getCervezas()) {
                this.detalleCervezas.add(detalleCerveza);
                this.total += (menuService.obtenerValorBebida(detalleCerveza.getCodigoCerveza()) * detalleCerveza.getCantidad());
            }
        }
        return total;
    }

    @Override
    public Long getTotal() {
        return total;
    }

    @Override
    public List<DetallePlato> getDetallePlatos() {
        return detallePlatos;
    }

    public List<DetalleCerveza> getDetalleCervezas() {
        return detalleCervezas;
    }

}
