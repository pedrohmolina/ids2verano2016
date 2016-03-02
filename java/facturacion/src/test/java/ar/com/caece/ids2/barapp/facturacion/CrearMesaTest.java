package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Sebastian Schepens on 17/2/2016.
 */
public class CrearMesaTest {
    private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
    private MenuService menuService = mock(MenuServiceImpl.class);
    private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

    @After
    public void clean() throws TableNotFoundException {
        reset(mesaService);
        reset(pedidoService);
        reset(menuService);
    }

    @Test
    public void testCrearMesa() throws Exception {
        Mesa m = mock(Mesa.class);
        when(mesaService.createMesa("Paulaner")).thenReturn(m);
        Assert.assertEquals(facturador.crearMesa("Paulaner"), m);
        verify(mesaService, times(1)).createMesa("Paulaner");
    }

    @Test(expected = DuplicateTableException.class)
    public void testCrearMesaFailsOnDuplicate() throws Exception {
        when(mesaService.createMesa("Paulaner")).thenThrow(DuplicateTableException.class);
        facturador.crearMesa("Paulaner");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearMesaFailsNullName() throws Exception {
        facturador.crearMesa(null);
    }
}
