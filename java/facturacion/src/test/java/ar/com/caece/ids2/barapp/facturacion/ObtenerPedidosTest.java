package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;
import ar.com.caece.ids2.barapp.facturacion.models.Pedido;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ObtenerPedidosTest {
    private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
    private MenuService menuService = mock(MenuServiceImpl.class);
    private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

    @Before
    public void clean() throws IllegalArgumentException, DuplicateTableException {
        reset(mesaService);
        reset(pedidoService);
    }

    @Test
    public void obtenerPedido() throws TableNotFoundException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(m.getCodigoMesa()));
        when(m.getPedidos()).thenReturn(pedidos);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        List<Pedido> pedidoList = facturador.obtenerPedidos(m.getCodigoMesa());
        assertEquals(pedidoList, pedidos);
        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).getPedidos();
    }

    @Test(expected = TableNotFoundException.class)
    public void obtenerPedidoMesaNotFound() throws TableNotFoundException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        when(mesaService.getMesa(m.getCodigoMesa())).thenThrow(TableNotFoundException.class);

        facturador.obtenerPedidos(m.getCodigoMesa());
    }
}
