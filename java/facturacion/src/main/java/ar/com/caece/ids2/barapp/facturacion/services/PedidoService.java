package ar.com.caece.ids2.barapp.facturacion.services;

import ar.com.caece.ids2.barapp.facturacion.models.DetalleCerveza;
import ar.com.caece.ids2.barapp.facturacion.models.DetallePlato;
import ar.com.caece.ids2.barapp.facturacion.models.Pedido;

import java.util.List;

public interface PedidoService {

    public Long getCuenta(List<Pedido> pedidos);

    public Long getTotal();

    public List<DetallePlato> getDetallePlatos();

    public List<DetalleCerveza> getDetalleCervezas();

}
