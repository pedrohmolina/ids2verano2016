package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ObtenerEstadoMesaTest {
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
    public void obtenerEstadoMesaClosedTest() throws TableNotFoundException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        when(m.getEstado()).thenReturn(Mesa.State.LIBRE);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        Integer estado = facturador.obtenerEstadoMesa(m.getCodigoMesa());
        Assert.assertEquals(estado.intValue(), 0);
        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).getEstado();
    }

    @Test
    public void obtenerEstadoMesaOpenTest() throws TableNotFoundException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        when(m.getEstado()).thenReturn(Mesa.State.OCUPADA);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        Integer estado = facturador.obtenerEstadoMesa(m.getCodigoMesa());
        Assert.assertEquals(estado.intValue(), 1);
        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).getEstado();
    }

    @Test(expected = TableNotFoundException.class)
    public void obtenerEstadoMesaNotExistTest() throws TableNotFoundException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        when(mesaService.getMesa(m.getCodigoMesa())).thenThrow(TableNotFoundException.class);

        facturador.obtenerEstadoMesa(m.getCodigoMesa());
    }
}
