package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class OcuparMesaTest {
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
    public void ocupaMesaClose() throws TableNotFoundException, TableAlreadyOccupiedException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        facturador.ocuparMesa(m.getCodigoMesa());
        verify(mesaService, times(1)).getMesa(m.getCodigoMesa());
        verify(m, times(1)).open();
    }

    @Test(expected = TableNotFoundException.class)
    public void ocupaMesaNotFound() throws TableNotFoundException, TableAlreadyOccupiedException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);

        when(mesaService.getMesa(m.getCodigoMesa())).thenThrow(TableNotFoundException.class);

        facturador.ocuparMesa(m.getCodigoMesa());
    }

    @Test(expected = TableAlreadyOccupiedException.class)
    public void ocupaMesaAlreadyOpen() throws TableNotFoundException, TableAlreadyOccupiedException {
        Mesa m = mock(Mesa.class);
        when(m.getCodigoMesa()).thenReturn(0);
        doThrow(TableAlreadyOccupiedException.class).when(m).open();

        when(mesaService.getMesa(m.getCodigoMesa())).thenReturn(m);

        facturador.ocuparMesa(m.getCodigoMesa());
    }
}
