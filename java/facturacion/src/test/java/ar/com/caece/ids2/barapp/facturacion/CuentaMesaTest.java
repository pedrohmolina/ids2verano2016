package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CuentaMesaTest {
    private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
    private MenuService menuService = mock(MenuServiceImpl.class);
    private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

    @Before
    public void clean() {
        reset(mesaService);
        reset(pedidoService);
    }

    // cuentaMesa
    @Test()
    public void testCuentaMesa() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        when(m.getPedidos()).thenReturn(new ArrayList<Pedido>());
        when(m.isClosed()).thenReturn(false);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        Cuenta cuenta = facturador.cuentaMesa(m.getCodigoMesa());
        Assert.assertNotNull(cuenta);
        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).isClosed();
        verify(m, times(1)).getPedidos();
    }

    @Test(expected = TableNotOccupiedException.class)
    public void testCuentaFailsOnClosedTable() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        when(m.isClosed()).thenReturn(true);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        facturador.cuentaMesa(m.getCodigoMesa());
    }

    @Test(expected = TableNotFoundException.class)
    public void testCuentaFailsOnInexistantTable() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        when(mesaService.getMesa(m.getCodigoMesa())).thenThrow(TableNotFoundException.class);

        facturador.cuentaMesa(m.getCodigoMesa());
    }

    @Test()
    public void testCuentaReturnsPedidosAndTotal() throws Exception {
        DetallePlato detallePlato = mock(DetallePlato.class);
        when(detallePlato.getCodigoPlato()).thenReturn(1);
        when(detallePlato.getCantidad()).thenReturn(1);
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido(m.getCodigoMesa());
        pedido.withPlato(detallePlato);
        pedidos.add(pedido);
        when(m.getPedidos()).thenReturn(pedidos);
        when(m.isClosed()).thenReturn(false);

        when(menuService.obtenerValorPlato(detallePlato.getCodigoPlato())).thenReturn(100L);
        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        Cuenta cuenta = facturador.cuentaMesa(m.getCodigoMesa());
        Assert.assertEquals(cuenta.getPlatos().size(), 1);
        Assert.assertEquals(cuenta.getPlatos().get(0), detallePlato);
        Long total = menuService.obtenerValorPlato(detallePlato.getCodigoPlato()) * detallePlato.getCantidad();
        Assert.assertEquals(cuenta.getTotal(), total);

        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).isClosed();
        verify(m, times(1)).getPedidos();
    }

    @Test()
    public void testCuentaReturnsMultiplePedidosWithMultipleItems() throws Exception {
        DetallePlato detallePlato1 = mock(DetallePlato.class);
        when(detallePlato1.getCodigoPlato()).thenReturn(1);
        when(detallePlato1.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorPlato(detallePlato1.getCodigoPlato())).thenReturn(100L);
        DetallePlato detallePlato2 = mock(DetallePlato.class);
        when(detallePlato2.getCodigoPlato()).thenReturn(2);
        when(detallePlato2.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorPlato(detallePlato2.getCodigoPlato())).thenReturn(200L);
        DetalleCerveza detalleCerveza1 = mock(DetalleCerveza.class);
        when(detalleCerveza1.getCodigoCerveza()).thenReturn(3);
        when(detalleCerveza1.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorBebida(detalleCerveza1.getCodigoCerveza())).thenReturn(300L);
        DetalleCerveza detalleCerveza2 = mock(DetalleCerveza.class);
        when(detalleCerveza2.getCodigoCerveza()).thenReturn(4);
        when(detalleCerveza2.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorBebida(detalleCerveza2.getCodigoCerveza())).thenReturn(400L);

        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido1 = mock(Pedido.class);
        List<DetallePlato> detallePlatos1 = new ArrayList<>();
        detallePlatos1.add(detallePlato1);
        when(pedido1.getPlatos()).thenReturn(detallePlatos1);
        List<DetalleCerveza> detalleBebidas1 = new ArrayList<>();
        detalleBebidas1.add(detalleCerveza1);
        when(pedido1.getCervezas()).thenReturn(detalleBebidas1);
        pedidos.add(pedido1);

        Pedido pedido2 = mock(Pedido.class);
        List<DetallePlato> detallePlatos2 = new ArrayList<>();
        detallePlatos2.add(detallePlato2);
        when(pedido2.getPlatos()).thenReturn(detallePlatos2);
        List<DetalleCerveza> detalleBebidas2 = new ArrayList<>();
        detalleBebidas2.add(detalleCerveza2);
        when(pedido2.getCervezas()).thenReturn(detalleBebidas2);
        pedidos.add(pedido2);

        when(m.getPedidos()).thenReturn(pedidos);
        when(m.isClosed()).thenReturn(false);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        Cuenta cuenta = facturador.cuentaMesa(m.getCodigoMesa());

        Assert.assertEquals(cuenta.getPlatos().size(), 2);
        Assert.assertEquals(cuenta.getCervezas().size(), 2);
        Assert.assertEquals(cuenta.getPlatos().get(0), pedido1.getPlatos().get(0));
        Assert.assertEquals(cuenta.getCervezas().get(0), pedido1.getCervezas().get(0));
        Assert.assertEquals(cuenta.getPlatos().get(1), pedido2.getPlatos().get(0));
        Assert.assertEquals(cuenta.getCervezas().get(1), pedido2.getCervezas().get(0));
        Assert.assertEquals(cuenta.getTotal().longValue(), 1000L);

        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).isClosed();
        verify(m, times(1)).getPedidos();
        verify(menuService, times(1)).obtenerValorBebida(detalleCerveza1.getCodigoCerveza());
        verify(menuService, times(1)).obtenerValorBebida(detalleCerveza2.getCodigoCerveza());
        verify(menuService, times(1)).obtenerValorPlato(detallePlato1.getCodigoPlato());
        verify(menuService, times(1)).obtenerValorPlato(detallePlato2.getCodigoPlato());
    }
}
