namespace TomaDePedido.Interfaces
{
    using System.Collections.Generic;
    using Models;
    public interface IGestorFacturacion
    {
        /// <summary>
        /// Envia el pedido completo tanto si es un pedido nuevo como si se realizó una modificación
        /// </summary>
        /// <param name="pedido">Pedido realizado por el cliente</param>
        void EnviarPedido(IPedido pedido);

        /// <summary>
        /// Cambia el estado de una mesa a "Abierta"
        /// </summary>
        /// <param name="codigo">Codigo de la Mesa</param>
        void OcuparMesa(int codigo);

        /// <summary>
        /// Cambia el estado de una mesa a "Cerrada"
        /// </summary>
        /// <param name="codigo">Codigo de la Mesa</param>
        void LiberarMesa(int codigo);

        /// <summary>
        /// Obtiene el Estado de la Mesa a partir de su Codigo
        /// </summary>
        /// <param name="codigo">Codigo de la Mesa</param>
        /// <returns></returns>
        int ObtenerEstadoMesa(int codigo);

        /// <summary>
        /// Obtiene el Saldo deudor de la Mesa a partir de su Codigo
        /// </summary>
        /// <param name="codigo">Codigo de Mesa</param>
        /// <returns></returns>
        long ObtenerSaldoMesa(int codigo);

        /// <summary>
        /// Obtiene el listado de las Mesas disponibles en el local
        /// </summary>
        /// <returns>Listado de todas las Mesas</returns>
        List<Mesa> ObtenerMesas();

        Mesa ObtenerMesa(int codigoMesa);
        
        /// <summary>
        /// Obtiene el listado de Pedidos realizados por una Mesa
        /// </summary>
        /// <param name="codigo">Codigo de Mesa</param>
        /// <returns>Listado de todos los pedidos de una mesa en particular</returns>
        List<Pedido> ObtenerPedidos(int codigo);
    }
}