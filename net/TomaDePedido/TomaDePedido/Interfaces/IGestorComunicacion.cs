using System.Collections.Generic;

namespace TomaDePedido.Interfaces
{
    public interface IGestorComunicacion
    {
        List<IPedido> ObtenerPedidos(int codigoMesa);
        void EnviarPedido(IPedido pedido);
    }
}