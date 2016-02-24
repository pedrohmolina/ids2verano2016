package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LiberarMesaTest {
    private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
    private MenuService menuService = mock(MenuServiceImpl.class);
    private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

    @Before
    public void clean() {
        reset(mesaService);
        reset(pedidoService);
    }

    @Test
    public void liberarMesaClosesMesa() throws TableNotFoundException, TableNotOccupiedException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        facturador.liberarMesa(m.getCodigoMesa());
        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).close();
    }

    @Test(expected = TableNotFoundException.class)
    public void ocupaMesaNotFound() throws TableNotFoundException, TableNotOccupiedException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        when(mesaService.getMesa(m.getCodigoMesa())).thenThrow(TableNotFoundException.class);

        facturador.liberarMesa(m.getCodigoMesa());
    }

    @Test(expected = TableNotOccupiedException.class)
    public void ocupaMesaAlreadyOpen() throws TableNotFoundException, TableNotOccupiedException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        doThrow(TableNotOccupiedException.class).when(m).close();

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        facturador.liberarMesa(m.getCodigoMesa());
    }
}
