package ar.com.caece.ids2.barapp.facturacion;

public class FacturarMesaTest {
/*	private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
	private MenuService menuService = mock(MenuServiceImpl.class);
	private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

	@Before
	public void setUpFacturador() throws IllegalArgumentException, DuplicateTableException {
		Mesa paulaner = facturador.crearMesa("Paulaner");

		Pedido pedido = new Pedido(paulaner.getCodigoMesa());
		pedido.setEstado(Pedido.State.PENDIENTE);
		paulaner.addPedido(pedido);
	}

	// factuar un mesa que exista
	@Test
	public void facturar_mesa_existe() throws IllegalArgumentException, TableNotFoundException {
		MedioDePago medioPago = new MedioDePago();
		List<Factura> facturas = facturador.facturarMesa(0, 1000L, 20L, medioPago);
		assertEquals(1, facturas.size());
	}

	// factuar un mesa que con dos facturas
	@Test
	public void facturar_mesa_no_existe() throws IllegalArgumentException, TableNotFoundException {
		MedioDePago medioPago = new MedioDePago();
		// la mesa debe tener consumido mas de mil
		List<Factura> facturas = facturador.facturarMesa(0, 1000L, 20L, medioPago);
		assertEquals(2, facturas.size());
	}

	// Facturar una mesa que esta cerrada
	@Test
	public void facturar_mesa_estado_close()
			throws IllegalArgumentException, TableNotFoundException, DuplicateTableException {
		MedioDePago medioPago = new MedioDePago();
		Mesa mesa = facturador.crearMesa("mesa");
		// ver como setearle el estado close
		List<Factura> facturas = facturador.facturarMesa(0, 1000L, 20L, medioPago);
		assertEquals(2, facturas.size());
	}

	// Facturar una mesa que esta abierta
	@Test
	public void facturar_mesa_estado_open()
			throws IllegalArgumentException, TableNotFoundException, DuplicateTableException {
		MedioDePago medioPago = new MedioDePago();
		Mesa mesa = facturador.crearMesa("mesa");
		// ver como setearle el estado close
		List<Factura> facturas = facturador.facturarMesa(0, 1000L, 20L, medioPago);
		assertEquals(2, facturas.size());
	}*/
}
