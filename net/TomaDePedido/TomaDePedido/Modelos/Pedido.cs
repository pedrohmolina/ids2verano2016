//-----------------------------------------------------------------------
// <copyright file="Pedido.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using System.Collections.Generic;
    using Interfaces;
    using TomaDePedido.Enums;

    public class Pedido : IPedido
    {
        private int Codigo { get; set; }

        public int CodigoMesa { get; }

        public Enums.EstadoPedido Estado { get; }

        public bool EntregarTodoJunto { get; set; }

        public List<DetallePlato> Platos {get; set;}

        public List<DetalleCerveza> Cervezas { get; set; }

        public Pedido() { }

        public Pedido(int codigoMesa)
        {
            this.CodigoMesa = codigoMesa;
            this.Estado = Enums.EstadoPedido.Pendiente;
            this.Cervezas = new List<DetalleCerveza>();
            this.Platos = new List<DetallePlato>();
        }

        public void AgregarCerveza(int codigoCerveza, int cantidad)
        {
            this.Cervezas.Add(new DetalleCerveza(codigoCerveza, cantidad));
        }

        public void AgregarPlato(int codigoPlato, int cantidad, string comentario)
        {
            this.Platos.Add(new DetallePlato(codigoPlato, cantidad, comentario));
        }

        public int ObtenerCodigo()
        {
            return this.Codigo;
        }

        public void AsignarCodigo(int codigo)
        {
            this.Codigo = codigo;
        }
    }
}
