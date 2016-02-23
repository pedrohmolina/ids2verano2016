//-----------------------------------------------------------------------
// <copyright file="GestorFacturacion.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Gestores
{
    using System;
    using System.Collections.Generic;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación con el módulo de Facturacíon
    /// </summary>
    public class GestorFacturacion : IGestorFacturacion
    {
        private IGestorComunicacion gestorComunicacion;

        public GestorFacturacion()
        {

        }

        public GestorFacturacion(IGestorComunicacion gestorComunicacion)
        {
            this.gestorComunicacion = gestorComunicacion;
        }

        public void OcuparMesa(int codigo)
        {
            
        }

        public void LiberarMesa(int codigo)
        {
            
        }

        public void EnviarPedido(IPedido pedido)
        {
            this.gestorComunicacion.EnviarPedido(pedido);
        }

        public int ObtenerEstadoMesa(int codigo)
        {
            return 0;
        }

        public List<IMesa> ObtenerMesas()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Obtiene los pedidos a partir de un codigo de mesa
        /// </summary>
        /// <param name="codigo">Codigo de Mesa</param>
        /// <returns></returns>
        public List<IPedido> ObtenerPedidos(int codigo)
        {
            return this.gestorComunicacion.ObtenerPedidos(codigo);
        }

        public double ObtenerSaldoMesa(int codigo)
        {
            throw new NotImplementedException();
        }
    }
}
