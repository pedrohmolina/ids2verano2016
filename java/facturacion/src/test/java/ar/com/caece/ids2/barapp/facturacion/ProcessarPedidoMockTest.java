package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.DetallePlato;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;
import ar.com.caece.ids2.barapp.facturacion.models.Pedido;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ProcessarPedidoMockTest {
    private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
    private MenuService menuService = mock(MenuServiceImpl.class);
    private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

    @Before
    public void prepare() throws TableNotFoundException {
        reset(mesaService);
        reset(pedidoService);
    }

    // procesarPedido
    @Test(expected = TableNotOccupiedException.class)
    public void testProcesarPedidoFailsIfTableNotOcuppied() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getEstado()).thenReturn(Mesa.State.LIBRE);
        when(mesaService.getMesa(0)).thenReturn(m);

        Pedido pedido = mock(Pedido.class);
        when(pedido.getCodigoMesa()).thenReturn(0);
        facturador.procesarPedido(pedido);
        verify(mesaService).getMesa(0);
        verify(m).getEstado();
    }

    @Test()
    public void testProcesarPedidoAddsPedidoToMesa() throws Exception {
        Mesa m = spy(new Mesa("Paulaner"));
        when(m.getEstado()).thenReturn(Mesa.State.OCUPADA);
        when(mesaService.getMesa(0)).thenReturn(m);

        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(0);
        pedido.withPlato(detallePlato);
        pedido.setEstado(Pedido.State.PENDIENTE);

        Assert.assertEquals(m.getPedidos().size(), 0);
        facturador.procesarPedido(pedido);
        Assert.assertEquals(m.getPedidos().size(), 1);
        Assert.assertEquals(m.getPedidos().get(0), pedido);
        verify(mesaService).getMesa(0);
        verify(m).addPedido(pedido);
    }

    @Test()
    public void testProcesarPedidoModifiesExistantPedido() throws Exception {
        Mesa m = spy(new Mesa("Paulaner"));
        when(m.getCodigoMesa()).thenReturn(0);
        when(m.getEstado()).thenReturn(Mesa.State.OCUPADA);
        when(mesaService.getMesa(0)).thenReturn(m);

        Pedido pedido = mock(Pedido.class);
        when(pedido.getCodigoMesa()).thenReturn(0);
        when(pedido.getCodigo()).thenReturn(0);
        when(pedido.getCervezas()).thenReturn(new ArrayList<>());
        List<DetallePlato> list = new ArrayList<>();
        list.add(new DetallePlato(1, 1));
        when(pedido.getPlatos()).thenReturn(list);
        when(pedido.getEstado()).thenReturn(Pedido.State.PENDIENTE);

        facturador.procesarPedido(pedido);
        verify(m, times(1)).addPedido(pedido);
        verify(m, times(0)).removePedido(anyInt());

        Pedido pedidoModificado = mock(Pedido.class);
        when(pedidoModificado.getCodigoMesa()).thenReturn(0);
        when(pedidoModificado.getCodigo()).thenReturn(0);
        when(pedidoModificado.getCervezas()).thenReturn(new ArrayList<>());
        List<DetallePlato> detalle2 = new ArrayList<>();
        detalle2.add(new DetallePlato(1, 1));
        detalle2.add(new DetallePlato(2, 2));
        when(pedidoModificado.getPlatos()).thenReturn(detalle2);
        when(pedidoModificado.getEstado()).thenReturn(Pedido.State.PENDIENTE);

        facturador.procesarPedido(pedidoModificado);
        verify(m, times(1)).removePedido(pedidoModificado.getCodigo());
        verify(m, times(1)).removePedido(anyInt());
        verify(m, times(1)).addPedido(pedidoModificado);
    }

    @Test()
    public void testProcesarPedidoRemovesPedidoWhenCanceled() throws Exception {
        Mesa m = spy(new Mesa("Paulaner"));
        when(m.getCodigoMesa()).thenReturn(0);
        when(m.getEstado()).thenReturn(Mesa.State.OCUPADA);
        when(mesaService.getMesa(0)).thenReturn(m);

        Pedido pedido = mock(Pedido.class);
        when(pedido.getCodigoMesa()).thenReturn(0);
        when(pedido.getCodigo()).thenReturn(0);
        when(pedido.getCervezas()).thenReturn(new ArrayList<>());
        List<DetallePlato> list = new ArrayList<>();
        list.add(new DetallePlato(1, 1));
        when(pedido.getPlatos()).thenReturn(list);
        when(pedido.getEstado()).thenReturn(Pedido.State.PENDIENTE);

        facturador.procesarPedido(pedido);
        verify(m, times(1)).addPedido(pedido);
        verify(m, times(0)).removePedido(anyInt());

        Pedido pedidoModificado = mock(Pedido.class);
        when(pedidoModificado.getCodigoMesa()).thenReturn(0);
        when(pedidoModificado.getCodigo()).thenReturn(0);
        when(pedidoModificado.getCervezas()).thenReturn(new ArrayList<>());
        List<DetallePlato> detalle2 = new ArrayList<>();
        detalle2.add(new DetallePlato(1, 1));
        when(pedidoModificado.getPlatos()).thenReturn(detalle2);
        when(pedidoModificado.getEstado()).thenReturn(Pedido.State.CANCELADO);

        facturador.procesarPedido(pedidoModificado);
        verify(m, times(1)).removePedido(pedidoModificado.getCodigo());
        verify(m, times(1)).addPedido(pedido);
        verify(m, never()).addPedido(pedidoModificado);
    }

}
