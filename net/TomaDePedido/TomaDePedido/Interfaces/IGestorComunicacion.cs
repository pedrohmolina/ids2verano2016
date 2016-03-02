using System.Collections.Generic;
using TomaDePedido.Models;

namespace TomaDePedido.Interfaces
{
    public interface IGestorComunicacion
    {
        List<Pedido> ObtenerPedidos(int codigoMesa);
        List<Mesa> ObtenerMesas();
        void EnviarPedido(IPedido pedido);
        Mesa ObtenerMesa(int codigoMesa);
        long ObtenerSaldoMesa(int codigoMesa);
    }
}