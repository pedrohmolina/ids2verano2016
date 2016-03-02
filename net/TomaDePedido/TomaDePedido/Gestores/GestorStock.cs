//-----------------------------------------------------------------------
// <copyright file="GestorStock.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Gestores
{
    using System;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación con el módulo de Stock
    /// </summary>
    public class GestorStock : IGestorStock
    {
        private IGestorComunicacion gestorComunicacion;

        /// <summary>
        /// Constructor base de la clase
        /// </summary>
        public GestorStock()
        {
        }

        /// <summary>
        /// Constructor a partir de un Gestor de Comunicacion
        /// </summary>
        /// <param name="gestorComunicacion">Gestor de Comunicacion</param>
        public GestorStock(IGestorComunicacion gestorComunicacion)
        {
            this.gestorComunicacion = gestorComunicacion;
        }

        public void CancelarPedido(int codigoDetalle)
        {
            throw new NotImplementedException();
        }

        public void PedirCerveza(IDetalleCerveza detalle)
        {
            throw new NotImplementedException();
        }
    }
}
