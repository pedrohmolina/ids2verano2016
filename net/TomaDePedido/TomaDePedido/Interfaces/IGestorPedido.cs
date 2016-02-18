﻿namespace TomaDePedido.Interfaces
{
    using TomaDePedido.Enums;

    public interface IGestorPedido
    {
        void TomarPedido();

        /// <summary>
        /// Alertará acerca de la finalización del preparado de un plato
        /// </summary>
        /// <param name="codigoDetalle">Codigo de Detalle del Plato</param>
        void Alertar(int codigoDetalle);

        /// <summary>
        /// Obtiene el Saldo deudor de la Mesa a partir de su Codigo
        /// </summary>
        /// <param name="codigo">Codigo de Mesa</param>
        /// <returns></returns>
        double ObtenerSaldoMesa(int codigo);

        /// <summary>
        /// Obtiene el Estado de la Mesa a partir de su Codigo
        /// </summary>
        /// <param name="codigo">Codigo de la Mesa</param>
        /// <returns></returns>
        Enums.EstadoMesa ObtenerEstadoMesa(int codigoMesa);

        /// <summary>
        /// Cambia el estado de una mesa a "Abierta"
        /// </summary>
        /// <param name="codigo">Codigo de la Mesa</param>
        void AbrirMesa(int codigo);

        /// <summary>
        /// Cambia el estado de una mesa a "Cerrada"
        /// </summary>
        /// <param name="codigo">Codigo de la Mesa</param>
        void CerrarMesa(int codigo);
    }
}