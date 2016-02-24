//-----------------------------------------------------------------------
// <copyright file="Mesa.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;
    using System.Collections.Generic;

    public class Mesa : IMesa
    {
        public int CodigoMesa { get; set; }
        public Enums.EstadoMesa Estado { get; set; }
        public string Nombre { get; set; }
        public long Total { get; set; }
        public List<Pedido> Pedidos { get; set; }

        public Mesa() {
            Pedidos = new List<Pedido>();
        }

        public void ModificarEstado(Enums.EstadoMesa estado)
        {
            this.Estado = estado;
            //Todo enviar update de estado
        }

        public void AgregarPedido(Pedido pedido)
        {
            this.Pedidos.Add(pedido);
        }
    }
}
