//-----------------------------------------------------------------------
// <copyright file="GestorCocina.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Gestores
{
    using System;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación con el módulo de Cocina
    /// </summary>
    public class GestorCocina : IGestorCocina
    {
        private IGestorComunicacion gestorComunicacion;

        public GestorCocina()
        {
        }

        public GestorCocina(IGestorComunicacion gestorComunicacion)
        {
            this.gestorComunicacion = gestorComunicacion;
        }

        /// <summary>
        /// Envia a la Cocina la orden de que se cancele el plato perteneciente a un detalle
        /// </summary>
        /// <param name="codigoDetalle">Codigo de Detalle</param>
        public void CancelarPlato(int codigoDetalle)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Envia a la cocina la orden de que se prepare un plato a partir de un detalle
        /// </summary>
        /// <param name="detalle">Detalle de Plato</param>
        public void PedirPlato(IDetallePlato detalle)
        {
            throw new NotImplementedException();
        }
    }
}
