package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.Cuenta;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;
import ar.com.caece.ids2.barapp.facturacion.models.Pedido;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ObtenerSaldoTest {
    private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
    private MenuService menuService = mock(MenuServiceImpl.class);
    private Facturador facturador = spy(new FacturadorImpl(mesaService, pedidoService, menuService));

    @Before
    public void clean() {
        reset(mesaService);
        reset(pedidoService);
        reset(menuService);
        reset(facturador);
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
    public void testObtenerSaldoFailsOnClosedTable() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        doThrow(TableNotOccupiedException.class).when(facturador).cuentaMesa(0);

        facturador.obtenerSaldo(m.getCodigoMesa());
    }

    @Test(expected = TableNotFoundException.class)
    public void testObtenerSaldoFailsOnInexistantTable() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        doThrow(TableNotFoundException.class).when(facturador).cuentaMesa(0);

        facturador.obtenerSaldo(m.getCodigoMesa());
    }

    @Test()
    public void testCuentaReturnsPedidosAndTotal() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        when(m.isClosed()).thenReturn(false);
        when(m.getPedidos()).thenReturn(new ArrayList<Pedido>());

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        Cuenta cuenta = mock(Cuenta.class);
        when(cuenta.getTotal()).thenReturn(100L);

        doReturn(cuenta).when(facturador).cuentaMesa(0);

        Long saldo = facturador.obtenerSaldo(m.getCodigoMesa());
        Assert.assertEquals(saldo.longValue(), 100L);
        verify(facturador, times(1)).cuentaMesa(m.getCodigoMesa());
        verify(cuenta, times(1)).getTotal();
    }
}
