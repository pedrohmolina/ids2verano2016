//-----------------------------------------------------------------------
// <copyright file="GestorFantasma.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Gestores
{
    using System;
    using System.Collections.Generic;
    using TomaDePedido.Interfaces;
    using TomaDePedido.Models;

    /// <summary>
    /// Gestiona la comunicación con ese Blanco legal que maneja las responsabilidades que no se definieron
    /// </summary>
    public class GestorFantasma : IGestorFantasma
    {
        private IGestorComunicacion gestorComunicacion;

        public GestorFantasma()
        {
        }

        public GestorFantasma(IGestorComunicacion gestorComunicacion)
        {
            this.gestorComunicacion = gestorComunicacion;
        }

        public List<Cerveza> ObtenerCervezas()
        {
            throw new NotImplementedException();
        }

        public List<Plato> ObtenerPlatos()
        {
            throw new NotImplementedException();
        }
    }
}
